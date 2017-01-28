package com.shreya.auth;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by shreya on 25/1/17.
 */
@Component
public class SecretKeyProvider {
    public byte[] getKey() throws URISyntaxException, IOException {
        return Files.readAllBytes(Paths.get(this.getClass().getResource("/jwt.key").toURI()));
    }
}
