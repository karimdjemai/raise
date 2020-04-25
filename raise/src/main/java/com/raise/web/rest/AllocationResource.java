package com.raise.web.rest;

import com.raise.domain.Allocation;
import com.raise.service.AllocationService;
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
 * REST controller for managing {@link com.raise.domain.Allocation}.
 */
@RestController
@RequestMapping("/api")
public class AllocationResource {

    private final Logger log = LoggerFactory.getLogger(AllocationResource.class);

    private static final String ENTITY_NAME = "allocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AllocationService allocationService;

    public AllocationResource(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    /**
     * {@code POST  /allocations} : Create a new allocation.
     *
     * @param allocation the allocation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new allocation, or with status {@code 400 (Bad Request)} if the allocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/allocations")
    public ResponseEntity<Allocation> createAllocation(@Valid @RequestBody Allocation allocation) throws URISyntaxException {
        log.debug("REST request to save Allocation : {}", allocation);
        if (allocation.getId() != null) {
            throw new BadRequestAlertException("A new allocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Allocation result = allocationService.save(allocation);
        return ResponseEntity.created(new URI("/api/allocations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /allocations} : Updates an existing allocation.
     *
     * @param allocation the allocation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated allocation,
     * or with status {@code 400 (Bad Request)} if the allocation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the allocation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/allocations")
    public ResponseEntity<Allocation> updateAllocation(@Valid @RequestBody Allocation allocation) throws URISyntaxException {
        log.debug("REST request to update Allocation : {}", allocation);
        if (allocation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Allocation result = allocationService.save(allocation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, allocation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /allocations} : get all the allocations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of allocations in body.
     */
    @GetMapping("/allocations")
    public ResponseEntity<List<Allocation>> getAllAllocations(Pageable pageable) {
        log.debug("REST request to get a page of Allocations");
        Page<Allocation> page = allocationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /allocations/:id} : get the "id" allocation.
     *
     * @param id the id of the allocation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the allocation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/allocations/{id}")
    public ResponseEntity<Allocation> getAllocation(@PathVariable Long id) {
        log.debug("REST request to get Allocation : {}", id);
        Optional<Allocation> allocation = allocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(allocation);
    }

    /**
     * {@code DELETE  /allocations/:id} : delete the "id" allocation.
     *
     * @param id the id of the allocation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/allocations/{id}")
    public ResponseEntity<Void> deleteAllocation(@PathVariable Long id) {
        log.debug("REST request to delete Allocation : {}", id);
        allocationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
