package com.raise.service;

import com.raise.domain.Allocation;
import com.raise.repository.AllocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Allocation}.
 */
@Service
@Transactional
public class AllocationService {

    private final Logger log = LoggerFactory.getLogger(AllocationService.class);

    private final AllocationRepository allocationRepository;

    public AllocationService(AllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

    /**
     * Save a allocation.
     *
     * @param allocation the entity to save.
     * @return the persisted entity.
     */
    public Allocation save(Allocation allocation) {
        log.debug("Request to save Allocation : {}", allocation);
        return allocationRepository.save(allocation);
    }

    /**
     * Get all the allocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Allocation> findAll(Pageable pageable) {
        log.debug("Request to get all Allocations");
        return allocationRepository.findAll(pageable);
    }

    /**
     * Get one allocation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Allocation> findOne(Long id) {
        log.debug("Request to get Allocation : {}", id);
        return allocationRepository.findById(id);
    }

    /**
     * Delete the allocation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Allocation : {}", id);
        allocationRepository.deleteById(id);
    }
}
