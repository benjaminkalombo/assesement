package com.discovery.assesement.service;

import com.discovery.assesement.models.CsvDataClass;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public interface CsvDataClassService {

    void importCsvFile(MultipartFile file);
    CsvDataClass getCsvData(Long id);
    File exportCsv();
    List<CsvDataClass> getAllCsvData();
}
