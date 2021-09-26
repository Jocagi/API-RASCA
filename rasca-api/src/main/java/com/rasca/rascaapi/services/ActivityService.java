package com.rasca.rascaapi.services;

import com.rasca.rascaapi.domain.Activities;
import com.rasca.rascaapi.exceptions.EtBadRequestException;
import com.rasca.rascaapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface ActivityService {
    List<Activities> obtenerActividades(String ID_Certificador);

    Activities obtenerActividadPorID(String ID_Certificador,Long IDActividad) throws EtResourceNotFoundException;

    Activities agregaActividad (String Nombre,Integer Cupo,String Fecha_Inicio,String Descripcion,Integer Horas_Otorgadas,String R_Facultad,String R_Year,String R_Beca,String ID_Certificador,Long ID_Administrador) throws EtBadRequestException;

    void actualizarActividad(Long ID_Certificador, Long IDActividad, Activities Actividad) throws EtBadRequestException;

    void aprobarActividad(Long ID_Actividad, Long ID_Administrador) throws EtBadRequestException;

    void rechazarActividad(Long ID_Actividad, Long ID_Administrador) throws EtBadRequestException;
}
