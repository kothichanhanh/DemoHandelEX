package com.msb.epay.common;

import org.bouncycastle.jce.provider.*;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;

public class DesedeCrypter {

    private static final String CHAR_ENCODING = "UTF-8";

    public static String encrypt(String key, String input)
            throws Exception {

        byte[] bytes = null;
        SecretKey sKey = null;
        Security.addProvider(new BouncyCastleProvider());
        Cipher encipher = Cipher.getInstance("DESede/ECB/PKCS5PADDING", "BC");
        bytes = input.getBytes(CHAR_ENCODING);
        sKey = getKey(key);
        // Encrypt
        byte[] enc;
        encipher.init(Cipher.ENCRYPT_MODE, sKey);
        enc = encipher.doFinal(bytes);
        return bytesToHex(enc);
    }
    public static String decrypt(String key, String cipher) {

        byte[] bytes = null;
        SecretKey sKey = null;
        Security.addProvider(new BouncyCastleProvider());
        Cipher encipher = null;
        try {
            encipher = Cipher.getInstance("DESede/ECB/PKCS5PADDING", "BC");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }

        bytes = hexToBytes(cipher);
        sKey = getKey(key);

        byte[] enc = null;

        try {
            encipher.init(Cipher.DECRYPT_MODE, sKey);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        try {
            enc = encipher.doFinal(bytes);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }

        return new String(enc);
    }
    private static SecretKey getKey(String key) {
        byte[] bKey = key.getBytes();
        try {
            DESedeKeySpec keyspec = new DESedeKeySpec(bKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey lclSK = keyFactory.generateSecret(keyspec);

            return lclSK;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static String bytesToHex(final byte[] bytes) {
        final StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (final byte b : bytes) {
            final String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                buf.append("0");
            }
            buf.append(hex);
        }
        return buf.toString();
    }
    private static byte[] hexToBytes(final String hex) {
        final byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }
        return bytes;
    }
}