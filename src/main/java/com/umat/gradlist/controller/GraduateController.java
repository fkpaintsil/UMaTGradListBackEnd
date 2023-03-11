package com.umat.gradlist.controller;

import com.umat.gradlist.dto.SearchDTO;
import com.umat.gradlist.model.Graduate;
import com.umat.gradlist.service.GraduateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/graduates")
public class GraduateController {

    private final GraduateService graduateService;

    @Autowired
    public GraduateController(final GraduateService graduateService) {
        this.graduateService = graduateService;
    }

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
