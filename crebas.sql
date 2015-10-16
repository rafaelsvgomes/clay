/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     16/10/2015 15:07:43                          */
/*==============================================================*/


drop index INDEX_7;

drop table BANCO;

drop index INDEX_13;

drop table CATEGORIA;

drop index INDEX_26;

drop table CLIENTE;

drop index INDEX_6;

drop table CLIENTEREDE;

drop index INDEX_8;

drop table ENDERECOPESSOA;

drop index INDEX_25;

drop table FORNECEDOR;

drop index INDEX_20;

drop table PEDIDO;

drop index INDEX_21;

drop table PEDIDOPRODUTO;

drop index INDEX_3;

drop table PESSOA;

drop index INDEX_4;

drop table PESSOACONTA;

drop index INDEX_9;

drop table PLANOASSINATURA;

drop index INDEX_22;

drop table PRODUTO;

drop index INDEX_12;

drop table PRODUTOCOMPOSICAO;

drop index INDEX_10;

drop table SITUACAOCLIENTE;

drop index INDEX_17;

drop table SITUACAOPEDIDO;

drop index INDEX_11;

drop table TELEFONE;

drop index INDEX_5;

drop table TIPOCONTA;

drop index INDEX_14;

drop table TIPOENDERECO;

drop index INDEX_23;

drop table TIPOPEDIDO;

drop index INDEX_15;

drop table TIPOTELEFONE;

drop index INDEX_16;

drop table UF;

drop index INDEX_24;

drop table UNIDADEVENDA;

drop index INDEX_1;

drop table USUARIO;

drop index INDEX_2;

drop table USUARIOPESSOA;

drop index INDEX_18;

drop table VALORPRODUTO;

drop sequence SEQENDERECOPESSOA;

drop sequence SEQPEDIDO;

drop sequence SEQPEDIDOPRODUTO;

drop sequence SEQPESSOA;

drop sequence SEQPESSOACONTA;

drop sequence SEQPESSOAREDE;

drop sequence SEQPLANOASSINATURA;

drop sequence SEQPRODUTO;

drop sequence SEQPRODUTOCOMPOSICAO;

drop sequence SEQTELEFONE;

drop sequence SEQUSUARIO;

drop sequence SEQVALORPRODUTO;

create sequence SEQENDERECOPESSOA
increment 1;

create sequence SEQPEDIDO
increment 1;

create sequence SEQPEDIDOPRODUTO
increment 1;

create sequence SEQPESSOA
increment 1;

create sequence SEQPESSOACONTA
increment 1;

create sequence SEQPESSOAREDE
increment 1;

create sequence SEQPLANOASSINATURA
increment 1;

create sequence SEQPRODUTO
increment 1;

create sequence SEQPRODUTOCOMPOSICAO
increment 1;

create sequence SEQTELEFONE
increment 1;

create sequence SEQUSUARIO
increment 1;

create sequence SEQVALORPRODUTO
increment 1;

/*==============================================================*/
/* Table: BANCO                                                 */
/*==============================================================*/
create table BANCO (
   CODBANCO             INT4                 not null,
   DSBANCO              VARCHAR(40)          not null,
   constraint PK_BANCO primary key (CODBANCO)
);

/*==============================================================*/
/* Index: INDEX_7                                               */
/*==============================================================*/
create unique index INDEX_7 on BANCO (
CODBANCO
);

/*==============================================================*/
/* Table: CATEGORIA                                             */
/*==============================================================*/
create table CATEGORIA (
   IDCATEGORIA          BIGINT               not null,
   DSCATEGORIA          VARCHAR(40)          not null,
   IDCATEGORIAPAI       BIGINT               null,
   constraint PK_CATEGORIA primary key (IDCATEGORIA)
);

/*==============================================================*/
/* Index: INDEX_13                                              */
/*==============================================================*/
create unique index INDEX_13 on CATEGORIA (
IDCATEGORIA
);

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE (
   IDCLIENTE            BIGINT               not null,
   IDPLANOASSINATURA    BIGINT               not null,
   IDSITUACAOCLIENTE    BIGINT               not null,
   constraint PK_CLIENTE primary key (IDCLIENTE)
);

/*==============================================================*/
/* Index: INDEX_26                                              */
/*==============================================================*/
create unique index INDEX_26 on CLIENTE (
IDCLIENTE
);

/*==============================================================*/
/* Table: CLIENTEREDE                                           */
/*==============================================================*/
create table CLIENTEREDE (
   IDCLIENTEREDE        BIGINT               not null,
   IDCLIENTE            BIGINT               not null,
   IDCLIENTEPAI         BIGINT               null,
   IDCLIENTEINDICACAO   BIGINT               null,
   constraint PK_CLIENTEREDE primary key (IDCLIENTEREDE)
);

/*==============================================================*/
/* Index: INDEX_6                                               */
/*==============================================================*/
create unique index INDEX_6 on CLIENTEREDE (
IDCLIENTEREDE
);

/*==============================================================*/
/* Table: ENDERECOPESSOA                                        */
/*==============================================================*/
create table ENDERECOPESSOA (
   IDENDERECOPESSOA     BIGINT               not null,
   IDPESSOA             BIGINT               not null,
   IDTIPOENDERECO       BIGINT               not null,
   DSENDERECO           VARCHAR(50)          not null,
   DSNUMERO             VARCHAR(10)          not null,
   DSCOMPLEMENTO        VARCHAR(50)          null,
   DSBAIRRO             VARCHAR(40)          not null,
   DSCIDADE             VARCHAR(40)          not null,
   NUMCEP               VARCHAR(10)          null,
   CODUF                VARCHAR(2)           not null,
   constraint PK_ENDERECOPESSOA primary key (IDENDERECOPESSOA)
);

/*==============================================================*/
/* Index: INDEX_8                                               */
/*==============================================================*/
create unique index INDEX_8 on ENDERECOPESSOA (
IDENDERECOPESSOA
);

/*==============================================================*/
/* Table: FORNECEDOR                                            */
/*==============================================================*/
create table FORNECEDOR (
   IDFORNECEDOR         BIGINT               not null,
   constraint PK_FORNECEDOR primary key (IDFORNECEDOR)
);

/*==============================================================*/
/* Index: INDEX_25                                              */
/*==============================================================*/
create unique index INDEX_25 on FORNECEDOR (
IDFORNECEDOR
);

/*==============================================================*/
/* Table: PEDIDO                                                */
/*==============================================================*/
create table PEDIDO (
   IDPEDIDO             BIGINT               not null,
   IDTIPOPEDIDO         BIGINT               null,
   IDSITUACAOPEDIDO     INT                  null,
   IDCLIENTE            BIGINT               null,
   DATAPEDIDO           TIMESTAMP            null,
   VLTOTALBRUTO         NUMERIC(12,2)        null,
   VLTOTALLIQUIDO       NUMERIC(12,2)        null,
   VLFRETE              NUMERIC(12,2)        null,
   constraint PK_PEDIDO primary key (IDPEDIDO)
);

/*==============================================================*/
/* Index: INDEX_20                                              */
/*==============================================================*/
create unique index INDEX_20 on PEDIDO (
IDPEDIDO
);

/*==============================================================*/
/* Table: PEDIDOPRODUTO                                         */
/*==============================================================*/
create table PEDIDOPRODUTO (
   IDPEDIDOPRODUTO      BIGINT               not null,
   IDPEDIDO             BIGINT               not null,
   IDPRODUTO            BIGINT               not null,
   IDVALORPRODUTO       BIGINT               null,
   QTPRODUTO            BIGINT               not null,
   VLDESCONTO           NUMERIC(12,2)        null,
   constraint PK_PEDIDOPRODUTO primary key (IDPEDIDOPRODUTO)
);

/*==============================================================*/
/* Index: INDEX_21                                              */
/*==============================================================*/
create unique index INDEX_21 on PEDIDOPRODUTO (
IDPEDIDOPRODUTO
);

/*==============================================================*/
/* Table: PESSOA                                                */
/*==============================================================*/
create table PESSOA (
   IDPESSOA             BIGINT               not null,
   NOMEPESSOA           VARCHAR(50)          not null,
   DSRAZAOSOCIAL        VARCHAR(50)          null,
   CODTIPOPESSOA        CHAR(1)              not null
      constraint CKC_CODTIPOPESSOA_PESSOA check (CODTIPOPESSOA in ('F','J')),
   NUMCPFCNPJ           VARCHAR(14)          not null,
   CODSEXO              CHAR(1)              null
      constraint CKC_CODSEXO_PESSOA check (CODSEXO is null or (CODSEXO in ('M','F'))),
   DATANASCIMENTO       DATE                 null,
   DATACADASTRO         DATE                 not null,
   DSEMAIL              VARCHAR(50)          null,
   constraint PK_PESSOA primary key (IDPESSOA)
);

/*==============================================================*/
/* Index: INDEX_3                                               */
/*==============================================================*/
create  index INDEX_3 on PESSOA (
IDPESSOA
);

/*==============================================================*/
/* Table: PESSOACONTA                                           */
/*==============================================================*/
create table PESSOACONTA (
   IDPESSOACONTA        BIGINT               not null,
   IDPESSOA             BIGINT               not null,
   IDTIPOCONTA          BIGINT               null,
   CODBANCO             INT4                 not null,
   NUMAGENCIA           VARCHAR(10)          not null,
   NUMCONTA             BIGINT               not null,
   BOLCONTAPRINCIPAL    BOOL                 not null,
   constraint PK_PESSOACONTA primary key (IDPESSOACONTA)
);

/*==============================================================*/
/* Index: INDEX_4                                               */
/*==============================================================*/
create  index INDEX_4 on PESSOACONTA (
IDPESSOACONTA
);

/*==============================================================*/
/* Table: PLANOASSINATURA                                       */
/*==============================================================*/
create table PLANOASSINATURA (
   IDPLANOASSINATURA    BIGINT               not null,
   NOMEPLANOASSINATURA  VARCHAR(30)          not null,
   DSPLANOASSINATURA    TEXT                 null,
   VLADESAO             MONEY                null,
   VLRENOVACAO          MONEY                null,
   VLCOMPRAMINIMA       MONEY                null,
   BOLATIVO             BOOL                 not null,
   constraint PK_PLANOASSINATURA primary key (IDPLANOASSINATURA)
);

comment on column PLANOASSINATURA.DSPLANOASSINATURA is
'DESCRIÇÃO DETALHADA DO PLANO';

/*==============================================================*/
/* Index: INDEX_9                                               */
/*==============================================================*/
create unique index INDEX_9 on PLANOASSINATURA (
IDPLANOASSINATURA
);

/*==============================================================*/
/* Table: PRODUTO                                               */
/*==============================================================*/
create table PRODUTO (
   IDPRODUTO            BIGINT               not null,
   IDCATEGORIA          BIGINT               null,
   IDUNIDADEVENDA       BIGINT               not null,
   IDFORNECEDOR         BIGINT               null,
   NOMEPRODUTO          VARCHAR(40)          not null,
   DSPRODUTO            TEXT                 null,
   PERCMARGEMVENDA      NUMERIC(10,4)        null,
   QTDPESO              NUMERIC(10,4)        null,
   QTDALTURA            NUMERIC(10,4)        null,
   QTDLARGURA           NUMERIC(10,4)        null,
   VLPONTOPRODUTO       BIGINT               null,
   BOLVISIVEL           BOOL                 null,
   BOLCOMPOSICAO        BOOL                 not null,
   constraint PK_PRODUTO primary key (IDPRODUTO)
);

comment on column PRODUTO.VLPONTOPRODUTO is
'VALOR DO PRODUTO EM PONTOS PARA O MMN';

comment on column PRODUTO.BOLCOMPOSICAO is
'VERIFICA SE O PRODUTO É UMA COMPOSIÇÃO DE OUTROS PRODUTOS';

/*==============================================================*/
/* Index: INDEX_22                                              */
/*==============================================================*/
create unique index INDEX_22 on PRODUTO (
IDPRODUTO
);

/*==============================================================*/
/* Table: PRODUTOCOMPOSICAO                                     */
/*==============================================================*/
create table PRODUTOCOMPOSICAO (
   IDPRODUTOCOMPOSICAO  SERIAL               not null,
   IDPRODUTO            BIGINT               not null,
   QTPRODUTOCOMPOSICAO  BIGINT               not null,
   constraint PK_PRODUTOCOMPOSICAO primary key (IDPRODUTOCOMPOSICAO)
);

/*==============================================================*/
/* Index: INDEX_12                                              */
/*==============================================================*/
create unique index INDEX_12 on PRODUTOCOMPOSICAO (
IDPRODUTOCOMPOSICAO
);

/*==============================================================*/
/* Table: SITUACAOCLIENTE                                       */
/*==============================================================*/
create table SITUACAOCLIENTE (
   IDSITUACAOCLIENTE    BIGINT               not null,
   DSSITUACAOCLIENTE    VARCHAR(30)          not null,
   constraint PK_SITUACAOCLIENTE primary key (IDSITUACAOCLIENTE)
);

/*==============================================================*/
/* Index: INDEX_10                                              */
/*==============================================================*/
create unique index INDEX_10 on SITUACAOCLIENTE (
IDSITUACAOCLIENTE
);

/*==============================================================*/
/* Table: SITUACAOPEDIDO                                        */
/*==============================================================*/
create table SITUACAOPEDIDO (
   IDSITUACAOPEDIDO     INT                  not null,
   DSSITUACAOPEDIDO     VARCHAR(30)          not null,
   constraint PK_SITUACAOPEDIDO primary key (IDSITUACAOPEDIDO)
);

comment on table SITUACAOPEDIDO is
'SITUAÇÃO DO PEDIDO (ABERTO, APROVADO)';

/*==============================================================*/
/* Index: INDEX_17                                              */
/*==============================================================*/
create unique index INDEX_17 on SITUACAOPEDIDO (
IDSITUACAOPEDIDO
);

/*==============================================================*/
/* Table: TELEFONE                                              */
/*==============================================================*/
create table TELEFONE (
   IDTELEFONE           BIGINT               not null,
   IDTIPOTELEFONE       BIGINT               null,
   IDPESSOA             BIGINT               not null,
   DSTELEFONE           VARCHAR(15)          not null,
   constraint PK_TELEFONE primary key (IDTELEFONE)
);

/*==============================================================*/
/* Index: INDEX_11                                              */
/*==============================================================*/
create unique index INDEX_11 on TELEFONE (
IDTELEFONE
);

/*==============================================================*/
/* Table: TIPOCONTA                                             */
/*==============================================================*/
create table TIPOCONTA (
   IDTIPOCONTA          BIGINT               not null,
   DSTIPOCONTA          VARCHAR(30)          not null,
   constraint PK_TIPOCONTA primary key (IDTIPOCONTA)
);

/*==============================================================*/
/* Index: INDEX_5                                               */
/*==============================================================*/
create  index INDEX_5 on TIPOCONTA (
IDTIPOCONTA
);

/*==============================================================*/
/* Table: TIPOENDERECO                                          */
/*==============================================================*/
create table TIPOENDERECO (
   IDTIPOENDERECO       BIGINT               not null,
   DSTIPOENDERECO       VARCHAR(30)          not null,
   constraint PK_TIPOENDERECO primary key (IDTIPOENDERECO)
);

/*==============================================================*/
/* Index: INDEX_14                                              */
/*==============================================================*/
create unique index INDEX_14 on TIPOENDERECO (
IDTIPOENDERECO
);

/*==============================================================*/
/* Table: TIPOPEDIDO                                            */
/*==============================================================*/
create table TIPOPEDIDO (
   IDTIPOPEDIDO         BIGINT               not null,
   DSTIPOPEDIDO         VARCHAR(50)          null,
   constraint PK_TIPOPEDIDO primary key (IDTIPOPEDIDO)
);

/*==============================================================*/
/* Index: INDEX_23                                              */
/*==============================================================*/
create unique index INDEX_23 on TIPOPEDIDO (
IDTIPOPEDIDO
);

/*==============================================================*/
/* Table: TIPOTELEFONE                                          */
/*==============================================================*/
create table TIPOTELEFONE (
   IDTIPOTELEFONE       BIGINT               not null,
   DSTIPOTELEFONE       VARCHAR(30)          not null,
   constraint PK_TIPOTELEFONE primary key (IDTIPOTELEFONE)
);

/*==============================================================*/
/* Index: INDEX_15                                              */
/*==============================================================*/
create unique index INDEX_15 on TIPOTELEFONE (
IDTIPOTELEFONE
);

/*==============================================================*/
/* Table: UF                                                    */
/*==============================================================*/
create table UF (
   CODUF                VARCHAR(2)           not null,
   DSUF                 VARCHAR(40)          not null,
   constraint PK_UF primary key (CODUF)
);

/*==============================================================*/
/* Index: INDEX_16                                              */
/*==============================================================*/
create unique index INDEX_16 on UF (
CODUF
);

/*==============================================================*/
/* Table: UNIDADEVENDA                                          */
/*==============================================================*/
create table UNIDADEVENDA (
   IDUNIDADEVENDA       BIGINT               not null,
   DSUNIDADEVENDA       VARCHAR(50)          null,
   constraint PK_UNIDADEVENDA primary key (IDUNIDADEVENDA)
);

comment on table UNIDADEVENDA is
'Garrafa, frasco, pote etc';

/*==============================================================*/
/* Index: INDEX_24                                              */
/*==============================================================*/
create unique index INDEX_24 on UNIDADEVENDA (
IDUNIDADEVENDA
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   IDUSUARIO            BIGINT               not null,
   DSUSUARIO            VARCHAR(50)          not null,
   DSSENHA              VARCHAR(30)          not null,
   constraint PK_USUARIO primary key (IDUSUARIO)
);

/*==============================================================*/
/* Index: INDEX_1                                               */
/*==============================================================*/
create  index INDEX_1 on USUARIO (
IDUSUARIO
);

/*==============================================================*/
/* Table: USUARIOPESSOA                                         */
/*==============================================================*/
create table USUARIOPESSOA (
   IDUSUARIOPESSOA      BIGINT               not null,
   IDPESSOA             BIGINT               not null,
   IDUSUARIO            BIGINT               not null,
   constraint PK_USUARIOPESSOA primary key (IDUSUARIOPESSOA)
);

/*==============================================================*/
/* Index: INDEX_2                                               */
/*==============================================================*/
create  index INDEX_2 on USUARIOPESSOA (
IDUSUARIOPESSOA
);

/*==============================================================*/
/* Table: VALORPRODUTO                                          */
/*==============================================================*/
create table VALORPRODUTO (
   IDVALORPRODUTO       BIGINT               not null,
   IDPRODUTO            BIGINT               not null,
   VLCUSTO              NUMERIC(12,2)        null,
   VLPRODUTO            NUMERIC(12,2)        not null,
   VLDESCONTO           NUMERIC(12,2)        null,
   DTATUALIZACAO        TIMESTAMP            not null,
   constraint PK_VALORPRODUTO primary key (IDVALORPRODUTO)
);

/*==============================================================*/
/* Index: INDEX_18                                              */
/*==============================================================*/
create  index INDEX_18 on VALORPRODUTO (
IDVALORPRODUTO
);

alter table CATEGORIA
   add constraint FK_CATEGORI_REFERENCE_CATEGORI foreign key (IDCATEGORIAPAI)
      references CATEGORIA (IDCATEGORIA)
      on delete restrict on update restrict;

alter table CLIENTE
   add constraint FK_CLIENTE_REFERENCE_PESSOA foreign key (IDCLIENTE)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table CLIENTE
   add constraint FK_CLIENTE_REFERENCE_PLANOASS foreign key (IDPLANOASSINATURA)
      references PLANOASSINATURA (IDPLANOASSINATURA)
      on delete restrict on update restrict;

alter table CLIENTE
   add constraint FK_CLIENTE_REFERENCE_SITUACAO foreign key (IDSITUACAOCLIENTE)
      references SITUACAOCLIENTE (IDSITUACAOCLIENTE)
      on delete restrict on update restrict;

alter table CLIENTEREDE
   add constraint FK_CLIENTE_CLIENTEREDEIND foreign key (IDCLIENTEINDICACAO)
      references CLIENTE (IDCLIENTE)
      on delete restrict on update restrict;

alter table CLIENTEREDE
   add constraint FK_CLIENTE_CLIENTEREDEPAI foreign key (IDCLIENTEPAI)
      references CLIENTE (IDCLIENTE)
      on delete restrict on update restrict;

alter table CLIENTEREDE
   add constraint FK_CLIENTE_CLIREDE foreign key (IDCLIENTE)
      references CLIENTE (IDCLIENTE)
      on delete restrict on update restrict;

alter table ENDERECOPESSOA
   add constraint FK_ENDERECO_REFERENCE_PESSOA foreign key (IDPESSOA)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table ENDERECOPESSOA
   add constraint FK_ENDERECO_REFERENCE_UF foreign key (CODUF)
      references UF (CODUF)
      on delete restrict on update restrict;

alter table ENDERECOPESSOA
   add constraint FK_ENDERECO_REFERENCE_TIPOENDE foreign key (IDTIPOENDERECO)
      references TIPOENDERECO (IDTIPOENDERECO)
      on delete restrict on update restrict;

alter table FORNECEDOR
   add constraint FK_FORNECED_REFERENCE_PESSOA foreign key (IDFORNECEDOR)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table PEDIDO
   add constraint FK_PEDIDO_REFERENCE_TIPOPEDI foreign key (IDTIPOPEDIDO)
      references TIPOPEDIDO (IDTIPOPEDIDO)
      on delete restrict on update restrict;

alter table PEDIDO
   add constraint FK_PEDIDO_REFERENCE_SITUACAO foreign key (IDSITUACAOPEDIDO)
      references SITUACAOPEDIDO (IDSITUACAOPEDIDO)
      on delete restrict on update restrict;

alter table PEDIDO
   add constraint FK_PEDIDO_REFERENCE_CLIENTE foreign key (IDCLIENTE)
      references CLIENTE (IDCLIENTE)
      on delete restrict on update restrict;

alter table PEDIDOPRODUTO
   add constraint FK_PEDIDOPR_REFERENCE_PEDIDO foreign key (IDPEDIDO)
      references PEDIDO (IDPEDIDO)
      on delete restrict on update restrict;

alter table PEDIDOPRODUTO
   add constraint FK_PEDIDOPR_REFERENCE_PRODUTO foreign key (IDPRODUTO)
      references PRODUTO (IDPRODUTO)
      on delete restrict on update restrict;

alter table PEDIDOPRODUTO
   add constraint FK_PEDIDOPR_REFERENCE_VALORPRO foreign key (IDVALORPRODUTO)
      references VALORPRODUTO (IDVALORPRODUTO)
      on delete restrict on update restrict;

alter table PESSOACONTA
   add constraint FK_PESSOACO_FK_PESSOA_PESSOA foreign key (IDPESSOA)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table PESSOACONTA
   add constraint FK_PESSOACO_FK_PESSOA_TIPOCONT foreign key (IDTIPOCONTA)
      references TIPOCONTA (IDTIPOCONTA)
      on delete restrict on update restrict;

alter table PESSOACONTA
   add constraint FK_PESSOACO_REFERENCE_BANCO foreign key (CODBANCO)
      references BANCO (CODBANCO)
      on delete restrict on update restrict;

alter table PRODUTO
   add constraint FK_PRODUTO_REFERENCE_CATEGORI foreign key (IDCATEGORIA)
      references CATEGORIA (IDCATEGORIA)
      on delete restrict on update restrict;

alter table PRODUTO
   add constraint FK_PRODUTO_REFERENCE_UNIDADEV foreign key (IDUNIDADEVENDA)
      references UNIDADEVENDA (IDUNIDADEVENDA)
      on delete restrict on update restrict;

alter table PRODUTO
   add constraint FK_PRODUTO_REFERENCE_FORNECED foreign key (IDFORNECEDOR)
      references FORNECEDOR (IDFORNECEDOR)
      on delete restrict on update restrict;

alter table PRODUTOCOMPOSICAO
   add constraint FK_PRODUTOC_REFERENCE_PRODUTO foreign key (IDPRODUTO)
      references PRODUTO (IDPRODUTO)
      on delete restrict on update restrict;

alter table TELEFONE
   add constraint FK_TELEFONE_REFERENCE_TIPOTELE foreign key (IDTIPOTELEFONE)
      references TIPOTELEFONE (IDTIPOTELEFONE)
      on delete restrict on update restrict;

alter table TELEFONE
   add constraint FK_TELEFONE_REFERENCE_PESSOA foreign key (IDPESSOA)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table USUARIOPESSOA
   add constraint FK_USUARIOP_FK_USUARI_PESSOA foreign key (IDPESSOA)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table USUARIOPESSOA
   add constraint FK_USUARIOP_FK_USUARI_USUARIO foreign key (IDUSUARIO)
      references USUARIO (IDUSUARIO)
      on delete restrict on update restrict;

alter table VALORPRODUTO
   add constraint FK_VALORPRO_REFERENCE_PRODUTO foreign key (IDPRODUTO)
      references PRODUTO (IDPRODUTO)
      on delete restrict on update restrict;

