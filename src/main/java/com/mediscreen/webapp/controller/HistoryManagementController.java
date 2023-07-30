package com.mediscreen.webapp.controller;

import com.mediscreen.webapp.beans.AssessmentBean;
import com.mediscreen.webapp.beans.HistoryBean;
import com.mediscreen.webapp.beans.PatientBean;
import com.mediscreen.webapp.service.AssessmentManagementService;
import com.mediscreen.webapp.service.HistoryManagementService;
import com.mediscreen.webapp.service.PatientManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing patient history information.
 */
@Controller
public class HistoryManagementController {

    @Autowired
    HistoryManagementService historyManagementService;

    @Autowired
    PatientManagementService patientManagementService;

    @Autowired
    AssessmentManagementService assessmentManagementService;

    /**
     * Retrieves the patient's history details by their patient ID and displays them in the "details" view.
     *
     * @param patientId The ID of the patient for whom the history details are requested.
     * @param model     The model to add the patient, history notes, and risk assessment details.
     * @return The view name "details" to display the patient's history details.
     */
    @GetMapping("/details/{patientId}")
    public String getHistory(@PathVariable("patientId") Integer patientId, Model model) {
        List<HistoryBean> histories = historyManagementService.getHistoriesByPatientId(patientId);
        PatientBean patient = patientManagementService.getPatientById(patientId);
        AssessmentBean assessment = assessmentManagementService.getRisk(patientId);

        model.addAttribute("patient", patient);
        model.addAttribute("notes", histories);
        model.addAttribute("assessment", assessment);

        return "details";
    }

    /**
     * Displays the "details/add" view to add a new history note for a patient.
     *
     * @param patientId The ID of the patient for whom the history note is being added.
     * @param model     The model to add the patient ID and an empty HistoryBean.
     * @return The view name "details/add" to display the add history note form.
     */
    @GetMapping("/details/{patientId}/add")
    public String addHistory(@PathVariable("patientId") Integer patientId, Model model) {
        HistoryBean historyBean = new HistoryBean();
        historyBean.setPatId(patientId);

        model.addAttribute("patientId", patientId);
        model.addAttribute("historyBean", historyBean);
        return "details/add";
    }

    /**
     * Validates and saves the newly added history note for a patient.
     *
     * @param patientId    The ID of the patient for whom the history note is being added.
     * @param historyBean  The HistoryBean object containing the history note data.
     * @param result       The result of the validation.
     * @param model        The model to add the patient details and validation errors (if any).
     * @return If validation is successful, redirects to the "details/{patientId}" view. Otherwise, stays on "details/add" view.
     */
    @PostMapping("/details/{patientId}/validate")
    public String validate(@PathVariable("patientId") Integer patientId, @ModelAttribute @Valid HistoryBean historyBean, BindingResult result, Model model) {
        PatientBean patient = patientManagementService.getPatientById(patientId);

        if (!result.hasErrors()) {
            historyManagementService.addHistory(historyBean, patient);
            return "redirect:/details/"  + patientId ;
        }

        return "details/add";
    }

    /**
     * Displays the "details/update" view to edit a history note for a patient.
     *
     * @param patientId The ID of the patient whose history note is being updated.
     * @param noteId    The ID of the history note to be updated.
     * @param model     The model to add the patient ID, history note ID, and the existing HistoryBean.
     * @return The view name "details/update" to display the update history note form.
     */
    @GetMapping("/details/{patientId}/update")
    public String showUpdateForm(@PathVariable("patientId") Integer patientId, @RequestParam("noteId") String noteId, Model model) {
        model.addAttribute("patientId", patientId);
        model.addAttribute("noteId", noteId);
        model.addAttribute("historyBean", historyManagementService.getHistoryById(noteId));

        return "details/update";
    }

    /**
     * Updates and saves the edited history note for a patient.
     *
     * @param noteId      The ID of the history note to be updated.
     * @param historyBean The HistoryBean object containing the updated history note data.
     * @param result      The result of the validation.
     * @param model       The model to add validation errors (if any).
     * @return If validation is successful, redirects to the "details/{patientId}" view. Otherwise, stays on "details/update" view.
     */
    @PostMapping("/details/update/{noteId}")
    public String updateHistory(@PathVariable("noteId") String noteId, @ModelAttribute @Valid HistoryBean historyBean,
                                BindingResult result, Model model) {

        if (result.hasErrors()) return "details/update";

        PatientBean patient = patientManagementService.getPatientById(historyBean.getPatId());
        historyBean.setId(noteId);
        historyBean.setPatient(patient.getFamily());

        historyManagementService.updateHistory(historyBean.getPatId(), noteId, historyBean);
        return "redirect:/details/" + historyBean.getPatId();
    }

    /**
     * Deletes a history note for a patient.
     *
     * @param patientId The ID of the patient whose history note is being deleted.
     * @param noteId    The ID of the history note to be deleted.
     * @return Redirects to the "details/{patientId}" view after the history note is deleted.
     */
    @GetMapping("/details/{patientId}/delete")
    public String deleteHistory(@PathVariable("patientId") Integer patientId, @RequestParam("noteId") String noteId) {
        historyManagementService.deleteHistory(noteId);
        return "redirect:/details/" + patientId;
    }
}
