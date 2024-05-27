package de.tinf22b6.dhbwhub.security.OAuth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final OAuth2User delegate;

    public CustomOAuth2User(OAuth2User delegate) {
        this.delegate = delegate;
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getName() {
        return delegate.getAttribute("name");
    }

    public String getEmail(){
        return delegate.getAttribute("email");
    }

    public String getPicture(){
        return delegate.getAttribute("picture");
    }
}
