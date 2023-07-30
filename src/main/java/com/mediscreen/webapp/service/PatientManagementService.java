package com.mediscreen.webapp.service;

import com.mediscreen.webapp.beans.PatientBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mediscreen.webapp.proxies.MicroservicePatientsProxy;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Service class to manage patient-related operations.
 */
@Service
public class PatientManagementService {

    @Autowired
    private MicroservicePatientsProxy patientProxy;

    /**
     * Retrieves a list of all patients.
     *
     * @return A list of PatientBean representing all patients in the system.
     */
    public List<PatientBean> getPatientList() {
        List<PatientBean> patients = patientProxy.getPatientList();
        return patients;
    }

    /**
     * Retrieves a patient by their ID.
     *
     * @param id The ID of the patient to retrieve.
     * @return The PatientBean representing the patient with the specified ID.
     */
    public PatientBean getPatientById(Integer id) {
        return patientProxy.getPatientById(id);
    }

    /**
     * Adds a new patient to the system.
     *
     * @param patientBean The PatientBean object containing the details of the new patient.
     */
    public void addPatient(PatientBean patientBean) {
        patientProxy.addPatient(patientBean);
    }

    /**
     * Updates an existing patient's information.
     *
     * @param id The ID of the patient to update.
     * @param patientBean The PatientBean object containing the updated patient information.
     */
    public void updatePatient(Integer id, PatientBean patientBean) {
        patientProxy.updatePatient(id, patientBean);
    }

    /**
     * Deletes a patient from the system by their ID.
     *
     * @param id The ID of the patient to delete.
     */
    public void deletePatient(Integer id) {
        patientProxy.deletePatientById(id);
    }

    /**
     * Calculates the age of a patient based on their date of birth (DOB).
     *
     * @param dob The date of birth (DOB) of the patient.
     * @return The age of the patient in years.
     */
    public Integer calculateAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dob, currentDate).getYears();
    }
}
