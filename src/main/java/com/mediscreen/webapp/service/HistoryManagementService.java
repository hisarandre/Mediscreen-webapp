package com.mediscreen.webapp.service;

import com.mediscreen.webapp.beans.HistoryBean;
import com.mediscreen.webapp.beans.PatientBean;
import com.mediscreen.webapp.proxies.MicroserviceHistoriesProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class to manage patient history related operations.
 */
@Service
public class HistoryManagementService {

    @Autowired
    private MicroserviceHistoriesProxy historyProxy;

    /**
     * Retrieves the list of patient histories based on the patient's ID.
     *
     * @param id The ID of the patient for whom the histories are requested.
     * @return A list of HistoryBean containing the patient's histories.
     */
    public List<HistoryBean> getHistoriesByPatientId(Integer id){
        List<HistoryBean> histories = historyProxy.getHistoryByPatientId(id);
        return histories;
    }

    /**
     * Adds a new history for a patient.
     *
     * @param historyBean The HistoryBean object containing the new history data.
     * @param patientBean The PatientBean object representing the patient for whom the history is added.
     */
    public void addHistory(HistoryBean historyBean, PatientBean patientBean){
        historyBean.setId(null);
        historyBean.setPatId(patientBean.getId());
        historyBean.setPatient(patientBean.getFamily());
        historyProxy.addHistory(historyBean);
    }

    /**
     * Retrieves a patient's history by its note ID.
     *
     * @param noteId The ID of the history note to be retrieved.
     * @return The HistoryBean object representing the patient's history.
     */
    public HistoryBean getHistoryById(String noteId){
        return historyProxy.getHistoryById(noteId);
    }

    /**
     * Updates a patient's history.
     *
     * @param id The ID of the patient whose history is being updated.
     * @param noteId The ID of the history note to be updated.
     * @param historyBean The HistoryBean object containing the updated history data.
     */
    public void updateHistory(Integer id, String noteId, HistoryBean historyBean) {
        historyBean.setId(noteId);
        historyBean.setPatId(id);

        historyProxy.updateHistory(noteId, historyBean);
    }

    /**
     * Deletes a patient's history by its note ID.
     *
     * @param noteId The ID of the history note to be deleted.
     */
    public void deleteHistory(String noteId) {
        historyProxy.deleteHistory(noteId);
    }

}
