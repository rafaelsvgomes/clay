package br.com.clay.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "UF")
@NamedQueries({ @NamedQuery(name = UF.LISTAR, query = "SELECT u FROM UF u") })
public class UF implements ClayEntidade {
    private static final long serialVersionUID = -8342220191530234596L;

    public static final String LISTAR = "UF.Listar";

    @Id
    @Column(name = "IDUF", unique = true, nullable = false)
    private Short id;

    @Column(name = "dsuf")
    private String descUf;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescUf() {
        return descUf;
    }

    public void setDescUf(String descUf) {
        this.descUf = descUf;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     * 
     * Sobrescrevendo para ser encontrado via converter
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     * 
     * Sobrescrevendo para ser encontrado via converter
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClayEntidade other = (ClayEntidade) obj;
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        return true;
    }

}
