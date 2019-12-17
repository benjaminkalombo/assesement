package com.discovery.assesement.repository;

import com.discovery.assesement.models.CsvDataClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsvDataClassRepository extends JpaRepository<CsvDataClass, Long> {
}
