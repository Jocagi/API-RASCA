package com.rasca.rascaapi.repositories;

import com.rasca.rascaapi.domain.Activities;
import com.rasca.rascaapi.exceptions.EtBadRequestException;
import com.rasca.rascaapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface ActivityRepository {
    List<Activities> obtenerActividades (String ID_Certificador) throws EtResourceNotFoundException;

    Activities encontrarPorID (String ID_Certificador, Long ID_Actividad) throws EtResourceNotFoundException;

    Long crearActividad (String Nombre, Integer Cupo, String Fecha_Inicio, String Descripcion, Integer Horas_Otorgadas, String R_Facultad, String R_Year, String R_Beca, String ID_Certificador, Long ID_Administrador) throws EtBadRequestException;

    void actualizarActividad(Long ID_Certificador, Long IDActividad, Activities Actividad) throws EtBadRequestException;
}
