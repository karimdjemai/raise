package com.raise.web.rest;

import com.raise.domain.Supply;
import com.raise.service.SupplyService;
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
 * REST controller for managing {@link com.raise.domain.Supply}.
 */
@RestController
@RequestMapping("/api")
public class SupplyResource {

    private final Logger log = LoggerFactory.getLogger(SupplyResource.class);

    private static final String ENTITY_NAME = "supply";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SupplyService supplyService;

    public SupplyResource(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    /**
     * {@code POST  /supplies} : Create a new supply.
     *
     * @param supply the supply to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new supply, or with status {@code 400 (Bad Request)} if the supply has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/supplies")
    public ResponseEntity<Supply> createSupply(@Valid @RequestBody Supply supply) throws URISyntaxException {
        log.debug("REST request to save Supply : {}", supply);
        if (supply.getId() != null) {
            throw new BadRequestAlertException("A new supply cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Supply result = supplyService.save(supply);
        return ResponseEntity.created(new URI("/api/supplies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /supplies} : Updates an existing supply.
     *
     * @param supply the supply to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supply,
     * or with status {@code 400 (Bad Request)} if the supply is not valid,
     * or with status {@code 500 (Internal Server Error)} if the supply couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/supplies")
    public ResponseEntity<Supply> updateSupply(@Valid @RequestBody Supply supply) throws URISyntaxException {
        log.debug("REST request to update Supply : {}", supply);
        if (supply.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Supply result = supplyService.save(supply);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, supply.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /supplies} : get all the supplies.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of supplies in body.
     */
    @GetMapping("/supplies")
    public ResponseEntity<List<Supply>> getAllSupplies(Pageable pageable) {
        log.debug("REST request to get a page of Supplies");
        Page<Supply> page = supplyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /supplies/:id} : get the "id" supply.
     *
     * @param id the id of the supply to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the supply, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/supplies/{id}")
    public ResponseEntity<Supply> getSupply(@PathVariable Long id) {
        log.debug("REST request to get Supply : {}", id);
        Optional<Supply> supply = supplyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(supply);
    }

    /**
     * {@code DELETE  /supplies/:id} : delete the "id" supply.
     *
     * @param id the id of the supply to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/supplies/{id}")
    public ResponseEntity<Void> deleteSupply(@PathVariable Long id) {
        log.debug("REST request to delete Supply : {}", id);
        supplyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
