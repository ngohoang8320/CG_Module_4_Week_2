package com.codegym.repository;

import com.codegym.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProvinceRepository extends JpaRepository<Province, Long> {
}
