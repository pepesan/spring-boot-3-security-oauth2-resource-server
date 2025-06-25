package com.cursosdedesarrollo.springbootsecurityoauth2resourceserver.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class ApiController {

    // Ruta pública
    @GetMapping("/public/hello")
    public String publicHello() {
        return "¡Hola Mundo Público!";
    }

    // Ruta protegida
    @GetMapping("/api/userinfo")
    public Map<String, Object> userInfo(@AuthenticationPrincipal Jwt jwt) {
        // Devuelve algunos claims del token como ejemplo
        return Map.of(
                "sub", jwt.getSubject(),
                "issuer", jwt.getIssuer().toString(),
                "scope", jwt.getClaimAsStringList("scope")
        );
    }
}
