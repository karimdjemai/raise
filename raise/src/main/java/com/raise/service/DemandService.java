package com.raise.service;

import com.raise.domain.Demand;
import com.raise.repository.DemandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Demand}.
 */
@Service
@Transactional
public class DemandService {

    private final Logger log = LoggerFactory.getLogger(DemandService.class);

    private final DemandRepository demandRepository;

    public DemandService(DemandRepository demandRepository) {
        this.demandRepository = demandRepository;
    }

    /**
     * Save a demand.
     *
     * @param demand the entity to save.
     * @return the persisted entity.
     */
    public Demand save(Demand demand) {
        log.debug("Request to save Demand : {}", demand);
        return demandRepository.save(demand);
    }

    /**
     * Get all the demands.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Demand> findAll(Pageable pageable) {
        log.debug("Request to get all Demands");
        return demandRepository.findAll(pageable);
    }

    /**
     * Get one demand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Demand> findOne(Long id) {
        log.debug("Request to get Demand : {}", id);
        return demandRepository.findById(id);
    }

    /**
     * Delete the demand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Demand : {}", id);
        demandRepository.deleteById(id);
    }
}
