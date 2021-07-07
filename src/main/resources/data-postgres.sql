insert into "fitnesscenter"(id,name,address,phone,email) values (nextval('seq_fitnesscenter'),'synergy','veselina','12321321','synergy@');
insert into "users"(rolee,id,password,namee,korisnickoime,surname,fitnescentar_id,phoneNumber,email,birthDate,rola,active,enabled) values ('trainer',nextval('seq_user'),'$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou','Dejan','deks','Dvornic',1,'1234','dvornicdejan08@gmail.com','1998-02-08','trainer',true,true);
insert into "users"(rolee,id,password,namee,korisnickoime,surname,phoneNumber,email,birthDate,rola,active,enabled) values ('admin',nextval('seq_user'),'$2y$10$1p9FJ4b6sWPs97ACLIeG8.5VRhK4.4roqBGVI6PQjmnOyKM2jCwou','Ivana','Ivanica','Dvornic','1234','dejandvornic08@gmail.com','1998-02-08','trainer',true,true);

insert into "training"(id,name,description,type,duration) values (nextval('seq_training'),'botcamp','bas jako','kardio','50');
insert into "training"(id,name,description,type,duration) values (nextval('seq_training'),'botcamps','bas jakos','kardios','500');
insert into "schedule"(id,price,slobodnih_mesta,beginDate,training_id) values (nextval('seq_schedule'),100,10,'2020-10-01',1);
insert into "schedule"(id,price,slobodnih_mesta,beginDate,training_id) values (nextval('seq_schedule'),160,20,'2021-11-01',2);
insert into "hall"(id,capacity,label,fitnessala_id) values (nextval('seq_hall'),10,'nesto',1);
INSERT INTO AUTHORITY (name) VALUES ('ROLE_TRAINER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 3);