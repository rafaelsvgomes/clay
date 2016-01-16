import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jboss.crypto.CryptoUtil;

public class GerarSenha {
    public static void main(String[] args) {
        System.out.println(convertStringToMd5("admin"));
    }

    private static String convertStringToMd5(String valor) {
        MessageDigest mDigest;
        try {
            System.out.println(CryptoUtil.createPasswordHash("MD5", "hex", null, null, valor));
            System.out.println(CryptoUtil.createPasswordHash("MD5", "hex", "UTF-8", null, valor));
            System.out.println(CryptoUtil.createPasswordHash("MD5", "hex", "UTF-8", "user", valor));
            mDigest = MessageDigest.getInstance("MD5");
            byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : valorMD5) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (UnsupportedEncodingException ue) {
            return null;
        }
    }
    // Leia mais em: JSF Filter: Criando um sistema de login com criptografia MD5
    // http://www.devmedia.com.br/jsf-filter-criando-um-sistema-de-login-com-criptografia-md5/29975#ixzz3xGgK0RyG
}
