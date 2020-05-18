package projet.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import jfox.javafx.view.IManagerGui;


public class MenuVertical extends VBox {
	
	
	// Champs
	
	@Inject
	private IManagerGui 	managerGui;
	
	
	// Initialisation
	
	@PostConstruct
	public void init() {

		
		// Variables de travail
		Button button;
		

	    this.getStyleClass().add("menu-vertical");
	    this.setFillWidth(true);

	    button = new Button("Mémos");
	    button.setOnAction(  (e) -> managerGui.showView( EnumView.MemoListe )  );
	    this.getChildren().addAll( button );

	    button = new Button("Personnes");
	    button.setOnAction(  (e) -> managerGui.showView( EnumView.PersonneListe )  );
	    this.getChildren().addAll( button );

	    button = new Button("Catégories");
	    button.setOnAction(  (e) -> managerGui.showView( EnumView.CategorieListe )  );
	    this.getChildren().addAll( button );

	    button = new Button("Comptes");
	    button.setOnAction(  (e) -> managerGui.showView( EnumView.CompteListe )  );
	    this.getChildren().addAll( button );

	    button = new Button("Déconnexion");
	    button.setOnAction(  (e) -> managerGui.showView( EnumView.Connexion )  );
	    this.getChildren().addAll( button );
	
	    for ( Node node :this.getChildren() ) {
	    	if ( node instanceof Button ) {
		    	( (Button) node ).setPrefWidth(100);
	    	}
	    }
	    
	    
	}

	
	
	
	
}
