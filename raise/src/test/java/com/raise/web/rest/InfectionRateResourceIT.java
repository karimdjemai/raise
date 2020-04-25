package com.raise.web.rest;

import com.raise.RaiseApp;
import com.raise.domain.InfectionRate;
import com.raise.repository.InfectionRateRepository;
import com.raise.service.InfectionRateService;

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
 * Integration tests for the {@link InfectionRateResource} REST controller.
 */
@SpringBootTest(classes = RaiseApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class InfectionRateResourceIT {

    private static final Double DEFAULT_AMOUNT = 0D;
    private static final Double UPDATED_AMOUNT = 1D;

    private static final Long DEFAULT_POPULATION = 0L;
    private static final Long UPDATED_POPULATION = 1L;

    @Autowired
    private InfectionRateRepository infectionRateRepository;

    @Autowired
    private InfectionRateService infectionRateService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInfectionRateMockMvc;

    private InfectionRate infectionRate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InfectionRate createEntity(EntityManager em) {
        InfectionRate infectionRate = new InfectionRate()
            .amount(DEFAULT_AMOUNT)
            .population(DEFAULT_POPULATION);
        return infectionRate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InfectionRate createUpdatedEntity(EntityManager em) {
        InfectionRate infectionRate = new InfectionRate()
            .amount(UPDATED_AMOUNT)
            .population(UPDATED_POPULATION);
        return infectionRate;
    }

    @BeforeEach
    public void initTest() {
        infectionRate = createEntity(em);
    }

    @Test
    @Transactional
    public void createInfectionRate() throws Exception {
        int databaseSizeBeforeCreate = infectionRateRepository.findAll().size();

        // Create the InfectionRate
        restInfectionRateMockMvc.perform(post("/api/infection-rates").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infectionRate)))
            .andExpect(status().isCreated());

        // Validate the InfectionRate in the database
        List<InfectionRate> infectionRateList = infectionRateRepository.findAll();
        assertThat(infectionRateList).hasSize(databaseSizeBeforeCreate + 1);
        InfectionRate testInfectionRate = infectionRateList.get(infectionRateList.size() - 1);
        assertThat(testInfectionRate.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testInfectionRate.getPopulation()).isEqualTo(DEFAULT_POPULATION);
    }

    @Test
    @Transactional
    public void createInfectionRateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = infectionRateRepository.findAll().size();

        // Create the InfectionRate with an existing ID
        infectionRate.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInfectionRateMockMvc.perform(post("/api/infection-rates").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infectionRate)))
            .andExpect(status().isBadRequest());

        // Validate the InfectionRate in the database
        List<InfectionRate> infectionRateList = infectionRateRepository.findAll();
        assertThat(infectionRateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = infectionRateRepository.findAll().size();
        // set the field null
        infectionRate.setAmount(null);

        // Create the InfectionRate, which fails.

        restInfectionRateMockMvc.perform(post("/api/infection-rates").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infectionRate)))
            .andExpect(status().isBadRequest());

        List<InfectionRate> infectionRateList = infectionRateRepository.findAll();
        assertThat(infectionRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPopulationIsRequired() throws Exception {
        int databaseSizeBeforeTest = infectionRateRepository.findAll().size();
        // set the field null
        infectionRate.setPopulation(null);

        // Create the InfectionRate, which fails.

        restInfectionRateMockMvc.perform(post("/api/infection-rates").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infectionRate)))
            .andExpect(status().isBadRequest());

        List<InfectionRate> infectionRateList = infectionRateRepository.findAll();
        assertThat(infectionRateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInfectionRates() throws Exception {
        // Initialize the database
        infectionRateRepository.saveAndFlush(infectionRate);

        // Get all the infectionRateList
        restInfectionRateMockMvc.perform(get("/api/infection-rates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(infectionRate.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].population").value(hasItem(DEFAULT_POPULATION.intValue())));
    }
    
    @Test
    @Transactional
    public void getInfectionRate() throws Exception {
        // Initialize the database
        infectionRateRepository.saveAndFlush(infectionRate);

        // Get the infectionRate
        restInfectionRateMockMvc.perform(get("/api/infection-rates/{id}", infectionRate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(infectionRate.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.population").value(DEFAULT_POPULATION.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInfectionRate() throws Exception {
        // Get the infectionRate
        restInfectionRateMockMvc.perform(get("/api/infection-rates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInfectionRate() throws Exception {
        // Initialize the database
        infectionRateService.save(infectionRate);

        int databaseSizeBeforeUpdate = infectionRateRepository.findAll().size();

        // Update the infectionRate
        InfectionRate updatedInfectionRate = infectionRateRepository.findById(infectionRate.getId()).get();
        // Disconnect from session so that the updates on updatedInfectionRate are not directly saved in db
        em.detach(updatedInfectionRate);
        updatedInfectionRate
            .amount(UPDATED_AMOUNT)
            .population(UPDATED_POPULATION);

        restInfectionRateMockMvc.perform(put("/api/infection-rates").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInfectionRate)))
            .andExpect(status().isOk());

        // Validate the InfectionRate in the database
        List<InfectionRate> infectionRateList = infectionRateRepository.findAll();
        assertThat(infectionRateList).hasSize(databaseSizeBeforeUpdate);
        InfectionRate testInfectionRate = infectionRateList.get(infectionRateList.size() - 1);
        assertThat(testInfectionRate.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testInfectionRate.getPopulation()).isEqualTo(UPDATED_POPULATION);
    }

    @Test
    @Transactional
    public void updateNonExistingInfectionRate() throws Exception {
        int databaseSizeBeforeUpdate = infectionRateRepository.findAll().size();

        // Create the InfectionRate

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInfectionRateMockMvc.perform(put("/api/infection-rates").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infectionRate)))
            .andExpect(status().isBadRequest());

        // Validate the InfectionRate in the database
        List<InfectionRate> infectionRateList = infectionRateRepository.findAll();
        assertThat(infectionRateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInfectionRate() throws Exception {
        // Initialize the database
        infectionRateService.save(infectionRate);

        int databaseSizeBeforeDelete = infectionRateRepository.findAll().size();

        // Delete the infectionRate
        restInfectionRateMockMvc.perform(delete("/api/infection-rates/{id}", infectionRate.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InfectionRate> infectionRateList = infectionRateRepository.findAll();
        assertThat(infectionRateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
