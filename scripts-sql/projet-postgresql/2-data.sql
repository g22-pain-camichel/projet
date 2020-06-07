SET search_path TO projet;


-- Supprimer toutes les données
DELETE FROM service;
DELETE FROM concerner;
DELETE FROM memo;
DELETE FROM telephone;
DELETE FROM personne;
DELETE FROM categorie;
DELETE FROM role;
DELETE FROM utilisateur;
DELETE FROM benevole;
DELETE FROM participant;




INSERT INTO utilisateur (idutilisateur, pseudo, motdepasse, email, role ) VALUES 
  (1, 'geek', 'geek', 'geek@3il.fr', 1),
  (2, 'chef', 'chef', 'chef@3il.fr', 0),
  (3, 'job', 'job', 'job@3il.fr', 2 );

ALTER TABLE utilisateur ALTER COLUMN idutilisateur RESTART WITH 4;

-- Role

INSERT INTO role (idutilisateur, role) VALUES 
  ( 1, 'ADMINISTRATEUR' ),
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
  	(5, 'suzie', 'angela', 1, {d'2000-05-09'}, 'suzangela@gmail.com', '+33 06 49 03 78 10', 'interne', {t '16:00'}, {t '18:30'}, false, 122082203108),
  	(6, 'Martin', 'Paul', 0, {d'1976-11-27'}, 'reponsable@exemple.com', '+33 02 98 68 47 32', 'interne', {t '08:00'}, {t '02:30'}, false, NULL),
  	(7, 'imbert', 'Odette', 1, {d'1988-02-25'}, 'odette.imbert@gmail.com', '+33 02 99 68 47 32', 'interne', {t '10:00'}, {t '14:30'}, false, NULL),
  	(8, 'boulay', 'Andrée', 0, {d'1999-03-30'}, 'andree.boulay@hotmail.com', '+33 02 99 68 46 02 ', 'externe', {t '06:00'}, {t '17:30'}, false, NULL),
  	(9, 'yves', 'Delmas', 0, {d'1980-05-30'}, 'yves.delma@gmail.com', '+33 02 05 21 46 02 ', 'externe', {t '10:00'}, {t '20:30'}, false, NULL),
  	(10, 'mathilde', 'Grenier', 1, {d'1996-06-30'}, 'mathilde.grenier@gmail.com', '+33 02 99 68 46 02 ', 'externe', {t '06:00'}, {t '17:30'}, false, NULL),
  	(11, 'david', 'Lagarde', 0, {d'1995-03-30'}, 'david.lagarde@hotmail.com', '+33 02 91 75 46 02 ', 'interne', {t '06:00'}, {t '17:30'}, false, NULL),
  	(12, 'leon', 'Marthy', 0, {d'1999-01-31'}, 'leon.marty@hotmail.com', '+33 02 73 45 46 02 ', 'interne', {t '10:00'}, {t '18:30'}, false, NULL),
  	(13, 'veronique', 'Lemoine', 1, {d'1998-03-31'}, 'veronique.lemoine@yahoo.com', '+33 02 70 48 46 02 ', 'externe', {t '10:00'}, {t '16:30'}, false, NULL),
  	(14, 'julien', 'Lenoir', 0, {d'1999-12-30'}, 'julien.lenoir@hotmail.com', '+33 02 98 38 56 02 ', 'externe', {t '14:00'}, {t '20:30'}, false, NULL),
  	(15, 'legrand', 'jeanine', 0, {d'1989-12-12'}, 'legrand.jeanine@hotmail.com', '+33 02 98 38 56 02 ', 'externe', {t '14:00'}, {t '20:30'}, false, NULL),
  	(16, 'loiseau', 'julie', 0, {d'1979-12-30'}, 'loiseau.julie@hotmail.com', '+33 02 98 38 56 02 ', 'externe', {t '14:00'}, {t '20:30'}, false, NULL),
  	(17, 'coste', 'agathe', 0, {d'1978-10-01'}, 'Coste.agathe@gmail.com', '+33 02 98 38 56 02 ', 'externe', {t '14:00'}, {t '20:30'}, false, NULL),
  	(18, 'maillard', 'honore', 0, {d'1996-02-20'}, 'maillard.honore@hotmail.com', '+33 02 98 38 56 02 ', 'externe', {t '14:00'}, {t '20:30'}, false, NULL),
  	(19, 'boyer', 'gabriel', 0, {d'1999-12-30'}, 'boyer.gabriel@hotmail.com', '+33 02 98 38 56 02 ', 'externe', {t '14:00'}, {t '20:30'}, false, NULL),
  	(20, 'Adele', 'Barthelemy', 0, {d'1997-01-01'}, 'adele.barthelemy@gmail.com', '+33 02 99 61 54 01 ', 'interne', {t '08:00'}, {t '21:30'}, false, NULL);
ALTER TABLE benevole ALTER COLUMN identifiant RESTART WITH 6;

  -- Participant 
  
INSERT INTO participant (num, nom, prenom, sexe, dtNaiss, email, tel, role, adressePost, cm, estValide) VALUES
  	(1, 'luc', 'river', 0, {d'2000-12-05'}, 'luc@gmail.com', '+33 07 54 33 08 39', 0, 'xxxxxxxxxxxx', NULL, false),
  	(2, 'julie', 'rose', 1, {d'1999-08-07'}, 'julih@gmail.com', '+33 07 44 73 98 30', 1, 'xxxxxxxxxxxx', NULL, false),
  	(3, 'ange', 'robert', 0, {d'1995-02-01'}, 'angelo@yahoo.fr', '+33 07 22 76 48 20', 1, 'xxxxxxxxxxxx', NULL, false),
  	(4, 'margaret', 'lehall', 1, {d'1996-03-01'}, 'margaret.lehall@gmail.com', '+33 07 30 66 48 20', 1, 'xxxxxxxxxxxx', NULL, false),
  	(5, 'renee', 'prevost', 0, {d'1989-02-01'}, 'Renee.prevost@hotmail.fr', '+33 07 22 80 49 20', 1, 'xxxxxxxxxxxx', NULL, false),
  	(6, 'margaud', 'legros', 1, {d'1988-07-31'}, 'margaud.legros', '+33 07 23 84 50 20', 1, 'xxxxxxxxxxxx', NULL, false),
  	(7, 'gerard', 'adam', 0, {d'1984-04-01'}, 'gerard.adam@gmail.com', '+33 07 22 85 51 23', 1, 'xxxxxxxxxxxx', NULL, false),
  	(8, 'elise', 'da Costa', 1, {d'1984-09-14'}, 'Elise.dacosta@gmail.com', '+33 07 55 85 46 73', 1, 'xxxxxxxxxxxx', NULL, false),
  	(9, 'andree', 'mahe', 0, {d'1985-11-02'}, 'andree.mahe@hotmail.com', '+33 07 55 85 46 73', 1, 'xxxxxxxxxxxx', NULL, false),
  	(10, 'bailly', 'suzane', 1, {d'1988-12-24'}, 'bailly.Suzanne@hotmail.com', '+33 07 55 85 46 73', 1, 'xxxxxxxxxxxx', NULL, false),
  	(11, 'olivier', 'antoine', 0, {d'1990-12-02'}, 'olivier.antoine@hotmail.com', '+33 07 85 65 56 73', 1, 'xxxxxxxxxxxx', NULL, false),
  	(12, 'alphonse', 'mallet', 1, {d'1985-10-15'}, 'alphonse.mallet@hotmail.com', '+33 07 55 85 46 73', 1, 'xxxxxxxxxxxx', NULL, false),
  	(13, 'dijoux', 'charles', 0, {d'1989-06-02'}, 'dijoux.charles@gmail.com', '+33 07 65 85 46 73', 1, 'xxxxxxxxxxxx', NULL, false),
  	(14, 'guillon', 'claire', 1, {d'1998-10-25'}, 'guillon.claire@gmail.com', '+33 07 55 85 46 73', 1, 'xxxxxxxxxxxx', NULL, false),
  	(15, 'lemonnier', 'gabriel', 0, {d'1990-12-22'}, 'lemonier.gabriel@hotmail.com', '+33 07 65 75 46 73', 1, 'xxxxxxxxxxxx', NULL, false),
  	(16, 'didier', 'denis', 1, {d'1989-01-05'}, 'didier.denis@gmail.com', '+33 07 55 85 46 73', 1, 'xxxxxxxxxxxx', NULL, false),
  	(17, 'bazin', 'theophile', 0, {d'1990-02-02'}, 'bazin.theophile@hotmail.com', '+33 07 55 85 46 73', 1, 'xxxxxxxxxxxx', NULL, false),
  	(18, 'leconte', 'marcelle', 1, {d'1986-11-02'}, 'alphonse.mallet@hotmail.com', '+33 07 55 85 46 73', 1, 'xxxxxxxxxxxx', NULL, false),
  	(19, 'renard', 'phillipe', 1, {d'1985-11-02'}, 'renard.phillipe@hotmail.com', '+33 07 55 85 46 73', 1, 'xxxxxxxxxxxx', NULL, false),
  	(20, 'suzane', 'beldouce', 0, {d'2002-09-29'}, 'suzaned@gmail.com', '+33 06 49 03 75 10', 1, 'xxxxxxxxxxxx', NULL, false);
  

ALTER TABLE participant ALTER COLUMN num RESTART WITH 5;
	
	-- Club

INSERT INTO club (num, nom, nomCapitain, nbRepasReserves, numParticipant, categorie, estValide, activite) VALUES
	(1, 'manchester', 'luc', 3, 1, 0, false, 0),
	(2, 'manchester', 'julie', 3, 2, 0, false, 0),
	(3, 'barcelone', 'ange', 3, 4, 3, false, 0),
	(4, 'barcelone', 'suzane', 3, 20, 1, false, 0),
	(5, 'madrid', 'renard', 4, 19, 1, false, 1),
	(6, 'madrid', 'leconte', 4,18, 1, false, 1),
	(7, 'ajax', 'renee', 4, 5, 1, false, 1),
	(8, 'ajax', 'bazin', 4, 17, 1, false, 1),
	(9, 'atletico', 'margaud', 4, 6, 1, false, 1),
	(10, 'atletico', 'guillon', 4, 14, 1, false, 1),
	(11, 'Chelsea', 'gerard', 4, 7, 1, false, 1),
	(12, 'liverpool', 'elise', 4, 8, 1, false, 1),
	(13, 'Arsenal', 'julie', 4, 2, 1, false, 1),
	(14, 'City', 'andree', 4, 9, 1, false, 1),
	(15, 'Inter', 'alphonse', 3, 12, 3, false, 1);

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
	('epreuve 1', 4),
	('epreuve 2', 2);
