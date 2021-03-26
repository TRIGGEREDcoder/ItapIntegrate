package com.cg.itapintegration.repository;

import com.cg.itapintegration.domain.EntOne;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EntOne entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntOneRepository extends JpaRepository<EntOne, Long>, JpaSpecificationExecutor<EntOne> {
}
