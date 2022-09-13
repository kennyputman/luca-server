package com.lucaapps.server.domain.user.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDto {

    @NotNull
    @Email
    @JsonProperty("email")
    private String email;

    @NotBlank
    @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
    @JsonProperty("password")
    private String password;

}
