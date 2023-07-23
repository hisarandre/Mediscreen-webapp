package com.mediscreen.webapp.service;

import com.mediscreen.webapp.beans.HistoryBean;
import com.mediscreen.webapp.beans.PatientBean;
import com.mediscreen.webapp.proxies.MicroserviceHistoriesProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HistoryManagementService {

    @Autowired
    private MicroserviceHistoriesProxy historyProxy;

    public List<HistoryBean> getHistoriesByPatientId(Integer id){
        List<HistoryBean> histories = historyProxy.getHistoryByPatientId(id);
        return histories;
    }

    public void addHistory(HistoryBean historyBean, PatientBean patientBean){
        historyBean.setId(null);
        historyBean.setPatId(patientBean.getId());
        historyBean.setPatient(patientBean.getFamily());
        historyProxy.addHistory(historyBean);
    }

    public HistoryBean getHistoryById(String noteId){
        return historyProxy.getHistoryById(noteId);
    }

    public void updateHistory(Integer id, String noteId, HistoryBean historyBean){
        historyBean.setId(noteId);
        historyBean.setPatId(id);

        historyProxy.updateHistory(noteId, historyBean);
    }

    public void deleteHistory(String noteId) {
        historyProxy.deleteHistory(noteId);
    }

}
