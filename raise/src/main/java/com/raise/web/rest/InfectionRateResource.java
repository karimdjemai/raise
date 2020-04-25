package com.raise.web.rest;

import com.raise.domain.InfectionRate;
import com.raise.service.InfectionRateService;
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
 * REST controller for managing {@link com.raise.domain.InfectionRate}.
 */
@RestController
@RequestMapping("/api")
public class InfectionRateResource {

    private final Logger log = LoggerFactory.getLogger(InfectionRateResource.class);

    private static final String ENTITY_NAME = "infectionRate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InfectionRateService infectionRateService;

    public InfectionRateResource(InfectionRateService infectionRateService) {
        this.infectionRateService = infectionRateService;
    }

    /**
     * {@code POST  /infection-rates} : Create a new infectionRate.
     *
     * @param infectionRate the infectionRate to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new infectionRate, or with status {@code 400 (Bad Request)} if the infectionRate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/infection-rates")
    public ResponseEntity<InfectionRate> createInfectionRate(@Valid @RequestBody InfectionRate infectionRate) throws URISyntaxException {
        log.debug("REST request to save InfectionRate : {}", infectionRate);
        if (infectionRate.getId() != null) {
            throw new BadRequestAlertException("A new infectionRate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InfectionRate result = infectionRateService.save(infectionRate);
        return ResponseEntity.created(new URI("/api/infection-rates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /infection-rates} : Updates an existing infectionRate.
     *
     * @param infectionRate the infectionRate to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated infectionRate,
     * or with status {@code 400 (Bad Request)} if the infectionRate is not valid,
     * or with status {@code 500 (Internal Server Error)} if the infectionRate couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/infection-rates")
    public ResponseEntity<InfectionRate> updateInfectionRate(@Valid @RequestBody InfectionRate infectionRate) throws URISyntaxException {
        log.debug("REST request to update InfectionRate : {}", infectionRate);
        if (infectionRate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InfectionRate result = infectionRateService.save(infectionRate);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, infectionRate.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /infection-rates} : get all the infectionRates.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of infectionRates in body.
     */
    @GetMapping("/infection-rates")
    public ResponseEntity<List<InfectionRate>> getAllInfectionRates(Pageable pageable) {
        log.debug("REST request to get a page of InfectionRates");
        Page<InfectionRate> page = infectionRateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /infection-rates/:id} : get the "id" infectionRate.
     *
     * @param id the id of the infectionRate to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the infectionRate, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/infection-rates/{id}")
    public ResponseEntity<InfectionRate> getInfectionRate(@PathVariable Long id) {
        log.debug("REST request to get InfectionRate : {}", id);
        Optional<InfectionRate> infectionRate = infectionRateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(infectionRate);
    }

    /**
     * {@code DELETE  /infection-rates/:id} : delete the "id" infectionRate.
     *
     * @param id the id of the infectionRate to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/infection-rates/{id}")
    public ResponseEntity<Void> deleteInfectionRate(@PathVariable Long id) {
        log.debug("REST request to delete InfectionRate : {}", id);
        infectionRateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
