package com.lamah.gestionemployer.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
