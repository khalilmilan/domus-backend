package com.groupe.mgroupe.repository;

import com.groupe.mgroupe.model.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupeRepository extends JpaRepository<Groupe,Long> {

    List<Groupe> findGroupesByIdUser(Long idUser);
}
