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




INSERT INTO utilisateur (idutilisateur, pseudo, motdepasse, email, role ) VALUES 
  (1, 'geek', 'geek', 'geek@3il.fr', 1),
  (2, 'chef', 'chef', 'chef@3il.fr', 0),
  (3, 'job', 'job', 'job@3il.fr', 2 );

ALTER TABLE utilisateur ALTER COLUMN idutilisateur RESTART WITH 4;

-- Compte

INSERT INTO compte (idcompte, pseudo, motdepasse, email ) VALUES 
  (1, 'geek', 'geek', 'geek@3il.fr' ),
  (2, 'chef', 'chef', 'chef@3il.fr' ),
  (3, 'job', 'job', 'job@3il.fr' );
  
 
-- Role

INSERT INTO role (idcompte, role) VALUES 
  ( 1, 'ADMINISTRATEUR' ),
  ( 1, 'UTILISATEUR' ),
  ( 2, 'UTILISATEUR' ),
  ( 3, 'UTILISATEUR' );


ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 4;

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
  
  -- Permi
  
INSERT INTO permi(numero, dateDelivrance, lieu) VALUES
	(911091204209, {d'2000-08-02'}, 'foreke'),
	(122082203108, {d'1990-10-20'}, 'bamenda'),
	(788171614617, {d'1999-01-15'}, 'bouda'),
	(513853284285, {d'2003-11-08'}, 'doul');

  -- Benevole 
  
INSERT INTO benevole (identifiant, nom, prenom, sexe, dtNaiss, email, tel, type, hrDbDispo, hrFinDispo, estValide, numero) VALUES
  	(1, 'jerome', 'vincent', 0, {d'2000-02-25'}, 'jvincent@gmail.com', '+33 07 54 23 08 39', 'interne', {t '12:20'}, {t '16:30'}, false, NULL),
  	(2, 'julia', 'adele', 1, {d'1996-08-15'}, 'juliaadh@gmail.com', '+33 07 44 23 98 30', 'externe', {t '10:00'}, {t '15:30'}, false, 911091204209),
  	(3, 'andres', 'brayan', 0, {d'1999-10-01'}, 'andres@yahoo.fr', '+33 07 22 76 48 21', 'externe', {t '08:00'}, {t '12:00'}, false, NULL),
  	(4, 'rufus', 'denzel', 0, {d'1993-07-23'}, 'rufus@gmail.com', '+33 06 12 68 51 32', 'interne', {t '10:00'}, {t '16:00'}, false, NULL),
  	(5, 'suzie', 'angela', 1, {d'2000-05-09'}, 'suzangela@gmail.com', '+33 06 49 03 78 10', 'interne', {t '16:00'}, {t '18:30'}, false, 122082203108);
  	 	
ALTER TABLE benevole ALTER COLUMN identifiant RESTART WITH 6;

  -- Participant 
  
INSERT INTO participant (num, nom, prenom, sexe, dtNaiss, email, tel, role, adressePost, cm, estValide) VALUES
  	(1, 'luc', 'river', 0, {d'2000-12-05'}, 'luc@gmail.com', '+33 07 54 33 08 39', 0, 'xxxxxxxxxxxx', NULL, false),
  	(2, 'julie', 'rose', 0, {d'1999-08-07'}, 'julih@gmail.com', '+33 07 44 73 98 30', 1, 'xxxxxxxxxxxx', NULL, false),
  	(3, 'ange', 'robert', 0, {d'1995-02-01'}, 'angelo@yahoo.fr', '+33 07 22 76 48 20', 1, 'xxxxxxxxxxxx', NULL, false),
  	(4, 'suzane', 'beldouce', 0, {d'2002-09-29'}, 'suzaned@gmail.com', '+33 06 49 03 75 10', 1, 'xxxxxxxxxxxx', NULL, false);

ALTER TABLE participant ALTER COLUMN num RESTART WITH 5;
	
	-- Club

INSERT INTO club (num, nomCapitain, nbRepasReserves, numParticipant, categorie, estValide, activite) VALUES
	(1, 'luc', 3, 1, 0, false, 0),
	(2, 'suzane', 3, 4, 1, false, 0),
	(3, 'julie', 4, 2, 1, false, 1),
	(4, 'ange', 3, 3, 3, false, 1);

ALTER TABLE club ALTER COLUMN num RESTART WITH 5;
	
	-- Tache

INSERT INTO tache (libelle, emplacement, hr_deb, hr_fin, taille) VALUES
	('lorem ipsum', 'idn', {t '10:30'}, {t '14:00'}, 3),
	('dolor sit amet', 'idn', {t '08:00'}, {t '10:00'}, 3),
	('ipsum lorem', 'idn', {t '14:00'}, {t '16:00'}, 2),
	('amet sit dolor', 'idn', {t '10:00'}, {t '16:00'}, 4);

	-- Equipebenevole
	
INSERT INTO equipebenevole(num, nom, nbreBenevole, libelle, estValide) VALUES
	(1, 'equipe 1', 3, 'lorem ipsum', false),
	(2, 'equipe 2', 4, 'dolor sit amet', false),
	(3, 'equipe 3', 3, 'ipsum lorem', false),
	(4, 'equipe 4', 2, 'amet sit dolor', false);
	
ALTER TABLE equipebenevole ALTER COLUMN num RESTART WITH 5;

	-- Constituer
	
INSERT INTO constituer (identifiant, num) VALUES
	(1, 2),
	(2, 1);

	-- Epreuve

INSERT INTO epreuve (nom, distance, hr_deb, hr_fin) VALUES
	('epreuve 1', 123, {t '10:00'}, {t '16:00'}),
	('epreuve 2', 460, {t '14:00'}, {t '16:00'}),
	('epreuve 3', 463, {t '08:00'}, {t '10:00'}),
	('epreuve 4', 383, {t '10:30'}, {t '14:00'});
	
	-- Lier
	
INSERT INTO lier (nom, libelle, statut) VALUES
	('epreuve 2', 'amet sit dolor', 0),
	('epreuve 1', 'lorem ipsum', 0),
	('epreuve 2', 'dolor sit amet', 0),
	('epreuve 1', 'ipsum lorem', 0);
	
	-- Inscrire
	
INSERT INTO inscrire (nom, num) VALUES
	('epreuve 1', 3),
	('epreuve 2', 1),
	('epreuve 3', 4),
	('epreuve 4', 2);
