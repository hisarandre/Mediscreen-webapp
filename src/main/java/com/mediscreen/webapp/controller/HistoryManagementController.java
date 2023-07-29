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

@Controller
public class HistoryManagementController {

    @Autowired
    HistoryManagementService historyManagementService;

    @Autowired
    PatientManagementService patientManagementService;

    @Autowired
    AssessmentManagementService assessmentManagementService;

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

    @GetMapping("/details/{patientId}/add")
    public String addHistory(@PathVariable("patientId") Integer patientId, Model model) {
        HistoryBean historyBean = new HistoryBean();
        historyBean.setPatId(patientId);

        model.addAttribute("patientId", patientId);
        model.addAttribute("historyBean", historyBean);
        return "details/add";
    }

    @PostMapping("/details/{patientId}/validate")
    public String validate(@PathVariable("patientId") Integer patientId, @ModelAttribute @Valid HistoryBean historyBean, BindingResult result, Model model) {
        PatientBean patient = patientManagementService.getPatientById(patientId);

        if (!result.hasErrors()) {
            historyManagementService.addHistory(historyBean, patient);
            return "redirect:/details/"  + patientId ;
        }

        return "details/add";
    }

    @GetMapping("/details/{patientId}/update")
    public String showUpdateForm(@PathVariable("patientId") Integer patientId, @RequestParam("noteId") String noteId, Model model) {
        model.addAttribute("patientId", patientId);
        model.addAttribute("noteId", noteId);
        model.addAttribute("historyBean", historyManagementService.getHistoryById(noteId));

        return "details/update";
    }

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

    @GetMapping("/details/{patientId}/delete")
    public String deleteHistory(@PathVariable("patientId") Integer patientId, @RequestParam("noteId") String noteId) {
        historyManagementService.deleteHistory(noteId);
        return "redirect:/details/" + patientId;
    }
}
