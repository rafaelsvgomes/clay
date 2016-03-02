package br.com.clay.mb;

import br.com.clay.entidade.Pedido;
import br.com.clay.entidade.PessoaTelefone;
import br.com.clay.enums.TipoPessoa;
import br.com.uol.pagseguro.domain.checkout.Checkout;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.DocumentType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;

public class CreateCheckout {
    /**
     * Class with a main method to illustrate the usage of the domain class Checkout //
     */
    public static void main(String[] args) {

        // try {

        Boolean onlyCheckoutCode = false;

        // String checkoutURL = new CreateCheckout().getCheckout().register(PagSeguroConfig.getAccountCredentials(), onlyCheckoutCode);

        // System.out.println(checkoutURL);

        // } catch (PagSeguroServiceException e) {
        // System.err.println(e.getMessage());
        // }
    }

    public String getCheckoutCode(Pedido pedido) throws PagSeguroServiceException {
        return getCheckoutUrl(pedido).replace("https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code=", "");
    }

    public String getCheckoutUrl(Pedido pedido) throws PagSeguroServiceException {
        Boolean onlyCheckoutCode = false;
        String checkoutURL = getCheckout(pedido).register(PagSeguroConfig.getAccountCredentials(), onlyCheckoutCode);
        System.out.println(checkoutURL);

        return checkoutURL;
    }

    private DocumentType getDocumentType(TipoPessoa tipoPessoa) {
        if (tipoPessoa.equals(TipoPessoa.F)) {
            return DocumentType.CPF;
        } else {
            return DocumentType.CNPJ;
        }
    }

    private Checkout getCheckout(Pedido pedido) {
        Checkout checkout = new Checkout();

        checkout.addItem(pedido.getId().toString(), pedido.getCliente().getPlanoAssinatura().getNomePlanoAssinatura(), pedido.getListaPedidoProduto().size(),
                pedido.getValorTotalLiquido(), 0l, null);

        PessoaTelefone telefone = pedido.getCliente().getTelefoneCelular();
        telefone.setDescTelefone(telefone.getDescTelefone().replace("(", "").replace(")", ""));

        checkout.setSender(pedido.getCliente().getNomePessoa(), pedido.getCliente().getDescEmail(), pedido.getCliente().getTelefoneCelular().getDDD(), pedido.getCliente()
                .getTelefoneCelular().getNumero(), getDocumentType(pedido.getCliente().getTipoPessoa()), pedido.getCliente().getNumCpfCnpj());

        checkout.setCurrency(Currency.BRL);

        // TODO: rafael - Verificar regra para referencia.
        checkout.setReference(pedido.getId().toString());

        // checkout.setNotificationURL("http://www.meusite.com.br/notification");

        // checkout.setRedirectURL("http://www.meusite.com.br/redir");

        // Another way to set checkout parameters

        return checkout;
    }

    public CreateCheckout() {

    }
}