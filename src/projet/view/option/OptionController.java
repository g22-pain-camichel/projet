package projet.view.option;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;
import projet.view.connexion.ModelConnexion;

public class OptionController {
	@Inject
	private IManagerGui		managerGui;
	@Inject 
	private ModelConnexion modelConnexion;
	
	@FXML
	private Label user;
	
	
	@FXML 
	private void initialize() {
		// TODO Auto-generated method stub
		user.setText(modelConnexion.getUtilisateurActif().getPseudo());
	}
	
	@FXML
	public void doAccueil() {
		managerGui.showView(EnumView.Accueil);
	}
	
	@FXML
	public void deconnexion() {
		modelConnexion.fermerSessionUtilisateur();
		managerGui.showView(EnumView.Connexion);
	}
	
}
