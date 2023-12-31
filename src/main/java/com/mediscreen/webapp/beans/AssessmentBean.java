package com.mediscreen.webapp.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssessmentBean {
    private Integer patId;

    private String family;

    private String given;

    private Integer age;

    private String diabetesAssessment;

    @Override
    public String toString() {
        return "{ Patient : " +
                given + " " + family + " " +
                "(age " + age +
                ") diabetes assessment is: " + diabetesAssessment +
                "}";
    }
}
