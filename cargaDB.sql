INSERT INTO uf values ('AC','Acre (AC)');
INSERT INTO uf values ('AL','Alagoas (AL)');
INSERT INTO uf values ('AP','Amapá (AP)');
INSERT INTO uf values ('AM','Amazonas (AM)');
INSERT INTO uf values ('BA','Bahia (BA)');
INSERT INTO uf values ('CE','Ceará (CE)');
INSERT INTO uf values ('DF','Distrito Federal (DF)');
INSERT INTO uf values ('ES','Espírito Santo (ES)');
INSERT INTO uf values ('GO','Goiás (GO)');
INSERT INTO uf values ('MA','Maranhão (MA)');
INSERT INTO uf values ('MT','Mato Grosso (MT)');
INSERT INTO uf values ('MS','Mato Grosso do Sul (MS)');
INSERT INTO uf values ('MG','Minas Gerais (MG)');
INSERT INTO uf values ('PA','Pará (PA) ');
INSERT INTO uf values ('PB','Paraíba (PB)');
INSERT INTO uf values ('PR','Paraná (PR)');
INSERT INTO uf values ('PE','Pernambuco (PE)');
INSERT INTO uf values ('PI','Piauí (PI)');
INSERT INTO uf values ('RJ','Rio de Janeiro (RJ)');
INSERT INTO uf values ('RN','Rio Grande do Norte (RN)');
INSERT INTO uf values ('RS','Rio Grande do Sul (RS)');
INSERT INTO uf values ('RO','Rondônia (RO)');
INSERT INTO uf values ('RR','Roraima (RR)');
INSERT INTO uf values ('SC','Santa Catarina (SC)');
INSERT INTO uf values ('SP','São Paulo (SP)');
INSERT INTO uf values ('SE','Sergipe (SE)');
INSERT INTO uf values ('TO','Tocantins (TO)');

INSERT INTO tipoendereco values (1, 'Residencial');
INSERT INTO tipoendereco values (2, 'Comercial');

INSERT INTO tipotelefone values (1, 'Residencial');
INSERT INTO tipotelefone values (2, 'Celular');
INSERT INTO tipotelefone values (3, 'Comercial');

--TipoConta
INSERT INTO tipoconta values (1, 'Conta Corrente');
INSERT INTO tipoconta values (2, 'Poupança');

--Banco
insert into banco values (001, 'Banco do Brasil');
insert into banco values (033, 'Santander');
insert into banco values (104, 'Caixa Econômica Federal');
insert into banco values (237, 'Bradesco');
insert into banco values (341, 'Itaú Unibanco');
insert into banco values (756, 'Banco Cooperativo do Brasil');

--UnidadeVenda
insert into unidadevenda values (1, 'Kit');
insert into unidadevenda values (2, 'Frasco');
insert into unidadevenda values (3, 'Pacote');

--Categoria
insert into categoria values (1,'Kit Adesão',null);
insert into categoria values (2,'Cosméticos',null);
insert into categoria values (3,'Creme',1);
insert into categoria values (4,'Shampoo',1);

--OrigemPagamento
insert into origempagamento values (1, 'Em Mãos');
insert into origempagamento values (2, 'PagSeguro');

--PedidoSituacao
insert into pedidosituacao values (1,'Aberto');
insert into pedidosituacao values (2,'Aguardando pagamento');
insert into pedidosituacao values (3,'Aguardando retirada');
insert into pedidosituacao values (4,'Entrega parcial');
insert into pedidosituacao values (5,'Finalizado');

--PedidoProdutoSituacao
insert into pedidoprodutosituacao values (1,'Não Entregue');
insert into pedidoprodutosituacao values (2,'Entregue');

--PedidoTipo
insert into pedidotipo values (1,'Assinatura');
insert into pedidotipo values (2,'Compra');

--StatusPagamento (PagSeguro)
insert into statuspagamento values (1,'Aguardando pagamento');
insert into statuspagamento values (2,'Em análise');
insert into statuspagamento values (3,'Pago');
insert into statuspagamento values (4,'Disponível');
insert into statuspagamento values (5,'Em disputa');
insert into statuspagamento values (6,'Devolvida');
insert into statuspagamento values (7,'Cancelada');

--ClienteSituacao
insert into clientesituacao values (1,'Cadastrado');
insert into clientesituacao values (2,'Ativo');
insert into clientesituacao values (3,'Inativo');
insert into clientesituacao values (4,'Bloqueado');

--GRUPO
INSERT INTO GRUPO values ('ADMIN');
INSERT INTO GRUPO values ('USER');
INSERT INTO GRUPO values ('CLIENTE');
INSERT INTO GRUPO values ('GESTOR');

---------

--Produto
insert into produto values (nextval('seqproduto'),1,1,null,'Kit Plano 1','Kit de adesão, referente ao plano 1',10,0,0,0,0,0,20,false,true);
insert into produto values (nextval('seqproduto'),1,1,null,'Kit Plano 2','Kit de adesão, referente ao plano 2',10,0,0,0,0,0,20,false,true);
insert into produto values (nextval('seqproduto'),1,1,null,'Kit Plano 3','Kit de adesão, referente ao plano 3',10,0,0,0,0,0,20,false,true);
insert into produto values (nextval('seqproduto'),2,2,null,'Creme para as mãos','Creme suave para uso diário',10,0,0,0,0,0,30,true,false);
insert into produto values (nextval('seqproduto'),3,2,null,'Shampoo de argila','Shampoo para cabelos secos',15,0,0,0,0,0,25,true,false);
insert into produto values (nextval('seqproduto'),2,3,null,'Base maquiagem','Base para maquiagem',10,0,0,0,0,0,30,true,false);
insert into produto values (nextval('seqproduto'),3,3,null,'Máscara','MáscaraDesc',15,0,0,0,0,0,25,true,false);

--ProdutoValor
insert into produtovalor select nextval('seqprodutovalor'),p.idproduto,0,100,0,'2016-02-29' from produto p where p.nomeproduto = 'Kit Plano 1';
insert into produtovalor select nextval('seqprodutovalor'),p.idproduto,0,200,0,'2016-02-29' from produto p where p.nomeproduto = 'Kit Plano 2';
insert into produtovalor select nextval('seqprodutovalor'),p.idproduto,0,300,0,'2016-02-29' from produto p where p.nomeproduto = 'Kit Plano 3';
insert into produtovalor select nextval('seqprodutovalor'),p.idproduto,10,30,0,'2016-02-29' from produto p where p.nomeproduto = 'Creme para as mãos';
insert into produtovalor select nextval('seqprodutovalor'),p.idproduto,25,26,0,'2016-02-29' from produto p where p.nomeproduto = 'Shampoo de argila';
insert into produtovalor select nextval('seqprodutovalor'),p.idproduto,50,60,1,'2016-02-29' from produto p where p.nomeproduto = 'Base maquiagem';
insert into produtovalor select nextval('seqprodutovalor'),p.idproduto,38,42,2,'2016-02-29' from produto p where p.nomeproduto = 'Máscara';

--ProdutoComposicao
insert into produtocomposicao select nextval('seqprodutocomposicao'), p.idproduto, p1.idproduto, 1 from produto p, produto p1 where p.nomeproduto = 'Kit Plano 1' and p1.nomeproduto = 'Creme para as mãos';
insert into produtocomposicao select nextval('seqprodutocomposicao'), p.idproduto, p1.idproduto, 1 from produto p, produto p1 where p.nomeproduto = 'Kit Plano 1' and p1.nomeproduto = 'Shampoo de argila';
insert into produtocomposicao select nextval('seqprodutocomposicao'), p.idproduto, p1.idproduto, 1 from produto p, produto p1 where p.nomeproduto = 'Kit Plano 2' and p1.nomeproduto = 'Base maquiagem';
insert into produtocomposicao select nextval('seqprodutocomposicao'), p.idproduto, p1.idproduto, 1 from produto p, produto p1 where p.nomeproduto = 'Kit Plano 2' and p1.nomeproduto = 'Máscara';
insert into produtocomposicao select nextval('seqprodutocomposicao'), p.idproduto, p1.idproduto, 1 from produto p, produto p1 where p.nomeproduto = 'Kit Plano 3' and p1.nomeproduto = 'Shampoo de argila';
insert into produtocomposicao select nextval('seqprodutocomposicao'), p.idproduto, p1.idproduto, 1 from produto p, produto p1 where p.nomeproduto = 'Kit Plano 3' and p1.nomeproduto = 'Base maquiagem';

--PlanoAssinatura
insert into planoassinatura select nextval('seqplanoassinatura'), 1, 'Plano 1', 'Desc Plano 1', 100.00, 100.00, 100.00, true from produto p where p.nomeproduto = 'Kit Plano 1';
insert into planoassinatura select nextval('seqplanoassinatura'), 2, 'Plano 2', 'Desc Plano 2', 100.00, 100.00, 100.00, true from produto p where p.nomeproduto = 'Kit Plano 2';
insert into planoassinatura select nextval('seqplanoassinatura'), 3, 'Plano 3', 'Desc Plano 3', 100.00, 100.00, 100.00, true from produto p where p.nomeproduto = 'Kit Plano 3';

--INSERT PESSOA ADM
INSERT INTO pessoa values (nextval('seqpessoa'), 'Administrador', null, 'F', '00000000000', 'M', '2000-01-01', '2000-01-01', 'admin@clay.com');
INSERT INTO cliente select p.idpessoa, 1, 2, '2000-01-01' from pessoa p where p.nomepessoa = 'Administrador';
INSERT INTO USUARIO values (nextval('sequsuario'), 'admin', 'bdfb8ce799ed1782a38a47c8090f6941');--adminclay123
INSERT INTO usuariopessoa select nextval('sequsuariopessoa'), p.idpessoa, u.idusuario from pessoa p, usuario u where p.nomepessoa = 'Administrador' and u.dsusuario = 'admin';
INSERT INTO USUARIOGRUPO select nextval('sequsuariogrupo'), 'ADMIN', u.idusuario from usuario u where u.dsusuario = 'admin';


