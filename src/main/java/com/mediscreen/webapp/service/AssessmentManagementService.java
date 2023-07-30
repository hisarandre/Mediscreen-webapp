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

/**
 * Service class to manage assessments related operations.
 */
@Service
public class AssessmentManagementService {

    @Autowired
    private MicroserviceAssessmentsProxy assessmentProxy;

    /**
     * Retrieves the risk assessment for a given patient based on their patient ID.
     *
     * @param patientId The ID of the patient for whom the risk assessment is requested.
     * @return The risk assessment information as an AssessmentBean.
     */
    public AssessmentBean getRisk(Integer patientId){
       return assessmentProxy.getRisk(patientId);
    }

    /**
     * Retrieves the risk assessment for a patient based on their patient ID provided in the request body.
     *
     * @param patientId The ID of the patient for whom the risk assessment is requested.
     * @return A String containing the risk assessment information.
     */
    public String getAssessmentById(Integer patientId){
        return assessmentProxy.getAssessmentById(patientId).getBody();
    }
}
