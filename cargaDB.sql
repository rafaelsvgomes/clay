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
insert into produto values (nextval('SEQPRODUTO'),4,3,null,'Kit Plano 1','Kit de adesão, referente ao plano 1',10,0,0,0,20,false,true);
insert into produto values (nextval('SEQPRODUTO'),4,3,null,'Kit Plano 2','Kit de adesão, referente ao plano 2',10,0,0,0,20,false,true);
insert into produto values (nextval('SEQPRODUTO'),4,3,null,'Kit Plano 3','Kit de adesão, referente ao plano 3',10,0,0,0,20,false,true);
insert into produto values (nextval('SEQPRODUTO'),2,1,null,'Creme para as mãos','Creme suave para uso diário',10,0,0,0,30,true,false);
insert into produto values (nextval('SEQPRODUTO'),3,1,null,'Shampoo de argila','Shampoo para cabelos secos',15,0,0,0,25,true,false);
insert into produto values (nextval('SEQPRODUTO'),2,1,null,'Base maquiagem','Base para maquiagem desc',10,0,0,0,30,true,false);
insert into produto values (nextval('SEQPRODUTO'),3,1,null,'Máscara','Máscara desc',15,0,0,0,25,true,false);
insert into produto values (nextval('SEQPRODUTO'),2,1,null,'Pomada de argila','Pomada de argila desc',10,0,0,0,30,true,false);
insert into produto values (nextval('SEQPRODUTO'),3,1,null,'Loção hidratante','Loção hidratante desc',15,0,0,0,25,true,false);

--ProdutoComposicao
INSERT INTO produtocomposicao select nextval('seqprodutocomposicao'), p.idproduto, pi.idproduto, 5 from produto p, produto pi where p.nomeproduto = 'Kit Plano 1' and pi.nomeproduto = 'Creme para as mãos';
INSERT INTO produtocomposicao select nextval('seqprodutocomposicao'), p.idproduto, pi.idproduto, 10 from produto p, produto pi where p.nomeproduto = 'Kit Plano 1' and pi.nomeproduto = 'Shampoo de argila';
INSERT INTO produtocomposicao select nextval('seqprodutocomposicao'), p.idproduto, pi.idproduto, 6 from produto p, produto pi where p.nomeproduto = 'Kit Plano 2' and pi.nomeproduto = 'Base maquiagem';
INSERT INTO produtocomposicao select nextval('seqprodutocomposicao'), p.idproduto, pi.idproduto, 11 from produto p, produto pi where p.nomeproduto = 'Kit Plano 2' and pi.nomeproduto = 'Máscara';
INSERT INTO produtocomposicao select nextval('seqprodutocomposicao'), p.idproduto, pi.idproduto, 100 from produto p, produto pi where p.nomeproduto = 'Kit Plano 3' and pi.nomeproduto = 'Pomada de argila';
INSERT INTO produtocomposicao select nextval('seqprodutocomposicao'), p.idproduto, pi.idproduto, 52 from produto p, produto pi where p.nomeproduto = 'Kit Plano 3' and pi.nomeproduto = 'Loção hidratante';

--ProdutoValor
insert into produtovalor select nextval('seqprodutovalor'), p.idproduto, 10.5, 20.59, 2.0, '2016-02-20' from produto p where p.nomeproduto = 'Shampoo de argila';
insert into produtovalor select nextval('seqprodutovalor'), p.idproduto, 20.5, 40.57, 1.5, '2016-02-20' from produto p where p.nomeproduto = 'Creme para as mãos';
insert into produtovalor select nextval('seqprodutovalor'), p.idproduto, 15.44, 40.57, 4.5, '2016-02-20' from produto p where p.nomeproduto = 'Base maquiagem';
insert into produtovalor select nextval('seqprodutovalor'), p.idproduto, 50.5, 55.57, 5.5, '2016-02-20' from produto p where p.nomeproduto = 'Máscara';
insert into produtovalor select nextval('seqprodutovalor'), p.idproduto, 21.3, 25.57, 2.52, '2016-02-20' from produto p where p.nomeproduto = 'Pomada de argila';
insert into produtovalor select nextval('seqprodutovalor'), p.idproduto, 22.75, 47.57, 4.57, '2016-02-20' from produto p where p.nomeproduto = 'Loção hidratante';


--PlanoAssinatura
insert into planoassinatura values (nextval('seqplanoassinatura'), 1, 'Plano 1', 'Desc Plano 1', 100.00, 100.00, 100.00, true);
insert into planoassinatura values (nextval('seqplanoassinatura'), 2, 'Plano 2', 'Desc Plano 2', 200.00, 200.00, 200.00, true);
insert into planoassinatura values (nextval('seqplanoassinatura'), 3, 'Plano 3', 'Desc Plano 3', 300.00, 300.00, 300.00, true);

--PedidoSituacao
insert into pedidosituacao values (1,'Aberto');
insert into pedidosituacao values (2,'Aguardando pagamento');
insert into pedidosituacao values (3,'Aguardando retirada');
insert into pedidosituacao values (4,'Finalizado');

--ClienteSituacao
insert into clientesituacao values (1,'Cadastrado');
insert into clientesituacao values (2,'Ativo');
insert into clientesituacao values (3,'Inativo');
insert into clientesituacao values (4,'Bloqueado');

--GRUPO
INSERT INTO GRUPO values ('ADMIN');
INSERT INTO GRUPO values ('CLIENTE');
INSERT INTO GRUPO values ('GESTOR');

--USUARIO
INSERT INTO USUARIO values (nextval('sequsuario'), 'admin', 'bdfb8ce799ed1782a38a47c8090f6941');--adminclay123
INSERT INTO USUARIO values (nextval('sequsuario'), 'user', '076c8b756a48c7b7c23c88f10fd260b8');--userclay123

--USUARIOGRUPO
INSERT INTO USUARIOGRUPO VALUES (nextval('sequsuariogrupo'), 'ADMIN', 1);
INSERT INTO USUARIOGRUPO VALUES (nextval('sequsuariogrupo'), 'USER', 2);

--INSERT PESSOA ADM
INSERT INTO pessoa values (nextval('seqpessoa'), 'Gestor Master Clay', null, 'F', '00000000000', 'M', '2000-01-01', '2000-01-01', 'admin@clay.com');
INSERT INTO cliente select p.idpessoa, 1, 2, '2000-01-01' from pessoa p where p.nomepessoa = 'Administrador';
INSERT INTO usuariopessoa select nextval('sequsuariopessoa'), p.idpessoa, u.idusuario from pessoa p, usuario u where p.nomepessoa = 'Administrador' and u.dsusuario = 'admin';

--INSERT GESTOR MASTER
INSERT INTO pessoa values (nextval('seqpessoa'), 'Gestor Master Clay', 'Gestor Master Clay SA', 'J', '00000000000000', null, '2000-01-01', '2000-01-01', 'gestormaster@gmail.com');
INSERT INTO pessoaendereco select nextval('seqpessoaendereco'), p.idpessoa, 2, 'Endereço Gestor', 0, '', 'Brasília', 'Brasília', '00000000', 'DF' from pessoa p where p.nomepessoa = 'Gestor Master Clay';
INSERT INTO pessoaconta select nextval('seqpessoaconta'), p.idpessoa, 2, 1, 1, 11, true from pessoa p where p.nomepessoa = 'Gestor Master Clay';  
INSERT INTO pessoatelefone select nextval('seqpessoatelefone'), 1, p.idpessoa, '(61)99999999' from pessoa p where p.nomepessoa = 'Gestor Master Clay';  
INSERT INTO cliente select p.idpessoa, 1, 2, '2000-01-01' from pessoa p where p.nomepessoa = 'Gestor Master Clay';
INSERT INTO usuariopessoa select nextval('sequsuariopessoa'), p.idpessoa, u.idusuario from pessoa p, usuario u where p.nomepessoa = 'Gestor Master Clay' and u.dsusuario = 'gestormaster';



