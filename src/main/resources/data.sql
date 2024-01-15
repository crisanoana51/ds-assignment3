INSERT INTO role(name) VALUES('ROLE_CLIENT');
INSERT INTO role(name) VALUES('ROLE_ADMIN');
INSERT INTO userr(enabled,first_name,last_name,username,email,password) VALUES (true,'Oana','Crisan','admin','oanacrisan@yahoo.com','$2a$12$k7zfj2HfI9oGn2W27rBYw.PolfhuAJ3uKPA230VlPCHN1HI7aMQfe');
INSERT INTO userr_roles(user_id, roles_id) VALUES (1, 2);
INSERT INTO userr(enabled,first_name,last_name,username,email,password) VALUES (true,'Client','Client','client','client@yahoo.com','$2a$12$k7zfj2HfI9oGn2W27rBYw.PolfhuAJ3uKPA230VlPCHN1HI7aMQfe');
INSERT INTO userr_roles(user_id, roles_id) VALUES (2, 1);