package com.rasca.rascaapi.repositories;

import com.rasca.rascaapi.domain.Activities;
import com.rasca.rascaapi.domain.Administrator;
import com.rasca.rascaapi.domain.Approver;
import com.rasca.rascaapi.domain.Student;
import com.rasca.rascaapi.exceptions.EtBadRequestException;
import com.rasca.rascaapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface ActivityRepository {
    List<Activities> obtenerActividades (Long ID_Certificador) throws EtResourceNotFoundException;

    Activities encontrarPorID (Long ID_Certificador, Long ID_Actividad) throws EtResourceNotFoundException;

    Long crearActividad (String Nombre, Integer Cupo, String Fecha_Inicio, String Descripcion, Integer Horas_Otorgadas, String R_Facultad, String R_Year, String R_Beca, Long ID_Certificador, Long ID_Administrador) throws EtBadRequestException;

    void actualizarActividad(Long ID_Certificador, Long IDActividad, Activities Actividad) throws EtBadRequestException;

    Approver getApprover(Long IDPersona);

    Student  getStudent(Long IDPersona);

    Administrator getAdministrator(Long IDPersona);
}
