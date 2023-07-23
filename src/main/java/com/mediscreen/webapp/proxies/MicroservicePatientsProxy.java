package com.mediscreen.webapp.proxies;

import com.mediscreen.webapp.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "mediscreen-patient", url = "${PATIENT.PROXY}")
public interface MicroservicePatientsProxy {
    @GetMapping("/patient/{id}")
    PatientBean getPatientById(@PathVariable("id") Integer id);

    @GetMapping(value = "/patient/all")
    List<PatientBean> getPatientList();

    @PostMapping(value = "/patient/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    void addPatient(@ModelAttribute PatientBean patientBean);

    @PutMapping("/patient/update/{id}")
    void updatePatient(@PathVariable("id") Integer id, @RequestBody PatientBean patientBean);

    @DeleteMapping("/patient/{id}")
    void deletePatientById(@PathVariable("id") Integer id);
}
