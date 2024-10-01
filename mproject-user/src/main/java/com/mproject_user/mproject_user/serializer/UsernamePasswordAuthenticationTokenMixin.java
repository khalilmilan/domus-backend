package com.mproject_user.mproject_user.serializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Mixin class for {@link UsernamePasswordAuthenticationToken} to use custom deserialization.
 * This class is used to apply the custom {@link UsernamePasswordAuthenticationTokenDeserializer}
 * for deserializing JSON into a {@link UsernamePasswordAuthenticationToken} object.
 */
@JsonDeserialize(using = UsernamePasswordAuthenticationTokenDeserializer.class)
public class UsernamePasswordAuthenticationTokenMixin extends UsernamePasswordAuthenticationToken {

    /**
     * Constructs a {@link UsernamePasswordAuthenticationTokenMixin} with the given principal and credentials.
     *
     * @param principal the principal object (usually a {@link Jwt} or {@link UserDetails})
     * @param credentials the credentials (usually a password)
     */
    public UsernamePasswordAuthenticationTokenMixin(Object principal, Object credentials) {
        super(principal, credentials);
    }

}
