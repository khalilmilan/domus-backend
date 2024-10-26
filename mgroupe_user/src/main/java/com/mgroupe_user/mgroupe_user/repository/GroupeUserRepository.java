package com.mgroupe_user.mgroupe_user.repository;

import com.mgroupe_user.mgroupe_user.model.GroupeUser;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupeUserRepository extends JpaRepository<GroupeUser,Long> {

    List<GroupeUser> findGroupeUserByIdGroupe(Long idGroupe);
    GroupeUser findGroupeUserByIdGroupeAndIdUser(Long idGroupe,Long idUser);

    @Query("SELECT u.idUser FROM GroupeUser u WHERE u.idGroupe = :idGroupe")
    List<Long> findUsersInEvent(@Param("idGroupe") Long idGroupe);

    @Query("SELECT u.idGroupe FROM GroupeUser u WHERE u.idUser = :idUser")
    List<Long> findGroupeByMembre(@Param("idUSer") Long idUser);


}
