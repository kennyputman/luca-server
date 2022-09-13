package com.lucaapps.server.domain.user.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AppUserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
