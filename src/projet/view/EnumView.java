package projet.view;

import javafx.scene.Scene;
import jfox.javafx.view.IEnumView;


public enum EnumView implements IEnumView {

	
	// Valeurs
	
	Info				( "systeme/ViewInfo.fxml" ),
	Connexion			( "connexion/connexion.fxml" ),
	Accueil				( "accueil/accueil.fxml" ),	
	CompteListe			( "compte/ViewCompteListe.fxml" ),
	CompteForm			( "compte/ViewCompteForm.fxml" ),
	CategorieListe		( "personne/ViewCategorieListe.fxml" ),
	CategorieForm		( "personne/ViewCategorieForm.fxml" ),
	PersonneListe		( "personne/ViewPersonneListe.fxml" ),
	PersonneForm		( "personne/ViewPersonneForm.fxml" ),
	MemoListe			( "memo/ViewMemoListe.fxml" ),
	MemoForm			( "memo/ViewMemoForm.fxml" ),
	MemoAjoutPersonnes	( "memo/ViewMemoAjoutPersonnes.fxml" ),
	ServiceListe		( "service/ViewServiceListe.fxml" ),
	ServiceForm			( "service/ViewServiceForm.fxml" ),
	TestDaoCategorie	( "test/ViewTestDaoCategorie.fxml" ),
	TestDaoMemo			( "test/ViewTestDaoMemo.fxml" ),
	TestDaoService		( "test/ViewTestDaoService.fxml" ),
	EtatPersonnesParCateogire1	( "personne/ViewEtatPersonnesParCategorie1.fxml" ),
	EtatPersonnesParCateogire2	( "personne/ViewEtatPersonnesParCategorie2.fxml" ),
	ListParticipants 	("listeparticipants/ListeParticipantView.fxml"),
	ListBenevoles		("listebenevoles/ListedesBenevoles.fxml"),
	EquipeParticipant	("equipeparticipant/EquipesDeParticipant.fxml"),
	TachesMissions		("tacheMission/TacheMission.fxml"),
	InscrireParticipant	("inscrireparticipant/InscrireParticipant.fxml"),
	Options				("option/Option.fxml"),
	Apropos				("apropos/Apropos.fxml"),
	;

	
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
