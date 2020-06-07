SET search_path TO projet;


-- Supprime toutes les fonctions du schéma

DO $ccode$
DECLARE 
	r RECORD;
BEGIN
	FOR r IN
		SELECT 'DROP FUNCTION ' || ns.nspname || '.' || proname 
	       || '(' || oidvectortypes(proargtypes) || ')' AS sql
		FROM pg_proc INNER JOIN pg_namespace ns ON (pg_proc.pronamespace = ns.oid)
		WHERE ns.nspname = 'projet'  
	LOOP
		EXECUTE r.sql;
	END LOOP;
END;
$ccode$;


-- Fonctions

CREATE FUNCTION utilisateur_inserer(
	p_pseudo 		VARCHAR,
	p_motdepasse 	VARCHAR,
	p_email			VARCHAR,
	p_idutilisateur 	OUT	INT,
	p_roles			VARCHAR[]
) 
AS $ccode$
DECLARE v_role VARCHAR;
BEGIN
	INSERT INTO utilisateur ( pseudo, motdepasse, email )
	VALUES ( p_pseudo, p_motdepasse,p_email )
	RETURNING idutilisateur INTO p_idutilisateur;
	
	FOREACH v_role IN ARRAY p_roles LOOP
		INSERT INTO role ( idutilisateur, role )
		VALUES ( p_idutilisateur, v_role );
	END LOOP;
END;
$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION utilisateur_modifier(
	p_pseudo 		VARCHAR,
	p_motdepasse 	VARCHAR,
	p_email			VARCHAR,
	p_idutilisateur 		INT,
	p_roles			VARCHAR[]
) 
RETURNS VOID 
AS $ccode$
DECLARE v_role VARCHAR;
BEGIN
	UPDATE utilisateur 
	SET pseudo = p_pseudo, 
		motdepasse = p_motdepasse, 
		email = p_email 
	WHERE idutilisateur =  p_idutilisateur;

	DELETE FROM role WHERE idutilisateur = p_idutilisateur;
	
	FOREACH v_role IN ARRAY p_roles LOOP
		INSERT INTO role ( idutilisateur, role )
		VALUES ( p_idutilisateur, v_role );
	END LOOP;
END;
$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION utilisateur_supprimer( p_idutilisateur INT ) 
RETURNS VOID 
AS $ccode$
BEGIN
	DELETE FROM role WHERE idutilisateur = p_idutilisateur;
	DELETE FROM utilisateur WHERE idutilisateur = p_idutilisateur;
END;
$ccode$ LANGUAGE plpgsql;


--CREATE FUNCTION utilisateur_retrouver( p_idutilisateur INT ) 
--RETURNS TABLE (
--    idutilisateur    INT,
--    pseudo      VARCHAR,
--    motdepasse  VARCHAR,
--    email       VARCHAR,
--    roles       VARCHAR[]
--)
--AS $ccode$
--BEGIN
--	RETURN QUERY
--	SELECT c.*, ARRAY_AGG( r.role ) AS roles
--	FROM utilisateur c 
--	LEFT JOIN role r ON c.idutilisateur = r.idutilisateur
--	WHERE c.idutilisateur = p_idutilisateur
--	GROUP BY c.idutilisateur;
--END;
--$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION utilisateur_retrouver( p_idutilisateur INT ) 
RETURNS SETOF v_utilisateur_avec_roles 
AS $ccode$
BEGIN
	RETURN QUERY
	SELECT * 
	FROM v_utilisateur_avec_roles
	WHERE idutilisateur = p_idutilisateur;
END;
$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION utilisateur_lister_tout() 
RETURNS SETOF v_utilisateur_avec_roles 
AS $ccode$
BEGIN
	RETURN QUERY
	SELECT * 
	FROM v_utilisateur_avec_roles 
	ORDER BY pseudo;
END;
$ccode$ LANGUAGE plpgsql;


--CREATE FUNCTION utilisateur_valider_authentification( p_pseudo VARCHAR, p_motdepasse VARCHAR ) 
--RETURNS TABLE (
--    idutilisateur    INT,
--    pseudo      VARCHAR,
--    motdepasse  VARCHAR,
--    email       VARCHAR,
--    roles       VARCHAR[]
--)
--AS $ccode$
--BEGIN
--	RETURN QUERY
--	SELECT c.*,  ARRAY_AGG( r.role ) AS roles
--	FROM utilisateur c 
--	LEFT JOIN role r ON c.idutilisateur = r.idutilisateur
--	WHERE c.pseudo = P_pseudo
--	  AND c.motdepasse = p_motdepasse
--	GROUP BY c.idutilisateur;
--END;
--$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION utilisateur_valider_authentification( p_pseudo VARCHAR, p_motdepasse VARCHAR ) 
RETURNS SETOF v_utilisateur_avec_roles
AS $ccode$
BEGIN
	RETURN QUERY
	SELECT * FROM v_utilisateur_avec_roles
	WHERE pseudo = P_pseudo
	  AND motdepasse = p_motdepasse;
END;
$ccode$ LANGUAGE plpgsql;


CREATE FUNCTION utilisateur_verifier_unicite_pseudo(
	p_pseudo 		VARCHAR,
	p_idutilisateur 		INT,
	OUT p_unicite	BOOLEAN
) 
AS $ccode$
BEGIN
	SELECT COUNT(*) = 0 INTO p_unicite
	FROM utilisateur
	WHERE pseudo = p_pseudo
	  AND idutilisateur <> P_idutilisateur;
END;
$ccode$ LANGUAGE plpgsql;


