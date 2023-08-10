package com.example.library.Repository;

import com.example.library.Model.AccumlatePoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccumlatePointRepository extends JpaRepository<AccumlatePoints , Long> {
    List<AccumlatePoints> findAllByUsers_Id(Long id);
    boolean existsByUsers_FullName(String name);
}
