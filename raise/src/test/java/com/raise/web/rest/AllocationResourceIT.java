package com.raise.web.rest;

import com.raise.RaiseApp;
import com.raise.domain.Allocation;
import com.raise.repository.AllocationRepository;
import com.raise.service.AllocationService;

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
 * Integration tests for the {@link AllocationResource} REST controller.
 */
@SpringBootTest(classes = RaiseApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class AllocationResourceIT {

    private static final Double DEFAULT_AMOUNT = 0D;
    private static final Double UPDATED_AMOUNT = 1D;

    private static final Double DEFAULT_COST = 0D;
    private static final Double UPDATED_COST = 1D;

    @Autowired
    private AllocationRepository allocationRepository;

    @Autowired
    private AllocationService allocationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAllocationMockMvc;

    private Allocation allocation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Allocation createEntity(EntityManager em) {
        Allocation allocation = new Allocation()
            .amount(DEFAULT_AMOUNT)
            .cost(DEFAULT_COST);
        return allocation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Allocation createUpdatedEntity(EntityManager em) {
        Allocation allocation = new Allocation()
            .amount(UPDATED_AMOUNT)
            .cost(UPDATED_COST);
        return allocation;
    }

    @BeforeEach
    public void initTest() {
        allocation = createEntity(em);
    }

    @Test
    @Transactional
    public void createAllocation() throws Exception {
        int databaseSizeBeforeCreate = allocationRepository.findAll().size();

        // Create the Allocation
        restAllocationMockMvc.perform(post("/api/allocations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allocation)))
            .andExpect(status().isCreated());

        // Validate the Allocation in the database
        List<Allocation> allocationList = allocationRepository.findAll();
        assertThat(allocationList).hasSize(databaseSizeBeforeCreate + 1);
        Allocation testAllocation = allocationList.get(allocationList.size() - 1);
        assertThat(testAllocation.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testAllocation.getCost()).isEqualTo(DEFAULT_COST);
    }

    @Test
    @Transactional
    public void createAllocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = allocationRepository.findAll().size();

        // Create the Allocation with an existing ID
        allocation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAllocationMockMvc.perform(post("/api/allocations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allocation)))
            .andExpect(status().isBadRequest());

        // Validate the Allocation in the database
        List<Allocation> allocationList = allocationRepository.findAll();
        assertThat(allocationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = allocationRepository.findAll().size();
        // set the field null
        allocation.setAmount(null);

        // Create the Allocation, which fails.

        restAllocationMockMvc.perform(post("/api/allocations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allocation)))
            .andExpect(status().isBadRequest());

        List<Allocation> allocationList = allocationRepository.findAll();
        assertThat(allocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCostIsRequired() throws Exception {
        int databaseSizeBeforeTest = allocationRepository.findAll().size();
        // set the field null
        allocation.setCost(null);

        // Create the Allocation, which fails.

        restAllocationMockMvc.perform(post("/api/allocations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allocation)))
            .andExpect(status().isBadRequest());

        List<Allocation> allocationList = allocationRepository.findAll();
        assertThat(allocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAllocations() throws Exception {
        // Initialize the database
        allocationRepository.saveAndFlush(allocation);

        // Get all the allocationList
        restAllocationMockMvc.perform(get("/api/allocations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(allocation.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getAllocation() throws Exception {
        // Initialize the database
        allocationRepository.saveAndFlush(allocation);

        // Get the allocation
        restAllocationMockMvc.perform(get("/api/allocations/{id}", allocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(allocation.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAllocation() throws Exception {
        // Get the allocation
        restAllocationMockMvc.perform(get("/api/allocations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAllocation() throws Exception {
        // Initialize the database
        allocationService.save(allocation);

        int databaseSizeBeforeUpdate = allocationRepository.findAll().size();

        // Update the allocation
        Allocation updatedAllocation = allocationRepository.findById(allocation.getId()).get();
        // Disconnect from session so that the updates on updatedAllocation are not directly saved in db
        em.detach(updatedAllocation);
        updatedAllocation
            .amount(UPDATED_AMOUNT)
            .cost(UPDATED_COST);

        restAllocationMockMvc.perform(put("/api/allocations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAllocation)))
            .andExpect(status().isOk());

        // Validate the Allocation in the database
        List<Allocation> allocationList = allocationRepository.findAll();
        assertThat(allocationList).hasSize(databaseSizeBeforeUpdate);
        Allocation testAllocation = allocationList.get(allocationList.size() - 1);
        assertThat(testAllocation.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testAllocation.getCost()).isEqualTo(UPDATED_COST);
    }

    @Test
    @Transactional
    public void updateNonExistingAllocation() throws Exception {
        int databaseSizeBeforeUpdate = allocationRepository.findAll().size();

        // Create the Allocation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAllocationMockMvc.perform(put("/api/allocations").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allocation)))
            .andExpect(status().isBadRequest());

        // Validate the Allocation in the database
        List<Allocation> allocationList = allocationRepository.findAll();
        assertThat(allocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAllocation() throws Exception {
        // Initialize the database
        allocationService.save(allocation);

        int databaseSizeBeforeDelete = allocationRepository.findAll().size();

        // Delete the allocation
        restAllocationMockMvc.perform(delete("/api/allocations/{id}", allocation.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Allocation> allocationList = allocationRepository.findAll();
        assertThat(allocationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
