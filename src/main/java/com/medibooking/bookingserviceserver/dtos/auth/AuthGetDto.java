package com.medibooking.bookingserviceserver.dtos.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

@Data
@NoArgsConstructor
public class AuthGetDto {
    String username;
    Set<SimpleGrantedAuthority> grantedAuthorities;
}
