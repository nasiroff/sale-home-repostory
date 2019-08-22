package com.step.salehome.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
public class User implements UserDetails{

    private int idUser;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email pattern not match")
    private String email;

    @NotNull
    @NotBlank(message = "Password must not be blank")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20")
    private String password;

    @NotNull
    @NotBlank(message = "Last Name must not be blank")
    private String lastName;

    @NotNull
    @NotBlank(message = "First Name must not be blank")
    private String firstName;
    private String token;
    private Role role;
    private int status;
    private List<Integer> idFavoritePost;



    public User() {
        idFavoritePost = new ArrayList<>();
    }

    public void addIdFavoritePost(Integer idFavoritePost) {
        this.idFavoritePost.add(idFavoritePost);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleType());
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(grantedAuthority);
        return list;
    }

    @Override
    public String getUsername() {
        return email;
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
