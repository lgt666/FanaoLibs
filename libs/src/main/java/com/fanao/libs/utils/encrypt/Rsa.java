package com.fanao.libs.utils.encrypt;

import android.util.Log;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by liutao on 16/5/11.
 */
public class Rsa {
    private static final String ALGORITHM = "RSA";

    private static PublicKey getPublicKeyFromX509(String algorithm, String bysKey) throws NoSuchAlgorithmException, Exception {
        byte[] decodedKey = Base64.decode(bysKey);
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(x509);
    }

    /**
     * 加密
     *
     * @param content
     * @return
     */
    public static String encrypt(String content, String publicKey) {
        try {
            PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, publicKey);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubkey);

            byte plaintext[] = content.getBytes("UTF-8");
            byte[] output = cipher.doFinal(plaintext);
            String s = Base64.encode(output);
            return s;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     *
     * @param content
     * @return
     */
    public static String decrypt(String content, String publicKey) {
        try {
            PublicKey prikey = getPublicKeyFromX509(ALGORITHM, publicKey);

            byte plaintext[] = Base64.decode(content);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, prikey);
            byte[] output = cipher.doFinal(plaintext);
            return new String(output, ("UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
    public static boolean doCheck(String content, String sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = keyFactory
                    .generatePublic(new X509EncodedKeySpec(encodedKey));

            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes("utf-8"));
            Log.i("Result", "content :   "+content);
            Log.i("Result", "sign:   "+sign);
            boolean bverify = signature.verify(Base64.decode(sign));
            Log.i("Result","bverify = " + bverify);
            return bverify;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}