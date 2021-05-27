
insert into "users"(rolee,id,username,password,namee,surname,phoneNumber,email,birthDate,rola,active) values ('trainer',nextval('seq_user'),'Deks','123','Dejan','Dvornic','1234','Dejan@','1998-02-08','trainer',true);
insert into "fitnesscenter"(id,name,address,phone,email) values (nextval('seq_fitnesscenter'),'synergy','veselina','12321321','synergy@');