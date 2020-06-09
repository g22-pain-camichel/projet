package projet.view.equipeparticipant;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Club;
import projet.data.Participant;
import projet.report.EnumReport;
import projet.report.ManagerReport;
import projet.view.EnumView;
import projet.view.connexion.ModelConnexion;

public class EquipeParticipantController {
	@Inject
	private IManagerGui		managerGui;
	@Inject 
	private ManagerReport managerReport;
	@Inject 
	private ModelConnexion modelConnexion;

	@FXML
	private Label user;

	@FXML
	private Button button_add;

	@FXML
	private Button button_update;


	@FXML
	private Button button_delete;

	@FXML
	private Button button_find;

	@FXML
	private Button button_val;

	@Inject
	private ModelEquipeParticipant modelEquipeParticipant;

	@FXML
	private ListView<Club> listView;

	private Club courant;

	@FXML
	private Button button_validC;
	@FXML
	private Button button_nonValidC;

	@FXML
	private Button button_init;

	@FXML
	private TextField textField_id;

	@FXML
	private TextField textField_name;

	@FXML
	private TextField textField_nbRepas;

	private String txt="";
	@FXML
	private TextField textField_find, textField_cat;


	@FXML
	private TextField textField_idC;

	@FXML
	private TextField textField_nameC;
	@FXML
	private TextField textField_surnameC;
	@FXML
	private TextField textField_emailC;
	@FXML
	private TextField textField_phoneC;

	@FXML
	private TextField textfield_adresseC;
	@FXML
	private DatePicker datePicker_birthdayC;

	@FXML
	private TextField textField_idE, textField_nameE, textField_surnameE, 
		textField_emailE, textField_phoneE, textfield_adresseE;
	
	@FXML
	private DatePicker datePicker_birthdayE;
	
	private Participant capitain, equipier;
	@FXML
	private JFXButton button_print;

	@FXML
	private void initialize() {
		user.setText(modelConnexion.getUtilisateurActif().getPseudo());
		// data binding
		courant = modelEquipeParticipant.getCourant();
		capitain = modelEquipeParticipant.getCapitiane();
		equipier = modelEquipeParticipant.getEquipier();
		
		System.out.println("avant: "+listView.getSelectionModel().getSelectedIndex());
		if (txt.equals("Valide")) {
			listView.setItems(modelEquipeParticipant.getListe(true));
		}
		else if (txt.equals("NonValide")) {
			listView.setItems(modelEquipeParticipant.getListe(false));
		}
		else {
			listView.setItems(modelEquipeParticipant.getListe());
		}


		listView.setCellFactory(UtilFX.cellFactory(item -> item.getNom()+
				" [ validé: "+item.getEstValide().toString()+ " ]"));



		if (courant.getNum() != null) {
			UtilFX.selectInListView( listView, courant );
			listView.requestFocus();
			System.out.println("avant: "+listView.getSelectionModel().getSelectedIndex());
			
			// fill captain fields
			if (capitain.getNum() != null) fillCap();
			
			// fill partner fields
			if (equipier.getNum() != null) fillEq();

			textField_id.textProperty().bindBidirectional(courant.numProperty(), new ConverterStringInteger());

			textField_name.textProperty().bindBidirectional(courant.nomProperty());

			textField_nbRepas.textProperty().bindBidirectional(courant.nbRepasReservesProperty(),  new ConverterStringInteger());
			textField_cat.textProperty().bindBidirectional(courant.categorieProperty(),  new ConverterStringInteger());
		}

	}



	@FXML
	public void doFillGap() {
		if (listView.getSelectionModel().getSelectedIndex() > -1) {
			modelEquipeParticipant.preparerModifier(listView.getSelectionModel().getSelectedItem() );
			initialize();				
		}
	}

	@FXML
	public void doFind() {
		if (!textField_find.getText().isEmpty()) {
			List<Club> list = modelEquipeParticipant.find(textField_find.getText());
			if (list.isEmpty()) {
				managerGui.showDialogMessage("Aucun club possédant ce nom n'a été trouvé");
			}
			else {
				UtilFX.selectInListView( listView, list.get(0) );
			}
		}
	}

// fill captain fields
	
	private void fillCap() {
		textField_idC.textProperty().bindBidirectional(capitain.numProperty(), new ConverterStringInteger());
		textField_nameC.textProperty().bindBidirectional(capitain.nomProperty());
		textField_surnameC.textProperty().bindBidirectional(capitain.prenomProperty());
		textField_emailC.textProperty().bindBidirectional(capitain.emailProperty());
		textfield_adresseC.textProperty().bindBidirectional(capitain.adressePostProperty());
		textField_phoneC.textProperty().bindBidirectional(capitain.telProperty());
		datePicker_birthdayC.getEditor().textProperty().bindBidirectional(capitain.dtNaissProperty(), new ConverterStringLocalDate());
	}
	private void fillEq() {
		textField_idE.textProperty().bindBidirectional(equipier.numProperty(), new ConverterStringInteger());
		textField_nameE.textProperty().bindBidirectional(equipier.nomProperty());
		textField_surnameE.textProperty().bindBidirectional(equipier.prenomProperty());
		textField_emailE.textProperty().bindBidirectional(equipier.emailProperty());
		textfield_adresseE.textProperty().bindBidirectional(equipier.adressePostProperty());
		textField_phoneE.textProperty().bindBidirectional(equipier.telProperty());
		datePicker_birthdayE.getEditor().textProperty().bindBidirectional(equipier.dtNaissProperty(), new ConverterStringLocalDate());
	}
	
	

	@FXML
	public void find_validC() {
		txt = "Valide";
		initialize();
	}

	@FXML
	public void find_nonValidC() {
		txt = "NonValide";
		initialize();
	}

	@FXML
	public void doInitList() {
		txt = "";
		initialize();
	}

	@FXML
	public void doAdd() throws ParseException {
		courant.setEstValide(true);
		doUpdate();
	}

	@FXML
	public void doUpdate() throws ParseException {
		modelEquipeParticipant.validerMiseAJour();
		initialize();
	}

	@FXML
	public void doDelete() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			modelEquipeParticipant.supprimer( listView.getSelectionModel().getSelectedItem() );
			initialize();
		}
	}




	@FXML
	public void doPrint() {
		managerReport.openFilePdf(EnumReport.listeEquipeParticipant, null);
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
