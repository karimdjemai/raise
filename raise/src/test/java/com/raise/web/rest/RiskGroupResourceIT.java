package com.raise.web.rest;

import com.raise.RaiseApp;
import com.raise.domain.RiskGroup;
import com.raise.repository.RiskGroupRepository;
import com.raise.service.RiskGroupService;

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
 * Integration tests for the {@link RiskGroupResource} REST controller.
 */
@SpringBootTest(classes = RaiseApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class RiskGroupResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_CATEGORY = 0;
    private static final Integer UPDATED_CATEGORY = 1;

    @Autowired
    private RiskGroupRepository riskGroupRepository;

    @Autowired
    private RiskGroupService riskGroupService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskGroupMockMvc;

    private RiskGroup riskGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskGroup createEntity(EntityManager em) {
        RiskGroup riskGroup = new RiskGroup()
            .name(DEFAULT_NAME)
            .category(DEFAULT_CATEGORY);
        return riskGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskGroup createUpdatedEntity(EntityManager em) {
        RiskGroup riskGroup = new RiskGroup()
            .name(UPDATED_NAME)
            .category(UPDATED_CATEGORY);
        return riskGroup;
    }

    @BeforeEach
    public void initTest() {
        riskGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createRiskGroup() throws Exception {
        int databaseSizeBeforeCreate = riskGroupRepository.findAll().size();

        // Create the RiskGroup
        restRiskGroupMockMvc.perform(post("/api/risk-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(riskGroup)))
            .andExpect(status().isCreated());

        // Validate the RiskGroup in the database
        List<RiskGroup> riskGroupList = riskGroupRepository.findAll();
        assertThat(riskGroupList).hasSize(databaseSizeBeforeCreate + 1);
        RiskGroup testRiskGroup = riskGroupList.get(riskGroupList.size() - 1);
        assertThat(testRiskGroup.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRiskGroup.getCategory()).isEqualTo(DEFAULT_CATEGORY);
    }

    @Test
    @Transactional
    public void createRiskGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = riskGroupRepository.findAll().size();

        // Create the RiskGroup with an existing ID
        riskGroup.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskGroupMockMvc.perform(post("/api/risk-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(riskGroup)))
            .andExpect(status().isBadRequest());

        // Validate the RiskGroup in the database
        List<RiskGroup> riskGroupList = riskGroupRepository.findAll();
        assertThat(riskGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = riskGroupRepository.findAll().size();
        // set the field null
        riskGroup.setName(null);

        // Create the RiskGroup, which fails.

        restRiskGroupMockMvc.perform(post("/api/risk-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(riskGroup)))
            .andExpect(status().isBadRequest());

        List<RiskGroup> riskGroupList = riskGroupRepository.findAll();
        assertThat(riskGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = riskGroupRepository.findAll().size();
        // set the field null
        riskGroup.setCategory(null);

        // Create the RiskGroup, which fails.

        restRiskGroupMockMvc.perform(post("/api/risk-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(riskGroup)))
            .andExpect(status().isBadRequest());

        List<RiskGroup> riskGroupList = riskGroupRepository.findAll();
        assertThat(riskGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRiskGroups() throws Exception {
        // Initialize the database
        riskGroupRepository.saveAndFlush(riskGroup);

        // Get all the riskGroupList
        restRiskGroupMockMvc.perform(get("/api/risk-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)));
    }
    
    @Test
    @Transactional
    public void getRiskGroup() throws Exception {
        // Initialize the database
        riskGroupRepository.saveAndFlush(riskGroup);

        // Get the riskGroup
        restRiskGroupMockMvc.perform(get("/api/risk-groups/{id}", riskGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskGroup.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY));
    }

    @Test
    @Transactional
    public void getNonExistingRiskGroup() throws Exception {
        // Get the riskGroup
        restRiskGroupMockMvc.perform(get("/api/risk-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRiskGroup() throws Exception {
        // Initialize the database
        riskGroupService.save(riskGroup);

        int databaseSizeBeforeUpdate = riskGroupRepository.findAll().size();

        // Update the riskGroup
        RiskGroup updatedRiskGroup = riskGroupRepository.findById(riskGroup.getId()).get();
        // Disconnect from session so that the updates on updatedRiskGroup are not directly saved in db
        em.detach(updatedRiskGroup);
        updatedRiskGroup
            .name(UPDATED_NAME)
            .category(UPDATED_CATEGORY);

        restRiskGroupMockMvc.perform(put("/api/risk-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRiskGroup)))
            .andExpect(status().isOk());

        // Validate the RiskGroup in the database
        List<RiskGroup> riskGroupList = riskGroupRepository.findAll();
        assertThat(riskGroupList).hasSize(databaseSizeBeforeUpdate);
        RiskGroup testRiskGroup = riskGroupList.get(riskGroupList.size() - 1);
        assertThat(testRiskGroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRiskGroup.getCategory()).isEqualTo(UPDATED_CATEGORY);
    }

    @Test
    @Transactional
    public void updateNonExistingRiskGroup() throws Exception {
        int databaseSizeBeforeUpdate = riskGroupRepository.findAll().size();

        // Create the RiskGroup

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskGroupMockMvc.perform(put("/api/risk-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(riskGroup)))
            .andExpect(status().isBadRequest());

        // Validate the RiskGroup in the database
        List<RiskGroup> riskGroupList = riskGroupRepository.findAll();
        assertThat(riskGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRiskGroup() throws Exception {
        // Initialize the database
        riskGroupService.save(riskGroup);

        int databaseSizeBeforeDelete = riskGroupRepository.findAll().size();

        // Delete the riskGroup
        restRiskGroupMockMvc.perform(delete("/api/risk-groups/{id}", riskGroup.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RiskGroup> riskGroupList = riskGroupRepository.findAll();
        assertThat(riskGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
