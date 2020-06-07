package projet.view.accueil;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;
import projet.view.connexion.ModelConnexion;

public class ControllerAccueil {

	@Inject
	private IManagerGui managerGui;
	
	@Inject 
	private ModelConnexion modelConnexion;
	
	@FXML
	private Label totalB, totalEqB, totalP, totalEqP, user;
	
	
	@Inject
	private ModelAccueil modelAccueil;
	
	@FXML
	private void initialize() {
		totalB.setText(modelAccueil.totalBenevole());
		//totalEqB.setText(modelAccueil.totalEquipeBenevole());
		totalP.setText(modelAccueil.totalParticipant());
		totalEqP.setText(modelAccueil.totalEquipeParticipant());
		user.setText(modelConnexion.getUtilisateurActif().getPseudo());
	}
	
	@FXML
	public void deconnexion() {
		modelConnexion.fermerSessionUtilisateur();
		managerGui.showView(EnumView.Connexion);
	}
}
