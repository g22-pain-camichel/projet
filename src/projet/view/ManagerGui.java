package projet.view;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import jfox.commun.context.IContext;
import jfox.javafx.view.ManagerGuiAbstract;
import projet.view.connexion.ModelConnexion;


public class ManagerGui extends ManagerGuiAbstract {

	
	// Champs
	
	@Inject
	private IContext		context;
	@Inject
	private ModelConnexion	modelConnexion;
	
	
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
		stage.setTitle( "Gestion de bol d'air" );
		stage.setWidth(900);
		stage.setHeight(700);
		stage.setMinWidth(400);
		stage.setMinHeight(300);
		stage.getIcons().add(new Image(getClass().getResource("logo_ico.png").toExternalForm()));
		stage.sizeToScene();
		stage.setResizable(false);;
		
		// Configuration par défaut pour les boîtes de dialogue
		typeConfigDialogDefault = ConfigDialog.class;
	}

	
	@Override
	public Scene createScene( Parent root ) {
		BorderPane paneMenu = new BorderPane( root );
//		paneMenu.setTop( context.getBeanNew( MenuBarAppli.class ) );
		if( modelConnexion.getUtilisateurActif() != null ) {
			// Charge le panneau
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource( "Menu.fxml" ));
			loader.setControllerFactory( factoryController );
			//Parent root = loader.load();
			
			try {
				paneMenu.setLeft( loader.load() );
			} catch (IOException e) {
				throw new RuntimeException(e);
			} 	
	}
		Scene scene = new Scene( paneMenu );
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		scene.getStylesheets().add(getClass().getResource("menu-vertical.css").toExternalForm());
		return scene;
	}
	
}