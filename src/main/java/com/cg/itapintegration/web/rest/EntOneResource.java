package com.cg.itapintegration.web.rest;

import com.cg.itapintegration.service.EntOneService;
import com.cg.itapintegration.web.rest.errors.BadRequestAlertException;
import com.cg.itapintegration.service.dto.EntOneDTO;
import com.cg.itapintegration.service.dto.EntOneCriteria;
import com.cg.itapintegration.service.EntOneQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.cg.itapintegration.domain.EntOne}.
 */
@RestController
@RequestMapping("/api")
public class EntOneResource {

    private final Logger log = LoggerFactory.getLogger(EntOneResource.class);

    private static final String ENTITY_NAME = "entOne";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntOneService entOneService;

    private final EntOneQueryService entOneQueryService;

    public EntOneResource(EntOneService entOneService, EntOneQueryService entOneQueryService) {
        this.entOneService = entOneService;
        this.entOneQueryService = entOneQueryService;
    }

    /**
     * {@code POST  /ent-ones} : Create a new entOne.
     *
     * @param entOneDTO the entOneDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entOneDTO, or with status {@code 400 (Bad Request)} if the entOne has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ent-ones")
    public ResponseEntity<EntOneDTO> createEntOne(@RequestBody EntOneDTO entOneDTO) throws URISyntaxException {
        log.debug("REST request to save EntOne : {}", entOneDTO);
        if (entOneDTO.getId() != null) {
            throw new BadRequestAlertException("A new entOne cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntOneDTO result = entOneService.save(entOneDTO);
        return ResponseEntity.created(new URI("/api/ent-ones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ent-ones} : Updates an existing entOne.
     *
     * @param entOneDTO the entOneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entOneDTO,
     * or with status {@code 400 (Bad Request)} if the entOneDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entOneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ent-ones")
    public ResponseEntity<EntOneDTO> updateEntOne(@RequestBody EntOneDTO entOneDTO) throws URISyntaxException {
        log.debug("REST request to update EntOne : {}", entOneDTO);
        if (entOneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntOneDTO result = entOneService.save(entOneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, entOneDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ent-ones} : get all the entOnes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entOnes in body.
     */
    @GetMapping("/ent-ones")
    public ResponseEntity<List<EntOneDTO>> getAllEntOnes(EntOneCriteria criteria) {
        log.debug("REST request to get EntOnes by criteria: {}", criteria);
        List<EntOneDTO> entityList = entOneQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /ent-ones/count} : count all the entOnes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ent-ones/count")
    public ResponseEntity<Long> countEntOnes(EntOneCriteria criteria) {
        log.debug("REST request to count EntOnes by criteria: {}", criteria);
        return ResponseEntity.ok().body(entOneQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ent-ones/:id} : get the "id" entOne.
     *
     * @param id the id of the entOneDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entOneDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ent-ones/{id}")
    public ResponseEntity<EntOneDTO> getEntOne(@PathVariable Long id) {
        log.debug("REST request to get EntOne : {}", id);
        Optional<EntOneDTO> entOneDTO = entOneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entOneDTO);
    }

    /**
     * {@code DELETE  /ent-ones/:id} : delete the "id" entOne.
     *
     * @param id the id of the entOneDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ent-ones/{id}")
    public ResponseEntity<Void> deleteEntOne(@PathVariable Long id) {
        log.debug("REST request to delete EntOne : {}", id);
        entOneService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
