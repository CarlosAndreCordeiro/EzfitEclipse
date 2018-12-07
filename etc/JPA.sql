

CREATE TABLE aluno
(
  codigo integer NOT NULL,
  cpf character varying(15) NOT NULL,
  datanascimento date,
  email character varying(30),
  endereco character varying(100),
  nome character varying(50),
  senha character varying(20),
  sexo character varying(10),
  altura double precision,
  objetivo character varying(50),
  peso double precision,
  CONSTRAINT aluno_pkey PRIMARY KEY (codigo),
  CONSTRAINT uk_g6otv1ccqwf8a15re4tc1sr9q UNIQUE (cpf)
);
CREATE TABLE professor
(
  codigo integer NOT NULL,
  cpf character varying(15) NOT NULL,
  datanascimento date,
  email character varying(30),
  endereco character varying(100),
  nome character varying(50),
  senha character varying(20),
  sexo character varying(10),
  cref character varying(20),
  CONSTRAINT professor_pkey PRIMARY KEY (codigo),
  CONSTRAINT uk_f9xdq98nhl68237568oxfu6l4 UNIQUE (cpf)
);

CREATE TABLE exercicio
(
  codigo integer NOT NULL,
  descricao character varying(255),
  nome character varying(20),
  CONSTRAINT exercicio_pkey PRIMARY KEY (codigo),
  CONSTRAINT uk_ij9l0vq68vkc9mhl417ghs8p UNIQUE (nome)
);


CREATE TABLE treino
(
  codigo integer NOT NULL,
  descricao character varying(70),
  duracao integer,
  intensidade character varying(6),
  nome character varying(20),
  realizado boolean,
  cod_aluno integer,
  cod_professor integer,
  CONSTRAINT treino_pkey PRIMARY KEY (codigo),
  CONSTRAINT fka5fmb94t7mvaduwsqxq9gwpsj FOREIGN KEY (cod_professor)
      REFERENCES professor (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkhyu6vrfnn8cfcohis8pluh5sq FOREIGN KEY (cod_aluno)
      REFERENCES aluno (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_429bw9ei7l17shn4wvcyw67ei UNIQUE (nome)
);


CREATE TABLE treino_exercicio
(
  treino_codigo integer NOT NULL,
  exercicios_codigo integer NOT NULL,
  CONSTRAINT fk53d7wj07smw3ov8p8bnv2b99x FOREIGN KEY (treino_codigo)
      REFERENCES treino (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk8sr03a75pmuls504v0v4mtcl0 FOREIGN KEY (exercicios_codigo)
      REFERENCES exercicio (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_c85ypew2wd4n1fnvoeuy4vcdw UNIQUE (exercicios_codigo)
);







