package com.company.insuranceapp.security;

import com.company.insuranceapp.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.company.insuranceapp.config.springSecurityProperties.SecurityProperties.AUTHORITY_PREFIX;


@RequiredArgsConstructor
public class UserEssentialSecurity implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getRoles().stream().map(role -> new SimpleGrantedAuthority(AUTHORITY_PREFIX + role.getRoleType())).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.user.getEnabled();
    }
}
