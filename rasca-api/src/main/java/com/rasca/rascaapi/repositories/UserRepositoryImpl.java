package com.rasca.rascaapi.repositories;

import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.exceptions.EtAuthException;
import org.apache.logging.log4j.util.Chars;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class UserRepositoryImpl implements UserRepository{
    //? = PARAMETRO
    private static char doubleQuote = '"';
    //String de query de insercion a base de datos
    private static  String SQL_CREATE = "INSERT INTO \"Persona\" (\"IDPersona\", \"Correo\", \"Contrasena\", \"Usuario\", \"Nombres\", \"Apellidos\", \"Carnet\", \"FechaNac\", \"Telefono\", \"Fotografia\") VALUES(NEXTVAL('RASCA_USERS_SEQ'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //String de query de CANTIDAD DE USUARIOS a base de datos
    private static  String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM \"Persona\" WHERE \"Correo\" = ?";
    //String de query de BUSQUEDA DE USUARIO POR ID a base de datos
    private static  String SQL_FIND_BY_ID = "SELECT \"IDPersona\", \"Correo\", \"Contrasena\", \"Usuario\", \"Nombres\", \"Apellidos\", \"Carnet\", \"FechaNac\", \"Telefono\", \"Fotografia\" FROM \"Persona\" WHERE \"IDPersona\" = ?";
    //String de query para BUSQUEDA DE USUARIO POR LOGIN en bd
    private static  String SQL_FIND_BY_EMAIL = "SELECT \"IDPersona\", \"Correo\", \"Contrasena\", \"Usuario\", \"Nombres\", \"Apellidos\", \"Carnet\", \"FechaNac\", \"Telefono\", \"Fotografia\" FROM \"Persona\" WHERE \"Correo\" = ?";


    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public Long create(String Correo, String Contrasena, String Usuario, String Nombres, String Apellidos, String Carnet, String FechaNac, String Telefono, String Fotografia) throws EtAuthException {
        String hashedPassword = BCrypt.hashpw(Contrasena, BCrypt.gensalt(10));
        try{
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(FechaNac);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

            //Conexión a base de datos y preparación de query
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->{
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,Correo);
                ps.setString(2,hashedPassword);
                ps.setString(3,Usuario);
                ps.setString(4,Nombres);
                ps.setString(5,Apellidos);
                ps.setString(6,Carnet);
                ps.setDate(7, (java.sql.Date) sqlDate);
                ps.setString(8,Telefono);
                ps.setString(9,Fotografia);
                return ps;
            }, keyHolder);
            //Devolver id de usuario.
            return (Long) keyHolder.getKeys().get("IDPersona");
        }catch(Exception e){
            throw new EtAuthException("Datos invalidos, fallo al crear cuenta");
        }
    }


    @Override
    public User findByEmailAndPassword(String Correo, String Contraseña) throws EtAuthException {
        try{
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{Correo}, userRowMapper);
            if(!BCrypt.checkpw(Contraseña,user.getContrasena()))
                throw new EtAuthException("Email/password invalidos");
            return user;
        }catch (Exception e){
            throw new EtAuthException("Email/password invalidos");
        }
    }

    @Override
    public Long getCountByEmail(String email) {

        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Long.class);
    }

    @Override
    public User findByID(Long userId) {

        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userRowMapper);
    }

    //Logica para obtener datos de la base de datos segun ID de Usuario
    private RowMapper<User> userRowMapper = ((rs,rowNum)->{
        return new User(rs.getLong("IDPersona"),
                rs.getString("Correo"),
                rs.getString("Contrasena"),
                rs.getString("Usuario"),
                rs.getString("Nombres"),
                rs.getString("Apellidos"),
                rs.getString("Carnet"),
                rs.getString("FechaNac"),
                rs.getString("Telefono"),
                rs.getString("Fotografia"));
    });
}
