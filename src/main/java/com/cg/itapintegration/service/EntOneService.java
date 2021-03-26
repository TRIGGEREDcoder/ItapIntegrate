package com.cg.itapintegration.service;

import com.cg.itapintegration.service.dto.EntOneDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cg.itapintegration.domain.EntOne}.
 */
public interface EntOneService {

    /**
     * Save a entOne.
     *
     * @param entOneDTO the entity to save.
     * @return the persisted entity.
     */
    EntOneDTO save(EntOneDTO entOneDTO);

    /**
     * Get all the entOnes.
     *
     * @return the list of entities.
     */
    List<EntOneDTO> findAll();

    /**
     * Get the "id" entOne.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntOneDTO> findOne(Long id);

    /**
     * Delete the "id" entOne.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
