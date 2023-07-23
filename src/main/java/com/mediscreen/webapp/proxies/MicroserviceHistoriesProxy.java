package com.mediscreen.webapp.proxies;

import com.mediscreen.webapp.beans.HistoryBean;
import com.mediscreen.webapp.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "mediscreen-history", url = "${HISTORY.PROXY}")
public interface MicroserviceHistoriesProxy {

    @GetMapping("/patHistory/{id}")
    HistoryBean getHistoryById(@PathVariable String id);

    @GetMapping("/patHistory")
    List<HistoryBean> getHistoryByPatientId(@RequestParam("patId") Integer id) ;

    @PostMapping(value = "/patHistory/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    void addHistory(@ModelAttribute HistoryBean historyBean);

    @PutMapping("/patHistory/update/{id}")
    void updateHistory(@PathVariable("id") String id, @RequestBody HistoryBean historyBean);

    @DeleteMapping("/patHistory/{id}")
    void deleteHistory(@PathVariable("id") String id);

}
