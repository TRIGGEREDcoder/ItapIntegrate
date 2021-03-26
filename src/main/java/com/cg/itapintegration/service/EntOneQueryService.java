package com.cg.itapintegration.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.cg.itapintegration.domain.EntOne;
import com.cg.itapintegration.domain.*; // for static metamodels
import com.cg.itapintegration.repository.EntOneRepository;
import com.cg.itapintegration.service.dto.EntOneCriteria;
import com.cg.itapintegration.service.dto.EntOneDTO;
import com.cg.itapintegration.service.mapper.EntOneMapper;

/**
 * Service for executing complex queries for {@link EntOne} entities in the database.
 * The main input is a {@link EntOneCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EntOneDTO} or a {@link Page} of {@link EntOneDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EntOneQueryService extends QueryService<EntOne> {

    private final Logger log = LoggerFactory.getLogger(EntOneQueryService.class);

    private final EntOneRepository entOneRepository;

    private final EntOneMapper entOneMapper;

    public EntOneQueryService(EntOneRepository entOneRepository, EntOneMapper entOneMapper) {
        this.entOneRepository = entOneRepository;
        this.entOneMapper = entOneMapper;
    }

    /**
     * Return a {@link List} of {@link EntOneDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EntOneDTO> findByCriteria(EntOneCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EntOne> specification = createSpecification(criteria);
        return entOneMapper.toDto(entOneRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EntOneDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EntOneDTO> findByCriteria(EntOneCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EntOne> specification = createSpecification(criteria);
        return entOneRepository.findAll(specification, page)
            .map(entOneMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EntOneCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EntOne> specification = createSpecification(criteria);
        return entOneRepository.count(specification);
    }

    /**
     * Function to convert {@link EntOneCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EntOne> createSpecification(EntOneCriteria criteria) {
        Specification<EntOne> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EntOne_.id));
            }
            if (criteria.getFieldOne() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFieldOne(), EntOne_.fieldOne));
            }
        }
        return specification;
    }
}
