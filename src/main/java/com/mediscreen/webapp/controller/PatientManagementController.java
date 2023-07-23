package com.mediscreen.webapp.controller;

import com.mediscreen.webapp.beans.PatientBean;
import com.mediscreen.webapp.service.PatientManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PatientManagementController {

    @Autowired
    PatientManagementService patientManagementService;

    @GetMapping("/patient/list")
    public String getPatients(Model model) {
        List<PatientBean> patients = patientManagementService.getPatientList();
        model.addAttribute("patients", patients);

        return "patient/list";
    }

    @GetMapping("/patient/add")
    public String addPatient(Model model) {
        model.addAttribute("patientBean", new PatientBean());
        return "patient/add";
    }

    @PostMapping("/patient/validate")
    public String validate(@ModelAttribute @Valid PatientBean patientBean, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            patientManagementService.addPatient(patientBean);
            model.addAttribute("patients", patientManagementService.getPatientList());
            return "redirect:/patient/list";
        }
        return "patient/add";
    }

    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("patientBean", patientManagementService.getPatientById(id));
        return "patient/update";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable("id") Integer id, @Valid PatientBean patientBean,
                                BindingResult result, Model model) {

        if (result.hasErrors()) return "patient/update";

        patientBean.setId(id);
        patientManagementService.updatePatient(id, patientBean);
        model.addAttribute("patients", patientManagementService.getPatientList());
        return "redirect:/patient/list";
    }

    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable("id") Integer id, Model model) {
        patientManagementService.deletePatient(id);
        return "redirect:/patient/list";
    }
}
