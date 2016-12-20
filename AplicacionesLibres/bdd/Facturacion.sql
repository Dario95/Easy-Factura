/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     25/12/2016 15:56:37                          */
/*==============================================================*/


/*drop index CLIENTE_PK;

drop table CLIENTE;

drop index RELATIONSHIP_2_FK;

drop index RELATIONSHIP_1_FK;

drop table DETALLE;

drop index ESTABLECIMIENTO_PK;

drop table ESTABLECIMIENTO;

drop index RELATIONSHIP_4_FK;

drop index RELATIONSHIP_3_FK;

drop index FACTURA_PK;

drop table FACTURA;

drop index PRODUCTO_PK;

drop table PRODUCTO;*/

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE (
   ID_CLIENTE           VARCHAR(13)          not null,
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
/* Table: DETALLE                                               */
/*==============================================================*/
create table DETALLE (
   ID_PRODUCTO          INT4                 not null,
   ID_FACTURA           VARCHAR(10)          not null,
   CANTIDAD             INT4                 null,
   PRECIO_UNITARIO      MONEY                null,
   TOTAL                MONEY                null
);

/*==============================================================*/
/* Index: RELATIONSHIP_1_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_1_FK on DETALLE (
ID_FACTURA
);

/*==============================================================*/
/* Index: RELATIONSHIP_2_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_2_FK on DETALLE (
ID_PRODUCTO
);

/*==============================================================*/
/* Table: ESTABLECIMIENTO                                       */
/*==============================================================*/
create table ESTABLECIMIENTO (
   ID_ESTABLECIMIENTO   CHAR(13)             not null,
   NOMBRE_ESTABLECIMIENTO VARCHAR(100)          not null,
   DIRECCION_ESTABLECIMIENTO VARCHAR(200)          null,
   TELEFONO_ESTABLECIMIENTO VARCHAR(11)          null,
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
   ID_FACTURA           VARCHAR(10)          not null,
   ID_CLIENTE           VARCHAR(13)          not null,
   ID_ESTABLECIMIENTO   CHAR(13)             not null,
   FECHA_EMISION        DATE                 not null,
   ESTADO_FACTURA       VARCHAR(15)          null,
   AMBIENTE_FACTURA     VARCHAR(15)          null,
   TIPO_GASTO           VARCHAR(15)          null,
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
/* Table: PRODUCTO                                              */
/*==============================================================*/
create table PRODUCTO (
   ID_PRODUCTO          INT4                 not null,
   DESCRIPCION_PRODUCTO VARCHAR(100)          not null,
   constraint PK_PRODUCTO primary key (ID_PRODUCTO)
);

/*==============================================================*/
/* Index: PRODUCTO_PK                                           */
/*==============================================================*/
create unique index PRODUCTO_PK on PRODUCTO (
ID_PRODUCTO
);

alter table DETALLE
   add constraint FK_DETALLE_RELATIONS_FACTURA foreign key (ID_FACTURA)
      references FACTURA (ID_FACTURA)
      on delete restrict on update restrict;

alter table DETALLE
   add constraint FK_DETALLE_RELATIONS_PRODUCTO foreign key (ID_PRODUCTO)
      references PRODUCTO (ID_PRODUCTO)
      on delete restrict on update restrict;

alter table FACTURA
   add constraint FK_FACTURA_RELATIONS_ESTABLEC foreign key (ID_ESTABLECIMIENTO)
      references ESTABLECIMIENTO (ID_ESTABLECIMIENTO)
      on delete restrict on update restrict;

alter table FACTURA
   add constraint FK_FACTURA_RELATIONS_CLIENTE foreign key (ID_CLIENTE)
      references CLIENTE (ID_CLIENTE)
      on delete restrict on update restrict;

