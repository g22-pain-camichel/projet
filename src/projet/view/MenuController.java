package projet.view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import jfox.javafx.view.IManagerGui;
import projet.commun.Roles;
import projet.data.Utilisateur;
import projet.view.connexion.ModelConnexion;

public class MenuController {

	@Inject
	private IManagerGui managerGui;
	
	@Inject
	private ModelConnexion modelConnexion;

	@FXML
	public void doAccueil() {
		managerGui.showView(EnumView.Accueil);
	}
	
	@FXML
	public void doEpreuve() {
		managerGui.showView(EnumView.Epreuve);
	}

	@FXML
	public void doListParticpant() {
		managerGui.showView(EnumView.ListParticipants);
	}

	@FXML
	public void doListBenevoles() {
		managerGui.showView(EnumView.ListBenevoles);
	}

	@FXML
	public void doEquipeParticpant() {
		managerGui.showView(EnumView.EquipeParticipant);
	}

	@FXML
	public void doInscriptionParticipant() throws IOException, URISyntaxException {
		//Ouvrir le lien de notre site web
		Desktop d = Desktop.getDesktop();
		d.browse(new URI("https://www.google.com"));
	}

	@FXML
	public void doInscriptionBenevole() {
		// managerGui.showView(EnumView.InscrireBenevole);
	}

	@FXML
	public void doOptions() {
		managerGui.showView(EnumView.Options);
	}

	@FXML
	public void doApropos() {
		managerGui.showView(EnumView.Apropos);
	}

	@FXML
	public void doTacheMission() {
		managerGui.showView(EnumView.EquipeBenevole);
	}
	
	@FXML
	public void doEpreuveAdmin() {
		configurerMenu(modelConnexion.getUtilisateurActif(), 0);
		// le changement du compte connecté modifie automatiquement le menu
		modelConnexion.UtilisateurActifProperty().addListener((obs) -> {
			Platform.runLater( () -> configurerMenu( modelConnexion.getUtilisateurActif(), 0 ) );
		});
	}
	@FXML
	public void doCategorie() {
		configurerMenu(modelConnexion.getUtilisateurActif(), 1);
		// le changement du compte connecté modifie automatiquement le menu
		modelConnexion.UtilisateurActifProperty().addListener((obs) -> {
			Platform.runLater( () -> configurerMenu( modelConnexion.getUtilisateurActif(), 0 ) );
		});
	}
	
	
	private void configurerMenu( Utilisateur utilisateurActif, int categorie ) {
		if( utilisateurActif != null ) {
			if (categorie == 0) { // si c'est une épreuve
				if( utilisateurActif.isInRole( Roles.UTILISATEUR) ) {
					managerGui.showDialogMessage("Désolé seul les administrateurs ont accès à ces données");
				}
				if( utilisateurActif.isInRole( Roles.ADMINISTRATEUR ) ) {
					managerGui.showView(EnumView.EpreuveAdmin);
				}
			}
			else { // si c'est une tache
				if( utilisateurActif.isInRole( Roles.UTILISATEUR) ) {
					managerGui.showDialogMessage("Désolé seul les administrateurs ont accès à ces données");
				}
				if( utilisateurActif.isInRole( Roles.ADMINISTRATEUR ) ) {
					managerGui.showView(EnumView.Tache);
				}
			}
			
		}
	}
	
}
