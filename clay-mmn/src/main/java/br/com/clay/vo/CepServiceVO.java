package br.com.clay.vo;


import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("webservicecep")
public class CepServiceVO {

    @XStreamAlias("cep")
    private String cep;

    @XStreamAlias("logradouro")
    private String logradouro;

    @XStreamAlias("bairro")
    private String bairro;

    @XStreamAlias("localidade")
    private String localidade;

    @XStreamAlias("uf")
    private String uf;

    @XStreamAlias("ibge")
    private String ibge;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }
    
}