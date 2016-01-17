package br.com.clay.util;

import br.com.clay.entidade.Cliente;

public class SenhaUtil {

    public static String gerarSenhaUsuario(Cliente cliente) {
        return CriptografiaUtil.toMD5(getSenhaPadrao(cliente));
    }

    private static String getSenhaPadrao(Cliente cliente) {
        return DataUtil.toString(cliente.getDataNascimento(), DataUtil.DATA_DIA_MES_ANO_SEM_BARRA) + getParteCpfCnpjSenha(cliente.getNumCpfCnpj());
    }

    private static String getParteCpfCnpjSenha(String cpfCnpj) {
        return cpfCnpj.substring(0, 3);
    }
}
