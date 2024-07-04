package com.mstramohz.BankingApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity(name = "user_table")
public class AccountUser implements UserDetails {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    @NotBlank(message = "First name can't be empty")
    @Length(min = 3, message = "First Name can't be less than 3 letters")
    private String firstName;
    @Setter
    private String middleName;
    @Setter
    @NotNull(message = "Last name can't be empty")
    @NotBlank
    @Length(min = 3, message = "Last Name can't be less than 3 letters")
    private String lastName;
    @Setter
    @Email
    @Column(name = "email")
    @NotNull(message = "User Name can't be empty")
    private String username;
    @Setter
    @NotNull
    private String password;
    @Setter
    @NotNull
    @Pattern(regexp = "[0-9]{11}")
    private String phoneNumber;
    @Setter
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
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
    public String toString() {
        return "AccountUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
