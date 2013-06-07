-- criação de indices e chaves
ALTER TABLE user_role 
	ADD CONSTRAINT pk_role_user PRIMARY KEY (role_id, user_id) 
		USING INDEX TABLESPACE pg_default;
-- 
-- Montando os elementos basicos da autenticação
-- 
-- Tabela de Papeis
INSERT INTO role( id, authority, description)  VALUES (-1, '', '');
INSERT INTO role( id, authority, description)  VALUES (1, 'ROLE_USER', 'Usuário comum do sistema');
INSERT INTO role( id, authority, description)  VALUES (2, 'ROLE_ADMIN', 'Administrador do sistema');

--Tabela de usuários
INSERT INTO tb_user( id, password, name, username,enable, role_id) VALUES (1, '123','Marcio Robson', 'robsonmrsp', true, 1);
INSERT INTO tb_user( id, password, name, username, enable,role_id) VALUES (2, '123','Administrador', 'admin', true, 2);


