package com.raise.service;

import com.raise.domain.InfectionRate;
import com.raise.repository.InfectionRateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InfectionRate}.
 */
@Service
@Transactional
public class InfectionRateService {

    private final Logger log = LoggerFactory.getLogger(InfectionRateService.class);

    private final InfectionRateRepository infectionRateRepository;

    public InfectionRateService(InfectionRateRepository infectionRateRepository) {
        this.infectionRateRepository = infectionRateRepository;
    }

    /**
     * Save a infectionRate.
     *
     * @param infectionRate the entity to save.
     * @return the persisted entity.
     */
    public InfectionRate save(InfectionRate infectionRate) {
        log.debug("Request to save InfectionRate : {}", infectionRate);
        return infectionRateRepository.save(infectionRate);
    }

    /**
     * Get all the infectionRates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InfectionRate> findAll(Pageable pageable) {
        log.debug("Request to get all InfectionRates");
        return infectionRateRepository.findAll(pageable);
    }

    /**
     * Get one infectionRate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InfectionRate> findOne(Long id) {
        log.debug("Request to get InfectionRate : {}", id);
        return infectionRateRepository.findById(id);
    }

    /**
     * Delete the infectionRate by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InfectionRate : {}", id);
        infectionRateRepository.deleteById(id);
    }
}
