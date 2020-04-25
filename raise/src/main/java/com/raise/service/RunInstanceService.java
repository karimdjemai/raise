package com.raise.service;

import com.raise.domain.RunInstance;
import com.raise.repository.RunInstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RunInstance}.
 */
@Service
@Transactional
public class RunInstanceService {

    private final Logger log = LoggerFactory.getLogger(RunInstanceService.class);

    private final RunInstanceRepository runInstanceRepository;

    public RunInstanceService(RunInstanceRepository runInstanceRepository) {
        this.runInstanceRepository = runInstanceRepository;
    }

    /**
     * Save a runInstance.
     *
     * @param runInstance the entity to save.
     * @return the persisted entity.
     */
    public RunInstance save(RunInstance runInstance) {
        log.debug("Request to save RunInstance : {}", runInstance);
        return runInstanceRepository.save(runInstance);
    }

    /**
     * Get all the runInstances.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RunInstance> findAll(Pageable pageable) {
        log.debug("Request to get all RunInstances");
        return runInstanceRepository.findAll(pageable);
    }

    /**
     * Get one runInstance by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RunInstance> findOne(Long id) {
        log.debug("Request to get RunInstance : {}", id);
        return runInstanceRepository.findById(id);
    }

    /**
     * Delete the runInstance by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RunInstance : {}", id);
        runInstanceRepository.deleteById(id);
    }
}
