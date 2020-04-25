package com.raise.web.rest;

import com.raise.domain.RunInstance;
import com.raise.service.RunInstanceService;
import com.raise.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.raise.domain.RunInstance}.
 */
@RestController
@RequestMapping("/api")
public class RunInstanceResource {

    private final Logger log = LoggerFactory.getLogger(RunInstanceResource.class);

    private static final String ENTITY_NAME = "runInstance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RunInstanceService runInstanceService;

    public RunInstanceResource(RunInstanceService runInstanceService) {
        this.runInstanceService = runInstanceService;
    }

    /**
     * {@code POST  /run-instances} : Create a new runInstance.
     *
     * @param runInstance the runInstance to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new runInstance, or with status {@code 400 (Bad Request)} if the runInstance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/run-instances")
    public ResponseEntity<RunInstance> createRunInstance(@Valid @RequestBody RunInstance runInstance) throws URISyntaxException {
        log.debug("REST request to save RunInstance : {}", runInstance);
        if (runInstance.getId() != null) {
            throw new BadRequestAlertException("A new runInstance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RunInstance result = runInstanceService.save(runInstance);
        return ResponseEntity.created(new URI("/api/run-instances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /run-instances} : Updates an existing runInstance.
     *
     * @param runInstance the runInstance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated runInstance,
     * or with status {@code 400 (Bad Request)} if the runInstance is not valid,
     * or with status {@code 500 (Internal Server Error)} if the runInstance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/run-instances")
    public ResponseEntity<RunInstance> updateRunInstance(@Valid @RequestBody RunInstance runInstance) throws URISyntaxException {
        log.debug("REST request to update RunInstance : {}", runInstance);
        if (runInstance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RunInstance result = runInstanceService.save(runInstance);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, runInstance.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /run-instances} : get all the runInstances.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of runInstances in body.
     */
    @GetMapping("/run-instances")
    public ResponseEntity<List<RunInstance>> getAllRunInstances(Pageable pageable) {
        log.debug("REST request to get a page of RunInstances");
        Page<RunInstance> page = runInstanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /run-instances/:id} : get the "id" runInstance.
     *
     * @param id the id of the runInstance to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the runInstance, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/run-instances/{id}")
    public ResponseEntity<RunInstance> getRunInstance(@PathVariable Long id) {
        log.debug("REST request to get RunInstance : {}", id);
        Optional<RunInstance> runInstance = runInstanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(runInstance);
    }

    /**
     * {@code DELETE  /run-instances/:id} : delete the "id" runInstance.
     *
     * @param id the id of the runInstance to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/run-instances/{id}")
    public ResponseEntity<Void> deleteRunInstance(@PathVariable Long id) {
        log.debug("REST request to delete RunInstance : {}", id);
        runInstanceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
