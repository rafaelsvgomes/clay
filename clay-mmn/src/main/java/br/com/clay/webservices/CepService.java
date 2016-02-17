package br.com.clay.webservices;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import br.com.clay.vo.CepServiceVO;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;
import com.thoughtworks.xstream.io.xml.DomDriver;

@SuppressWarnings("deprecation")
public class CepService {
    
    private static String urlWebService = "http://cep.republicavirtual.com.br/web_cep.php";

    public CepServiceVO buscarCepWebService(String cep) {
        CepServiceVO cepServiceVO = null;
        BufferedReader br = null;
        
        cep = cep.replace("-", "");
        
        // os parametros a serem enviados
        Properties parameters = new Properties();
        parameters.setProperty("cep", cep);
        parameters.setProperty("formato", "xml");

        criarUrlWebService(parameters);

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
        } finally{
            closeBufferedReader(br);
        }
        return cepServiceVO;

    }

    /**
     * Método responsável por criar a url do web service
     * 
     * exemplo: http://cep.republicavirtual.com.br/web_cep.php?cep=72455490&formato=xml
     * 
     * @param parameters void
     * 
     */
    @SuppressWarnings("rawtypes")
    private void criarUrlWebService(Properties parameters) {
        Iterator i = parameters.keySet().iterator();
        
        int counter = 0;

        while (i.hasNext()) {

            String name = (String) i.next();
            String value = parameters.getProperty(name);
            urlWebService += (++counter == 1 ? "?" : "&") + name + "=" + value;
        }
    }
    
    /**
     * Método responsável por criar a conexao com o web Service retornando um BufferedReader para começar a ler o arquivo retorno
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

        // seta o metodo
        connection.setRequestProperty("Request-Method", "GET");

        // seta a variavel para ler o resultado
        connection.setDoInput(true);
        connection.setDoOutput(false);

        // conecta com a url destino
        connection.connect();

        // abre a conexÃ£o pra input
        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        return br;
    }
    
    /**
     * Método responsável por ler todo o arquivo retonando uma StringBuffer
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
     * Método responsável por fechar a conexao do buffered reader
     * @param br void
     * 
     */
    private void closeBufferedReader(BufferedReader br) {
        if(br != null){
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
