package projet.view;

import javafx.scene.Scene;
import jfox.javafx.view.IEnumView;


public enum EnumView implements IEnumView {

	
	// Valeurs
	
	// ours
	
	Info				( "systeme/ViewInfo.fxml" ),
	Connexion			( "connexion/connexion.fxml" ),
	Accueil				( "accueil/accueil.fxml" ),	
	Options				("option/Option.fxml"),
	Apropos				("apropos/Apropos.fxml"),
	ListBenevoles		("benevole/ListedesBenevoles.fxml"),
	EquipeBenevole		("equipebenevole/EquipeBenevole.fxml"),
	EquipeParticipant	("equipeparticipant/EquipesDeParticipant.fxml"),
	ListParticipants 	("participant/ListedesParticipants.fxml"),
	InscrireParticipant	("participant/InscrireParticipant.fxml"),
	Epreuve				("epreuve/Epreuve.fxml"),
	EpreuveAdmin		("epreuveadmin/EpreuveAdmin.fxml"),
	Tache				("tache/Tache.fxml");
	
	// Champs
	
	private String		path;
	private Object		controller;
	private Scene		scene;

	
	// Constructeur 
	
	EnumView( String path ) {
		this.path = path;
	}

	
	// Getters & setters

	@Override
	public String getPath() {
		return path;
	}
	
	@Override
	public Object getController() {
		return controller;
	}

	@Override
	public void setController(Object controller) {
		this.controller = controller;
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}
	
	@Override
	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
