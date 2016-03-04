function pagar(code){
		isOpenLightbox = PagSeguroLightbox(code,  {
                 success : function(transactionCode) {
                    document.getElementById("cod_transacao").value = transactionCode;
                    document.getElementById("botao_pag_efetuado").click();
                 },
                 abort : function() {
                 	document.getElementById("botao_abortar").click();
                 }
         });
		if (!isOpenLightbox){
	         location.href="https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code="+code;
		} 
     return false;
}