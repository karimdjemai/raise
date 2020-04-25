package com.raise.web.rest;

import com.raise.RaiseApp;
import com.raise.domain.RunInstance;
import com.raise.repository.RunInstanceRepository;
import com.raise.service.RunInstanceService;

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
 * Integration tests for the {@link RunInstanceResource} REST controller.
 */
@SpringBootTest(classes = RaiseApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class RunInstanceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private RunInstanceRepository runInstanceRepository;

    @Autowired
    private RunInstanceService runInstanceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRunInstanceMockMvc;

    private RunInstance runInstance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RunInstance createEntity(EntityManager em) {
        RunInstance runInstance = new RunInstance()
            .name(DEFAULT_NAME);
        return runInstance;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RunInstance createUpdatedEntity(EntityManager em) {
        RunInstance runInstance = new RunInstance()
            .name(UPDATED_NAME);
        return runInstance;
    }

    @BeforeEach
    public void initTest() {
        runInstance = createEntity(em);
    }

    @Test
    @Transactional
    public void createRunInstance() throws Exception {
        int databaseSizeBeforeCreate = runInstanceRepository.findAll().size();

        // Create the RunInstance
        restRunInstanceMockMvc.perform(post("/api/run-instances").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(runInstance)))
            .andExpect(status().isCreated());

        // Validate the RunInstance in the database
        List<RunInstance> runInstanceList = runInstanceRepository.findAll();
        assertThat(runInstanceList).hasSize(databaseSizeBeforeCreate + 1);
        RunInstance testRunInstance = runInstanceList.get(runInstanceList.size() - 1);
        assertThat(testRunInstance.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createRunInstanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = runInstanceRepository.findAll().size();

        // Create the RunInstance with an existing ID
        runInstance.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRunInstanceMockMvc.perform(post("/api/run-instances").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(runInstance)))
            .andExpect(status().isBadRequest());

        // Validate the RunInstance in the database
        List<RunInstance> runInstanceList = runInstanceRepository.findAll();
        assertThat(runInstanceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = runInstanceRepository.findAll().size();
        // set the field null
        runInstance.setName(null);

        // Create the RunInstance, which fails.

        restRunInstanceMockMvc.perform(post("/api/run-instances").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(runInstance)))
            .andExpect(status().isBadRequest());

        List<RunInstance> runInstanceList = runInstanceRepository.findAll();
        assertThat(runInstanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRunInstances() throws Exception {
        // Initialize the database
        runInstanceRepository.saveAndFlush(runInstance);

        // Get all the runInstanceList
        restRunInstanceMockMvc.perform(get("/api/run-instances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(runInstance.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getRunInstance() throws Exception {
        // Initialize the database
        runInstanceRepository.saveAndFlush(runInstance);

        // Get the runInstance
        restRunInstanceMockMvc.perform(get("/api/run-instances/{id}", runInstance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(runInstance.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingRunInstance() throws Exception {
        // Get the runInstance
        restRunInstanceMockMvc.perform(get("/api/run-instances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRunInstance() throws Exception {
        // Initialize the database
        runInstanceService.save(runInstance);

        int databaseSizeBeforeUpdate = runInstanceRepository.findAll().size();

        // Update the runInstance
        RunInstance updatedRunInstance = runInstanceRepository.findById(runInstance.getId()).get();
        // Disconnect from session so that the updates on updatedRunInstance are not directly saved in db
        em.detach(updatedRunInstance);
        updatedRunInstance
            .name(UPDATED_NAME);

        restRunInstanceMockMvc.perform(put("/api/run-instances").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRunInstance)))
            .andExpect(status().isOk());

        // Validate the RunInstance in the database
        List<RunInstance> runInstanceList = runInstanceRepository.findAll();
        assertThat(runInstanceList).hasSize(databaseSizeBeforeUpdate);
        RunInstance testRunInstance = runInstanceList.get(runInstanceList.size() - 1);
        assertThat(testRunInstance.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingRunInstance() throws Exception {
        int databaseSizeBeforeUpdate = runInstanceRepository.findAll().size();

        // Create the RunInstance

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRunInstanceMockMvc.perform(put("/api/run-instances").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(runInstance)))
            .andExpect(status().isBadRequest());

        // Validate the RunInstance in the database
        List<RunInstance> runInstanceList = runInstanceRepository.findAll();
        assertThat(runInstanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRunInstance() throws Exception {
        // Initialize the database
        runInstanceService.save(runInstance);

        int databaseSizeBeforeDelete = runInstanceRepository.findAll().size();

        // Delete the runInstance
        restRunInstanceMockMvc.perform(delete("/api/run-instances/{id}", runInstance.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RunInstance> runInstanceList = runInstanceRepository.findAll();
        assertThat(runInstanceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
