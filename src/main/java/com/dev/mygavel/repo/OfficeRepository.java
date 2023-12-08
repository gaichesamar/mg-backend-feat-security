package com.dev.mygavel.repo;

import com.dev.mygavel.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface OfficeRepository extends JpaRepository<Office, Long> {

    Optional<Office> findById(Long id);

    Office getById(Long id);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    List<Office> findByDateSuppNull();
}
