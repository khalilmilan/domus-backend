package com.mgroupe_user.mgroupe_user.repository;

import com.mgroupe_user.mgroupe_user.model.GroupeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupeUserRepository extends JpaRepository<GroupeUser,Long> {

    List<GroupeUser> findGroupeUserByIdGroupe(Long idGroupe);
    GroupeUser findGroupeUserByIdGroupeAndIdUser(Long idGroupe,Long idUser);
}
