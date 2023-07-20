package com.example.librarymanagment.model.entity;

import javax.persistence.*;

import com.example.librarymanagment.model.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
@Table(name = "_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String email;
    String address;
    String password;
    boolean enabled;

    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    List<Token> tokens;

    @OneToOne
    Cart cart;
    @OneToMany
    List<Transactions> transactions;
    @OneToOne
    Fined fined;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<BorrowDate> borrowDate;
    @OneToMany
    List<Comment> comments;
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    List<Request> requests;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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
        return enabled;
    }
}
