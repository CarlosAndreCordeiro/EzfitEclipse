--=====================INSERTS ================================--

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
  CONSTRAINT  UNIQUE (cpf)
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
  CONSTRAINT  UNIQUE (cpf)
);

CREATE TABLE exercicio
(
  codigo integer NOT NULL,
  descricao character varying(255),
  nome character varying(20),
  CONSTRAINT exercicio_pkey PRIMARY KEY (codigo),
  CONSTRAINT  UNIQUE (nome)
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
  CONSTRAINT FOREIGN KEY (cod_professor)
      REFERENCES professor (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT  FOREIGN KEY (cod_aluno)
      REFERENCES aluno (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT  UNIQUE (nome)
);


CREATE TABLE treino_exercicio
(
  treino_codigo integer NOT NULL,
  exercicios_codigo integer NOT NULL,
  CONSTRAINT  FOREIGN KEY (treino_codigo)
      REFERENCES treino (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT  FOREIGN KEY (exercicios_codigo)
      REFERENCES exercicio (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT  UNIQUE (exercicios_codigo)
);



--=====================GATILHOS ================================--

-- Professor nao pode ter nome nem cref Nulos.
CREATE FUNCTION crefProfessor() RETURNS trigger AS $crefProfessor$
BEGIN

	IF NEW.cref IS NULL THEN
		RAISE EXCEPTION 'O Cref do Professor n�o pode ser nulo';
	END IF;
	
	IF NEW.nome IS NULL THEN
		RAISE EXCEPTION '% Professor n�o pode ter um nome nulo', NEW.nome;
	END IF;
	

RETURN NEW;
END;
$crefProfessor$ LANGUAGE plpgsql;
CREATE TRIGGER crefProfessor BEFORE INSERT OR UPDATE ON Professor
FOR EACH ROW EXECUTE PROCEDURE crefProfessor();


    
-- Aluno nao pode ter o nome nulo.
CREATE FUNCTION nomeAluno() RETURNS trigger AS $nomeAluno$
BEGIN

	IF NEW.nome IS NULL THEN
	RAISE EXCEPTION '% Aluno n�o pode ter um nome nulo', NEW.nome;
	END IF;

RETURN NEW;
END;
$nomeAluno$ LANGUAGE plpgsql;
CREATE TRIGGER nomeAluno BEFORE INSERT OR UPDATE ON Aluno
FOR EACH ROW EXECUTE PROCEDURE nomeAluno();



--Aluno nao pode iniciar um treino sem ter terminado o treino anterior.
create or replace function novoTreinoDoAluno() returns trigger as $$
declare aux integer;

BEGIN
	aux = (select treino.cod_aluno from aluno, treino where aluno.codigo = treino.cod_aluno and treino.realizado= 'false');
		if(aux= new.cod_aluno) then 	
			raise exception 'o Aluno ainda nao completou o treino anterior';
		else 
			return new;
		end if;
END 
$$
language 'plpgsql';
create trigger novoTreinoDoAluno before insert on treino for each row execute procedure novoTreinoDoAluno();


    
--=====================ASSERTIONS ================================--


-- Um aluno no pode ter mais de dois treinos.
create Assertion restricao_tyreinoAluno
CHECK
	(exists(select codigo from treino
		group by codigo
		HAVING 2> (select count (*) from treino, aluno 
		where aluno.codigo = cod_aaluno and realizado = 'false')));


--Nenhum aluno do sexo masculino pode ter um treino com dura�ao inferior a 10.
CREATE ASSERTION restricaoDuracaoTreino
CHECK
(not exists  (select * from aluno a
		where sexo = 'M' and a.codigo in 
		(select cod_aluno 
		from treino 
		where duracao < 10)));
		
		
		
-- Um treino nao pode ter mais que 10 exercicios
CREATE ASSERTION restricao_treino
CHECK
	(not exists (select t.codigo from treino as t
				where 10 < any (select cout(exercicios_codigo) 
					from t5reino_exercicio 
					where t.codigo = treino_codigo)));

    