package projet.view.participant;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import com.jfoenix.controls.JFXTimePicker;

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
import projet.data.Club;
import projet.data.Participant;
import projet.data.Permi;
import projet.view.EnumView;
import projet.view.connexion.ModelConnexion;

public class ListeParticipantController {
	@Inject
	private IManagerGui		managerGui;
	@Inject 
	private ModelConnexion modelConnexion;
	
	@Inject
	private ModelParticipantListe modelParticipant;
	
	@FXML
	private Label user,label_cm;
	
	@FXML
	private TextField textField_id, textField_name, textField_surname, textField_email,
		textField_phone,textField_find,textfield_adresse,textfield_clubnom;
	
	
	@FXML
	private ToggleGroup toggleSex;
	
	@FXML
	private ListView<Participant> listView;
	
	@FXML
	private Button button_add, button_update, button_delete, button_find;
	
	private Participant courant;
	
	private Club club;
	
	@FXML
	private void initialize() {

		user.setText(modelConnexion.getUtilisateurActif().getPseudo());
		// data binding
		courant = modelParticipant.getCourant();
		listView.setItems(modelParticipant.getListe());
		listView.setCellFactory(UtilFX.cellFactory(item -> item.getNom()+" "+item.getPrenom()));
		
		if (courant != null) {
			UtilFX.selectInListView( listView, modelParticipant.getCourant() );
			listView.requestFocus();
			
			toggleSex.selectedToggleProperty().addListener(obs -> actualiserSexeDansModele());
			modelParticipant.getCourant().sexeProperty().addListener(obs -> actualiserSexeDansVue());
			
			textField_id.textProperty().bindBidirectional(courant.numProperty(), new ConverterStringInteger());
			
			textField_name.textProperty().bindBidirectional(courant.nomProperty());
			
			textField_surname.textProperty().bindBidirectional(courant.prenomProperty());
			
			textField_email.textProperty().bindBidirectional(courant.emailProperty());
			
			textField_phone.textProperty().bindBidirectional(courant.telProperty());
			
			
						
		}
	}
	
	@FXML
	public void doFillGap() {
		modelParticipant.preparerModifier(listView.getSelectionModel().getSelectedItem() );
		initialize();
	}
	
	@FXML
	public void doAdd() {
		
	}
	
	@FXML
	public void doUpdate() throws ParseException {
		modelParticipant.validerMiseAJour();
		initialize();
	}
	
	@FXML
	public void doDelete() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			modelParticipant.supprimer( listView.getSelectionModel().getSelectedItem() );
			initialize();
		}
	}
	
	@FXML
	public void doFind() {
		if (!textField_find.getText().isEmpty()) {
			List<Participant> maListe = modelParticipant.find(textField_find.getText());
			if (maListe.isEmpty()) {
				managerGui.showDialogMessage("Aucun bénévole possédant ce nom n'a été trouvé");
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
	
	private void actualiserSexeDansModele() {
		// modifie le sexe en fonction du bouton cliqué
		Toggle button = toggleSex.getSelectedToggle();
		int sex = toggleSex.getToggles().indexOf(button);
		modelParticipant.getCourant().setSexe(sex);
	}
	
	private void actualiserSexeDansVue() {
		int sex = modelParticipant.getCourant().getSexe();
		Toggle button = toggleSex.getToggles().get(sex);
		toggleSex.selectToggle(button);
	}
	
	@FXML
	public void doInscriptionParticipant() throws IOException, URISyntaxException {
		//Ouvrir le lien de notre site web
		Desktop d = Desktop.getDesktop();
		d.browse(new URI("https://www.google.com"));
	}
	
	@FXML
	public void doVisualiserDocument() throws IOException, URISyntaxException {
		//Ouvrir le lien de notre site web
		Desktop d = Desktop.getDesktop();
		d.browse(new URI("http://localhost/paincamiche/filesp/"+label_cm.getText()));
	}

	
}
