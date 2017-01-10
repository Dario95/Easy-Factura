/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     10/1/2017 15:26:50                           */
/*==============================================================*/


drop index CLIENTE_PK;

drop table CLIENTE;

drop index ESTABLECIMIENTO_PK;

drop table ESTABLECIMIENTO;

drop index RELATIONSHIP_3_FK;

drop index FACTURA_PK;

drop table FACTURA;

drop index GASTOSANUALESPERSONALES_PK;

drop table GASTOSANUALESPERSONALES;

drop index RELATIONSHIP_5_FK;

drop index HISTORIAL_PAGOS_PK;

drop table HISTORIAL_PAGOS;

drop index RELATIONSHIP_1_FK;

drop table TIPO_GASTO;

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE (
   ID_CLIENTE           CHAR(10)             not null,
   CONTRASENA           VARCHAR(30)          not null,
   NOMBRE_CLIENTE       VARCHAR(50)          not null,
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
   NOMBRE_ESTABLECIMIENTO VARCHAR(300)         not null,
   DIRECCION_ESTABLECIMIENTO VARCHAR(300)         null,
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
   ID_CLIENTE           CHAR(10)             not null,
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
/* Table: GASTOSANUALESPERSONALES                               */
/*==============================================================*/
create table GASTOSANUALESPERSONALES (
   ANIO_GASTOS          INT4                 not null,
   TOTAL_ALIMENTACION   MONEY                not null,
   TOTAL_SALUD          MONEY                not null,
   TOTAL_VIVIENDA       MONEY                not null,
   TOTAL_EDUCACION      MONEY                not null,
   TOTAL_VESTIMENTA     MONEY                not null,
   constraint PK_GASTOSANUALESPERSONALES primary key (ANIO_GASTOS)
);

/*==============================================================*/
/* Index: GASTOSANUALESPERSONALES_PK                            */
/*==============================================================*/
create unique index GASTOSANUALESPERSONALES_PK on GASTOSANUALESPERSONALES (
ANIO_GASTOS
);

/*==============================================================*/
/* Table: HISTORIAL_PAGOS                                       */
/*==============================================================*/
create table HISTORIAL_PAGOS (
   ANIO_HISTORIAL       INT4                 not null,
   ID_CLIENTE           CHAR(10)             null,
   TOTAL_ALIMENTACION   MONEY                null,
   TOTAL_SALUD          MONEY                null,
   TOTAL_VIVIENDA       MONEY                null,
   TOTAL_EDUCACION      MONEY                null,
   TOTAL_VESTIMENTA     MONEY                null,
   TOTAL_NEGOCIOS       MONEY                null,
   TOTAL_OTROS          MONEY                null,
   constraint PK_HISTORIAL_PAGOS primary key (ANIO_HISTORIAL)
);

/*==============================================================*/
/* Index: HISTORIAL_PAGOS_PK                                    */
/*==============================================================*/
create unique index HISTORIAL_PAGOS_PK on HISTORIAL_PAGOS (
ANIO_HISTORIAL
);

/*==============================================================*/
/* Index: RELATIONSHIP_5_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_5_FK on HISTORIAL_PAGOS (
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

alter table HISTORIAL_PAGOS
   add constraint FK_HISTORIA_RELATIONS_CLIENTE foreign key (ID_CLIENTE)
      references CLIENTE (ID_CLIENTE)
      on delete restrict on update restrict;

alter table TIPO_GASTO
   add constraint FK_TIPO_GAS_RELATIONS_FACTURA foreign key (ID_FACTURA)
      references FACTURA (ID_FACTURA)
      on delete restrict on update restrict;


---procedure para borrar clientes
 CREATE OR REPLACE FUNCTION borrarCliente (id varchar) RETURNS void AS $$
     BEGIN
         delete from tipo_gasto where id_factura in (select id_factura from factura where id_cliente=$1);
         delete from factura where id_cliente=$1;
         delete from cliente where id_cliente=$1;
     END;
 $$ LANGUAGE plpgsql;
