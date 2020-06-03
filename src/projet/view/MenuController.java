package projet.view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;

import javafx.fxml.FXML;
import jfox.javafx.view.IManagerGui;
import projet.view.connexion.ModelConnexion;

public class MenuController {

	@Inject
	private IManagerGui managerGui;

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
		managerGui.showView(EnumView.TachesMissions);
	}
	
	@FXML
	public void doEpreuveAdmin() {
		managerGui.showView(EnumView.EpreuveAdmin);
	}
	@FXML
	public void doCategorie() {
		managerGui.showView(EnumView.Categorie);
	}
}
