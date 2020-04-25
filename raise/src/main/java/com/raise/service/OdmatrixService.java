package com.raise.service;

import com.raise.domain.Odmatrix;
import com.raise.repository.OdmatrixRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Odmatrix}.
 */
@Service
@Transactional
public class OdmatrixService {

    private final Logger log = LoggerFactory.getLogger(OdmatrixService.class);

    private final OdmatrixRepository odmatrixRepository;

    public OdmatrixService(OdmatrixRepository odmatrixRepository) {
        this.odmatrixRepository = odmatrixRepository;
    }

    /**
     * Save a odmatrix.
     *
     * @param odmatrix the entity to save.
     * @return the persisted entity.
     */
    public Odmatrix save(Odmatrix odmatrix) {
        log.debug("Request to save Odmatrix : {}", odmatrix);
        return odmatrixRepository.save(odmatrix);
    }

    /**
     * Get all the odmatrices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Odmatrix> findAll(Pageable pageable) {
        log.debug("Request to get all Odmatrices");
        return odmatrixRepository.findAll(pageable);
    }

    /**
     * Get one odmatrix by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Odmatrix> findOne(Long id) {
        log.debug("Request to get Odmatrix : {}", id);
        return odmatrixRepository.findById(id);
    }

    /**
     * Delete the odmatrix by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Odmatrix : {}", id);
        odmatrixRepository.deleteById(id);
    }
}
