package br.com.clay.util;

import org.jboss.crypto.CryptoUtil;

public abstract class CriptografiaUtil {

    /**
     * M�todo respons�vel por criptografar a String com algoritmo MD5
     * 
     * @param valor
     * @return String
     * 
     */
    public static String toMD5(String valor) {
        return CryptoUtil.createPasswordHash("MD5", "hex", "UTF-8", null, valor);
    }

}