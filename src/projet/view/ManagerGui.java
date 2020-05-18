package projet.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import jfox.commun.context.IContext;
import jfox.javafx.view.ManagerGuiAbstract;


public class ManagerGui extends ManagerGuiAbstract {

	
	// Champs
	
	@Inject
	private IContext		context;
	
	
	// Initialisation
	
	@PostConstruct
	public void init() {
		setFactoryController( context::getBeanNew );
		addRessourcesToClose( context );
	}

	
	// Actions

	@Override
	protected void configureStage()  {
		
		// Choisit la vue à afficher
		showView( EnumView.Connexion);
		
		// Configure le stage
		stage.setTitle( "Gestion des bénévoles" );
		stage.setWidth(1120);
		stage.setHeight(690);
		stage.getIcons().add(new Image(getClass().getResource("../images/logo_ico.png").toExternalForm()));
		stage.setResizable(false);
	
		
		// Configuration par défaut pour les boîtes de dialogue
		typeConfigDialogDefault = ConfigDialog.class;
	}

	
	@Override
	public Scene createScene( Parent root ) {
		AnchorPane paneMenu = new AnchorPane( root );
		Scene scene = new Scene( paneMenu );
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		return scene;
	}
	
}