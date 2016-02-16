package br.com.clay.servico;

import java.math.BigInteger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.clay.entidade.Usuario;
import br.com.clay.vo.UsuarioLogado;

/**
 * Session Bean implementation class UsuarioServicoEJB
 */
@Stateless
@LocalBean
public class UsuarioServicoEJB extends ClayPersistencia<Usuario, Long> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Default constructor.
     */
    public UsuarioServicoEJB() {
        super(Usuario.class);
    }

    public UsuarioLogado obterUsuario(String userName) {
        String sql = "SELECT up.idpessoa, u.dsusuario, c.idclientesituacao, ug.cdgrupo FROM usuario u, usuariopessoa up, usuariogrupo ug, cliente c "
                + "WHERE u.idusuario = up.idusuario and up.idpessoa = c.idcliente and u.idusuario = ug.idusuario and u.dsusuario = :userName";

        Object[] usuario = (Object[]) em.createNativeQuery(sql).setParameter("userName", userName).getSingleResult();

        UsuarioLogado usuarioLogado = new UsuarioLogado();
        usuarioLogado.setIdCliente(((BigInteger) usuario[0]).longValue());
        usuarioLogado.setDescUsuario((String) usuario[1]);
        usuarioLogado.setIdClienteSituacao(((BigInteger) usuario[2]).longValue());
        usuarioLogado.setCodGrupo((String) usuario[3]);

        return usuarioLogado;

    }

}