package com.mgroupe_user.mgroupe_user.mapper;

import com.mgroupe_user.mgroupe_user.dto.UserDTO;
import com.mgroupe_user.mgroupe_user.dto.GroupeUserDTO;
import com.mgroupe_user.mgroupe_user.model.GroupeUser;

public class GroupeUserMapper {

    public static GroupeUserDTO mapToGroupeUserDto(GroupeUser groupeUser){
        GroupeUserDTO groupedtp = new GroupeUserDTO(
                groupeUser.getIdGroupeUser(),
                groupeUser.getIdGroupe(),
                groupeUser.getIdUser(),
                groupeUser.getStatus()
        );
        return groupedtp;
    }

    public static GroupeUser mapToGroupeUser(GroupeUserDTO groupeUserDto){
        GroupeUser groupe = new GroupeUser(
                groupeUserDto.getIdGroupeUser(),
                groupeUserDto.getIdGroupe(),
                groupeUserDto.getIdUser(),
                groupeUserDto.getStatus()
        );
        return groupe;
    }
}
