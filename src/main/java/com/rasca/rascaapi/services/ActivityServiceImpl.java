package com.rasca.rascaapi.services;

import com.rasca.rascaapi.domain.Activities;
import com.rasca.rascaapi.domain.Administrator;
import com.rasca.rascaapi.domain.Approver;
import com.rasca.rascaapi.domain.Student;
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
    public List<Activities> obtenerActividades(String Estado) {
        return activityRepository.obtenerActividades(Estado);
    }

    @Override
    public Activities obtenerActividadPorID(Long ID_Certificador, Long IDActividad) throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public Activities agregaActividad(String Nombre, Integer Cupo, String Fecha_Inicio, String Descripcion, Integer Horas_Otorgadas, String R_Facultad, String R_Year, String R_Beca, Long ID_Certificador, Long ID_Administrador) throws EtBadRequestException {
        Long IDActividad= activityRepository.crearActividad(Nombre,Cupo,Fecha_Inicio,Descripcion,Horas_Otorgadas,R_Facultad,R_Year,R_Beca, ID_Certificador,ID_Administrador);

        return activityRepository.encontrarPorID(ID_Certificador,IDActividad);
    }

    @Override
    public void actualizarActividad(Long ID_Certificador, Long IDActividad, Activities Actividad) throws EtBadRequestException {

    }

    @Override
    public void aprobarActividad(Long ID_Actividad, Long ID_Administrador) throws EtBadRequestException {
        activityRepository.aprobarActividad(ID_Actividad, ID_Administrador);
    }

    @Override
    public void rechazarActividad(Long ID_Actividad, Long ID_Administrador) throws EtBadRequestException {
        activityRepository.rechazarActividad(ID_Actividad, ID_Administrador);
    }

    @Override
    public void cancelarActividad(Long ID_Actividad, Long ID_Administrador) throws EtBadRequestException {
        activityRepository.cancelarActividad(ID_Actividad, ID_Administrador);
    }

    @Override
    public Approver obtenerCertificador(Long IDPerson) throws EtResourceNotFoundException {
            return activityRepository.getApprover(IDPerson);
    }

    @Override
    public Administrator obtenerAdministrador(Long IDPerson) throws EtResourceNotFoundException {
        return activityRepository.getAdministrator(IDPerson);
    }

    @Override
    public Student obtenerEstudiante(Long IDPerson) throws EtResourceNotFoundException {
        return activityRepository.getStudent(IDPerson);
    }
}
