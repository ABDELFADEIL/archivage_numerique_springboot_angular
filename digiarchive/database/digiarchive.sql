/*==============================================================*/
/* nom de sgbd :  mysql 5.0                                     */
/* date de cr�ation :  04/01/2021 19:07:44                      */
/*==============================================================*/


/*==============================================================*/
/* database : digiarchive                                              */
/*==============================================================*/
create database if not exists digiarchive;
use digiarchive;

/*==============================================================*/
/* table : account                                              */
/*==============================================================*/
create table if not exists account
(
   id           int not null  comment '',
   customer_id          int not null  comment '',
   account_id_type_code int not null  comment '',
   account_id_type_label varchar(55) not null  comment '',
   account_number       int not null  comment '',
   account_creating_date datetime not null  comment '',
   account_status       varchar(55) not null  comment '',
   account_user_id      int not null  comment '',
   primary key (id)
);


/*==============================================================*/
/* table : classification_nature                                */
/*==============================================================*/
create table if not exists classification_nature
(
   id int not null  comment '',
   classification_nature_code int not null  comment '',
   classification_nature_label varchar(55) not null  comment '',
   duration             int not null  comment '',
   primary key (id)
);

/*==============================================================*/
/* table : context                                              */
/*==============================================================*/
create table if not exists context
(
   id           int not null  comment '',
   account_id           int  comment '',
   customer_id          int not null  comment '',
   contract_id          int  comment '',
   classification_nature_id int not null  comment '',
   archiving_reference_date datetime not null  comment '',
   conserv_unit_id      int  comment '',
   deletion_date        datetime  comment '',
   final_business_processing_date datetime  comment '',
   final_stage_date     datetime  comment '',
   final_hold_date      datetime  comment '',
   frozen               bool  comment '',
   frozen_label         varchar(55)  comment '',
   hold_status          bool  comment '',
   min_type             varchar(55)  comment '',
   context_user_id      int not null  comment '',
   primary key (id)
);


/*==============================================================*/
/* table : contract                                             */
/*==============================================================*/
create table if not exists contract
(
   id          int not null  comment '',
   customer_id          int not null  comment '',
   contract_id_type_code int not null  comment '',
   contract_id_type_label varchar(55) not null  comment '',
   contract_number      int not null  comment '',
   contract_creating_date datetime not null  comment '',
   contract_status      varchar(55) not null  comment '',
   contract_user_id     int not null  comment '',
   primary key (id)
);


/*==============================================================*/
/* table : customer                                             */
/*==============================================================*/
create table if not exists customer
(
   id          int not null  comment '',
   birth_date           date not null  comment '',
   birth_dep            varchar(55)  comment '',
   client_first_name    varchar(55)  comment '',
   client_name          varchar(55)  comment '',
   client_nature_id     int  comment '',
   client_number        int not null  comment '',
   siren_number         varchar(55)  comment '',
   siret_number         varchar(55)  comment '',
   customer_status      varchar(55) not null  comment '',
   customer_user_id     int not null  comment '',
   primary key (id)
);

/*==============================================================*/
/* table : destruction_list                                     */
/*==============================================================*/
create table if not exists destruction_list
(
   id  int not null  comment '',
   description          varchar(200)  comment '',
   destruction_document_id int not null  comment '',
   user_validate_id     int  comment '',
   validation           bool  comment '',
   validation_date      datetime  comment '',
   primary key (id)
);

/*==============================================================*/
/* table : document                                             */
/*==============================================================*/
create table if not exists document
(
   id          int not null  comment '',
   context_id           int not null  comment '',
   archiving_format     varchar(55)  comment '',
   file_name            varchar(55)  comment '',
   encoded_doc          text not null  comment '',
   primary key (id)
);



/*==============================================================*/
/* table : event                                                */
/*==============================================================*/
create table if not exists event
(
   id             int not null  comment '',
   event_date           datetime not null  comment '',
   event_status         varchar(55) not null  comment '',
   primary key (id)
);

/*==============================================================*/
/* table : event_context                                        */
/*==============================================================*/
create table if not exists event_context
(
   context_id           int not null  comment '',
   event_id             int not null  comment '',
   primary key (context_id, event_id)
);

/*==============================================================*/
/* index : event_context2_fk                                    */
/*==============================================================*/

alter table event_context drop foreign key event_context2_fk;
alter table event_context add constraint event_context2_fk foreign key (context_id) references context (id) on delete cascade on update cascade;

/*==============================================================*/
/* index : event_context_fk                                     */
/*==============================================================*/

alter table event_context drop foreign key event_context_fk;
alter table event_context add constraint event_context_fk foreign key (event_id) references event (id)
on delete cascade on update cascade;

/*==============================================================*/
/* table : role                                                 */
/*==============================================================*/
create table if not exists role
(
   id              int not null  comment '',
   role_name            varchar(55) not null  comment '',
   primary key (id)
);

/*==============================================================*/
/* table : user                                                 */
/*==============================================================*/
create table if not exists user
(
   id              int not null  comment '',
   last_name            varchar(55)  comment '',
   first_name           varchar(55)  comment '',
   email                varchar(55) not null  comment '',
   uid                  varchar(22) not null  comment '',
   password             varchar(60) not null  comment '',
   primary key (id)
);

/*==============================================================*/
/* table : user_role                                            */
/*==============================================================*/
create table if not exists user_role
(
   user_id              int not null  comment '',
   role_id              int not null  comment '',
   primary key (user_id, role_id)
);

/*==============================================================*/
/* index : user_role2_fk                                        */
/*==============================================================*/

alter table user_role drop foreign key user_role2_fk;
alter table user_role add constraint user_role2_fk foreign key (user_id) references user (id)
on delete cascade on update cascade;
/*==============================================================*/
/* index : user_role_fk                                         */
/*==============================================================*/

alter table user_role drop foreign key user_role_fk;
alter table user_role add constraint user_role_fk foreign key (role_id) references role (id)
on delete cascade on update cascade;

/*==============================================================*/
/* index : association_9_fk                                     */
/*==============================================================*/

alter table account drop foreign key association_9_fk;
alter table account add constraint association_9_fk foreign key (customer_id) references customer (id)
on delete cascade on update cascade;

/*==============================================================*/
/* index : appartenir_fk                                        */
/*==============================================================*/

alter table context drop foreign key appartenir_fk;
alter table context add constraint appartenir_fk foreign key (classification_nature_id) references classification_nature (id) on delete cascade on update cascade;

/*==============================================================*/
/* index : association_2_fk                                     */
/*==============================================================*/

alter table context drop foreign key association_2_fk;
alter table context add constraint association_2_fk foreign key (account_id) references account (id)
on delete cascade on update cascade;

/*==============================================================*/
/* index : association_3_fk                                     */
/*==============================================================*/

alter table context drop foreign key association_3_fk;
alter table context add constraint association_3_fk foreign key (customer_id) references customer (id)
on delete cascade on update cascade;

/*==============================================================*/
/* index : association_4_fk                                     */
/*==============================================================*/

alter table context drop foreign key association_4_fk;
alter table context add constraint association_4_fk foreign key (contract_id) references contract (id)
on delete cascade on update cascade;

/*==============================================================*/
/* index : association_8_fk                                     */
/*==============================================================*/

alter table contract drop foreign key association_8_fk;
alter table contract add constraint association_8_fk foreign key (customer_id) references customer (id)
on delete cascade on update cascade;

/*==============================================================*/
/* index : association_1_fk                                     */
/*==============================================================*/

alter table document drop foreign key association_1_fk;
alter table document add constraint association_1_fk foreign key (context_id) references context (id)
on delete cascade on update cascade;


