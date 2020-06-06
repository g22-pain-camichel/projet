package projet.view.benevole;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import com.jfoenix.controls.JFXTimePicker;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
import projet.data.Permi;
import projet.view.EnumView;

public class ListeBenevoleController {
	@Inject
	private IManagerGui		managerGui;
	
	@Inject
	private ModelBenevole modelBenevole;
	
	@FXML
	private Label label_valide;
	
	@FXML
	private TextField textField_id, textField_name, textField_surname, textField_email,
		textField_phone, textField_equipe, textField_find, textField_permiN, textField_lieuDelP;
	
	@FXML
	private DatePicker datePicker_birthday, datePicker_permi;
	
	@FXML
	private JFXTimePicker timePicker_startH, timePicker_endH;
	
	@FXML
	private ToggleGroup toggleSex;
	
	@FXML
	private ListView<Benevole> listView;
	
	@FXML
	private ComboBox<String> comboBox_typeB, comboBox_valideB; 
	
	@FXML
	private Button button_val, button_update, button_delete, 
		button_find, button_validB, button_nonValidB, button_init;
	
	private Benevole courant;
	
	private Permi permi;
	
	private String txt="";
	
	@FXML
	private void initialize() {
		// data binding
		courant = modelBenevole.getCourant();
		permi = modelBenevole.getPermi();
		System.out.println("avant: "+listView.getSelectionModel().getSelectedIndex());
		if (txt.equals("Valide")) {
			listView.setItems(modelBenevole.getListe(true));
		}
		else if (txt.equals("NonValide")) {
			listView.setItems(modelBenevole.getListe(false));
		}
		else {
			listView.setItems(modelBenevole.getListe());
		}
		listView.setCellFactory(UtilFX.cellFactory(item -> item.getNom()+" "+item.getPrenom()+
				" [ validé: "+item.getEstValide().toString()+ " ]"));
		
		comboBox_typeB.setItems(FXCollections.observableArrayList("interne", "externe"));
		
		if (courant.getIdentifiant() != null) {
			UtilFX.selectInListView( listView, courant );
			listView.requestFocus();
			System.out.println("avant: "+listView.getSelectionModel().getSelectedIndex());
			label_valide.setText(courant.getEstValide().toString());
			
			if (courant.getEstValide()) {
				button_val.setDisable(true);
			}
			else {
				button_val.setDisable(false);
			}
			
			toggleSex.selectedToggleProperty().addListener(obs -> actualiserSexeDansModele());
			modelBenevole.getCourant().sexeProperty().addListener(obs -> actualiserSexeDansVue());
			
			textField_id.textProperty().bindBidirectional(courant.identifiantProperty(), new ConverterStringInteger());
			
			textField_name.textProperty().bindBidirectional(courant.nomProperty());
			
			textField_surname.textProperty().bindBidirectional(courant.prenomProperty());
			
			textField_email.textProperty().bindBidirectional(courant.emailProperty());
			
			textField_phone.textProperty().bindBidirectional(courant.telProperty());
			
			textField_permiN.textProperty().bindBidirectional(permi.numeroProperty());
			
			textField_lieuDelP.textProperty().bindBidirectional(permi.lieuProperty());
			
			datePicker_birthday.getEditor().textProperty().bindBidirectional(courant.dtNaissProperty(), new ConverterStringLocalDate());
			datePicker_birthday.getEditor().focusedProperty().addListener(new ListenerFocusValidation(courant.dtNaissProperty(), "le format de votre date est incorrect"));
			
			datePicker_permi.getEditor().textProperty().bindBidirectional(permi.dateDelivranceProperty(), new ConverterStringLocalDate());
			datePicker_permi.getEditor().focusedProperty().addListener(new ListenerFocusValidation(permi.dateDelivranceProperty(), "le format de votre date est incorrect"));
			
			timePicker_startH.getEditor().textProperty().bindBidirectional(courant.hrDbDispoProperty());
			timePicker_endH.getEditor().textProperty().bindBidirectional(courant.hrFinDispoProperty());
			
			comboBox_typeB.getEditor().textProperty().bindBidirectional(courant.typeProperty());
		}
	}
	
	@FXML
	public void doFillGap() {
		if (listView.getSelectionModel().getSelectedIndex() > -1) {
			modelBenevole.preparerAjouter();
			modelBenevole.preparerModifier(listView.getSelectionModel().getSelectedItem() );
			initialize();						
		}
	}
	
	@FXML
	public void doValider() throws ParseException {
		courant.setEstValide(true);
		doUpdate();
	}
	
	@FXML
	public void doUpdate() throws ParseException {
		modelBenevole.validerMiseAJour();
		modelBenevole.preparerAjouter();
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
	public void doFind() {
		if (!textField_find.getText().isEmpty()) {
			List<Benevole> maListe = modelBenevole.find(textField_find.getText());
			if (maListe.isEmpty()) {
				managerGui.showDialogMessage("Aucun bénévole possédant ce nom n'a été trouvé");
			}
			else {
				UtilFX.selectInListView( listView, maListe.get(0) );
			}
		}
	}
	
	@FXML
	public void find_validB() {
		txt = "Valide";
		initialize();
	}
	
	@FXML
	public void find_nonValidB() {
		txt = "NonValide";
		initialize();
	}
	
	@FXML
	public void doInitList() {
		txt = "";
		initialize();
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
		modelBenevole.preparerModifier(listView.getSelectionModel().getSelectedItem() );
		if (modelBenevole.getCourant().getIdentifiant() != null) {
			int sex = modelBenevole.getCourant().getSexe();
			Toggle button = toggleSex.getToggles().get(sex);
			toggleSex.selectToggle(button);
		}
	}
}
