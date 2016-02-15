package br.com.clay.filtros;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.security.SecurityContextAssociation;

import br.com.clay.servico.UsuarioServicoEJB;
import br.com.clay.vo.UsuarioLogado;

public class LoginFilter implements Filter {

    @Override
    public void destroy() {
    }

    @EJB
    private UsuarioServicoEJB usuarioServico;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (SecurityContextAssociation.getPrincipal() != null && ((HttpServletRequest) servletRequest).getSession().getAttribute("usuarioLogado") == null) {
            String userName = SecurityContextAssociation.getPrincipal().getName();

            inserirUsuarioSessao(userName, servletRequest);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    private void inserirUsuarioSessao(String userName, ServletRequest servletRequest) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();

        UsuarioLogado usuario = usuarioServico.obterUsuario(userName);

        session.setAttribute("usuarioLogado", usuario);
    }
}