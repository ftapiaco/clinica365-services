package com.clinica.services.v1.auth.jwt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Test
    public void testGenerateAndValidateToken() {
        String token = jwtUtil.generateToken("testuser");

        assertNotNull(token);
        assertTrue(jwtUtil.validateToken(token));

        String username = jwtUtil.getUsernameFromToken(token);
        assertEquals("testuser", username);
    }

    @Test
    public void testInvalidToken() {
        String invalidToken = "fake.token.value";

        assertFalse(jwtUtil.validateToken(invalidToken));
    }
}
