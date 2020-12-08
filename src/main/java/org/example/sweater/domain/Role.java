package org.example.sweater.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * TODO: add documentation
 */
public enum Role implements GrantedAuthority {
    USER, ADMIN, BOMJ;

    @Override
    public String getAuthority() {
        return name();
    }
}
