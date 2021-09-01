package com.rasca.rascaapi.repositories;

import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.exceptions.EtAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository{
    //String de query de insercion a base de datos
    private static final String SQL_CREATE = "INSERT INTO RASCA_USERS(USER_ID, FIRST_NAME, LAST_NAME, EMAIL,PASSWORD) VALUES(NEXTVAL('RASCA_USERS_SEQ'),?,?,?,?)";
    //String de query de CANTIDAD DE USUARIOS a base de datos
    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM RASCA_USERS WHERE EMAIL = ?";
    //String de query de BUSQUEDA DE USUARIO POR ID a base de datos
    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD FROM RASCA_USERS WHERE USER_ID = ?";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EtAuthException {
        try{
            //Conexión a base de datos y preparación de query
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->{
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,firstName);
                ps.setString(2,lastName);
                ps.setString(3,email);
                ps.setString(4,password);
                return ps;
            }, keyHolder);
            //Devolver id de usuario.
            return (Integer) keyHolder.getKeys().get("USER_ID");
        }catch(Exception e){
            throw new EtAuthException("Datos invalidos, fallo al crear cuenta");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Integer.class);
    }

    @Override
    public User findByID(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userRowMapper);
    }

    //Logica para obtener datos de la base de datos segun ID de Usuario
    private RowMapper<User> userRowMapper = ((rs,rowNum)->{
        return new User(rs.getInt("USER_ID"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"));
    });
}
