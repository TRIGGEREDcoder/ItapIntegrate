package com.cg.itapintegration.web.rest;

import com.cg.itapintegration.ItapIntegrationApp;
import com.cg.itapintegration.domain.EntOne;
import com.cg.itapintegration.repository.EntOneRepository;
import com.cg.itapintegration.service.EntOneService;
import com.cg.itapintegration.service.dto.EntOneDTO;
import com.cg.itapintegration.service.mapper.EntOneMapper;
import com.cg.itapintegration.service.dto.EntOneCriteria;
import com.cg.itapintegration.service.EntOneQueryService;

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
 * Integration tests for the {@link EntOneResource} REST controller.
 */
@SpringBootTest(classes = ItapIntegrationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class EntOneResourceIT {

    private static final String DEFAULT_FIELD_ONE = "AAAAAAAAAA";
    private static final String UPDATED_FIELD_ONE = "BBBBBBBBBB";

    @Autowired
    private EntOneRepository entOneRepository;

    @Autowired
    private EntOneMapper entOneMapper;

    @Autowired
    private EntOneService entOneService;

    @Autowired
    private EntOneQueryService entOneQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntOneMockMvc;

    private EntOne entOne;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntOne createEntity(EntityManager em) {
        EntOne entOne = new EntOne();
        entOne.setFieldOne(DEFAULT_FIELD_ONE);
        return entOne;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntOne createUpdatedEntity(EntityManager em) {
        EntOne entOne = new EntOne();
        entOne.setFieldOne(UPDATED_FIELD_ONE);
        return entOne;
    }

    @BeforeEach
    public void initTest() {
        entOne = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntOne() throws Exception {
        int databaseSizeBeforeCreate = entOneRepository.findAll().size();

        // Create the EntOne
        EntOneDTO entOneDTO = entOneMapper.toDto(entOne);
        restEntOneMockMvc.perform(post("/api/ent-ones").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entOneDTO)))
            .andExpect(status().isCreated());

        // Validate the EntOne in the database
        List<EntOne> entOneList = entOneRepository.findAll();
        assertThat(entOneList).hasSize(databaseSizeBeforeCreate + 1);
        EntOne testEntOne = entOneList.get(entOneList.size() - 1);
        assertThat(testEntOne.getFieldOne()).isEqualTo(DEFAULT_FIELD_ONE);
    }

    @Test
    @Transactional
    public void createEntOneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entOneRepository.findAll().size();

        // Create the EntOne with an existing ID
        entOne.setId(1L);
        EntOneDTO entOneDTO = entOneMapper.toDto(entOne);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntOneMockMvc.perform(post("/api/ent-ones").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entOneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntOne in the database
        List<EntOne> entOneList = entOneRepository.findAll();
        assertThat(entOneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEntOnes() throws Exception {
        // Initialize the database
        entOneRepository.saveAndFlush(entOne);

        // Get all the entOneList
        restEntOneMockMvc.perform(get("/api/ent-ones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entOne.getId().intValue())))
            .andExpect(jsonPath("$.[*].fieldOne").value(hasItem(DEFAULT_FIELD_ONE)));
    }
    
    @Test
    @Transactional
    public void getEntOne() throws Exception {
        // Initialize the database
        entOneRepository.saveAndFlush(entOne);

        // Get the entOne
        restEntOneMockMvc.perform(get("/api/ent-ones/{id}", entOne.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entOne.getId().intValue()))
            .andExpect(jsonPath("$.fieldOne").value(DEFAULT_FIELD_ONE));
    }


    @Test
    @Transactional
    public void getEntOnesByIdFiltering() throws Exception {
        // Initialize the database
        entOneRepository.saveAndFlush(entOne);

        Long id = entOne.getId();

        defaultEntOneShouldBeFound("id.equals=" + id);
        defaultEntOneShouldNotBeFound("id.notEquals=" + id);

        defaultEntOneShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultEntOneShouldNotBeFound("id.greaterThan=" + id);

        defaultEntOneShouldBeFound("id.lessThanOrEqual=" + id);
        defaultEntOneShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllEntOnesByFieldOneIsEqualToSomething() throws Exception {
        // Initialize the database
        entOneRepository.saveAndFlush(entOne);

        // Get all the entOneList where fieldOne equals to DEFAULT_FIELD_ONE
        defaultEntOneShouldBeFound("fieldOne.equals=" + DEFAULT_FIELD_ONE);

        // Get all the entOneList where fieldOne equals to UPDATED_FIELD_ONE
        defaultEntOneShouldNotBeFound("fieldOne.equals=" + UPDATED_FIELD_ONE);
    }

    @Test
    @Transactional
    public void getAllEntOnesByFieldOneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        entOneRepository.saveAndFlush(entOne);

        // Get all the entOneList where fieldOne not equals to DEFAULT_FIELD_ONE
        defaultEntOneShouldNotBeFound("fieldOne.notEquals=" + DEFAULT_FIELD_ONE);

        // Get all the entOneList where fieldOne not equals to UPDATED_FIELD_ONE
        defaultEntOneShouldBeFound("fieldOne.notEquals=" + UPDATED_FIELD_ONE);
    }

    @Test
    @Transactional
    public void getAllEntOnesByFieldOneIsInShouldWork() throws Exception {
        // Initialize the database
        entOneRepository.saveAndFlush(entOne);

        // Get all the entOneList where fieldOne in DEFAULT_FIELD_ONE or UPDATED_FIELD_ONE
        defaultEntOneShouldBeFound("fieldOne.in=" + DEFAULT_FIELD_ONE + "," + UPDATED_FIELD_ONE);

        // Get all the entOneList where fieldOne equals to UPDATED_FIELD_ONE
        defaultEntOneShouldNotBeFound("fieldOne.in=" + UPDATED_FIELD_ONE);
    }

    @Test
    @Transactional
    public void getAllEntOnesByFieldOneIsNullOrNotNull() throws Exception {
        // Initialize the database
        entOneRepository.saveAndFlush(entOne);

        // Get all the entOneList where fieldOne is not null
        defaultEntOneShouldBeFound("fieldOne.specified=true");

        // Get all the entOneList where fieldOne is null
        defaultEntOneShouldNotBeFound("fieldOne.specified=false");
    }
                @Test
    @Transactional
    public void getAllEntOnesByFieldOneContainsSomething() throws Exception {
        // Initialize the database
        entOneRepository.saveAndFlush(entOne);

        // Get all the entOneList where fieldOne contains DEFAULT_FIELD_ONE
        defaultEntOneShouldBeFound("fieldOne.contains=" + DEFAULT_FIELD_ONE);

        // Get all the entOneList where fieldOne contains UPDATED_FIELD_ONE
        defaultEntOneShouldNotBeFound("fieldOne.contains=" + UPDATED_FIELD_ONE);
    }

    @Test
    @Transactional
    public void getAllEntOnesByFieldOneNotContainsSomething() throws Exception {
        // Initialize the database
        entOneRepository.saveAndFlush(entOne);

        // Get all the entOneList where fieldOne does not contain DEFAULT_FIELD_ONE
        defaultEntOneShouldNotBeFound("fieldOne.doesNotContain=" + DEFAULT_FIELD_ONE);

        // Get all the entOneList where fieldOne does not contain UPDATED_FIELD_ONE
        defaultEntOneShouldBeFound("fieldOne.doesNotContain=" + UPDATED_FIELD_ONE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEntOneShouldBeFound(String filter) throws Exception {
        restEntOneMockMvc.perform(get("/api/ent-ones?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entOne.getId().intValue())))
            .andExpect(jsonPath("$.[*].fieldOne").value(hasItem(DEFAULT_FIELD_ONE)));

        // Check, that the count call also returns 1
        restEntOneMockMvc.perform(get("/api/ent-ones/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEntOneShouldNotBeFound(String filter) throws Exception {
        restEntOneMockMvc.perform(get("/api/ent-ones?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEntOneMockMvc.perform(get("/api/ent-ones/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEntOne() throws Exception {
        // Get the entOne
        restEntOneMockMvc.perform(get("/api/ent-ones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntOne() throws Exception {
        // Initialize the database
        entOneRepository.saveAndFlush(entOne);

        int databaseSizeBeforeUpdate = entOneRepository.findAll().size();

        // Update the entOne
        EntOne updatedEntOne = entOneRepository.findById(entOne.getId()).get();
        // Disconnect from session so that the updates on updatedEntOne are not directly saved in db
        em.detach(updatedEntOne);
        updatedEntOne.setFieldOne(UPDATED_FIELD_ONE);
        EntOneDTO entOneDTO = entOneMapper.toDto(updatedEntOne);

        restEntOneMockMvc.perform(put("/api/ent-ones").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entOneDTO)))
            .andExpect(status().isOk());

        // Validate the EntOne in the database
        List<EntOne> entOneList = entOneRepository.findAll();
        assertThat(entOneList).hasSize(databaseSizeBeforeUpdate);
        EntOne testEntOne = entOneList.get(entOneList.size() - 1);
        assertThat(testEntOne.getFieldOne()).isEqualTo(UPDATED_FIELD_ONE);
    }

    @Test
    @Transactional
    public void updateNonExistingEntOne() throws Exception {
        int databaseSizeBeforeUpdate = entOneRepository.findAll().size();

        // Create the EntOne
        EntOneDTO entOneDTO = entOneMapper.toDto(entOne);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntOneMockMvc.perform(put("/api/ent-ones").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entOneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntOne in the database
        List<EntOne> entOneList = entOneRepository.findAll();
        assertThat(entOneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntOne() throws Exception {
        // Initialize the database
        entOneRepository.saveAndFlush(entOne);

        int databaseSizeBeforeDelete = entOneRepository.findAll().size();

        // Delete the entOne
        restEntOneMockMvc.perform(delete("/api/ent-ones/{id}", entOne.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EntOne> entOneList = entOneRepository.findAll();
        assertThat(entOneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
