package com.muser.muser.model;

import com.muser.muser.model.enums.TokenClaims;
import com.muser.muser.model.enums.UserGender;
import com.muser.muser.model.enums.UserStatus;
import com.muser.muser.model.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Id
    @GeneratedValue
    private Long idUser;
    private String firstName;
    private String lastName;
    private String photo;
    private String password;
    private String email;
    private String adresse ;
    private UserGender gender;

    private  Long  idGroupe;
    private Date birthDate;
    private String phoneNumber;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private Long idRank;
    private Long idRole;


    /**
     * Constructs a map of claims based on the user's attributes.
     * This map is typically used to create JWT claims for the user.
     * @return a map of claims containing user attributes
     */
    public Map<String, Object> getClaims() {

        final Map<String, Object> claims = new HashMap<>();

        claims.put(TokenClaims.USER_ID.getValue(), this.idUser);
        claims.put(TokenClaims.USER_TYPE.getValue(), this.userType);
        claims.put(TokenClaims.USER_STATUS.getValue(), this.status);
        claims.put(TokenClaims.USER_FIRST_NAME.getValue(), this.firstName);
        claims.put(TokenClaims.USER_LAST_NAME.getValue(), this.lastName);
        claims.put(TokenClaims.USER_EMAIL.getValue(), this.email);
        claims.put(TokenClaims.USER_PHONE_NUMBER.getValue(), this.phoneNumber);

        return claims;

    }
}
