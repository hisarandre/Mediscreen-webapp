package com.mediscreen.webapp.proxies;

import com.mediscreen.webapp.beans.AssessmentBean;
import com.mediscreen.webapp.beans.HistoryBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "mediscreen-assessment", url = "${ASSESSMENT.PROXY}")
public interface MicroserviceAssessmentsProxy {

    @GetMapping(value = "assess/risk/{patientId}")
    public AssessmentBean getRisk(@PathVariable("patientId") Integer patientId);

    @PostMapping(value="/assess/id", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> getAssessmentById(Integer patId);

}
