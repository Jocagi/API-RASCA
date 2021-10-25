package com.rasca.rascaapi.repositories;

import com.rasca.rascaapi.domain.Activities;
import com.rasca.rascaapi.domain.Administrator;
import com.rasca.rascaapi.domain.Approver;
import com.rasca.rascaapi.domain.Student;
import com.rasca.rascaapi.exceptions.EtBadRequestException;
import com.rasca.rascaapi.exceptions.EtResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class ActivityRepositoryImpl implements ActivityRepository{

    private static final String SQL_CREAR_ACTIVIDAD="INSERT INTO \"Actividad\"(\n" +
            "\t\"Nombre\", \"Cupo\", \"Fecha_Inicio\", \"Estado\", \"Descripcion\", \"Horas_Otorgadas\", \"R_Facultad\", \"R_Year\", \"R_Beca\", \"ID_Certificador\", \"ID_Administrador\")\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_ENCONTRAR_POR_ID="SELECT \"ID_Actividad\", \"Nombre\", \"Cupo\", \"Fecha_Inicio\", \"Estado\", \"Descripcion\", \"Horas_Otorgadas\", \"R_Facultad\", \"R_Year\", \"R_Beca\", \"ID_Certificador\", \"ID_Administrador\"\n" +
            "\tFROM \"Actividad\" WHERE \"ID_Actividad\" = ?;";
    private static final String SQL_ENCONTRAR_POR_ESTADO="SELECT \"ID_Actividad\", \"Nombre\", \"Cupo\", \"Fecha_Inicio\", \"Estado\", \"Descripcion\", \"Horas_Otorgadas\", \"R_Facultad\", \"R_Year\", \"R_Beca\", \"ID_Certificador\", \"ID_Administrador\"\n" +
            "\tFROM \"Actividad\" WHERE \"Estado\"= ?;";
    private static final String SQL_APROBAR_ACTIVIDAD="UPDATE \"Actividad\"\n" +
            "SET \"Estado\"= 'A', \"ID_Administrador\" = ?\n" +
            "WHERE \"ID_Actividad\" = ?;";
    private static final String SQL_RECHAZAR_ACTIVIDAD="UPDATE \"Actividad\"\n" +
            "SET \"Estado\"= 'R', \"ID_Administrador\" = ?\n" +
            "WHERE \"ID_Actividad\" = ?;";
    private static final String SQL_CANCELAR_ACTIVIDAD="UPDATE \"Actividad\"\n" +
            "SET \"Estado\"= 'E', \"ID_Administrador\" = ?\n" +
            "WHERE \"ID_Actividad\" = ?;";

    private static  String SQL_FIND_APPROVER_BY_IDPERSON = "SELECT \"IDCertificador\", \"IDCargo\"  " +
            "FROM \"Certificador\" WHERE \"IDPersona\" = ?";
    private static  String SQL_FIND_ADMINISTRATOR_BY_IDPERSON = "SELECT \"IDAdministrador\", \"IDCargo\"  " +
            "FROM \"Administrador\" WHERE \"IDPersona\" = ?";
    private static  String SQL_FIND_STUDENT_BY_IDPERSON = "SELECT \"IDEstudiante\", \"IDCarrera\", \"IDBeca\"  " +
            "FROM \"Estudiante\" WHERE \"IDPersona\" = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Activities> obtenerActividades(String Estado) throws EtResourceNotFoundException {
        try{
            return jdbcTemplate.query(
                    SQL_ENCONTRAR_POR_ESTADO, new Object[]{Estado}, new ResultSetExtractor<List<Activities>>() {
                public List<Activities> extractData(ResultSet rs) throws SQLException {
                    List<Activities> activitiesList = new LinkedList<Activities>();
                    if(rs != null){
                        while(rs.next()) {
                            Activities item = new Activities(
                                    rs.getLong("ID_Actividad"),
                                    rs.getString("Nombre"),
                                    rs.getInt("Cupo"),
                                    rs.getDate("Fecha_Inicio"),
                                    rs.getString("Estado"),
                                    rs.getString("Descripcion"),
                                    rs.getInt("Horas_Otorgadas"),
                                    rs.getString("R_Facultad"),
                                    rs.getString("R_Year"),
                                    rs.getString("R_Beca"),
                                    rs.getLong("ID_Certificador"),
                                    rs.getLong("ID_Administrador"));
                            activitiesList.add(item);
                        }
                    }
                    return activitiesList;
                }
            });
        }catch(Exception e){
        }
        return null;
    }

    @Override
    public Activities encontrarPorID(Long ID_Certificador, Long ID_Actividad) throws EtResourceNotFoundException {
        try{
            return jdbcTemplate.queryForObject(SQL_ENCONTRAR_POR_ID,new Object[]{ID_Actividad,ID_Certificador},activityRowMapper);
        }catch(Exception e){
        }
        return null;
    }

    @Override
    public Long crearActividad(String Nombre, Integer Cupo, String Fecha_Inicio, String Descripcion, Integer Horas_Otorgadas, String R_Facultad, String R_Year, String R_Beca, Long ID_Certificador, Long ID_Administrador) throws EtBadRequestException {
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
                ps.setLong(10,ID_Certificador);
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

    @Override
    public void aprobarActividad(Long ID_Actividad, Long ID_Administrador) throws EtBadRequestException {
        try{
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_APROBAR_ACTIVIDAD);
                ps.setLong(1, ID_Administrador);
                ps.setLong(2, ID_Actividad);
                return ps;
            });
        }catch (Exception e){
            throw new EtBadRequestException("Solicitud Invalida: " + e.getMessage());
        }
    }

    @Override
    public void rechazarActividad(Long ID_Actividad, Long ID_Administrador) throws EtBadRequestException {
        try{
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_RECHAZAR_ACTIVIDAD);
                ps.setLong(1, ID_Administrador);
                ps.setLong(2, ID_Actividad);
                return ps;
            });
        }catch (Exception e){
            throw new EtBadRequestException("Solicitud Invalida: " + e.getMessage());
        }
    }

    @Override
    public void cancelarActividad(Long ID_Actividad, Long ID_Administrador) throws EtBadRequestException {
        try{
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CANCELAR_ACTIVIDAD);
                ps.setLong(1, ID_Administrador);
                ps.setLong(2, ID_Actividad);
                return ps;
            });
        }catch (Exception e){
            throw new EtBadRequestException("Solicitud Invalida: " + e.getMessage());
        }
    }


   @Override
    public Approver getApprover(Long IDPersona) {
        return jdbcTemplate.queryForObject(SQL_FIND_APPROVER_BY_IDPERSON, new Object[]{IDPersona}, approverRowMapper);
    }

    @Override
    public Student getStudent(Long IDPersona) {
        return jdbcTemplate.queryForObject(SQL_FIND_STUDENT_BY_IDPERSON, new Object[]{IDPersona}, studentRowMapper);
    }

    @Override
    public Administrator getAdministrator(Long IDPersona) {
        return jdbcTemplate.queryForObject(SQL_FIND_ADMINISTRATOR_BY_IDPERSON, new Object[]{IDPersona}, administratorRowMapper);
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
               rs.getLong("ID_Certificador"),
               rs.getLong("ID_Administrador"));
    });

    private RowMapper<Approver> approverRowMapper = ((rs, rowNum) -> {
        return new Approver(rs.getLong("IDCertificador"),
                rs.getLong("IDCargo"));
    });

    private RowMapper<Administrator> administratorRowMapper = ((rs, rowNum) -> {
        return new Administrator(rs.getLong("IDAdministrador"),
                rs.getLong("IDCargo"));
    });

    private RowMapper<Student> studentRowMapper = ((rs, rowNum) -> {
        return new Student(rs.getLong("IDEstudiante"),
                rs.getLong("IDCarrera"),
                rs.getLong("IDBeca"));
    });
}

