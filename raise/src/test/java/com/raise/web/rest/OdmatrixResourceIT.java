package com.raise.web.rest;

import com.raise.RaiseApp;
import com.raise.domain.Odmatrix;
import com.raise.domain.Region;
import com.raise.repository.OdmatrixRepository;
import com.raise.service.OdmatrixService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OdmatrixResource} REST controller.
 */
@SpringBootTest(classes = RaiseApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class OdmatrixResourceIT {

    private static final Double DEFAULT_COST = 0D;
    private static final Double UPDATED_COST = 1D;

    @Autowired
    private OdmatrixRepository odmatrixRepository;

    @Autowired
    private OdmatrixService odmatrixService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOdmatrixMockMvc;

    private Odmatrix odmatrix;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Odmatrix createEntity(EntityManager em) {
        Odmatrix odmatrix = new Odmatrix()
            .cost(DEFAULT_COST);
        // Add required entity
        Region region;
        if (TestUtil.findAll(em, Region.class).isEmpty()) {
            region = RegionResourceIT.createEntity(em);
            em.persist(region);
            em.flush();
        } else {
            region = TestUtil.findAll(em, Region.class).get(0);
        }
        odmatrix.setFromRegion(region);
        // Add required entity
        odmatrix.setToRegion(region);
        return odmatrix;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Odmatrix createUpdatedEntity(EntityManager em) {
        Odmatrix odmatrix = new Odmatrix()
            .cost(UPDATED_COST);
        // Add required entity
        Region region;
        if (TestUtil.findAll(em, Region.class).isEmpty()) {
            region = RegionResourceIT.createUpdatedEntity(em);
            em.persist(region);
            em.flush();
        } else {
            region = TestUtil.findAll(em, Region.class).get(0);
        }
        odmatrix.setFromRegion(region);
        // Add required entity
        odmatrix.setToRegion(region);
        return odmatrix;
    }

    @BeforeEach
    public void initTest() {
        odmatrix = createEntity(em);
    }

    @Test
    @Transactional
    public void createOdmatrix() throws Exception {
        int databaseSizeBeforeCreate = odmatrixRepository.findAll().size();

        // Create the Odmatrix
        restOdmatrixMockMvc.perform(post("/api/odmatrices").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(odmatrix)))
            .andExpect(status().isCreated());

        // Validate the Odmatrix in the database
        List<Odmatrix> odmatrixList = odmatrixRepository.findAll();
        assertThat(odmatrixList).hasSize(databaseSizeBeforeCreate + 1);
        Odmatrix testOdmatrix = odmatrixList.get(odmatrixList.size() - 1);
        assertThat(testOdmatrix.getCost()).isEqualTo(DEFAULT_COST);
    }

    @Test
    @Transactional
    public void createOdmatrixWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = odmatrixRepository.findAll().size();

        // Create the Odmatrix with an existing ID
        odmatrix.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOdmatrixMockMvc.perform(post("/api/odmatrices").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(odmatrix)))
            .andExpect(status().isBadRequest());

        // Validate the Odmatrix in the database
        List<Odmatrix> odmatrixList = odmatrixRepository.findAll();
        assertThat(odmatrixList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCostIsRequired() throws Exception {
        int databaseSizeBeforeTest = odmatrixRepository.findAll().size();
        // set the field null
        odmatrix.setCost(null);

        // Create the Odmatrix, which fails.

        restOdmatrixMockMvc.perform(post("/api/odmatrices").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(odmatrix)))
            .andExpect(status().isBadRequest());

        List<Odmatrix> odmatrixList = odmatrixRepository.findAll();
        assertThat(odmatrixList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOdmatrices() throws Exception {
        // Initialize the database
        odmatrixRepository.saveAndFlush(odmatrix);

        // Get all the odmatrixList
        restOdmatrixMockMvc.perform(get("/api/odmatrices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(odmatrix.getId().intValue())))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getOdmatrix() throws Exception {
        // Initialize the database
        odmatrixRepository.saveAndFlush(odmatrix);

        // Get the odmatrix
        restOdmatrixMockMvc.perform(get("/api/odmatrices/{id}", odmatrix.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(odmatrix.getId().intValue()))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOdmatrix() throws Exception {
        // Get the odmatrix
        restOdmatrixMockMvc.perform(get("/api/odmatrices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOdmatrix() throws Exception {
        // Initialize the database
        odmatrixService.save(odmatrix);

        int databaseSizeBeforeUpdate = odmatrixRepository.findAll().size();

        // Update the odmatrix
        Odmatrix updatedOdmatrix = odmatrixRepository.findById(odmatrix.getId()).get();
        // Disconnect from session so that the updates on updatedOdmatrix are not directly saved in db
        em.detach(updatedOdmatrix);
        updatedOdmatrix
            .cost(UPDATED_COST);

        restOdmatrixMockMvc.perform(put("/api/odmatrices").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOdmatrix)))
            .andExpect(status().isOk());

        // Validate the Odmatrix in the database
        List<Odmatrix> odmatrixList = odmatrixRepository.findAll();
        assertThat(odmatrixList).hasSize(databaseSizeBeforeUpdate);
        Odmatrix testOdmatrix = odmatrixList.get(odmatrixList.size() - 1);
        assertThat(testOdmatrix.getCost()).isEqualTo(UPDATED_COST);
    }

    @Test
    @Transactional
    public void updateNonExistingOdmatrix() throws Exception {
        int databaseSizeBeforeUpdate = odmatrixRepository.findAll().size();

        // Create the Odmatrix

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOdmatrixMockMvc.perform(put("/api/odmatrices").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(odmatrix)))
            .andExpect(status().isBadRequest());

        // Validate the Odmatrix in the database
        List<Odmatrix> odmatrixList = odmatrixRepository.findAll();
        assertThat(odmatrixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOdmatrix() throws Exception {
        // Initialize the database
        odmatrixService.save(odmatrix);

        int databaseSizeBeforeDelete = odmatrixRepository.findAll().size();

        // Delete the odmatrix
        restOdmatrixMockMvc.perform(delete("/api/odmatrices/{id}", odmatrix.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Odmatrix> odmatrixList = odmatrixRepository.findAll();
        assertThat(odmatrixList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
