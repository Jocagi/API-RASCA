package com.rasca.rascaapi.repositories;

import com.rasca.rascaapi.domain.Activities;
import com.rasca.rascaapi.exceptions.EtBadRequestException;
import com.rasca.rascaapi.exceptions.EtResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class ActivityRepositoryImpl implements ActivityRepository{
    private static final String SQL_CREAR_ACTIVIDAD="INSERT INTO \"Actividad\"(\n" +
            "\t\"Nombre\", \"Cupo\", \"Fecha_Inicio\", \"Estado\", \"Descripcion\", \"Horas_Otorgadas\", \"R_Facultad\", \"R_Year\", \"R_Beca\", \"ID_Certificador\", \"ID_Administrador\")\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_ENCONTRAR_POR_ID="SELECT \"ID_Actividad\", \"Nombre\", \"Cupo\", \"Fecha_Inicio\", \"Estado\", \"Descripcion\", \"Horas_Otorgadas\", \"R_Facultad\", \"R_Year\", \"R_Beca\", \"ID_Certificador\", \"ID_Administrador\"\n" +
            "\tFROM \"Actividad\" WHERE \"ID_Actividad\" = ? AND \"ID_Certificador\" = ?;";
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Activities> obtenerActividades(String ID_Certificador) throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public Activities encontrarPorID(String ID_Certificador, Long ID_Actividad) throws EtResourceNotFoundException {
        try{
            return jdbcTemplate.queryForObject(SQL_ENCONTRAR_POR_ID,new Object[]{ID_Actividad,ID_Certificador},activityRowMapper);
        }catch(Exception e){

        }
        return null;
    }

    @Override
    public Long crearActividad(String Nombre, Integer Cupo, String Fecha_Inicio, String Descripcion, Integer Horas_Otorgadas, String R_Facultad, String R_Year, String R_Beca, String ID_Certificador, Long ID_Administrador) throws EtBadRequestException {
        try{
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(Fecha_Inicio);
            java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREAR_ACTIVIDAD, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,Nombre);
                ps.setInt(2,Cupo);
                ps.setDate(3,sqlDate);
                ps.setString(4,"P");
                ps.setString(5,Descripcion);
                ps.setInt(6,Horas_Otorgadas);
                ps.setString(7,R_Facultad);
                ps.setString(8,R_Year);
                ps.setString(9,R_Beca);
                ps.setString(10,ID_Certificador);
                ps.setLong(11,ID_Administrador);
                return ps;
            },keyHolder);
            return (Long) keyHolder.getKeys().get("ID_Actividad");
        }catch (Exception e){
            throw new EtBadRequestException("Solicitud Invalida");
        }
    }

    @Override
    public void actualizarActividad(Long ID_Certificador, Long IDActividad, Activities Actividad) throws EtBadRequestException {

    }

    private RowMapper<Activities> activityRowMapper = ((rs, rowNum)->{
       return new Activities(rs.getLong("ID_Actividad"),
               rs.getString("Nombre"),
               rs.getInt("Cupo"),
               rs.getDate("Fecha_Inicio"),
               rs.getString("Estado"),
               rs.getString("Descripcion"),
               rs.getInt("Horas_Otorgadas"),
               rs.getString("R_Facultad"),
               rs.getString("R_Year"),
               rs.getString("R_Beca"),
               rs.getString("ID_Certificador"),
               rs.getLong("ID_Administrador"));
    });
}
