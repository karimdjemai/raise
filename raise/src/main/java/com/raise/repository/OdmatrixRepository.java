package com.raise.repository;

import com.raise.domain.Odmatrix;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Odmatrix entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OdmatrixRepository extends JpaRepository<Odmatrix, Long> {
}
