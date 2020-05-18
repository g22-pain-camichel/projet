package projet.view.accueil;

import javax.inject.Inject;

import javafx.fxml.FXML;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;

public class ControllerAccueil {

	@Inject
	private IManagerGui managerGui;

	@FXML
	public void deconnexion() {
		managerGui.showView(EnumView.Connexion);
	}
}
