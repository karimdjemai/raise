package com.raise.repository;

import com.raise.domain.RiskGroup;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RiskGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskGroupRepository extends JpaRepository<RiskGroup, Long> {
}
