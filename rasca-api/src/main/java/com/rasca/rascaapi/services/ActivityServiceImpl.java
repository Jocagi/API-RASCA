package com.rasca.rascaapi.services;

import com.rasca.rascaapi.domain.Activities;
import com.rasca.rascaapi.exceptions.EtBadRequestException;
import com.rasca.rascaapi.exceptions.EtResourceNotFoundException;
import com.rasca.rascaapi.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService{
    @Autowired
    ActivityRepository activityRepository;
    @Override
    public List<Activities> obtenerActividades(String ID_Certificador) {
        return null;
    }

    @Override
    public Activities obtenerActividadPorID(String ID_Certificador, Long IDActividad) throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public Activities agregaActividad(String Nombre, Integer Cupo, String Fecha_Inicio, String Descripcion, Integer Horas_Otorgadas, String R_Facultad, String R_Year, String R_Beca, String ID_Certificador, Long ID_Administrador) throws EtBadRequestException {
        Long IDActividad= activityRepository.crearActividad(Nombre,Cupo,Fecha_Inicio,Descripcion,Horas_Otorgadas,R_Facultad,R_Year,R_Beca, ID_Certificador,ID_Administrador);
        return activityRepository.encontrarPorID(ID_Certificador,IDActividad);
    }

    @Override
    public void actualizarActividad(Long ID_Certificador, Long IDActividad, Activities Actividad) throws EtBadRequestException {

    }
}
