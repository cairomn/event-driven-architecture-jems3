-- public.instituicoes definição
-- Drop table
-- DROP TABLE public.instituicoes;
CREATE TABLE public.instituicoes ( id uuid NOT NULL, cnpj varchar(255) NULL, nome varchar(255) NULL, CONSTRAINT instituicoes_pkey PRIMARY KEY (id));

-- public.roles definição
-- Drop table
-- DROP TABLE public.roles;
CREATE TABLE public.roles ( id bigserial NOT NULL, "name" varchar(20) NULL, CONSTRAINT roles_pkey PRIMARY KEY (id));

-- public.users definição
-- Drop table
-- DROP TABLE public.users;
CREATE TABLE public.users ( id int8 NOT NULL, email varchar(255) NULL, nome varchar(255) NULL, senha varchar(255) NULL, CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email), CONSTRAINT uk8mdsk2iooama6t7f1khdietde UNIQUE (nome), CONSTRAINT users_pkey PRIMARY KEY (id));

-- public.blocos definição
-- Drop table
-- DROP TABLE public.blocos;
CREATE TABLE public.blocos ( id uuid NOT NULL, nome varchar(255) NULL, instituicao_id uuid NOT NULL, CONSTRAINT blocos_pkey PRIMARY KEY (id), CONSTRAINT fk9oo9ueigqxc9tuvlpld6j2783 FOREIGN KEY (instituicao_id) REFERENCES public.instituicoes(id));

-- public.pisos definição
-- Drop table
-- DROP TABLE public.pisos;
CREATE TABLE public.pisos ( id uuid NOT NULL, nome varchar(255) NULL, bloco_id uuid NOT NULL, CONSTRAINT pisos_pkey PRIMARY KEY (id), CONSTRAINT fk1ic7ndokg3vro13xi2vloix3a FOREIGN KEY (bloco_id) REFERENCES public.blocos(id));

-- public.salas definição
-- Drop table
-- DROP TABLE public.salas;
CREATE TABLE public.salas ( id uuid NOT NULL, nome varchar(255) NULL, piso_id uuid NOT NULL, CONSTRAINT salas_pkey PRIMARY KEY (id), CONSTRAINT fkndcmftaahon6imbhli9rr0nt6 FOREIGN KEY (piso_id) REFERENCES public.pisos(id));

-- public.user_roles definição
-- Drop table
-- DROP TABLE public.user_roles;
CREATE TABLE public.user_roles ( user_id int8 NOT NULL, role_id int8 NOT NULL, CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id), CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES public.roles(id), CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id));

-- public.microcontroladores definição
-- Drop table
-- DROP TABLE public.microcontroladores;
CREATE TABLE public.microcontroladores ( id uuid NOT NULL, apelido varchar(255) NULL, mac_address varchar(255) NULL, is_deleted bool DEFAULT false NULL, sala_id uuid NULL, CONSTRAINT microcontroladores_pkey PRIMARY KEY (id), CONSTRAINT fk4yo9sv7otb81s0vpnrk3jl6gt FOREIGN KEY (sala_id) REFERENCES public.salas(id));

-- public.sensores definição
-- Drop table
-- DROP TABLE public.sensores;
CREATE TABLE public.sensores ( id uuid NOT NULL, apelido varchar(100) NULL, st_sensor int4 NULL, tp_sensor int4 NULL, microcontrolador_id uuid NULL, CONSTRAINT sensores_pkey PRIMARY KEY (id), CONSTRAINT fksyfjmrj4thn7tycatej655gew FOREIGN KEY (microcontrolador_id) REFERENCES public.microcontroladores(id));

-- public.atuadores definição
-- Drop table
-- DROP TABLE public.atuadores;
CREATE TABLE public.atuadores ( id uuid NOT NULL, apelido varchar(100) NULL, st_atuador int4 NULL, tp_atuador int4 NULL, microcontrolador_id uuid NULL, CONSTRAINT atuadores_pkey PRIMARY KEY (id), CONSTRAINT fk8vb5444vwm17kypaah7kq7h93 FOREIGN KEY (microcontrolador_id) REFERENCES public.microcontroladores(id));

-- CREATE users
INSERT INTO public.users (id, nome, email, senha) VALUES (1, 'VIEWER', 'viewer@admin.com', '$2a$12$p6WYzOtrOly8a91bVC1ii.wdg1wzOvelT9dPmT3iazAz6kH08TVWa'); -- Senha 123456
INSERT INTO public.users (id, nome, email, senha) VALUES (2, 'USER', 'user@admin.com', '$2a$12$p6WYzOtrOly8a91bVC1ii.wdg1wzOvelT9dPmT3iazAz6kH08TVWa'); -- Senha 123456
INSERT INTO public.users (id, nome, email, senha) VALUES (3, 'ADMIN', 'admin@admin.com', '$2a$12$p6WYzOtrOly8a91bVC1ii.wdg1wzOvelT9dPmT3iazAz6kH08TVWa'); -- Senha 123456

-- CREATE roles
INSERT INTO public.roles (id, name) VALUES (1, 'ROLE_VIEWER');
INSERT INTO public.roles (id, name) VALUES (2, 'ROLE_USER');
INSERT INTO public.roles (id, name) VALUES (3, 'ROLE_ADMIN');

-- CREATE user_roles
INSERT INTO public.user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO public.user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO public.user_roles (user_id, role_id) VALUES (3, 3);

-- CREATE instituicoes
INSERT INTO public.instituicoes (id, cnpj, nome) VALUES ('801974f0-df06-4331-8b34-636610bcccad'::uuid, '63025530005173', 'ICMC');

-- CREATE blocos
INSERT INTO public.blocos (id, nome, instituicao_id) VALUES ('cd371472-dd11-4cd0-ac84-1555b2e4f36e'::uuid, 'Computação', '801974f0-df06-4331-8b34-636610bcccad'::uuid);

-- CREATE pisos
INSERT INTO public.pisos (id, nome, bloco_id) VALUES ('380a5629-ee14-459d-8397-43da5fdd1304'::uuid, 'Computação - Piso 01', 'cd371472-dd11-4cd0-ac84-1555b2e4f36e'::uuid);

-- CREATE salas
INSERT INTO public.salas (id, nome, piso_id) VALUES ('99513a23-0401-44cd-b0ca-83f607b4f884'::uuid, 'Computação - Piso 01 - Sala 1001', '380a5629-ee14-459d-8397-43da5fdd1304'::uuid);

-- CREATE microcontroladores
INSERT INTO public.microcontroladores (id, apelido, mac_address, is_deleted, sala_id) VALUES ('3319a8bc-836c-4301-8384-e6932b267d84'::uuid, 'kannada-elephant-2206', '246bc724d9c2', false, '99513a23-0401-44cd-b0ca-83f607b4f884'::uuid);

-- CREATE sensores
INSERT INTO public.sensores (id, apelido, st_sensor, tp_sensor, microcontrolador_id) VALUES ('23c8a36d-b48d-4885-8ede-b1fc1562d8b9'::uuid, 'madurese-rat-2206', 1, 1, '3319a8bc-836c-4301-8384-e6932b267d84'::uuid);
INSERT INTO public.sensores (id, apelido, st_sensor, tp_sensor, microcontrolador_id) VALUES ('40fafe32-87d0-444e-b36d-ac7470ab4278'::uuid, 'thai-narwhal-2206', 1, 2, '3319a8bc-836c-4301-8384-e6932b267d84'::uuid);
