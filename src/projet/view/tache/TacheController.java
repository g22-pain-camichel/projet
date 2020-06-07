package projet.view.tache;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Tache;
import projet.view.EnumView;
import projet.view.connexion.ModelConnexion;

public class TacheController {
	@Inject
	private IManagerGui		managerGui;
	@Inject 
	private ModelConnexion modelConnexion;
	
	@Inject
	private ModelTache modelTache;

	@FXML
	private Label user;
	
	@FXML
	private TextField textField_nom, textField_size, textField_location, textField_find;
	
	@FXML
	private JFXTimePicker timePicker_startH, timePicker_endH;
	
	@FXML
	private ListView<Tache> listView;
	
	@FXML
	private ComboBox<String> comboBox_typeB, comboBox_valideB; 
	
	@FXML
	private Button button_create, button_update, button_delete, 
		button_find, button_refresh;
	
	private Tache tache;
	
	
	@FXML
	private void initialize() {
		user.setText(modelConnexion.getUtilisateurActif().getPseudo());
		// data binding
		tache = modelTache.getTache();
		listView.setItems(modelTache.getListe());
		listView.setCellFactory(UtilFX.cellFactory(item -> item.getLibelle()));
		button_create.setDisable(false);
		
		if (tache.getLibelle() != null) {
			UtilFX.selectInListView( listView, tache );
			listView.requestFocus();
			button_create.setDisable(true);
		}
		textField_size.textProperty().bindBidirectional(tache.tailleProperty(), new ConverterStringInteger());
		
		textField_nom.textProperty().bindBidirectional(tache.libelleProperty());
		
		textField_location.textProperty().bindBidirectional(tache.emplacementProperty());
		
		timePicker_startH.getEditor().textProperty().bindBidirectional(tache.hr_debProperty());
		timePicker_endH.getEditor().textProperty().bindBidirectional(tache.hr_finProperty());
	}
	
	@FXML
	public void doFillGap() {
		if (listView.getSelectionModel().getSelectedIndex() > -1) {
			modelTache.preparerAjouter();
			modelTache.preparerModifier(listView.getSelectionModel().getSelectedItem() );
			initialize();						
		}
	}
	
	@FXML
	public void doRefresh() throws ParseException {
		modelTache.preparerAjouter();
		initialize();
	}
	
	@FXML
	public void doUpdate() throws ParseException {
		if (button_create.isDisable()) 
			modelTache.validerMiseAJour(1);
		else 
			modelTache.validerMiseAJour(0);
		modelTache.preparerAjouter();
		initialize();
	}
	
	@FXML
	public void doDelete() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			modelTache.supprimer( listView.getSelectionModel().getSelectedItem() );
			initialize();
		}
	}
	
	@FXML
	public void doFind() {
		if (!textField_find.getText().isEmpty()) {
			List<Tache> maListe = modelTache.find(textField_find.getText());
			if (maListe.isEmpty()) {
				managerGui.showDialogMessage("Aucune tâche possédant ce nom n'a été trouvé");
			}
			else {
				UtilFX.selectInListView( listView, maListe.get(0) );
			}
		}
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
