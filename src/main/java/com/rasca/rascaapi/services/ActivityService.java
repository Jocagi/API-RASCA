package com.rasca.rascaapi.services;

import com.rasca.rascaapi.domain.Activities;
import com.rasca.rascaapi.domain.Administrator;
import com.rasca.rascaapi.domain.Approver;
import com.rasca.rascaapi.domain.Student;
import com.rasca.rascaapi.exceptions.EtBadRequestException;
import com.rasca.rascaapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface ActivityService {

    List<Activities> obtenerActividades(String Estado);

    Activities obtenerActividadPorID(Long ID_Certificador,Long IDActividad);

    Activities agregaActividad (String Nombre,Integer Cupo,String Fecha_Inicio,String Descripcion,Integer Horas_Otorgadas,String R_Facultad,String R_Year,String R_Beca,Long ID_Certificador,Long ID_Administrador);

    void actualizarActividad(Long ID_Certificador, Long IDActividad, Activities Actividad);

    Approver obtenerCertificador (Long IDPerson);

    Administrator obtenerAdministrador (Long IDPerson);

    Student obtenerEstudiante (Long IDPerson);

    void aprobarActividad(Long ID_Actividad, Long ID_Administrador);

    void rechazarActividad(Long ID_Actividad, Long ID_Administrador);

    void cancelarActividad(Long ID_Actividad, Long ID_Administrador);

}
