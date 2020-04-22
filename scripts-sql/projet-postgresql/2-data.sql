SET search_path TO projet;


-- Supprimer toutes les données
DELETE FROM service;
DELETE FROM concerner;
DELETE FROM memo;
DELETE FROM telephone;
DELETE FROM personne;
DELETE FROM categorie;
DELETE FROM role;
DELETE FROM compte;
DELETE FROM benevole;
DELETE FROM participant;


-- Compte

INSERT INTO compte (idcompte, pseudo, motdepasse, email ) VALUES 
  (1, 'geek', 'geek', 'geek@3il.fr' ),
  (2, 'chef', 'chef', 'chef@3il.fr' ),
  (3, 'job', 'job', 'job@3il.fr' );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 4;


-- Role

INSERT INTO role (idcompte, role) VALUES 
  ( 1, 'ADMINISTRATEUR' ),
  ( 1, 'UTILISATEUR' ),
  ( 2, 'UTILISATEUR' ),
  ( 3, 'UTILISATEUR' );


-- Categorie
  
INSERT INTO categorie (idcategorie, libelle ) VALUES 
  (1, 'Employés' ),
  (2, 'Partenaires' ),
  (3, 'Clients' ),
  (4, 'Fournisseurs' ),
  (5, 'Dirigeants' );

ALTER TABLE categorie ALTER COLUMN idcategorie RESTART WITH 6;


-- Personne

INSERT INTO personne (idpersonne, idcategorie, nom, prenom) VALUES 
  ( 1, 1, 'GRASSET', 'Jérôme' ),
  ( 2, 1, 'BOUBY', 'Claude' ),
  ( 3, 1, 'AMBLARD', 'Emmanuel' );

ALTER TABLE personne ALTER COLUMN idpersonne RESTART WITH 4;


-- Telephone

INSERT INTO telephone (idtelephone, idpersonne, libelle, numero ) VALUES 
  ( 11, 1, 'Portable', '06 11 11 11 11' ),
  ( 12, 1, 'Fax', '05 55 99 11 11' ),
  ( 13, 1, 'Bureau', '05 55 11 11 11' ),
  ( 21, 2, 'Portable', '06 22 22 22 22' ),
  ( 22, 2, 'Fax', '05 55 99 22 22' ),
  ( 23, 2, 'Bureau', '05 55 22 22 22' ),
  ( 31, 3, 'Portable', '06 33 33 33 33' ),
  ( 32, 3, 'Fax', '05 55 99 33 33' ),
  ( 33, 3, 'Bureau', '05 55 33 33 33' );

ALTER TABLE telephone ALTER COLUMN idtelephone RESTART WITH 100;


-- Memo

INSERT INTO memo (idmemo, titre, description, flagurgent, statut, effectif, budget, echeance, idcategorie ) VALUES 
  ( 1, 'Mémo n°1', 'Texte du mémo n°1', TRUE,  2,   2,   1234.56,   {d  '2020-02-25' }, 1 ),
  ( 2, 'Mémo n°2', 'Texte du mémo n°2', FALSE, 1,   4,   5000.00,   {d  '2020-05-18' }, 2 ),
  ( 3, 'Mémo n°3', NULL, TRUE, 0, NULL, NULL, NULL, NULL );

ALTER TABLE memo ALTER COLUMN idmemo RESTART WITH 4;


-- Concerner

INSERT INTO concerner (idmemo, idPersonne) VALUES 
  ( 1, 1 ),
  ( 1, 2 ),
  ( 1, 3 ),
  ( 2, 1 ),
  ( 2, 2 );


-- Service

INSERT INTO service ( idservice, nom, anneecreation, flagsiege ) VALUES 
  ( 1, 'Direction', 2007, TRUE ),
  ( 2, 'Comptabilité', NULL, TRUE ),
  ( 3, 'Agence Limoges', 2008, FALSE ),
  ( 4, 'Agence Brive', 2014, FALSE );
  
ALTER TABLE service ALTER COLUMN idservice RESTART WITH 5;
  
  -- Benevole 
  
INSERT INTO benevole (identifiant, nom, prenom, sexe, date_de_naissance, email, tel, type, heure_debut_dispo, heure_fin_dispo) VALUES
  	(1, 'jerome', 'vincent', 0, {d'2000-02-25'}, 'jvincent@gmail.com', '+33 07 54 23 08 39', 'interne', {t '12:20'}, {t '16:30'}),
  	(2, 'julia', 'adele', 1, {d'1996-08-15'}, 'juliaadh@gmail.com', '+33 07 44 23 98 30', 'externe', {t '10:00'}, {t '15:30'}),
  	(3, 'andres', 'brayan', 0, {d'1999-10-01'}, 'andres@yahoo.fr', '+33 07 22 76 48 21', 'externe', {t '08:00'}, {t '12:00'}),
  	(4, 'suzie', 'angela', 1, {d'2000-05-09'}, 'suzangela@gmail.com', '+33 06 49 03 78 10', 'interne', {t '16:00'}, {t '18:30'});

ALTER TABLE benevole ALTER COLUMN identifiant RESTART WITH 5;

  -- Participant 
  
INSERT INTO participant (num, nom, prenom, sexe, dtnaiss, email, tel, role, adressePost, cm) VALUES
  	(1, 'luc', 'river', 0, {d'2000-12-05'}, 'luc@gmail.com', '+33 07 54 33 08 39', 0, 'xxxxxxxxxxxx', NULL),
  	(2, 'julie', 'rose', 0, {d'1999-08-07'}, 'julih@gmail.com', '+33 07 44 73 98 30', 1, 'xxxxxxxxxxxx', NULL),
  	(3, 'ange', 'robert', 0, {d'1995-02-01'}, 'angelo@yahoo.fr', '+33 07 22 76 48 20', 1, 'xxxxxxxxxxxx', NULL),
  	(4, 'suzane', 'beldouce', 0, {d'2002-09-29'}, 'suzaned@gmail.com', '+33 06 49 03 75 10', 1, 'xxxxxxxxxxxx', NULL);

ALTER TABLE participant ALTER COLUMN num RESTART WITH 5;
	
	-- Club

INSERT INTO club (num, nom_Capitain, nbRepasReserves, num_participant) VALUES
	(1, 'luc', 3, 1),
	(2, 'suzane', 3, 4),
	(3, 'julie', 4, 2),
	(4, 'ange', 3, 3);

ALTER TABLE club ALTER COLUMN num RESTART WITH 5;
	
	-- Tache

INSERT INTO tache (libelle, emplacement) VALUES
	('lorem ipsum', 'idn'),
	('dolor sit amet', 'idn'),
	('ipsum lorem', 'idn'),
	('amet sit dolor', 'idn');

	-- Equipebenevole
	
INSERT INTO equipebenevole(num, nombre_de_benevole, heure_debut_dispo,heure_fin_dispo, libelle) VALUES
	(1, 5, {t '12:20'}, {t '14:30'}, 'lorem ipsum'),
	(2, 4, {t '10:20'}, {t '14:30'}, 'dolor sit amet'),
	(3, 3, {t '08:20'}, {t '12:30'}, 'ipsum lorem'),
	(4, 2, {t '14:20'}, {t '16:30'}, 'amet sit dolor');
	
ALTER TABLE equipebenevole ALTER COLUMN num RESTART WITH 5;

	-- Constituer
	
INSERT INTO constituer (identifiant, num) VALUES
	(1, 2),
	(2, 1),
	(3, 4),
	(4, 3);

	-- Epreuve

INSERT INTO epreuve (nom, distance) VALUES
	('epreuve 1', 123),
	('epreuve 2', 460),
	('epreuve 3', 463),
	('epreuve 4', 383);
	
	-- Lier
	
INSERT INTO lier (nom, libelle) VALUES
	('epreuve 1', 'amet sit dolor'),
	('epreuve 2', 'lorem ipsum'),
	('epreuve 3', 'amet sit dolor'),
	('epreuve 4', 'ipsum lorem');
	
	-- Inscrire
	
INSERT INTO inscrire (nom, num) VALUES
	('epreuve 1', 3),
	('epreuve 2', 1),
	('epreuve 3', 4),
	('epreuve 4', 2);
