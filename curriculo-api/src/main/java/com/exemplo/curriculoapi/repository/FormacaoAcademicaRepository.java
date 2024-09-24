package com.exemplo.curriculoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.curriculoapi.model.FormacaoAcademica;

public interface FormacaoAcademicaRepository extends JpaRepository<FormacaoAcademica, Long> {
}

