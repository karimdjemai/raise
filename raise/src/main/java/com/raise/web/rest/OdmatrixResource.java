package com.raise.web.rest;

import com.raise.domain.Odmatrix;
import com.raise.service.OdmatrixService;
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
 * REST controller for managing {@link com.raise.domain.Odmatrix}.
 */
@RestController
@RequestMapping("/api")
public class OdmatrixResource {

    private final Logger log = LoggerFactory.getLogger(OdmatrixResource.class);

    private static final String ENTITY_NAME = "odmatrix";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OdmatrixService odmatrixService;

    public OdmatrixResource(OdmatrixService odmatrixService) {
        this.odmatrixService = odmatrixService;
    }

    /**
     * {@code POST  /odmatrices} : Create a new odmatrix.
     *
     * @param odmatrix the odmatrix to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new odmatrix, or with status {@code 400 (Bad Request)} if the odmatrix has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/odmatrices")
    public ResponseEntity<Odmatrix> createOdmatrix(@Valid @RequestBody Odmatrix odmatrix) throws URISyntaxException {
        log.debug("REST request to save Odmatrix : {}", odmatrix);
        if (odmatrix.getId() != null) {
            throw new BadRequestAlertException("A new odmatrix cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Odmatrix result = odmatrixService.save(odmatrix);
        return ResponseEntity.created(new URI("/api/odmatrices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /odmatrices} : Updates an existing odmatrix.
     *
     * @param odmatrix the odmatrix to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated odmatrix,
     * or with status {@code 400 (Bad Request)} if the odmatrix is not valid,
     * or with status {@code 500 (Internal Server Error)} if the odmatrix couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/odmatrices")
    public ResponseEntity<Odmatrix> updateOdmatrix(@Valid @RequestBody Odmatrix odmatrix) throws URISyntaxException {
        log.debug("REST request to update Odmatrix : {}", odmatrix);
        if (odmatrix.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Odmatrix result = odmatrixService.save(odmatrix);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, odmatrix.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /odmatrices} : get all the odmatrices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of odmatrices in body.
     */
    @GetMapping("/odmatrices")
    public ResponseEntity<List<Odmatrix>> getAllOdmatrices(Pageable pageable) {
        log.debug("REST request to get a page of Odmatrices");
        Page<Odmatrix> page = odmatrixService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /odmatrices/:id} : get the "id" odmatrix.
     *
     * @param id the id of the odmatrix to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the odmatrix, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/odmatrices/{id}")
    public ResponseEntity<Odmatrix> getOdmatrix(@PathVariable Long id) {
        log.debug("REST request to get Odmatrix : {}", id);
        Optional<Odmatrix> odmatrix = odmatrixService.findOne(id);
        return ResponseUtil.wrapOrNotFound(odmatrix);
    }

    /**
     * {@code DELETE  /odmatrices/:id} : delete the "id" odmatrix.
     *
     * @param id the id of the odmatrix to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/odmatrices/{id}")
    public ResponseEntity<Void> deleteOdmatrix(@PathVariable Long id) {
        log.debug("REST request to delete Odmatrix : {}", id);
        odmatrixService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
