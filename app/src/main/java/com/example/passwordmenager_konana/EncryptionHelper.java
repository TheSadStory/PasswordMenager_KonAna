package com.example.passwordmenager_konana;

import android.util.Base64;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionHelper {
    private static final String ALGORITHM = "AES";

    public static SecretKeySpec generateKey(String pin) throws NoSuchAlgorithmException {
        byte[] key = pin.getBytes();
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = java.util.Arrays.copyOf(key, 16); // Use only the first 128 bit
        return new SecretKeySpec(key, ALGORITHM);
    }

    public static byte[] encrypt(String data, String pin) throws Exception {
        SecretKeySpec secretKey = generateKey(pin);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data.getBytes("UTF-8"));
    }

    public static String decrypt(byte[] data, String pin) throws Exception {
        SecretKeySpec secretKey = generateKey(pin);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(data);
        return new String(decryptedBytes);
    }

}

