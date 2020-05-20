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
	
	@FXML
	private Label totalP;
	
	@FXML
	private Label totalEqP;
	
	
	@Inject
	private ModelAccueil modelAccueil;
	
	@FXML
	private void initialize() {
		totalB.setText(modelAccueil.totalBenevole());
		totalEqB.setText(modelAccueil.totalEquipeBenevole());
		totalP.setText(modelAccueil.totalParticipant());
		totalEqP.setText(modelAccueil.totalEquipeParticipant());
		
	}
	
	@FXML
	public void deconnexion() {
		managerGui.showView(EnumView.Connexion);
	}
	
	
	
}
