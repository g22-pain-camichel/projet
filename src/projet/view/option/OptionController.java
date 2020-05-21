package projet.view.option;

import javax.inject.Inject;

import javafx.fxml.FXML;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;

public class OptionController {
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