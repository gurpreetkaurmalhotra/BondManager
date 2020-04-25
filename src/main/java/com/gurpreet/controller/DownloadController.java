package com.gurpreet.controller;

import com.gurpreet.service.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

@Slf4j
@RestController
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    DownloadService downloadService;

    @GetMapping("/csv")
    public void exportSalesToCSVWithSlicing(@QueryParam("startDate") String startDate,
                                            @QueryParam("endDate") String endDate,
                                            HttpServletResponse response) {
        downloadService.downloadCsvV3(startDate, endDate, response);
    }

}
