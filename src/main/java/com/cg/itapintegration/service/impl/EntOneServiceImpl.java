package com.cg.itapintegration.service.impl;

import com.cg.itapintegration.service.EntOneService;
import com.cg.itapintegration.domain.EntOne;
import com.cg.itapintegration.repository.EntOneRepository;
import com.cg.itapintegration.service.dto.EntOneDTO;
import com.cg.itapintegration.service.mapper.EntOneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EntOne}.
 */
@Service
@Transactional
public class EntOneServiceImpl implements EntOneService {

    private final Logger log = LoggerFactory.getLogger(EntOneServiceImpl.class);

    private final EntOneRepository entOneRepository;

    private final EntOneMapper entOneMapper;

    public EntOneServiceImpl(EntOneRepository entOneRepository, EntOneMapper entOneMapper) {
        this.entOneRepository = entOneRepository;
        this.entOneMapper = entOneMapper;
    }

    /**
     * Save a entOne.
     *
     * @param entOneDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EntOneDTO save(EntOneDTO entOneDTO) {
        log.debug("Request to save EntOne : {}", entOneDTO);
        EntOne entOne = entOneMapper.toEntity(entOneDTO);
        entOne = entOneRepository.save(entOne);
        return entOneMapper.toDto(entOne);
    }

    /**
     * Get all the entOnes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntOneDTO> findAll() {
        log.debug("Request to get all EntOnes");
        return entOneRepository.findAll().stream()
            .map(entOneMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one entOne by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EntOneDTO> findOne(Long id) {
        log.debug("Request to get EntOne : {}", id);
        return entOneRepository.findById(id)
            .map(entOneMapper::toDto);
    }

    /**
     * Delete the entOne by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntOne : {}", id);
        entOneRepository.deleteById(id);
    }
}
