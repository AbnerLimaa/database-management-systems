USE BDSpotPer398067

CREATE TABLE Gravadora
(
	Cod_Grav integer NOT NULL PRIMARY KEY IDENTITY,
	Nome_Grav varchar(255) NOT NULL,
	End_Grav varchar(255),
	End_HomePage_Grav varchar(255)
) ON BDSpotPer_fg1

CREATE TABLE Telefones
(
	Cod_Tel integer NOT NULL PRIMARY KEY IDENTITY,
	Num_Tel varchar(255) NOT NULL,
	Grav_Tel integer NOT NULL,

	CONSTRAINT FK_Tel_Grav FOREIGN KEY (Grav_Tel) REFERENCES Gravadora (Cod_Grav)
) ON BDSpotPer_fg1

CREATE TABLE Album
(
	Cod_Alb integer NOT NULL PRIMARY KEY IDENTITY,
	CD_Alb varchar(255) NOT NULL,
	DataGrav_Alb date NOT NULL,
	Desc_Alb varchar(255) NOT NULL,
	Grav_Alb integer NOT NULL,

	CONSTRAINT FK_Alb_Grav FOREIGN KEY (Grav_Alb) REFERENCES Gravadora (Cod_Grav)
) ON BDSpotPer_fg1

CREATE TABLE Compra
(
	Cod_Compra integer PRIMARY KEY IDENTITY,
	Preco_Compra integer NOT NULL,
	Data_Compra date NOT NULL,
	Tipo_Compra varchar(255) NOT NULL,
	Alb_Compra integer NOT NULL,
	
	CONSTRAINT FK_Compra_Album FOREIGN KEY (Alb_Compra) REFERENCES Album (Cod_Alb),
	CONSTRAINT CK_Data_Compra CHECK (Data_Compra > '2000-01-01'),
	CONSTRAINT CK_Tipo_Compra CHECK (Tipo_Compra = 'CD' OR Tipo_Compra = 'Vinil' OR Tipo_Compra = 'Download') 
) ON BDSpotPer_fg1

CREATE TABLE Composicao
(
	Cod_Compcao integer NOT NULL PRIMARY KEY IDENTITY,
	Desc_Compcao varchar(255) NOT NULL
) ON BDSpotPer_fg1

CREATE TABLE Faixa
(
	Cod_Faixa integer NOT NULL PRIMARY KEY NONCLUSTERED IDENTITY,
	Tempo_Faixa integer NOT NULL,
	Desc_Faixa varchar(255) NOT NULL,
	Tipo_Faixa varchar(255) NOT NULL,
	Num_Faixa integer NOT NULL,
	Alb_Faixa integer NOT NULL,
	Compcao_Faixa integer NOT NULL,

	CONSTRAINT FK_Faixa_Alb FOREIGN KEY (Alb_Faixa) REFERENCES Album (Cod_Alb) ON DELETE CASCADE,
	CONSTRAINT FK_Faixa_Compcao FOREIGN KEY (Compcao_Faixa) REFERENCES Composicao (Cod_Compcao),
	CONSTRAINT CK_Tipo_Faixa CHECK (Tipo_Faixa = 'ADD' OR Tipo_Faixa = 'DDD')
) ON BDSpotPer_fg2

CREATE CLUSTERED INDEX IDX_Faixa_Alb
	ON Faixa (Alb_Faixa)
	WITH (FILLFACTOR=100);
	

CREATE NONCLUSTERED INDEX IDX_Faixa_Tipo
	ON Faixa (Tipo_Faixa)
	WITH (FILLFACTOR=100);

CREATE TABLE Playlist
(
	Cod_Play integer NOT NULL PRIMARY KEY IDENTITY,
	Nome_Play varchar(255) NOT NULL,
	Data_Play date NOT NULL,
	Tempo_Play integer NOT NULL
) ON BDSpotPer_fg2

CREATE TABLE Interprete
(
	Cod_Int integer NOT NULL PRIMARY KEY IDENTITY,
	Nome_Int varchar(255) NOT NULL,
	Tipo_Int varchar(255) NOT NULL
) ON BDSpotPer_fg1

CREATE TABLE Periodo_Musical
(
	Cod_PerMus integer NOT NULL PRIMARY KEY IDENTITY,
	Desc_PerMus varchar(255) NOT NULL,
	Inicio_PerMus date NOT NULL,
	Fim_PerMus date NOT NULL
) ON BDSpotPer_fg1

CREATE TABLE Compositor
(
	Cod_Comptor integer NOT NULL PRIMARY KEY IDENTITY,
	Nome_Comptor varchar(255) NOT NULL,
	Cidade_Comptor varchar(255),
	Pais_Comptor varchar(255),
	DataNasc_Comptor date,
	DataMorte_Comptor date,
	PerMus_Comptor integer NOT NULL,

	CONSTRAINT FK_Comptor_PerMus FOREIGN KEY (PerMus_Comptor) REFERENCES Periodo_Musical (Cod_PerMus)
) ON BDSpotPer_fg1

CREATE TABLE Agrupa_Faixa_Play
(
	Cod_Agrupa integer NOT NULL PRIMARY KEY IDENTITY,
	Cod_Faixa_Agrupa integer NOT NULL,
	Cod_Play_Agrupa integer NOT NULL,
	DataUlt_Agrupa date NOT NULL,
	NumVezes_Agrupa integer NOT NULL,

	CONSTRAINT FK_Agrupa_Faixa FOREIGN KEY (Cod_Faixa_Agrupa) REFERENCES Faixa (Cod_Faixa) ON DELETE CASCADE,
	CONSTRAINT FK_Agrupa_Play FOREIGN KEY (Cod_Play_Agrupa) REFERENCES Playlist (Cod_Play)
) ON BDSpotPer_fg2

CREATE TABLE Possui_Int_Faixa
(
	Cod_Possui integer NOT NULL PRIMARY KEY IDENTITY,
	Cod_Int_Possui integer NOT NULL,
	Cod_Faixa_Possui integer NOT NULL,

	CONSTRAINT FK_Possui_Int FOREIGN KEY (Cod_Int_Possui) REFERENCES Interprete (Cod_Int) ON DELETE CASCADE,
	CONSTRAINT FK_Possui_Faixa FOREIGN KEY (Cod_Faixa_Possui) REFERENCES Faixa (Cod_Faixa)
) ON BDSpotPer_fg1

CREATE TABLE Criada_Faixa_Comptor
(
	Cod_Criada integer NOT NULL PRIMARY KEY IDENTITY,
	Cod_Faixa_Criada integer NOT NULL,
	Cod_Comptor_Criada integer NOT NULL,

	CONSTRAINT FK_Criada_Faixa FOREIGN KEY (Cod_Faixa_Criada) REFERENCES Faixa (Cod_Faixa) ON DELETE CASCADE,
	CONSTRAINT FK_Criada_Comptor FOREIGN KEY (Cod_Comptor_Criada) REFERENCES Compositor (Cod_Comptor)
) ON BDSpotPer_fg1