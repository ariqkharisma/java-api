package com.ariq.citcitapi.user.model.dto.UserRequest;

import com.ariq.citcitapi.user.model.AppUser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestRegister {
    @NotEmpty
    private String name;

    @NotEmpty
    @Size(min = 6, message = "Username should not be less than 6")
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 6, message = "Password should not be less than 6")
    private String password;

    public UserRequestRegister(String username) {
        this.username = username;
    }

    public UserRequestRegister(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AppUser convertToEntity() {
        return AppUser.builder()
                .name(this.name)
                .username(this.username)
                .email(this.email)
                .password(this.password)
                .build();
    }

    public AppUser convertToEntityPost() {
        return AppUser.builder().username(this.username).build();
    }

}
