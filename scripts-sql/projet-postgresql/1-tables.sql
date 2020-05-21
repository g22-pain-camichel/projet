SET search_path TO projet;


-- Schéma

DROP SCHEMA IF EXISTS projet CASCADE;
CREATE SCHEMA projet AUTHORIZATION projet;
GRANT ALL PRIVILEGES ON SCHEMA projet TO projet;


-- Tables

CREATE TABLE utilisateur (
	idutilisateur		INT				GENERATED BY DEFAULT AS IDENTITY,
	pseudo			VARCHAR(25)		NOT NULL,
	motdepasse		VARCHAR(25) 	NOT NULL,
	email			VARCHAR(100)	NOT NULL,
	role			INT				NOT NULL,
	CHECK (role IN (0, 1, 2)),
	PRIMARY KEY (idutilisateur),
	UNIQUE (pseudo)
);

CREATE TABLE compte (
	idcompte		INT				GENERATED BY DEFAULT AS IDENTITY,
	pseudo			VARCHAR(25)		NOT NULL,
	motdepasse		VARCHAR(25) 	NOT NULL,
	email			VARCHAR(100)	NOT NULL,
	PRIMARY KEY (idcompte),
	UNIQUE (pseudo)
);

CREATE TABLE role (
	idcompte		INT				NOT NULL,
	role			VARCHAR(20)		NOT NULL,
	CHECK( Role IN ('ADMINISTRATEUR','UTILISATEUR') ),	
	PRIMARY KEY (idcompte, role),
	FOREIGN KEY (idcompte) REFERENCES compte (idcompte)
);


CREATE TABLE categorie (
	idcategorie		INT				GENERATED BY DEFAULT AS IDENTITY,
	libelle			VARCHAR(25)		NOT NULL,
	PRIMARY KEY (idcategorie)
);

CREATE TABLE personne (
	idpersonne		INT				GENERATED BY DEFAULT AS IDENTITY,
	idcategorie		INT				NOT NULL,
	nom				VARCHAR(25)  	NOT NULL,
	prenom			VARCHAR(25) 	NOT NULL,
	PRIMARY KEY (idpersonne),
 	FOREIGN KEY (idcategorie) REFERENCES categorie (idcategorie)
);

CREATE TABLE telephone (
	idtelephone		INT				GENERATED BY DEFAULT AS IDENTITY,
	idpersonne		INT			 	NOT NULL,
	Libelle			VARCHAR(25)		NOT NULL,
	Numero			VARCHAR(25)		NOT NULL,
	PRIMARY KEY (idtelephone),
	FOREIGN KEY (idpersonne) REFERENCES personne (idpersonne)
);

CREATE TABLE memo (	
	idmemo 			INT				GENERATED BY DEFAULT AS IDENTITY, 
	titre 			VARCHAR(50)		NOT NULL, 
	description		VARCHAR(1000), 
	flagurgent		BOOLEAN			NOT NULL, 
	statut			SMALLINT		NOT NULL	DEFAULT 0,
	effectif		INT,
	budget			DOUBLE PRECISION,
	echeance		Date,
	idcategorie		INT,
	CHECK( statut BETWEEN 0 AND 2 ),	
	PRIMARY KEY (idmemo),
 	FOREIGN KEY (idcategorie) REFERENCES categorie (idcategorie)
);

CREATE TABLE concerner (
	idmemo		INT				NOT NULL,
	idpersonne	INT				NOT NULL,
	PRIMARY KEY (idmemo, idpersonne),
	FOREIGN KEY (idmemo) REFERENCES memo (idmemo),
	FOREIGN KEY (idpersonne) REFERENCES personne (idpersonne)
);

CREATE TABLE service (	
	idservice 		INT				GENERATED BY DEFAULT AS IDENTITY, 
	nom 			VARCHAR(50)		NOT NULL, 
	description		VARCHAR(1000), 
	anneecreation	SMALLINT,
	flagsiege		BOOLEAN			NOT NULL, 
	PRIMARY KEY (idservice)
);

------------------------------------------------------------
-- Table: benevole
------------------------------------------------------------
CREATE TABLE benevole(
	identifiant         INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
	nom                 VARCHAR (50) NOT NULL UNIQUE,
	prenom              VARCHAR (50) NOT NULL ,
	sexe                INT  NOT NULL ,
	dtNaiss   DATE  NOT NULL ,
	email               VARCHAR (20) NOT NULL ,
	tel                 VARCHAR (20) NOT NULL ,
	type                VARCHAR (20) NOT NULL ,
	hrDbDispo   TIME  NOT NULL ,
	hrFinDispo     TIME  NOT NULL  ,
	CHECK((sexe = 0 OR sexe = 1) AND (type = 'interne' OR type = 'externe')),	
	CONSTRAINT benevole_PK PRIMARY KEY (identifiant)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: participant
------------------------------------------------------------
CREATE TABLE participant(
	num                  INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
	nom               VARCHAR (20) NOT NULL UNIQUE,
	prenom               VARCHAR (20) NOT NULL ,
	sexe                 INT  NOT NULL ,
	dtNaiss              DATE  NOT NULL ,
	tel                  VARCHAR (20) NOT NULL ,
	email                VARCHAR (20) NOT NULL ,
	role                 INT NOT NULL,
	adressePost          VARCHAR (50) NOT NULL ,
	cm   				 VARCHAR (2000)  ,
	CHECK( (role = 0 OR role = 1) AND (sexe = 0 OR sexe = 1)),
	CONSTRAINT participant_PK PRIMARY KEY (num)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: tache
------------------------------------------------------------
CREATE TABLE tache(
	libelle       VARCHAR (20) NOT NULL ,
	emplacement   VARCHAR (20) NOT NULL  ,
	CONSTRAINT tache_PK PRIMARY KEY (libelle)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: club
------------------------------------------------------------
CREATE TABLE club(
	num               INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
	nomCapitain      VARCHAR (20) NOT NULL ,
	nbRepasReserves   INT  NOT NULL ,
	numParticipant   INT  NOT NULL  ,
	CONSTRAINT club_PK PRIMARY KEY (num)

	,CONSTRAINT club_participant_FK FOREIGN KEY (numParticipant) REFERENCES participant(num)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: equipeBenevole
------------------------------------------------------------
CREATE TABLE equipeBenevole(
	num               INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
	nbreBenevole   INT  NOT NULL ,
	hrDbDispo    TIME  NOT NULL ,
	hrFinDispo      TIME  NOT NULL ,
	libelle              VARCHAR (20) NOT NULL  ,
	CONSTRAINT equipeBenevole_PK PRIMARY KEY (num)

	,CONSTRAINT equipeBenevole_tache_FK FOREIGN KEY (libelle) REFERENCES tache(libelle)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: epreuve
------------------------------------------------------------
CREATE TABLE epreuve(
	nom        VARCHAR (20) NOT NULL ,
	distance   FLOAT  NOT NULL  ,
	CONSTRAINT epreuve_PK PRIMARY KEY (nom)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: constituer
------------------------------------------------------------
CREATE TABLE constituer(
	identifiant   INT  NOT NULL ,
	num           INT NOT NULL  ,
	CONSTRAINT constituer_PK PRIMARY KEY (identifiant,num)

	,CONSTRAINT constituer_benevole_FK FOREIGN KEY (identifiant) REFERENCES benevole(identifiant)
	,CONSTRAINT constituer_equipeBenevole0_FK FOREIGN KEY (num) REFERENCES equipeBenevole(num)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: inscrire
------------------------------------------------------------
CREATE TABLE inscrire(
	nom    VARCHAR (20) NOT NULL ,
	num    INT  NOT NULL  ,
	CONSTRAINT inscrire_PK PRIMARY KEY (nom,num)

	,CONSTRAINT inscrire_epreuve_FK FOREIGN KEY (nom) REFERENCES epreuve(nom)
	,CONSTRAINT inscrire_club0_FK FOREIGN KEY (num) REFERENCES club(num)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: lier
------------------------------------------------------------
CREATE TABLE lier(
	nom       VARCHAR (20) NOT NULL ,
	libelle   VARCHAR (20) NOT NULL  ,
	CONSTRAINT lier_PK PRIMARY KEY (nom,libelle)

	,CONSTRAINT lier_epreuve_FK FOREIGN KEY (nom) REFERENCES epreuve(nom)
	,CONSTRAINT lier_tache0_FK FOREIGN KEY (libelle) REFERENCES tache(libelle)
)WITHOUT OIDS;


-- Vues

CREATE VIEW v_compte_avec_roles AS
	SELECT c.*, ARRAY_AGG( r.role ) AS roles
	FROM compte c 
	LEFT JOIN ROLE r ON c.idcompte = r.idcompte
	GROUP BY c.idcompte;

