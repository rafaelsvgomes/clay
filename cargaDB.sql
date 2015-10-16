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

--SituacaoPedido
insert into situacaopedido (1,'Aberto');
insert into situacaopedido (2,'Aguardando pagamento');
insert into situacaopedido (3,'Aguardando retirada');
insert into situacaopedido (4,'Finalizado');

--Categoria
insert into categoria (1,'Cosméticos',null);
insert into categoria (2,'Creme',1);
insert into categoria (3,'Shampoo',1);

