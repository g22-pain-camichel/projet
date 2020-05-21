package projet.view.tacheMission;

import javax.inject.Inject;

import javafx.fxml.FXML;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;

public class TacheMissionController {
	@Inject
	private IManagerGui		managerGui;
	
	@FXML
	public void doAccueil() {
		managerGui.showView(EnumView.Accueil);
	}
	
	@FXML
	public void deconnexion() {
		managerGui.showView(EnumView.Connexion);
	}
	
}