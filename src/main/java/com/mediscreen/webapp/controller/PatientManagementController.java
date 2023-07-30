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

/**
 * Controller class for managing patient information.
 */
@Controller
public class PatientManagementController {

    @Autowired
    PatientManagementService patientManagementService;


    /**
     * Retrieves a list of patients and displays them in the "patient/list" view.
     *
     * @param model The model to add the list of patients.
     * @return The view name "patient/list" to display the patient list.
     */
    @GetMapping("/patient/list")
    public String getPatients(Model model) {
        List<PatientBean> patients = patientManagementService.getPatientList();
        model.addAttribute("patients", patients);

        return "patient/list";
    }

    /**
     * Displays the "patient/add" view to add a new patient.
     *
     * @param model The model to add an empty PatientBean.
     * @return The view name "patient/add" to display the add patient form.
     */
    @GetMapping("/patient/add")
    public String addPatient(Model model) {
        model.addAttribute("patientBean", new PatientBean());
        return "patient/add";
    }

    /**
     * Validates and adds a new patient.
     *
     * @param patientBean The PatientBean containing the patient data to be validated and added.
     * @param result      The BindingResult to capture validation errors.
     * @param model       The model to add the list of patients after addition.
     * @return If validation succeeds, redirects to the "patient/list" view to display the updated patient list.
     *         If validation fails, returns the view name "patient/add" to display the validation errors.
     */
    @PostMapping("/patient/validate")
    public String validate(@ModelAttribute @Valid PatientBean patientBean, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            patientManagementService.addPatient(patientBean);
            model.addAttribute("patients", patientManagementService.getPatientList());
            return "redirect:/patient/list";
        }
        return "patient/add";
    }

    /**
     * Displays the "patient/update" view to edit an existing patient.
     *
     * @param id    The ID of the patient to be updated.
     * @param model The model to add the PatientBean with existing patient data.
     * @return The view name "patient/update" to display the update patient form.
     */
    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("patientBean", patientManagementService.getPatientById(id));
        return "patient/update";
    }

    /**
     * Updates an existing patient.
     *
     * @param id          The ID of the patient to be updated.
     * @param patientBean The PatientBean containing the updated patient data.
     * @param result      The BindingResult to capture validation errors.
     * @param model       The model to add the list of patients after the update.
     * @return If validation succeeds, redirects to the "patient/list" view to display the updated patient list.
     *         If validation fails, returns the view name "patient/update" to display the validation errors.
     */
    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable("id") Integer id, @Valid PatientBean patientBean,
                                BindingResult result, Model model) {

        if (result.hasErrors()) return "patient/update";

        patientBean.setId(id);
        patientManagementService.updatePatient(id, patientBean);
        model.addAttribute("patients", patientManagementService.getPatientList());
        return "redirect:/patient/list";
    }

    /**
     * Deletes an existing patient.
     *
     * @param id    The ID of the patient to be deleted.
     * @param model The model to add the list of patients after the deletion.
     * @return Redirects to the "patient/list" view to display the updated patient list.
     */
    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable("id") Integer id, Model model) {
        patientManagementService.deletePatient(id);
        return "redirect:/patient/list";
    }
}
