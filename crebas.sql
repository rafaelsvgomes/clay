/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     09/10/2015 09:14:09                          */
/*==============================================================*/


drop index INDEX_7;

drop table BANCO;

drop index INDEX_13;

drop table CATEGORIA;

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

drop index INDEX_19;

drop index INDEX_6;

drop table PESSOAREDE;

drop index INDEX_9;

drop table PLANOASSINATURA;

drop index INDEX_22;

drop table PRODUTO;

drop index INDEX_10;

drop table SITUACAOPESSOA;

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

drop sequence SEQPESSOA;

drop sequence SEQPESSOACONTA;

drop sequence SEQPESSOAREDE;

drop sequence SEQPLANOASSINATURA;

drop sequence SEQPRODUTO;

drop sequence SEQTELEFONE;

drop sequence SEQUSUARIO;

drop sequence SEQVALORPRODUTO;

create sequence SEQENDERECOPESSOA
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
   IDCATEGORIA          INT8                 not null,
   DSCATEGORIA          VARCHAR(40)          not null,
   IDCATEGORIAPAI       INT8                 null,
   constraint PK_CATEGORIA primary key (IDCATEGORIA)
);

/*==============================================================*/
/* Index: INDEX_13                                              */
/*==============================================================*/
create unique index INDEX_13 on CATEGORIA (
IDCATEGORIA
);

/*==============================================================*/
/* Table: ENDERECOPESSOA                                        */
/*==============================================================*/
create table ENDERECOPESSOA (
   IDENDERECOPESSOA     INT8                 not null,
   IDPESSOA             INT8                 null,
   IDTIPOENDERECO       INT4                 null,
   DSENDERECO           VARCHAR(50)          null,
   DSNUMERO             VARCHAR(10)          null,
   DSCOMPLEMENTO        VARCHAR(50)          null,
   DSBAIRRO             VARCHAR(40)          null,
   DSCIDADE             VARCHAR(40)          null,
   NUMCEP               VARCHAR(10)          null,
   CODUF                VARCHAR(2)           null,
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
   IDFORNECEDOR         INT8                 not null,
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
   IDPEDIDO             INT8                 not null,
   IDTIPOPEDIDO         INT8                 null,
   IDPESSOA             INT8                 null,
   DATAPEDIDO           TIMESTAMP            null,
   VLTOTALBRUTO         MONEY                null,
   VLTOTALLIQUIDO       MONEY                null,
   VLFRETE              MONEY                null,
   STPEDIDO             INT2                 null,
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
   IDPEDIDOPRODUTO      INT8                 not null,
   IDPEDIDO             INT8                 null,
   IDPRODUTO            INT8                 null,
   QTPRODUTO            INT8                 null,
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
   IDPESSOA             INT8                 not null,
   NOMEPESSOA           VARCHAR(50)          not null,
   DSRAZAOSOCIAL        VARCHAR(50)          null,
   CODTIPOPESSOA        CHAR(1)              null
      constraint CKC_CODTIPOPESSOA_PESSOA check (CODTIPOPESSOA is null or (CODTIPOPESSOA in ('F','J'))),
   IDSITUACAOPESSOA     INT4                 null,
   IDPLANOASSINATURA    INT4                 null,
   NUMCPFCNPJ           VARCHAR(14)          not null,
   CODSEXO              CHAR(1)              null
      constraint CKC_CODSEXO_PESSOA check (CODSEXO is null or (CODSEXO in ('M','F'))),
   DATANASCIMENTO       DATE                 null,
   DATACADASTRO         DATE                 null,
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
   IDPESSOACONTA        INT8                 not null,
   IDPESSOA             INT8                 null,
   IDTIPOCONTA          INT4                 null,
   CODBANCO             INT4                 null,
   NUMAGENCIA           VARCHAR(10)          null,
   NUMCONTA             DECIMAL(15,15)       null,
   BOLCONTAPRINCIPAL    BOOL                 null,
   constraint PK_PESSOACONTA primary key (IDPESSOACONTA)
);

/*==============================================================*/
/* Index: INDEX_4                                               */
/*==============================================================*/
create  index INDEX_4 on PESSOACONTA (
IDPESSOACONTA
);

/*==============================================================*/
/* Table: PESSOAREDE                                            */
/*==============================================================*/
create table PESSOAREDE (
   IDPESSOAREDE         INT8                 not null,
   IDPESSOA             INT8                 not null,
   IDPESSOAPAI          INT8                 not null,
   IDPESSOAINDICACAO    INT8                 not null,
   constraint PK_PESSOAREDE primary key (IDPESSOAREDE)
);

/*==============================================================*/
/* Index: INDEX_6                                               */
/*==============================================================*/
create  index INDEX_6 on PESSOAREDE (
IDPESSOAREDE
);

/*==============================================================*/
/* Index: INDEX_19                                              */
/*==============================================================*/
create unique index INDEX_19 on PESSOAREDE (
IDPESSOA,
IDPESSOAPAI,
IDPESSOAINDICACAO
);

/*==============================================================*/
/* Table: PLANOASSINATURA                                       */
/*==============================================================*/
create table PLANOASSINATURA (
   IDPLANOASSINATURA    INT4                 not null,
   NOMEPLANOASSINATURA  VARCHAR(30)          not null,
   DSPLANOASSINATURA    TEXT                 null,
   VLADESAO             MONEY                null,
   VLRENOVACAO          MONEY                null,
   VLCOMPRAMINIMA       MONEY                null,
   BOLATIVO             BOOL                 null,
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
   IDPRODUTO            INT8                 not null,
   IDCATEGORIA          INT8                 null,
   IDUNIDADEVENDA       INT8                 null,
   IDFORNECEDOR         INT8                 null,
   NOMEPRODUTO          VARCHAR(40)          null,
   DSPRODUTO            TEXT                 null,
   PERCMARGEMVENDA      MONEY                null,
   QTDPESO              NUMERIC(8,4)         null,
   QTDALTURA            NUMERIC(8,4)         null,
   QTDLARGURA           NUMERIC(8,4)         null,
   BOLVISIVEL           BOOL                 null,
   VLPONTOPRODUTO       INT8                 null,
   constraint PK_PRODUTO primary key (IDPRODUTO)
);

/*==============================================================*/
/* Index: INDEX_22                                              */
/*==============================================================*/
create unique index INDEX_22 on PRODUTO (
IDPRODUTO
);

/*==============================================================*/
/* Table: SITUACAOPESSOA                                        */
/*==============================================================*/
create table SITUACAOPESSOA (
   IDSITUACAOPESSOA     INT4                 not null,
   DSSITUACAOPESSOA     VARCHAR(30)          not null,
   constraint PK_SITUACAOPESSOA primary key (IDSITUACAOPESSOA)
);

/*==============================================================*/
/* Index: INDEX_10                                              */
/*==============================================================*/
create unique index INDEX_10 on SITUACAOPESSOA (
IDSITUACAOPESSOA
);

/*==============================================================*/
/* Table: TELEFONE                                              */
/*==============================================================*/
create table TELEFONE (
   IDTELEFONE           INT8                 not null,
   IDTIPOTELEFONE       INT4                 null,
   IDPESSOA             INT8                 not null,
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
   IDTIPOCONTA          INT4                 not null,
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
   IDTIPOENDERECO       INT4                 not null,
   DSTIPOENDERECO       VARCHAR(30)          null,
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
   IDTIPOPEDIDO         INT8                 not null,
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
   IDTIPOTELEFONE       INT4                 not null,
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
   IDUNIDADEVENDA       INT8                 not null,
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
   IDUSUARIO            INT8                 not null,
   DSUSUARIO            VARCHAR(50)          null,
   DSSENHA              VARCHAR(30)          null,
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
   IDUSUARIOPESSOA      INT8                 not null,
   IDPESSOA             INT8                 null,
   IDUSUARIO            INT8                 null,
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
   IDVALORPRODUTO       INT8                 not null,
   IDPRODUTO            INT8                 null,
   VLCUSTO              MONEY                null,
   VLPRODUTO            MONEY                null,
   VLDESCONTO           MONEY                null,
   DTATUALIZACAO        TIMESTAMP            null,
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
   add constraint FK_PEDIDO_REFERENCE_PESSOA foreign key (IDPESSOA)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table PEDIDOPRODUTO
   add constraint FK_PEDIDOPR_REFERENCE_PEDIDO foreign key (IDPEDIDO)
      references PEDIDO (IDPEDIDO)
      on delete restrict on update restrict;

alter table PEDIDOPRODUTO
   add constraint FK_PEDIDOPR_REFERENCE_PRODUTO foreign key (IDPRODUTO)
      references PRODUTO (IDPRODUTO)
      on delete restrict on update restrict;

alter table PESSOA
   add constraint FK_PESSOA_REFERENCE_SITUACAO foreign key (IDSITUACAOPESSOA)
      references SITUACAOPESSOA (IDSITUACAOPESSOA)
      on delete restrict on update restrict;

alter table PESSOA
   add constraint FK_PESSOA_REFERENCE_PLANOASS foreign key (IDPLANOASSINATURA)
      references PLANOASSINATURA (IDPLANOASSINATURA)
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

alter table PESSOAREDE
   add constraint FK_PESSOARE_FK_PESSOA_PESSOA foreign key (IDPESSOA)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table PESSOAREDE
   add constraint FK_PESSOARE_REFERENCE_PESSOAIND foreign key (IDPESSOAINDICACAO)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table PESSOAREDE
   add constraint FK_PESSOARE_REFERENCE_PESSOAPAI foreign key (IDPESSOAPAI)
      references PESSOA (IDPESSOA)
      on delete set default on update set default;

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

