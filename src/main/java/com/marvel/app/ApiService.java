package com.marvel.app;

import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

@Service
public class ApiService {
    private final ApiConfiguration configuration;

    public ApiService(ApiConfiguration configuration) {
        this.configuration = configuration;
    }

    public String getHeroesApiUrl() {
        String apiUrl = configuration.getApiUrl();
        String timestamp = configuration.getTimestamp();
        String publicKey = configuration.getPublicKey();
        String privateKey = configuration.getPrivateKey();
        String hash = getMd5(timestamp + privateKey + publicKey);
        /*
         * Server-side applications must pass two parameters in addition to the apikey parameter
         * a timestamp (or other long string which can change on a request-by-request basis)
         * a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)
         */
        String parameters = MessageFormat.format("ts={0}&apikey={1}&hash={2}", timestamp, publicKey, hash);
        return apiUrl + "characters?orderBy=-modified&limit=30&" + parameters;
    }

    private static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
