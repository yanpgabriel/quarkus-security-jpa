package com.yanpgabriel.providers;

import io.quarkus.security.jpa.PasswordProvider;
import org.wildfly.security.password.Password;
import org.wildfly.security.password.interfaces.SimpleDigestPassword;

import javax.xml.bind.DatatypeConverter;

public class CustomPasswordProvider implements PasswordProvider {
    
    @Override
    public Password getPassword(String pass) {
        byte[] digest = DatatypeConverter.parseHexBinary(pass);
        return SimpleDigestPassword.createRaw(SimpleDigestPassword.ALGORITHM_SIMPLE_DIGEST_SHA_256, digest);
    }
    
}
