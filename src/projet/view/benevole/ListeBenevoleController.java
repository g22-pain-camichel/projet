package projet.view.benevole;

import java.text.ParseException;

import javax.inject.Inject;

import com.jfoenix.controls.JFXTimePicker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.view.EnumView;

public class ListeBenevoleController {
	@Inject
	private IManagerGui		managerGui;
	
	@Inject
	private ModelBenevole modelBenevole;
	
	@FXML
	private TextField textField_id, textField_name, textField_surname, textField_email,
		textField_phone, textField_equipe, textField_find;
	
	@FXML
	private DatePicker datePicker_birthday;
	
	@FXML
	private JFXTimePicker timePicker_startH, timePicker_endH;
	
	@FXML
	private ComboBox comboBox_desireFunc;
	
	@FXML
	private ToggleGroup toggleSex;
	
	@FXML
	private ListView<Benevole> listView;
	
	@FXML
	private Button button_add, button_update, button_delete, button_find;
	
	private Benevole courant;
	
	@FXML
	private void initialize() {
		// data binding
		courant = modelBenevole.getCourant();
		listView.setItems(modelBenevole.getListe());
		listView.setCellFactory(UtilFX.cellFactory(item -> item.getNom()+" "+item.getPrenom()));
		
		if (courant != null) {
			UtilFX.selectInListView( listView, modelBenevole.getCourant() );
			listView.requestFocus();
			
			toggleSex.selectedToggleProperty().addListener(obs -> actualiserSexeDansModele());
			modelBenevole.getCourant().sexeProperty().addListener(obs -> actualiserSexeDansVue());
			
			textField_id.textProperty().bindBidirectional(courant.identifiantProperty(), new ConverterStringInteger());
			
			textField_name.textProperty().bindBidirectional(courant.nomProperty());
			
			textField_surname.textProperty().bindBidirectional(courant.prenomProperty());
			
			textField_email.textProperty().bindBidirectional(courant.emailProperty());
			
			textField_phone.textProperty().bindBidirectional(courant.telProperty());
			
			datePicker_birthday.getEditor().textProperty().bindBidirectional(courant.dtNaissProperty(), new ConverterStringLocalDate());
			datePicker_birthday.getEditor().focusedProperty().addListener(new ListenerFocusValidation(courant.dtNaissProperty(), "le format de votre date est incorrect"));
			
			timePicker_startH.getEditor().textProperty().bindBidirectional(courant.hrDbDispoProperty());
			timePicker_endH.getEditor().textProperty().bindBidirectional(courant.hrFinDispoProperty());
		}
	}
	
	@FXML
	public void doFillGap() {
		modelBenevole.preparerModifier(listView.getSelectionModel().getSelectedItem() );
		initialize();
	}
	
	@FXML
	public void doAdd() {
		
	}
	
	@FXML
	public void doUpdate() throws ParseException {
		modelBenevole.validerMiseAJour();
		initialize();
	}
	
	@FXML
	public void doDelete() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			modelBenevole.supprimer( listView.getSelectionModel().getSelectedItem() );
			initialize();
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
	
	private void actualiserSexeDansModele() {
		// modifie le sexe en fonction du bouton cliqué
		Toggle button = toggleSex.getSelectedToggle();
		int sex = toggleSex.getToggles().indexOf(button);
		modelBenevole.getCourant().setSexe(sex);
	}
	
	private void actualiserSexeDansVue() {
		int sex = modelBenevole.getCourant().getSexe();
		Toggle button = toggleSex.getToggles().get(sex);
		toggleSex.selectToggle(button);
	}
}
