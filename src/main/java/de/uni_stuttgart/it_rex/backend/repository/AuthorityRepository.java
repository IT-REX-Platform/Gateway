package de.uni_stuttgart.it_rex.backend.repository;

import de.uni_stuttgart.it_rex.backend.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
