package com.raise.web.rest;

import com.raise.domain.Demand;
import com.raise.service.DemandService;
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
 * REST controller for managing {@link com.raise.domain.Demand}.
 */
@RestController
@RequestMapping("/api")
public class DemandResource {

    private final Logger log = LoggerFactory.getLogger(DemandResource.class);

    private static final String ENTITY_NAME = "demand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DemandService demandService;

    public DemandResource(DemandService demandService) {
        this.demandService = demandService;
    }

    /**
     * {@code POST  /demands} : Create a new demand.
     *
     * @param demand the demand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new demand, or with status {@code 400 (Bad Request)} if the demand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/demands")
    public ResponseEntity<Demand> createDemand(@Valid @RequestBody Demand demand) throws URISyntaxException {
        log.debug("REST request to save Demand : {}", demand);
        if (demand.getId() != null) {
            throw new BadRequestAlertException("A new demand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Demand result = demandService.save(demand);
        return ResponseEntity.created(new URI("/api/demands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /demands} : Updates an existing demand.
     *
     * @param demand the demand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demand,
     * or with status {@code 400 (Bad Request)} if the demand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the demand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/demands")
    public ResponseEntity<Demand> updateDemand(@Valid @RequestBody Demand demand) throws URISyntaxException {
        log.debug("REST request to update Demand : {}", demand);
        if (demand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Demand result = demandService.save(demand);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, demand.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /demands} : get all the demands.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demands in body.
     */
    @GetMapping("/demands")
    public ResponseEntity<List<Demand>> getAllDemands(Pageable pageable) {
        log.debug("REST request to get a page of Demands");
        Page<Demand> page = demandService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /demands/:id} : get the "id" demand.
     *
     * @param id the id of the demand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/demands/{id}")
    public ResponseEntity<Demand> getDemand(@PathVariable Long id) {
        log.debug("REST request to get Demand : {}", id);
        Optional<Demand> demand = demandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(demand);
    }

    /**
     * {@code DELETE  /demands/:id} : delete the "id" demand.
     *
     * @param id the id of the demand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/demands/{id}")
    public ResponseEntity<Void> deleteDemand(@PathVariable Long id) {
        log.debug("REST request to delete Demand : {}", id);
        demandService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
