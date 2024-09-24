package com.exemplo.curriculoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.curriculoapi.model.Curriculo;

public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {
}
