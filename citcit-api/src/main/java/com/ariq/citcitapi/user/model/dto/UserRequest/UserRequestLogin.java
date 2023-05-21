package com.ariq.citcitapi.user.model.dto.UserRequest;

import com.ariq.citcitapi.user.model.AppUser;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestLogin {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public AppUser convertToEntity() {
        return AppUser.builder().username(this.username).password(this.password).build();
    }
}
