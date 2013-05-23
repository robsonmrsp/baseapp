-- criação de indices e chaves
ALTER TABLE user_role 
	ADD CONSTRAINT pk_role_user PRIMARY KEY (role_id, user_id) 
		USING INDEX TABLESPACE pg_default;


-- Populando a tabela de tipos de paineis
INSERT INTO panel_type(id, description, name) VALUES (1, '', '');
INSERT INTO panel_type(id, description, name) VALUES (2, 'Painel que concentra os demais paineis', 'TOTEM');
INSERT INTO panel_type(id, description, name) VALUES (3, 'Painel que exibe informações do evento em uma sala', 'HORIZONTAL');


-- Populando a tabela de tipos de paineis
INSERT INTO event_type(id, description, name)    VALUES (1, '', '');
INSERT INTO event_type(id, description, name)    VALUES (2, 'Congresso', 'CONGRESSO');
INSERT INTO event_type(id, description, name)    VALUES (3, 'Workshop', 'WORKSHOP');
INSERT INTO event_type(id, description, name)    VALUES (4, 'Convenção', 'CONVENÇÃO');
INSERT INTO event_type(id, description, name)    VALUES (5, 'outro', 'Outro');

INSERT INTO place(id, description, name)    VALUES (0, '', '');
-- 
-- Montando os elementos basicos da autenticação
-- 
-- Tabela de Papeis
INSERT INTO role( id, authority, description)  VALUES (-1, '', '');
INSERT INTO role( id, authority, description)  VALUES (1, 'ROLE_USER', 'Usuário comum do sistema');
INSERT INTO role( id, authority, description)  VALUES (2, 'ROLE_ADMIN', 'Administrador do sistema');


--Tabela de usuários
INSERT INTO tb_user( id, password, username, enable, role_id) VALUES (1, 'sints123', 'sints', true, 1);
INSERT INTO tb_user( id, password, username, enable,role_id) VALUES (2, 'admin123', 'admin', true, 2);


-- Tabela de associação de papeis e usuários.
--INSERT INTO user_role(role_id, user_id)   VALUES (1, 1);
--INSERT INTO user_role(role_id, user_id)   VALUES (2, 2);




