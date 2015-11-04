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
insert into banco values (001, 'Banco do Brasil S.A.');
insert into banco values (237, 'Banco Bradesco S.A.');
insert into banco values (341, 'Itaú Unibanco S.A.');
insert into banco values (104, 'Caixa Econômica Federal');

--UnidadeVenda
insert into unidadevenda values (1, 'Frasco');
insert into unidadevenda values (2, 'Pacote');
insert into unidadevenda values (3, 'Kit');

--Categoria
insert into categoria values (1,'Cosméticos',null);
insert into categoria values (2,'Creme',1);
insert into categoria values (3,'Shampoo',1);
insert into categoria values (4,'Kit Adesão',null);

--Produto
insert into produto values (1,4,3,null,'Kit Plano 1','Kit de adesão, referente ao plano 1',10,0,0,0,20,false,true);
insert into produto values (2,4,3,null,'Kit Plano 2','Kit de adesão, referente ao plano 2',10,0,0,0,20,false,true);
insert into produto values (3,4,3,null,'Kit Plano 3','Kit de adesão, referente ao plano 3',10,0,0,0,20,false,true);
insert into produto values (4,2,1,null,'Creme para as mãos','Creme suave para uso diário',10,0,0,0,30,true,false);
insert into produto values (5,3,1,null,'Shampoo de argila','Shampoo para cabelos secos',15,0,0,0,25,true,false);

--ProdutoComposicao
insert into produtocomposicao values (1,1,4,1);
insert into produtocomposicao values (2,1,5,1);
insert into produtocomposicao values (3,2,4,2);
insert into produtocomposicao values (4,2,5,2);
insert into produtocomposicao values (5,3,4,3);
insert into produtocomposicao values (6,3,5,3);

--ValorProduto
insert into valorproduto values (1,1,10,20,0,'2015-10-28');
insert into valorproduto values (2,2,20,40,0,'2015-10-28');
insert into valorproduto values (3,3,30,60,0,'2015-10-28');
insert into valorproduto values (4,4,8,15,1,'2015-10-28');
insert into valorproduto values (5,5,12,19.30,2,'2015-10-28');


--PlanoAssinatura
insert into planoassinatura values (nextval('seqplanoassinatura'), 1, 'Plano 1', 'Desc Plano 1', 100.00, 100.00, 100.00, true);
insert into planoassinatura values (nextval('seqplanoassinatura'), 2, 'Plano 2', 'Desc Plano 2', 100.00, 100.00, 100.00, true);
insert into planoassinatura values (nextval('seqplanoassinatura'), 3, 'Plano 3', 'Desc Plano 3', 100.00, 100.00, 100.00, true);

--SituacaoPedido
insert into situacaopedido values (1,'Aberto');
insert into situacaopedido values (2,'Aguardando pagamento');
insert into situacaopedido values (3,'Aguardando retirada');
insert into situacaopedido values (4,'Finalizado');

