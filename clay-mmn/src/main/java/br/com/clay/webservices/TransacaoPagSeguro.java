package br.com.clay.webservices;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.clay.servico.PedidoServicoEJB;

/**
 * Servlet implementation class TransacaoPagSeguro
 */
@WebServlet("/TransacaoPagSeguro")
public class TransacaoPagSeguro extends HttpServlet {

    private static final long serialVersionUID = 6619113612514405394L;

    @EJB
    PedidoServicoEJB ejb;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransacaoPagSeguro() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Solicitação Recebida. GET");
        String codTransacao = request.getParameter("id_transacao");
        String codNotificao = request.getParameter("notificationCode");
        System.out.println("GET: idTransacao = " + codTransacao + " notificationCode = " + codNotificao);

        ejb.atualizarTransacaoPedido(codNotificao);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Solicitação Recebida. POST");
        String codTransacao = request.getParameter("id_transacao");
        String codNotificao = request.getParameter("notificationCode");
        System.out.println("POST: idTransacao = " + codTransacao + " notificationCode = " + codNotificao);

        ejb.atualizarTransacaoPedido(codNotificao);
    }

}
