package com.rasca.rascaapi.repositories;

import com.rasca.rascaapi.domain.Activities;
import com.rasca.rascaapi.domain.Administrator;
import com.rasca.rascaapi.domain.Approver;
import com.rasca.rascaapi.domain.Student;
import com.rasca.rascaapi.exceptions.EtBadRequestException;
import com.rasca.rascaapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface ActivityRepository {

    List<Activities> obtenerActividades (String Estado);

    Activities encontrarPorID (Long ID_Certificador, Long ID_Actividad);

    Long crearActividad (String Nombre, Integer Cupo, String Fecha_Inicio, String Descripcion, Integer Horas_Otorgadas, String R_Facultad, String R_Year, String R_Beca, Long ID_Certificador, Long ID_Administrador);

    void actualizarActividad(Long ID_Certificador, Long IDActividad, Activities Actividad);

    Approver getApprover(Long IDPersona);

    Student  getStudent(Long IDPersona);

    Administrator getAdministrator(Long IDPersona);

    void aprobarActividad(Long ID_Actividad, Long ID_Administrador);

    void rechazarActividad(Long ID_Actividad, Long ID_Administrador);

    void cancelarActividad(Long ID_Actividad, Long ID_Administrador);

}
