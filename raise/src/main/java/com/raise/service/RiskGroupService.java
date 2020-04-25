package com.raise.service;

import com.raise.domain.RiskGroup;
import com.raise.repository.RiskGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RiskGroup}.
 */
@Service
@Transactional
public class RiskGroupService {

    private final Logger log = LoggerFactory.getLogger(RiskGroupService.class);

    private final RiskGroupRepository riskGroupRepository;

    public RiskGroupService(RiskGroupRepository riskGroupRepository) {
        this.riskGroupRepository = riskGroupRepository;
    }

    /**
     * Save a riskGroup.
     *
     * @param riskGroup the entity to save.
     * @return the persisted entity.
     */
    public RiskGroup save(RiskGroup riskGroup) {
        log.debug("Request to save RiskGroup : {}", riskGroup);
        return riskGroupRepository.save(riskGroup);
    }

    /**
     * Get all the riskGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RiskGroup> findAll(Pageable pageable) {
        log.debug("Request to get all RiskGroups");
        return riskGroupRepository.findAll(pageable);
    }

    /**
     * Get one riskGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RiskGroup> findOne(Long id) {
        log.debug("Request to get RiskGroup : {}", id);
        return riskGroupRepository.findById(id);
    }

    /**
     * Delete the riskGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RiskGroup : {}", id);
        riskGroupRepository.deleteById(id);
    }
}
