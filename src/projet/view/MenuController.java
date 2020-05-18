package projet.view;

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
	public void doInscriptionParticipant() {
		managerGui.showView(EnumView.InscrireParticipant);
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
}
