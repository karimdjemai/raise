package com.raise.web.rest;

import com.raise.domain.RiskGroup;
import com.raise.service.RiskGroupService;
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
 * REST controller for managing {@link com.raise.domain.RiskGroup}.
 */
@RestController
@RequestMapping("/api")
public class RiskGroupResource {

    private final Logger log = LoggerFactory.getLogger(RiskGroupResource.class);

    private static final String ENTITY_NAME = "riskGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskGroupService riskGroupService;

    public RiskGroupResource(RiskGroupService riskGroupService) {
        this.riskGroupService = riskGroupService;
    }

    /**
     * {@code POST  /risk-groups} : Create a new riskGroup.
     *
     * @param riskGroup the riskGroup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskGroup, or with status {@code 400 (Bad Request)} if the riskGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/risk-groups")
    public ResponseEntity<RiskGroup> createRiskGroup(@Valid @RequestBody RiskGroup riskGroup) throws URISyntaxException {
        log.debug("REST request to save RiskGroup : {}", riskGroup);
        if (riskGroup.getId() != null) {
            throw new BadRequestAlertException("A new riskGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RiskGroup result = riskGroupService.save(riskGroup);
        return ResponseEntity.created(new URI("/api/risk-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /risk-groups} : Updates an existing riskGroup.
     *
     * @param riskGroup the riskGroup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskGroup,
     * or with status {@code 400 (Bad Request)} if the riskGroup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskGroup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/risk-groups")
    public ResponseEntity<RiskGroup> updateRiskGroup(@Valid @RequestBody RiskGroup riskGroup) throws URISyntaxException {
        log.debug("REST request to update RiskGroup : {}", riskGroup);
        if (riskGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RiskGroup result = riskGroupService.save(riskGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskGroup.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /risk-groups} : get all the riskGroups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riskGroups in body.
     */
    @GetMapping("/risk-groups")
    public ResponseEntity<List<RiskGroup>> getAllRiskGroups(Pageable pageable) {
        log.debug("REST request to get a page of RiskGroups");
        Page<RiskGroup> page = riskGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /risk-groups/:id} : get the "id" riskGroup.
     *
     * @param id the id of the riskGroup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskGroup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/risk-groups/{id}")
    public ResponseEntity<RiskGroup> getRiskGroup(@PathVariable Long id) {
        log.debug("REST request to get RiskGroup : {}", id);
        Optional<RiskGroup> riskGroup = riskGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(riskGroup);
    }

    /**
     * {@code DELETE  /risk-groups/:id} : delete the "id" riskGroup.
     *
     * @param id the id of the riskGroup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/risk-groups/{id}")
    public ResponseEntity<Void> deleteRiskGroup(@PathVariable Long id) {
        log.debug("REST request to delete RiskGroup : {}", id);
        riskGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
