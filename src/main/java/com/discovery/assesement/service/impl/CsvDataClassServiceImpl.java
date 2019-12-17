package com.discovery.assesement.service.impl;

import com.discovery.assesement.models.CsvDataClass;
import com.discovery.assesement.repository.CsvDataClassRepository;
import com.discovery.assesement.service.CsvDataClassService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CsvDataClassServiceImpl implements CsvDataClassService {

    private final CsvDataClassRepository repository;

    public CsvDataClassServiceImpl(CsvDataClassRepository repository) {
        this.repository = repository;
    }

    @Override
    public void importCsvFile(MultipartFile file) {

        Reader reader;

        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // br = new BufferedReader(new InputStreamReader(is));

        reader = new BufferedReader(new InputStreamReader(is));
        CSVParser csvParser;
        try {
            csvParser = CSVParser.parse(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
            for (CSVRecord csvRecord : csvParser) {
                CsvDataClass csvDataClass = new CsvDataClass();
                csvDataClass.setFromPlanet(csvRecord.get(0));
                csvDataClass.setToPlanet(csvRecord.get(1));
                csvDataClass.setDistance(Long.parseLong(csvRecord.get(2)));
                repository.save(csvDataClass);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public CsvDataClass getCsvData(Long id) {
        return repository.getOne(id);
    }

    @Override
    public File exportCsv() {

        List<CsvDataClass> csvDataClassList = repository.findAll();
        File tempFile = null;
        final String CSV_SEPARATOR = ",";
        final String tmpdir = System.getProperty("java.io.tmpdir");
        try {
            tempFile = File.createTempFile(tmpdir + "/results" + new Date().toString() + "-", ".csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile), StandardCharsets.UTF_8));
            for (CsvDataClass csvDataClass : csvDataClassList) {
                // String status = promotion.getStatus();


                StringBuffer oneLine = new StringBuffer();
                oneLine.append(csvDataClass.getFromPlanet().trim().length() == 0 ? "" : csvDataClass.getFromPlanet());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(csvDataClass.getToPlanet().trim().length() == 0 ? "" : csvDataClass.getToPlanet());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(csvDataClass.getDistance() == 0 ? "" : csvDataClass.getDistance());
                oneLine.append(CSV_SEPARATOR);
                bw.write(oneLine.toString());
                bw.newLine();


            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    @Override
    public List<CsvDataClass> getAllCsvData() {
        return repository.findAll();
    }
}
