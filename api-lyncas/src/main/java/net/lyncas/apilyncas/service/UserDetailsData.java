package net.lyncas.apilyncas.service;

import lombok.RequiredArgsConstructor;
import net.lyncas.apilyncas.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * @author Vanessa Eich
 * @created 13/05/2022
 */
@RequiredArgsConstructor
public class UserDetailsData implements UserDetails {

    private final Optional<User> user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return new ArrayList<>();
    }

    @Override
    public String getPassword() {

        return user.orElse(new User()).getAuthentication().getPassword();
    }

    public String getUserId() {

        return user.orElse(new User()).getUserId().toString();
    }

    public String getName() {

        return user.orElse(new User()).getName();
    }

    public String getLastName() {

        return user.orElse(new User()).getLastName();
    }

    @Override
    public String getUsername() {

        return user.orElse(new User()).getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
