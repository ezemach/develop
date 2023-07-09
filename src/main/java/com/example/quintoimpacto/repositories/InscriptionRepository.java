package com.example.quintoimpacto.repositories;

import com.example.quintoimpacto.models.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
}