package projet.view.tache;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import com.jfoenix.controls.JFXTimePicker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Tache;
import projet.data.Tache;
import projet.view.EnumView;

public class TacheController {
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelTache modelTache;
	
	@FXML
	private TextField textField_nom, textField_size, textField_location, textField_find;
	
	@FXML
	private JFXTimePicker timePicker_startH, timePicker_endH;
	
	@FXML
	private Button button_create, button_update, button_delete, button_find;
	
	@FXML
	private ListView<Tache> listView;
	
	private Tache tache;
	
	@FXML
	private void initialize() {
		tache = modelTache.getTache();
		
		listView.setItems(modelTache.getListe());
		listView.setCellFactory(UtilFX.cellFactory(item -> item.getLibelle()));		
		disableForm();
		if (tache.getLibelle() != null) {
			UtilFX.selectInListView(listView, modelTache.getTache());
			listView.requestFocus();
			enableForm();
			button_create.setDisable(true);
			
			textField_nom.textProperty().bindBidirectional(tache.libelleProperty());
			textField_size.textProperty().bindBidirectional(tache.tailleProperty(),  new ConverterStringInteger());
			textField_size.focusedProperty().addListener(new ListenerFocusValidation(tache.tailleProperty(), "Un nombre valide est demandé"));
			textField_location.textProperty().bindBidirectional(tache.emplacementProperty());
			timePicker_startH.getEditor().textProperty().bindBidirectional(tache.hr_debProperty());
			timePicker_endH.getEditor().textProperty().bindBidirectional(tache.hr_finProperty());
		}
	}
	
	public void disableForm() {
		textField_nom.setDisable(true);
		textField_location.setDisable(true);
		textField_size.setDisable(true);
		button_create.setDisable(true);
		button_update.setDisable(true);
		button_delete.setDisable(true);
		timePicker_startH.setDisable(true);
		timePicker_endH.setDisable(true);
	}
	
	public void enableForm() {
		textField_nom.setDisable(false);
		textField_location.setDisable(false);
		textField_size.setDisable(false);
		button_create.setDisable(false);
		button_update.setDisable(false);
		button_delete.setDisable(false);
		timePicker_startH.setDisable(false);
		timePicker_endH.setDisable(false);
	}
	
	@FXML
	public void doFillGap() {
		if (listView.getSelectionModel().getSelectedIndex() > -1) {
			modelTache.preparerAjouter();
			modelTache.preparerModifier(listView.getSelectionModel().getSelectedItem() );
			initialize();			
		}
		else {
			modelTache.preparerAjouter();
			initialize();
		}
	}
	
	@FXML
	public void doUpdate() throws ParseException {
		modelTache.validerMiseAJour();
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
		managerGui.showView(EnumView.Connexion);
	}
}
