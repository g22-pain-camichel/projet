package projet.view.equipebenevole;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.data.Epreuve;
import projet.data.EquipeBenevole;
import projet.data.Tache;
import projet.view.EnumView;
import projet.view.connexion.ModelConnexion;

public class EquipeBenevoleController {
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelEquipeBenevole modelEquipeBenevole;
	@Inject 
	private ModelConnexion modelConnexion;
	
	@FXML
	private Label label_valide, user;
	
	@FXML
	private TextField textField_find, textField_equipe, textField_nbre, textField_id;
	
	@FXML
	private Button button_find, button_addB, button_rmvB, button_create, button_update, 
		button_delete, button_valid, button_validB, button_nValidB, button_refresh, button_init;
	
	@FXML
	private ComboBox<Epreuve> comboBox_epreuve;
	
	@FXML
	private ComboBox<Tache> comboBox_tache;
	
	@FXML
	private ComboBox<Benevole> comboBox_ajbvle, comboBox_enbvle;
	
	@FXML
	private ListView<EquipeBenevole> listView;
	
	@FXML
	private TableView<Benevole> tableView;
	
	@FXML
	private TableColumn<Benevole, String> tc_id, tc_nom, tc_prenom, tc_email, tc_tel;
	
	private EquipeBenevole courant;
	private Epreuve epreuve;
	private Benevole benevole;
	private Tache tache;
	
	private String txt = "";
	
	@FXML
	private void initialize() {
		user.setText(modelConnexion.getUtilisateurActif().getPseudo());
		// data binding
		courant = modelEquipeBenevole.getCourant();
		epreuve = modelEquipeBenevole.getEpreuve();
		benevole = modelEquipeBenevole.getBenevole();
		tache = modelEquipeBenevole.getTache();
		
		comboBox_epreuve.setItems(modelEquipeBenevole.getListeE());
		comboBox_epreuve.setCellFactory(UtilFX.cellFactory(item -> item.getNom()));
		
		listView.setCellFactory(UtilFX.cellFactory(item -> item.getNom()+
				" [ validé: "+item.getEstValide().toString()+ " ]"));
		
		if (txt.equals("Valide")) {
			listView.setItems(modelEquipeBenevole.getListe(true));
		}
		else if (txt.equals("NonValide")) {
			listView.setItems(modelEquipeBenevole.getListe(false));
		}
		else {
			listView.setItems(modelEquipeBenevole.getListe());
		}
		
		// tableview
		tc_id.setCellValueFactory(new PropertyValueFactory<Benevole, String>("identifiant"));
		tc_nom.setCellValueFactory(new PropertyValueFactory<Benevole, String>("nom"));
		tc_prenom.setCellValueFactory(new PropertyValueFactory<Benevole, String>("prenom"));
		tc_email.setCellValueFactory(new PropertyValueFactory<Benevole, String>("email"));
		tc_tel.setCellValueFactory(new PropertyValueFactory<Benevole, String>("tel"));
		
		tableView.setItems(modelEquipeBenevole.getListeBE());
		
		comboBox_ajbvle.setDisable(true);
		button_addB.setDisable(true);
		comboBox_enbvle.setDisable(true);
		button_rmvB.setDisable(true);
		button_create.setDisable(false);

		if (courant.getNum() != null) {
			
			UtilFX.selectInListView( listView, modelEquipeBenevole.getCourant() );
			listView.requestFocus();
			
			if (courant.getEstValide()) {
				disableForm();
			}
			else {
				enableForm();
				comboBox_ajbvle.setDisable(false);
				comboBox_ajbvle.setItems(modelEquipeBenevole.getListeB());
				comboBox_ajbvle.setCellFactory(UtilFX.cellFactory(item -> item.getNom()+
						" "+item.getPrenom()));
				button_addB.setDisable(false);
				
				comboBox_enbvle.setDisable(false);
				comboBox_enbvle.setItems(modelEquipeBenevole.getListB());
				comboBox_enbvle.setCellFactory(UtilFX.cellFactory(item -> item.getNom()+
						" "+item.getPrenom()));
				button_rmvB.setDisable(false);
				
				button_create.setDisable(true);
				
				label_valide.setText(courant.getEstValide().toString());
				
				if (courant.getEstValide()) {
					button_valid.setDisable(true);
				}
				else {
					button_valid.setDisable(false);
				}
			}
		}
		textField_id.textProperty().bindBidirectional(courant.numProperty(),  new ConverterStringInteger());
		textField_id.focusedProperty().addListener(new ListenerFocusValidation(courant.numProperty(), "Un nombre valide est demandé"));
		
		textField_equipe.textProperty().bindBidirectional(courant.nomProperty());
		textField_equipe.focusedProperty().addListener(new ListenerFocusValidation(courant.nomProperty(), "Un nom es demandé"));
		
		textField_nbre.textProperty().bindBidirectional(courant.nbreBenevoleProperty(),  new ConverterStringInteger());
		textField_nbre.focusedProperty().addListener(new ListenerFocusValidation(courant.nbreBenevoleProperty(), "Un nombre valide est demandé"));
		
	}
	
	public void disableForm() {
		textField_equipe.setDisable(true);
		textField_nbre.setDisable(true); 
		textField_id.setDisable(true); 
		
		comboBox_ajbvle.setDisable(true);
		comboBox_enbvle.setDisable(true);
		comboBox_epreuve.setDisable(true);
		comboBox_tache.setDisable(true);
		
		button_addB.setDisable(true);
		button_rmvB.setDisable(true);
		button_create.setDisable(true);
		button_delete.setDisable(true);
		button_update.setDisable(true);
		button_valid.setDisable(true);
	}
	
	public void enableForm() {
		textField_equipe.setDisable(false);
		textField_nbre.setDisable(false);
		textField_id.setDisable(false);
		
		comboBox_ajbvle.setDisable(false);
		comboBox_enbvle.setDisable(false);
		comboBox_epreuve.setDisable(false);
		comboBox_tache.setDisable(false);
		
		button_addB.setDisable(false);
		button_rmvB.setDisable(false);
		button_create.setDisable(false);
		button_delete.setDisable(false);
		button_update.setDisable(false);
		button_valid.setDisable(false);
	}
	
	@FXML
	public void doFillGap() {
		if (listView.getSelectionModel().getSelectedIndex() > -1) {
			modelEquipeBenevole.preparerAjouter();
			modelEquipeBenevole.preparerModifier(listView.getSelectionModel().getSelectedItem() );
			initialize();			
		}
	}
	
	@FXML
	public void doRefresh() throws ParseException {
		modelEquipeBenevole.preparerAjouter();
		initialize();
	}
	
	@FXML
	public void doValider() throws ParseException {
		courant.setEstValide(true);
		doUpdate();
	}
	
	@FXML
	public void doUpdate() throws ParseException {
		if (comboBox_tache.getSelectionModel().getSelectedIndex() > -1) {
			modelEquipeBenevole.preparerModifier(comboBox_tache.getSelectionModel().getSelectedItem());
			courant.setLibelle(tache.getLibelle());
		}
		modelEquipeBenevole.validerMiseAJour();
		modelEquipeBenevole.preparerAjouter();
		initialize();
	}
	
	@FXML
	public void doDelete() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			if (modelEquipeBenevole.isDeletable(listView.getSelectionModel().getSelectedItem())) {
				modelEquipeBenevole.supprimer( listView.getSelectionModel().getSelectedItem() );
			}
			else {
				managerGui.showDialogError("Veuillez retirer tous les bénévoles attribués à cette equipe afin de "
						+ "pouvoir effectuer cette opération");
			}
			initialize();
		}
	}
	
	@FXML
	public void doInitTache() {
		if (comboBox_epreuve.getSelectionModel().getSelectedIndex() > -1) {
			modelEquipeBenevole.preparerModifier(comboBox_epreuve.getSelectionModel().getSelectedItem());
			comboBox_tache.setItems(modelEquipeBenevole.getListeT());
		}
	}
	
	@FXML
	public void doFind() {
		if (!textField_find.getText().isEmpty()) {
			List<EquipeBenevole> maListe = modelEquipeBenevole.find(textField_find.getText());
			if (maListe.isEmpty()) {
				managerGui.showDialogMessage("Aucune équipe possédant ce nom n'a été trouvé");
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
		modelConnexion.fermerSessionUtilisateur();
		managerGui.showView(EnumView.Connexion);
	}
	
	@FXML
	public void addBenevoleEq() {
		if (!modelEquipeBenevole.equipePleine()) {
			modelEquipeBenevole.preparerModifier(comboBox_ajbvle.getSelectionModel().getSelectedItem());
			modelEquipeBenevole.ajouterBenevoleDansEquipe();
			initialize();
		}
		else {
			managerGui.showDialogError("Désole mais cette équipe est pleine");
			if (comboBox_tache.getSelectionModel().getSelectedIndex() > -1) {
				modelEquipeBenevole.preparerModifier(comboBox_tache.getSelectionModel().getSelectedItem());
				modelEquipeBenevole.bloquerTache();
				initialize();
			}
		}
	}
	@FXML
	public void rmvBenevoleEq() {
		if (comboBox_enbvle.getSelectionModel().getSelectedIndex() > -1) {
			modelEquipeBenevole.retirerBenevoleDansEquipe(comboBox_enbvle.getSelectionModel().getSelectedItem());
			initialize();
		}
		
	}
}
