package com.rasca.rascaapi.repositories;

import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.exceptions.EtAuthException;
import com.rasca.rascaapi.exceptions.EtRequestException;
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

    private static  String SQL_CREATE_STUDENT = "INSERT INTO \"Estudiante\" (\"IDEstudiante\", \"IDPersona\", \"IDCarrera\", \"IDBeca\") VALUES(NEXTVAL('RASCA_ESTUDIANTE_SEQ'), ?, 1, 1)";

    private static  String SQL_CREATE_APPROVER = "INSERT INTO \"Certificador\" (\"IDCertificador\", \"IDCargo\", \"IDPersona\") VALUES(NEXTVAL('RASCA_CERTIFICADOR_SEQ'), 1, ?)";

    private static  String SQL_CREATE_ADMINISTRATOR = "INSERT INTO \"Administrador\" (\"IDAdministrador\", \"IDPersona\", \"IDCargo\") VALUES(NEXTVAL('RASCA_ADMINISTRADO_SEQ'), ?, 1)";

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
            //throw new EtRequestException("Datos invalidos, fallo al crear cuenta");
            throw new EtRequestException(e.getMessage());
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

    @Override
    public Long createStudent(Long IDPersona) {
        try{
            //Conexión a base de datos y preparación de query
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->{
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_STUDENT, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1,IDPersona);
                return ps;
            }, keyHolder);
            //Devolver id de usuario.
            return (Long) keyHolder.getKeys().get("IDEstudiante");
        }catch(Exception e) {
            throw new EtAuthException("Datos invalidos, fallo al crear cuenta");
        }
    }

    @Override
    public String createApprover(Long IDPersona) {
        try{
            //Conexión a base de datos y preparación de query
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->{
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_APPROVER, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1,IDPersona);
                return ps;
            }, keyHolder);
            //Devolver id de usuario.
            return (String) keyHolder.getKeys().get("IDCertificador");
        }catch(Exception e) {
            throw new EtAuthException("Datos invalidos, fallo al crear cuenta");
        }
    }

    @Override
    public Long createAdministrator(Long IDPersona) {
        try{
            //Conexión a base de datos y preparación de query
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->{
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_ADMINISTRATOR, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1,IDPersona);
                return ps;
            }, keyHolder);
            //Devolver id de usuario.
            return (Long) keyHolder.getKeys().get("IDAdministrador");
        }catch(Exception e) {
            throw new EtAuthException("Datos invalidos, fallo al crear cuenta");
        }
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
