package com.umat.gradlist.repository;

import com.umat.gradlist.model.Graduate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GraduateRepository extends JpaRepository<Graduate, UUID> {
    List<Graduate> findByFullName(String fullName);

    List<Graduate> findByIndexNumber(String indexNumber);

    @Override
    Page<Graduate> findAll(Pageable pageable);
}
