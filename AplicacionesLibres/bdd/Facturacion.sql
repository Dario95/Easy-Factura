/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     2/1/2017 20:52:21                            */
/*==============================================================*/


/*drop index CLIENTE_PK;

drop table CLIENTE;

drop index ESTABLECIMIENTO_PK;

drop table ESTABLECIMIENTO;

drop index RELATIONSHIP_3_FK;

drop index RELATIONSHIP_4_FK;

drop index FACTURA_PK;

drop table FACTURA;

drop index RELATIONSHIP_1_FK;

drop table TIPO_GASTO;*/

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE (
   ID_CLIENTE           VARCHAR(13)          not null,
   CONTRASENA           VARCHAR(30)          not null,
   NOMBRE_CLIENTE       VARCHAR(50)          not null,
   DIRECCION_CLIENTE    VARCHAR(50)          null,
   EMAIL_CLIENTE        VARCHAR(30)          null,
   constraint PK_CLIENTE primary key (ID_CLIENTE)
);

/*==============================================================*/
/* Index: CLIENTE_PK                                            */
/*==============================================================*/
create unique index CLIENTE_PK on CLIENTE (
ID_CLIENTE
);

/*==============================================================*/
/* Table: ESTABLECIMIENTO                                       */
/*==============================================================*/
create table ESTABLECIMIENTO (
   ID_ESTABLECIMIENTO   VARCHAR(13)          not null,
   NOMBRE_ESTABLECIMIENTO VARCHAR(300)          not null,
   DIRECCION_ESTABLECIMIENTO VARCHAR(300)          null,
   TELEFONO_ESTABLECIMIENTO VARCHAR(10)          null,
   constraint PK_ESTABLECIMIENTO primary key (ID_ESTABLECIMIENTO)
);

/*==============================================================*/
/* Index: ESTABLECIMIENTO_PK                                    */
/*==============================================================*/
create unique index ESTABLECIMIENTO_PK on ESTABLECIMIENTO (
ID_ESTABLECIMIENTO
);

/*==============================================================*/
/* Table: FACTURA                                               */
/*==============================================================*/
create table FACTURA (
   ID_FACTURA           VARCHAR(20)          not null,
   ID_CLIENTE           VARCHAR(13)          not null,
   ID_ESTABLECIMIENTO   VARCHAR(13)          not null,
   FECHA_EMISION        DATE                 not null,
   ESTADO_FACTURA       VARCHAR(15)          null,
   AMBIENTE_FACTURA     VARCHAR(15)          null,
   TOTAL_SIN_IVA        MONEY                not null,
   IVA                  MONEY                not null,
   TOTAL_CON_IVA        MONEY                not null,
   constraint PK_FACTURA primary key (ID_FACTURA)
);

/*==============================================================*/
/* Index: FACTURA_PK                                            */
/*==============================================================*/
create unique index FACTURA_PK on FACTURA (
ID_FACTURA
);

/*==============================================================*/
/* Index: RELATIONSHIP_3_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_3_FK on FACTURA (
ID_ESTABLECIMIENTO
);

/*==============================================================*/
/* Index: RELATIONSHIP_4_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_4_FK on FACTURA (
ID_CLIENTE
);

/*==============================================================*/
/* Table: TIPO_GASTO                                            */
/*==============================================================*/
create table TIPO_GASTO (
   ID_FACTURA           VARCHAR(20)          not null,
   TIPO                 VARCHAR(12)          not null,
   TOTAL                MONEY                not null
);

/*==============================================================*/
/* Index: RELATIONSHIP_1_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_1_FK on TIPO_GASTO (
ID_FACTURA
);

alter table FACTURA
   add constraint FK_FACTURA_RELATIONS_ESTABLEC foreign key (ID_ESTABLECIMIENTO)
      references ESTABLECIMIENTO (ID_ESTABLECIMIENTO)
      on delete restrict on update restrict;

alter table FACTURA
   add constraint FK_FACTURA_RELATIONS_CLIENTE foreign key (ID_CLIENTE)
      references CLIENTE (ID_CLIENTE)
      on delete restrict on update restrict;

alter table TIPO_GASTO
   add constraint FK_TIPO_GAS_RELATIONS_FACTURA foreign key (ID_FACTURA)
      references FACTURA (ID_FACTURA)
      on delete restrict on update restrict;

