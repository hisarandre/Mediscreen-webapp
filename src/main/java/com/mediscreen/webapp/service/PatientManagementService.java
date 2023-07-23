package com.mediscreen.webapp.service;

import com.mediscreen.webapp.beans.PatientBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mediscreen.webapp.proxies.MicroservicePatientsProxy;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Service
public class PatientManagementService {

    @Autowired
    private MicroservicePatientsProxy patientProxy;

    public List<PatientBean> getPatientList() {
        List<PatientBean> patients = patientProxy.getPatientList();
        return patients;
    }

    public PatientBean getPatientById(Integer id) {
        return patientProxy.getPatientById(id);
    }

    public void addPatient(PatientBean patientBean) {
        patientProxy.addPatient(patientBean);
    }

    public void updatePatient(Integer id, PatientBean patientBean) {
        patientProxy.updatePatient(id, patientBean);
    }

    public void deletePatient(Integer id) {
        patientProxy.deletePatientById(id);
    }

    public Integer calculateAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dob, currentDate).getYears();
    }
}
