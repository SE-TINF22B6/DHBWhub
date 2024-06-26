package de.tinf22b6.dhbwhub.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.Administrator;
import de.tinf22b6.dhbwhub.model.ERole;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.repository.AdministratorRepository;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Getter
    private Long accountId;
    @Getter
    private Long userId;

    private String username;

    @Getter
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long accountId, Long userId, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.accountId = accountId;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user, AdministratorRepository administratorRepository) {
        List<Administrator> administratorList = administratorRepository.findAll();

        List<GrantedAuthority> simpleGrantedAuthority = new ArrayList<>();
        boolean isAdmin = administratorList.stream().anyMatch(admin -> admin.getId().equals(user.getAccount().getId()));
        if (isAdmin) {
            simpleGrantedAuthority.add(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.toString()));
        } else {
            simpleGrantedAuthority.add(new SimpleGrantedAuthority(ERole.ROLE_USER.toString()));
        }

        return new UserDetailsImpl(
                user.getAccount().getId(),
                user.getId(),
                user.getAccount().getUsername(),
                user.getAccount().getEmail(),
                user.getAccount().getPassword(),
                simpleGrantedAuthority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(accountId, user.accountId);
    }

}
