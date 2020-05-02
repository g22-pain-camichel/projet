package projet.view.connexion;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import jfox.commun.exception.ExceptionValidation;
import projet.dao.DaoUtilisateur;
import projet.data.Utilisateur;


public class ModelConnexion {
	
	// Logger
	public static final Logger logger = Logger.getLogger( ModelConnexion.class.getName() );
	
	
	// Données observables 
	
	// Vue connexion
	private final Utilisateur courant = new Utilisateur();

	// Utilisateur connecté
	private final Property<Utilisateur>	UtilisateurActif = new SimpleObjectProperty<>();

	
	// Autres champs
	@Inject
	private DaoUtilisateur	daoUtilisateur;
	

	// Getters 
	
	public Utilisateur getCourant() {
		return courant;
	}
	
	public Property<Utilisateur> UtilisateurActifProperty() {
		return UtilisateurActif;
	}
	
	public Utilisateur getUtilisateurActif() {
		return UtilisateurActif.getValue();
	}
	
	
	// Initialisation
	
	@PostConstruct
	public void init() {
		courant.setPseudo( "geek" );
		courant.setMotDePasse( "geek" );
	}
	
	
	// Actions


	public void ouvrirSessionUtilisateur() {

		Utilisateur Utilisateur = daoUtilisateur.validerAuthentification(
					courant.pseudoProperty().getValue(), courant.motDePasseProperty().getValue() );
		
		if( Utilisateur == null ) {
			throw new ExceptionValidation( "Pseudo ou mot de passe invalide." );
		} else {
			Platform.runLater( () -> UtilisateurActif.setValue( Utilisateur ) );
		}
	}
	

	public void fermerSessionUtilisateur() {
		UtilisateurActif.setValue( null );
	}

}
