package com.mediscreen.webapp.service;

import com.mediscreen.webapp.beans.AssessmentBean;
import com.mediscreen.webapp.beans.HistoryBean;
import com.mediscreen.webapp.beans.PatientBean;
import com.mediscreen.webapp.proxies.MicroserviceAssessmentsProxy;
import com.mediscreen.webapp.proxies.MicroserviceHistoriesProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentManagementService {

    @Autowired
    private MicroserviceAssessmentsProxy assessmentProxy;


    public AssessmentBean getRisk(Integer patientId){
       return assessmentProxy.getRisk(patientId);
    }

    public String getAssessmentById(Integer patientId){
        return assessmentProxy.getAssessmentById(patientId).getBody();
    }
}
