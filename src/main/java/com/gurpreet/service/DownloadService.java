package com.gurpreet.service;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface DownloadService {
    void downloadCsvV3(String startDate, String endDate, HttpServletResponse response);
}

