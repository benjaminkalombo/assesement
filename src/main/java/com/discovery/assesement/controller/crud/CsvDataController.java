package com.discovery.assesement.controller.crud;

import com.discovery.assesement.models.CsvDataClass;
import com.discovery.assesement.service.CsvDataClassService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.util.List;

@RestController
public class CsvDataController {

     private final CsvDataClassService csvDataClassService;

    public CsvDataController(CsvDataClassService csvDataClassService) {
        this.csvDataClassService = csvDataClassService;
    }


    @GetMapping("/csvdata/all")
    public List<CsvDataClass> getAllCsv() {
        return csvDataClassService.getAllCsvData();
    }
    @GetMapping("/csvdata/{id}")
    public CsvDataClass getCsvData(@PathVariable Long id) {
        return csvDataClassService.getCsvData(id);
    }


    @GetMapping(value = "/csvdata/file/", produces = "text/csv")
    public ResponseEntity getCsvDataFile() {
        File file = csvDataClassService.exportCsv();
        try {

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + file.getName() + ".csv")
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(new FileSystemResource(file));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to generate report: " + file, e);
        }
    }
    @PostMapping(value = "/csvdata/upload",produces = { MediaType.APPLICATION_JSON_VALUE })
    public void uploadFile(@RequestParam("file") MultipartFile file) {
         csvDataClassService.importCsvFile(file);

    }







}
