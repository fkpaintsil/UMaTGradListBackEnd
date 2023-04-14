package com.umat.gradlist.service;

import com.umat.gradlist.dto.SearchDTO;
import com.umat.gradlist.model.Graduate;
import com.umat.gradlist.repository.GraduateRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GraduateServiceImpl implements GraduateService {
    private final GraduateRepository graduateRepository;

    @Override
    public List<Graduate> saveFileContent(MultipartFile file) {
        List<CSVRecord> csvRecords = extractFileContent(file);
        List<Graduate> graduates = new ArrayList<>();

        for (CSVRecord csvRecord : csvRecords) {
            Graduate graduate = new Graduate(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2));
            graduates.add(graduate);
        }
        return graduateRepository.saveAll(graduates);
    }

    @Override
    public Iterable<Graduate> getGraduates() {
        return graduateRepository.findAll();
    }

    @Override
    public List<Graduate> searchGraduate(SearchDTO searchDetails) {
        List<Graduate> results = new ArrayList<>();
        String searchField = searchDetails.getField();
        String searchValue = searchDetails.getValue();

        if (searchField.equalsIgnoreCase("fullName")) {
            results = graduateRepository.findByFullName(searchValue);
        } else if (searchField.equalsIgnoreCase("indexNumber")) {
            results = graduateRepository.findByIndexNumber(searchValue);
        }

        return results;
    }

    private List<CSVRecord> extractFileContent(MultipartFile file) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            bufferedReader.readLine();
            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT);
            return csvParser.getRecords();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save file contents");
        }

    }
}
