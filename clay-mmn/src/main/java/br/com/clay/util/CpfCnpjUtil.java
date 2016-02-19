package br.com.clay.util;

public class CpfCnpjUtil {
    public static String getCpfCnpjLimpo(String cpf) {
        return cpf.replace("-", "").replace(".", "").replace("/", "");
    }
}
