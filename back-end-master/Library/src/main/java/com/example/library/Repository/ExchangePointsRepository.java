package com.example.library.Repository;

import com.example.library.Model.ExchangePoints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangePointsRepository extends JpaRepository<ExchangePoints, Long> {
}
