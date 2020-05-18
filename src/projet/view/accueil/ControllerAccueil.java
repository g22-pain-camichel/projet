package projet.view.accueil;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;

public class ControllerAccueil {

	@Inject
	private IManagerGui managerGui;
	
	@FXML
	private Label totalB, totalEqB;
	
	@Inject
	private ModelAccueil modelAccueil;
	
	@FXML
	private void initialize() {
		totalB.setText(modelAccueil.totalBenevole());
		totalEqB.setText(modelAccueil.totalEquipeBenevole());
	}
	
	@FXML
	public void deconnexion() {
		managerGui.showView(EnumView.Connexion);
	}
	
}
