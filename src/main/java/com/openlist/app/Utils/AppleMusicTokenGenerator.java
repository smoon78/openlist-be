package com.openlist.app.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.security.KeyFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Date;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.openlist.app.Config.AppleMusicConfig;

@Component
public class AppleMusicTokenGenerator {

    @Autowired
    private AppleMusicConfig appleMusicConfig;

    public String generateToken() {
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(15777000);

        ECPrivateKey ecPrivateKey = null;
        try {
            ecPrivateKey = getEcPrivateKey(appleMusicConfig.getPrivateKey());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            // Handle the exception as needed.
        }

        return JWT.create()
                  .withIssuer(appleMusicConfig.getTeamId())
                  .withKeyId(appleMusicConfig.getKeyId())
                  .withIssuedAt(Date.from(now))
                  .withExpiresAt(Date.from(expiration))
                  .sign(Algorithm.ECDSA256(null, ecPrivateKey));
    }

    private ECPrivateKey getEcPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        return (ECPrivateKey) keyFactory.generatePrivate(keySpec);
    }
}
