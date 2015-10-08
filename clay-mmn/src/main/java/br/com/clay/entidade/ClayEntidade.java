package br.com.clay.entidade;

import java.io.Serializable;

public abstract class ClayEntidade implements Serializable {

    private static final long serialVersionUID = -9159448032768256460L;

    public abstract Number getId();
}
