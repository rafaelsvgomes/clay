/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     18/02/2016 13:57:05                          */
/*==============================================================*/


drop index IDX_CODBANCO;

drop table BANCO;

drop index IDX_IDCATEGORIA;

drop table CATEGORIA;

drop index IDX_IDCLIENTE;

drop table CLIENTE;

drop index IDX_IDCLIENTEREDE;

drop table CLIENTEREDE;

drop index IDX_IDCLIENTESITUACAO;

drop table CLIENTESITUACAO;

drop index IDX_IDFORNECEDOR;

drop table FORNECEDOR;

drop index IDX_CDGRUPO;

drop table GRUPO;

drop index IDX_IDPEDIDOTIPO2;

drop table ORIGEMPAGAMENTO;

drop index IDX_IDPEDIDO;

drop table PEDIDO;

drop index IDX_IDPEDIDOPRODUTO;

drop table PEDIDOPRODUTO;

drop index IDX_IDPEDIDOSITUACAO2;

drop table PEDIDOPRODUTOSITUACAO;

drop index IDX_IDPEDIDOSITUACAO;

drop table PEDIDOSITUACAO;

drop index IDX_IDPEDIDOTIPO;

drop table PEDIDOTIPO;

drop index IDX_NUMCPFCNPJ;

drop index IDX_IDPESSOA;

drop table PESSOA;

drop index IDX_IDPESSOACONTA;

drop table PESSOACONTA;

drop index IDX_IDPESSOAENDERECO;

drop table PESSOAENDERECO;

drop index IDX_IDPESSOATELEFONE;

drop table PESSOATELEFONE;

drop index IDX_IDPLANOASSINATURA;

drop table PLANOASSINATURA;

drop index IDX_IDPRODUTO;

drop table PRODUTO;

drop index IDX_IDPRODUTOCOMPOSICAO;

drop index IDX_IDPROD_IDPRODITEM;

drop table PRODUTOCOMPOSICAO;

drop index IDX_IDPRODUTOVALOR;

drop table PRODUTOVALOR;

drop index IDX_IDTIPOCONTA;

drop table TIPOCONTA;

drop index IDX_IDTIPOENDERECO;

drop table TIPOENDERECO;

drop index IDX_IDTIPOTELEFONE;

drop table TIPOTELEFONE;

drop index IDX_CODUF;

drop table UF;

drop index IDX_IDUNIDADEVENDA;

drop table UNIDADEVENDA;

drop index IDX_UNQ_DSUSUARIO;

drop index IDX_IDUSUARIO;

drop table USUARIO;

drop index IDX_IDUSUARIOGRUPO;

drop table USUARIOGRUPO;

drop index IDX_IDUSUARIOPESSOA;

drop table USUARIOPESSOA;

drop sequence SEQPEDIDO;

drop sequence SEQPEDIDOPRODUTO;

drop sequence SEQPESSOA;

drop sequence SEQPESSOACONTA;

drop sequence SEQPESSOAENDERECO;

drop sequence SEQPESSOAREDE;

drop sequence SEQPLANOASSINATURA;

drop sequence SEQPRODUTO;

drop sequence SEQPRODUTOCOMPOSICAO;

drop sequence SEQPRODUTOVALOR;

drop sequence SEQPESSOATELEFONE;

drop sequence SEQUSUARIO;

drop sequence SEQUSUARIOGRUPO;

drop sequence SEQUSUARIOPESSOA;

create sequence SEQPEDIDO increment 1;

create sequence SEQPEDIDOPRODUTO increment 1;

create sequence SEQPESSOA increment 1;

create sequence SEQPESSOACONTA increment 1;

create sequence SEQPESSOAENDERECO increment 1;

create sequence SEQPESSOAREDE increment 1;

create sequence SEQPLANOASSINATURA increment 1;

create sequence SEQPRODUTO increment 1;

create sequence SEQPRODUTOCOMPOSICAO increment 1;

create sequence SEQPRODUTOVALOR increment 1;

create sequence SEQPESSOATELEFONE increment 1;

create sequence SEQUSUARIO increment 1;

create sequence SEQUSUARIOGRUPO increment 1;

create sequence SEQUSUARIOPESSOA increment 1;

/*==============================================================*/
/* Table: BANCO                                                 */
/*==============================================================*/
create table BANCO (
   CODBANCO             INT4                 not null,
   DSBANCO              VARCHAR(40)          not null,
   constraint PK_BANCO primary key (CODBANCO)
);

/*==============================================================*/
/* Index: IDX_CODBANCO                                          */
/*==============================================================*/
create unique index IDX_CODBANCO on BANCO (
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
/* Index: IDX_IDCATEGORIA                                       */
/*==============================================================*/
create unique index IDX_IDCATEGORIA on CATEGORIA (
IDCATEGORIA
);

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE (
   IDCLIENTE            BIGINT               not null,
   IDPLANOASSINATURA    BIGINT               not null,
   IDCLIENTESITUACAO    BIGINT               not null,
   DATAATUALIZACAO      TIMESTAMP            not null,
   constraint PK_CLIENTE primary key (IDCLIENTE)
);

/*==============================================================*/
/* Index: IDX_IDCLIENTE                                         */
/*==============================================================*/
create unique index IDX_IDCLIENTE on CLIENTE (
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
   CODNIVEL             BIGINT               null,
   CODLADO              INT2                 null
      constraint CKC_CODLADO_CLIENTER check (CODLADO is null or (CODLADO in (0,1))),
   constraint PK_CLIENTEREDE primary key (IDCLIENTEREDE)
);

comment on column CLIENTEREDE.CODNIVEL is
'Nível do cliente na árvore binária';

comment on column CLIENTEREDE.CODLADO is
'Lado do cliente em relação ao pai (0/1)';

/*==============================================================*/
/* Index: IDX_IDCLIENTEREDE                                     */
/*==============================================================*/
create unique index IDX_IDCLIENTEREDE on CLIENTEREDE (
IDCLIENTEREDE
);

/*==============================================================*/
/* Table: CLIENTESITUACAO                                       */
/*==============================================================*/
create table CLIENTESITUACAO (
   IDCLIENTESITUACAO    BIGINT               not null,
   DSCLIENTESITUACAO    VARCHAR(30)          not null,
   constraint PK_CLIENTESITUACAO primary key (IDCLIENTESITUACAO)
);

comment on table CLIENTESITUACAO is
'CADASTRADO, PENDENTE, ATIVO, INATIVO, BLOQUEADO';

/*==============================================================*/
/* Index: IDX_IDCLIENTESITUACAO                                 */
/*==============================================================*/
create unique index IDX_IDCLIENTESITUACAO on CLIENTESITUACAO (
IDCLIENTESITUACAO
);

/*==============================================================*/
/* Table: FORNECEDOR                                            */
/*==============================================================*/
create table FORNECEDOR (
   IDFORNECEDOR         BIGINT               not null,
   constraint PK_FORNECEDOR primary key (IDFORNECEDOR)
);

/*==============================================================*/
/* Index: IDX_IDFORNECEDOR                                      */
/*==============================================================*/
create unique index IDX_IDFORNECEDOR on FORNECEDOR (
IDFORNECEDOR
);

/*==============================================================*/
/* Table: GRUPO                                                 */
/*==============================================================*/
create table GRUPO (
   CDGRUPO              VARCHAR(50)          not null,
   constraint PK_GRUPO primary key (CDGRUPO)
);

/*==============================================================*/
/* Index: IDX_CDGRUPO                                           */
/*==============================================================*/
create  index IDX_CDGRUPO on GRUPO (
CDGRUPO
);

/*==============================================================*/
/* Table: ORIGEMPAGAMENTO                                       */
/*==============================================================*/
create table ORIGEMPAGAMENTO (
   IDORIGEMPAGAMENTO    BIGINT               not null,
   DSORIGEMPAGAMENTO    VARCHAR(50)          null,
   constraint PK_ORIGEMPAGAMENTO primary key (IDORIGEMPAGAMENTO)
);

comment on table ORIGEMPAGAMENTO is
'Se foi Em mãos, PagSeguro, etc.';

/*==============================================================*/
/* Index: IDX_IDPEDIDOTIPO2                                     */
/*==============================================================*/
create unique index IDX_IDPEDIDOTIPO2 on ORIGEMPAGAMENTO (
IDORIGEMPAGAMENTO
);

/*==============================================================*/
/* Table: PEDIDO                                                */
/*==============================================================*/
create table PEDIDO (
   IDPEDIDO             BIGINT               not null,
   IDPEDIDOTIPO         BIGINT               null,
   IDPEDIDOSITUACAO     INT                  null,
   IDCLIENTE            BIGINT               null,
   IDORIGEMPAGAMENTO    BIGINT               null,
   DATAPEDIDO           TIMESTAMP            null,
   VLTOTALBRUTO         NUMERIC(12,2)        null,
   VLTOTALLIQUIDO       NUMERIC(12,2)        null,
   VLFRETE              NUMERIC(12,2)        null,
   constraint PK_PEDIDO primary key (IDPEDIDO)
);

/*==============================================================*/
/* Index: IDX_IDPEDIDO                                          */
/*==============================================================*/
create unique index IDX_IDPEDIDO on PEDIDO (
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
   IDPEDIDOPRODUTOSITUACAO INT                  null,
   QTPRODUTO            BIGINT               not null,
   VLDESCONTO           NUMERIC(12,2)        null,
   QTPRODUTOENTREGUE    BIGINT               null,
   DATAENTREGA          TIMESTAMP            null,
   constraint PK_PEDIDOPRODUTO primary key (IDPEDIDOPRODUTO)
);

comment on column PEDIDOPRODUTO.QTPRODUTOENTREGUE is
'Quantidade do produto que já foi entregue';

/*==============================================================*/
/* Index: IDX_IDPEDIDOPRODUTO                                   */
/*==============================================================*/
create unique index IDX_IDPEDIDOPRODUTO on PEDIDOPRODUTO (
IDPEDIDOPRODUTO
);

/*==============================================================*/
/* Table: PEDIDOPRODUTOSITUACAO                                 */
/*==============================================================*/
create table PEDIDOPRODUTOSITUACAO (
   IDPEDIDOPRODUTOSITUACAO INT                  not null,
   DSPEDIDOPRODUTOSITUACAO VARCHAR(30)          not null,
   constraint PK_PEDIDOPRODUTOSITUACAO primary key (IDPEDIDOPRODUTOSITUACAO)
);

comment on table PEDIDOPRODUTOSITUACAO is
'SITUAÇÃO DO PEDIDO (ABERTO, APROVADO)';

/*==============================================================*/
/* Index: IDX_IDPEDIDOSITUACAO2                                 */
/*==============================================================*/
create unique index IDX_IDPEDIDOSITUACAO2 on PEDIDOPRODUTOSITUACAO (
IDPEDIDOPRODUTOSITUACAO
);

/*==============================================================*/
/* Table: PEDIDOSITUACAO                                        */
/*==============================================================*/
create table PEDIDOSITUACAO (
   IDPEDIDOSITUACAO     INT                  not null,
   DSPEDIDOSITUACAO     VARCHAR(30)          not null,
   constraint PK_PEDIDOSITUACAO primary key (IDPEDIDOSITUACAO)
);

comment on table PEDIDOSITUACAO is
'SITUAÇÃO DO PEDIDO (ABERTO, PAGO, ENTREGUE PARCIALMENTE, FINALIZADO)';

/*==============================================================*/
/* Index: IDX_IDPEDIDOSITUACAO                                  */
/*==============================================================*/
create unique index IDX_IDPEDIDOSITUACAO on PEDIDOSITUACAO (
IDPEDIDOSITUACAO
);

/*==============================================================*/
/* Table: PEDIDOTIPO                                            */
/*==============================================================*/
create table PEDIDOTIPO (
   IDPEDIDOTIPO         BIGINT               not null,
   DSPEDIDOTIPO         VARCHAR(50)          null,
   constraint PK_PEDIDOTIPO primary key (IDPEDIDOTIPO)
);

/*==============================================================*/
/* Index: IDX_IDPEDIDOTIPO                                      */
/*==============================================================*/
create unique index IDX_IDPEDIDOTIPO on PEDIDOTIPO (
IDPEDIDOTIPO
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
/* Index: IDX_IDPESSOA                                          */
/*==============================================================*/
create  index IDX_IDPESSOA on PESSOA (
IDPESSOA
);

/*==============================================================*/
/* Index: IDX_NUMCPFCNPJ                                        */
/*==============================================================*/
create unique index IDX_NUMCPFCNPJ on PESSOA (
NUMCPFCNPJ
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
/* Index: IDX_IDPESSOACONTA                                     */
/*==============================================================*/
create  index IDX_IDPESSOACONTA on PESSOACONTA (
IDPESSOACONTA
);

/*==============================================================*/
/* Table: PESSOAENDERECO                                        */
/*==============================================================*/
create table PESSOAENDERECO (
   IDPESSOAENDERECO     BIGINT               not null,
   IDPESSOA             BIGINT               not null,
   IDTIPOENDERECO       BIGINT               not null,
   DSENDERECO           VARCHAR(50)          not null,
   DSNUMERO             VARCHAR(10)          not null,
   DSCOMPLEMENTO        VARCHAR(50)          null,
   DSBAIRRO             VARCHAR(40)          not null,
   DSCIDADE             VARCHAR(40)          not null,
   NUMCEP               VARCHAR(10)          null,
   CODUF                VARCHAR(2)           not null,
   constraint PK_PESSOAENDERECO primary key (IDPESSOAENDERECO)
);

/*==============================================================*/
/* Index: IDX_IDPESSOAENDERECO                                  */
/*==============================================================*/
create unique index IDX_IDPESSOAENDERECO on PESSOAENDERECO (
IDPESSOAENDERECO
);

/*==============================================================*/
/* Table: PESSOATELEFONE                                        */
/*==============================================================*/
create table PESSOATELEFONE (
   IDPESSOATELEFONE     BIGINT               not null,
   IDTIPOTELEFONE       BIGINT               null,
   IDPESSOA             BIGINT               not null,
   DSTELEFONE           VARCHAR(15)          not null,
   constraint PK_PESSOATELEFONE primary key (IDPESSOATELEFONE)
);

/*==============================================================*/
/* Index: IDX_IDPESSOATELEFONE                                  */
/*==============================================================*/
create unique index IDX_IDPESSOATELEFONE on PESSOATELEFONE (
IDPESSOATELEFONE
);

/*==============================================================*/
/* Table: PLANOASSINATURA                                       */
/*==============================================================*/
create table PLANOASSINATURA (
   IDPLANOASSINATURA    BIGINT               not null,
   IDPRODUTO            BIGINT               null,
   NOMEPLANOASSINATURA  VARCHAR(30)          not null,
   DSPLANOASSINATURA    TEXT                 null,
   VLADESAO             NUMERIC(12,2)        null,
   VLRENOVACAO          NUMERIC(12,2)        null,
   VLCOMPRAMINIMA       NUMERIC(12,2)        null,
   BOLATIVO             BOOL                 not null,
   constraint PK_PLANOASSINATURA primary key (IDPLANOASSINATURA)
);

comment on column PLANOASSINATURA.DSPLANOASSINATURA is
'DESCRIÇÃO DETALHADA DO PLANO';

/*==============================================================*/
/* Index: IDX_IDPLANOASSINATURA                                 */
/*==============================================================*/
create unique index IDX_IDPLANOASSINATURA on PLANOASSINATURA (
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
   QTDESTOQUE           BIGINT               null,
   QTDESTOQUEMINIMO     BIGINT               null,
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
/* Index: IDX_IDPRODUTO                                         */
/*==============================================================*/
create unique index IDX_IDPRODUTO on PRODUTO (
IDPRODUTO
);

/*==============================================================*/
/* Table: PRODUTOCOMPOSICAO                                     */
/*==============================================================*/
create table PRODUTOCOMPOSICAO (
   IDPRODUTOCOMPOSICAO  BIGINT               not null,
   IDPRODUTO            BIGINT               not null,
   IDPRODUTOITEMCOMP    BIGINT               not null,
   QTPRODUTOCOMPOSICAO  BIGINT               not null,
   constraint PK_PRODUTOCOMPOSICAO primary key (IDPRODUTOCOMPOSICAO)
);

/*==============================================================*/
/* Index: IDX_IDPROD_IDPRODITEM                                 */
/*==============================================================*/
create unique index IDX_IDPROD_IDPRODITEM on PRODUTOCOMPOSICAO (
IDPRODUTO,
IDPRODUTOITEMCOMP
);

/*==============================================================*/
/* Index: IDX_IDPRODUTOCOMPOSICAO                               */
/*==============================================================*/
create unique index IDX_IDPRODUTOCOMPOSICAO on PRODUTOCOMPOSICAO (
IDPRODUTOCOMPOSICAO
);

/*==============================================================*/
/* Table: PRODUTOVALOR                                          */
/*==============================================================*/
create table PRODUTOVALOR (
   IDPRODUTOVALOR       BIGINT               not null,
   IDPRODUTO            BIGINT               not null,
   VLCUSTO              NUMERIC(12,2)        null,
   VLPRODUTO            NUMERIC(12,2)        not null,
   VLDESCONTO           NUMERIC(12,2)        null,
   DATAATUALIZACAO      TIMESTAMP            not null,
   constraint PK_PRODUTOVALOR primary key (IDPRODUTOVALOR)
);

/*==============================================================*/
/* Index: IDX_IDPRODUTOVALOR                                    */
/*==============================================================*/
create  index IDX_IDPRODUTOVALOR on PRODUTOVALOR (
IDPRODUTOVALOR
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
/* Index: IDX_IDTIPOCONTA                                       */
/*==============================================================*/
create  index IDX_IDTIPOCONTA on TIPOCONTA (
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
/* Index: IDX_IDTIPOENDERECO                                    */
/*==============================================================*/
create unique index IDX_IDTIPOENDERECO on TIPOENDERECO (
IDTIPOENDERECO
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
/* Index: IDX_IDTIPOTELEFONE                                    */
/*==============================================================*/
create unique index IDX_IDTIPOTELEFONE on TIPOTELEFONE (
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
/* Index: IDX_CODUF                                             */
/*==============================================================*/
create unique index IDX_CODUF on UF (
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
/* Index: IDX_IDUNIDADEVENDA                                    */
/*==============================================================*/
create unique index IDX_IDUNIDADEVENDA on UNIDADEVENDA (
IDUNIDADEVENDA
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   IDUSUARIO            BIGINT               not null,
   DSUSUARIO            VARCHAR(150)         not null,
   DSSENHA              VARCHAR(150)         not null,
   constraint PK_USUARIO primary key (IDUSUARIO)
);

/*==============================================================*/
/* Index: IDX_IDUSUARIO                                         */
/*==============================================================*/
create  index IDX_IDUSUARIO on USUARIO (
IDUSUARIO
);

/*==============================================================*/
/* Index: IDX_UNQ_DSUSUARIO                                     */
/*==============================================================*/
create unique index IDX_UNQ_DSUSUARIO on USUARIO (
DSUSUARIO
);

/*==============================================================*/
/* Table: USUARIOGRUPO                                          */
/*==============================================================*/
create table USUARIOGRUPO (
   IDUSUARIOGRUPO       BIGINT               not null,
   CDGRUPO              VARCHAR(50)          not null,
   IDUSUARIO            BIGINT               not null,
   constraint PK_USUARIOGRUPO primary key (IDUSUARIOGRUPO)
);

/*==============================================================*/
/* Index: IDX_IDUSUARIOGRUPO                                    */
/*==============================================================*/
create  index IDX_IDUSUARIOGRUPO on USUARIOGRUPO (
IDUSUARIOGRUPO
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
/* Index: IDX_IDUSUARIOPESSOA                                   */
/*==============================================================*/
create  index IDX_IDUSUARIOPESSOA on USUARIOPESSOA (
IDUSUARIOPESSOA
);

alter table CATEGORIA
   add constraint FK_CATEGORI_FK_CATEGO_CATEGORI foreign key (IDCATEGORIAPAI)
      references CATEGORIA (IDCATEGORIA)
      on delete restrict on update restrict;

alter table CLIENTE
   add constraint FK_CLIENTE_FK_CLIENT_CLIENTES foreign key (IDCLIENTESITUACAO)
      references CLIENTESITUACAO (IDCLIENTESITUACAO)
      on delete restrict on update restrict;

alter table CLIENTE
   add constraint FK_CLIENTE_FK_CLIENT_PESSOA foreign key (IDCLIENTE)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table CLIENTE
   add constraint FK_CLIENTE_FK_CLIENT_PLANOASS foreign key (IDPLANOASSINATURA)
      references PLANOASSINATURA (IDPLANOASSINATURA)
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

alter table FORNECEDOR
   add constraint FK_FORNECED_FK_FORNEC_PESSOA foreign key (IDFORNECEDOR)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table PEDIDO
   add constraint FK_PEDIDO_FK_PEDIDO_CLIENTE foreign key (IDCLIENTE)
      references CLIENTE (IDCLIENTE)
      on delete restrict on update restrict;

alter table PEDIDO
   add constraint FK_PEDIDO_FK_PEDIDO_PEDIDOSI foreign key (IDPEDIDOSITUACAO)
      references PEDIDOSITUACAO (IDPEDIDOSITUACAO)
      on delete restrict on update restrict;

alter table PEDIDO
   add constraint FK_PEDIDO_FK_PEDIDO_PEDIDOTI foreign key (IDPEDIDOTIPO)
      references PEDIDOTIPO (IDPEDIDOTIPO)
      on delete restrict on update restrict;

alter table PEDIDO
   add constraint FK_PEDIDO_REFERENCE_ORIGEMPA foreign key (IDORIGEMPAGAMENTO)
      references ORIGEMPAGAMENTO (IDORIGEMPAGAMENTO)
      on delete restrict on update restrict;

alter table PEDIDOPRODUTO
   add constraint FK_PEDIDOPR_FK_PEDIDO_PEDIDO foreign key (IDPEDIDO)
      references PEDIDO (IDPEDIDO)
      on delete restrict on update restrict;

alter table PEDIDOPRODUTO
   add constraint FK_PEDIDOPR_FK_PEDIDO_PRODUTO foreign key (IDPRODUTO)
      references PRODUTO (IDPRODUTO)
      on delete restrict on update restrict;

alter table PEDIDOPRODUTO
   add constraint FK_PEDIDOPR_FK_PEDIDO_PRODUTOV foreign key (IDVALORPRODUTO)
      references PRODUTOVALOR (IDPRODUTOVALOR)
      on delete restrict on update restrict;

alter table PEDIDOPRODUTO
   add constraint FK_PEDIDOPR_REFERENCE_PEDIDOPR foreign key (IDPEDIDOPRODUTOSITUACAO)
      references PEDIDOPRODUTOSITUACAO (IDPEDIDOPRODUTOSITUACAO)
      on delete restrict on update restrict;

alter table PESSOACONTA
   add constraint FK_PESSOACO_FK_PESSOA_BANCO foreign key (CODBANCO)
      references BANCO (CODBANCO)
      on delete restrict on update restrict;

alter table PESSOACONTA
   add constraint FK_PESSOACO_FK_PESSOA_PESSOA foreign key (IDPESSOA)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table PESSOACONTA
   add constraint FK_PESSOACO_FK_PESSOA_TIPOCONT foreign key (IDTIPOCONTA)
      references TIPOCONTA (IDTIPOCONTA)
      on delete restrict on update restrict;

alter table PESSOAENDERECO
   add constraint FK_PESSOAEN_FK_PESSOA_PESSOA foreign key (IDPESSOA)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table PESSOAENDERECO
   add constraint FK_PESSOAEN_FK_PESSOA_TIPOENDE foreign key (IDTIPOENDERECO)
      references TIPOENDERECO (IDTIPOENDERECO)
      on delete restrict on update restrict;

alter table PESSOAENDERECO
   add constraint FK_PESSOAEN_FK_PESSOA_UF foreign key (CODUF)
      references UF (CODUF)
      on delete restrict on update restrict;

alter table PESSOATELEFONE
   add constraint FK_PESSOATE_FK_PESSOA_PESSOA foreign key (IDPESSOA)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table PESSOATELEFONE
   add constraint FK_PESSOATE_FK_PESSOA_TIPOTELE foreign key (IDTIPOTELEFONE)
      references TIPOTELEFONE (IDTIPOTELEFONE)
      on delete restrict on update restrict;

alter table PLANOASSINATURA
   add constraint FK_PLANOASS_FK_PLANOA_PRODUTO foreign key (IDPRODUTO)
      references PRODUTO (IDPRODUTO)
      on delete restrict on update restrict;

alter table PRODUTO
   add constraint FK_PRODUTO_FK_PRODUT_CATEGORI foreign key (IDCATEGORIA)
      references CATEGORIA (IDCATEGORIA)
      on delete restrict on update restrict;

alter table PRODUTO
   add constraint FK_PRODUTO_FK_PRODUT_FORNECED foreign key (IDFORNECEDOR)
      references FORNECEDOR (IDFORNECEDOR)
      on delete restrict on update restrict;

alter table PRODUTO
   add constraint FK_PRODUTO_FK_PRODUT_UNIDADEV foreign key (IDUNIDADEVENDA)
      references UNIDADEVENDA (IDUNIDADEVENDA)
      on delete restrict on update restrict;

alter table PRODUTOCOMPOSICAO
   add constraint FK_PRODUTOCOMPOSICAO_PRODUTO foreign key (IDPRODUTO)
      references PRODUTO (IDPRODUTO)
      on delete restrict on update restrict;

alter table PRODUTOCOMPOSICAO
   add constraint FK_PRODUTOCOMPITEM_PRODUTO foreign key (IDPRODUTOITEMCOMP)
      references PRODUTO (IDPRODUTO)
      on delete restrict on update restrict;

alter table PRODUTOVALOR
   add constraint FK_PRODUTOV_FK_PRODUT_PRODUTO foreign key (IDPRODUTO)
      references PRODUTO (IDPRODUTO)
      on delete restrict on update restrict;

alter table USUARIOGRUPO
   add constraint FK_USUARIOGRUPO_GRUPO foreign key (CDGRUPO)
      references GRUPO (CDGRUPO)
      on delete restrict on update restrict;

alter table USUARIOGRUPO
   add constraint FK_USUARIOG_FK_USUARI_USUARIO foreign key (IDUSUARIO)
      references USUARIO (IDUSUARIO)
      on delete restrict on update restrict;

alter table USUARIOPESSOA
   add constraint FK_USUARIOP_FK_USUARI_PESSOA foreign key (IDPESSOA)
      references PESSOA (IDPESSOA)
      on delete restrict on update restrict;

alter table USUARIOPESSOA
   add constraint FK_USUARIOPESSOA_USUARIO foreign key (IDUSUARIO)
      references USUARIO (IDUSUARIO)
      on delete restrict on update restrict;

