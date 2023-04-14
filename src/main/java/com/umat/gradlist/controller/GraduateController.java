package com.umat.gradlist.controller;

import com.umat.gradlist.dto.SearchDTO;
import com.umat.gradlist.model.Graduate;
import com.umat.gradlist.service.GraduateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/graduates")
@RequiredArgsConstructor
public class GraduateController {

    private final GraduateService graduateService;

    @PostMapping("/upload-graduates-csv")
    public List<Graduate> saveFileContent(@RequestParam("file") MultipartFile file) {
        return graduateService.saveFileContent(file);
    }

    @GetMapping
    public Iterable<Graduate> getGraduates() {
        return graduateService.getGraduates();
    }

    @PostMapping("/search")
    public List<Graduate> searchGraduate(@RequestBody SearchDTO searchDetails) {
        return graduateService.searchGraduate(searchDetails);
    }
}
