package br.com.clay.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.clay.vo.CepServiceVO;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;
import com.thoughtworks.xstream.io.xml.DomDriver;

@SuppressWarnings("deprecation")
public class CepService {
    
    private static String urlWebService = "https://viacep.com.br/ws/";

    public CepServiceVO buscarCepWebService(String cep) {
        CepServiceVO cepServiceVO = null;
        BufferedReader br = null;

        cep = cep.replace("-", "");

        urlWebService += cep + "/xml/";

        try {
            br = criarConexaoWebService(br);

            StringBuilder newData = leArquivoRetorno(br);

            // Controi classe a partir do XML
            XStream xstream = new XStream(new DomDriver());
            Annotations.configureAliases(xstream, CepServiceVO.class);
            xstream.alias("webservicecep", CepServiceVO.class);
            cepServiceVO = (CepServiceVO) xstream.fromXML(newData.toString());

        } catch (Exception e) { 
            e.printStackTrace();
        } finally {
            closeBufferedReader(br);
        }
        return cepServiceVO;

    }


    /**
     * Metodo responsavel por criar a conexao com o web Service retornando um BufferedReader para comecar a ler o arquivo retorno
     * 
     * @param BufferedReader br
     * @return BufferedReader
     * @throws MalformedURLException
     * @throws IOException BufferedReader
     * 
     */
    private BufferedReader criarConexaoWebService(BufferedReader br) throws MalformedURLException, IOException {
        // cria o objeto url
        URL url = new URL(urlWebService);
        
        // cria o objeto httpurlconnection
       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
       
       System.out.println("Proxy? " + connection.usingProxy()); 

        // seta o metodo
        connection.setRequestProperty("Request-Method", "GET");

        // seta a variavel para ler o resultado
        connection.setDoInput(true);
        connection.setDoOutput(false);

        // conecta com a url destino
        connection.connect();

        // abre a conexão pra input
        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        return br;
    }

    /**
     * Metodo responsavel por ler todo o arquivo retonando uma StringBuffer
     * 
     * @param br
     * @return StringBuilder
     * @throws IOException StringBuffer
     * 
     */
    private StringBuilder leArquivoRetorno(BufferedReader br) throws IOException {
        // le ate o final
        StringBuilder newData = new StringBuilder();
        String s = "";
        while (null != ((s = br.readLine()))) {
            newData.append(s);
        }
        return newData;
    }

    /**
     * Metodo responsaavel por fechar a conexao do buffered reader
     * 
     * @param br void
     * 
     */
    private void closeBufferedReader(BufferedReader br) {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
