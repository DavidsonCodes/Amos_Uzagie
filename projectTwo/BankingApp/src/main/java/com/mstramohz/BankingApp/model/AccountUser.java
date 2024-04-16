package com.mstramohz.BankingApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@RequiredArgsConstructor
@Getter
@Entity
@Table(name = "account_user", uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
public class AccountUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "First name can't be empty")
    @NotBlank
    @Length(min = 3, message = "First Name can't be less than 3 letters")
    private String firstName;
    @NotNull(message = "Last name can't be empty")
    @NotBlank
    @Length(min = 3, message = "Last Name can't be less than 3 letters")
    private String lastName;
    @Length(min = 3, message = "Last Name can't be less than 3 letters")
    private String middleName;
    @Email
    @NotNull(message = "User Name can't be empty")
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "AccountUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
