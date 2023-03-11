package com.umat.gradlist.service;

import com.umat.gradlist.dto.SearchDTO;
import com.umat.gradlist.model.Graduate;
import org.springframework.web.multipart.MultipartFile;


import org.springframework.data.domain.Pageable;
import java.util.List;

public interface GraduateService {
    List<Graduate> saveFileContent(MultipartFile file);
    Iterable<Graduate> getGraduates();

    List<Graduate> searchGraduate(SearchDTO searchDetails);
}
