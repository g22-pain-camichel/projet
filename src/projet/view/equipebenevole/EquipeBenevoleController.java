package projet.view.equipebenevole;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.data.Epreuve;
import projet.data.EquipeBenevole;
import projet.data.Tache;
import projet.view.EnumView;

public class EquipeBenevoleController {
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelEquipeBenevole modelEquipeBenevole;
	
	@FXML
	private Label label_valide;
	
	@FXML
	private TextField textField_find, textField_equipe, textField_nbre;
	
	@FXML
	private Button button_find, button_addB, button_create, button_update, 
		button_delete, button_valid, button_validB, button_nValidB, button_init;
	
	@FXML
	private ComboBox<Epreuve> comboBox_epreuve;
	
	@FXML
	private ComboBox<Tache> comboBox_tache;
	
	@FXML
	private ComboBox<Benevole> comboBox_bvle;
	
	@FXML
	private ListView<EquipeBenevole> listView;
	
	@FXML
	private TableView<EquipeBenevole> tableView;
	
	private EquipeBenevole courant;
	
	private Epreuve epreuve;
	
	private String txt = "";
	
	@FXML
	private void initialize() {
		// data binding
		courant = modelEquipeBenevole.getCourant();
		epreuve = modelEquipeBenevole.getEpreuve();
		
		comboBox_epreuve.setItems(modelEquipeBenevole.getListeE());
		comboBox_epreuve.setCellFactory(UtilFX.cellFactory(item -> item.getNom()));
		
		listView.setCellFactory(UtilFX.cellFactory(item -> item.getLibelle()+
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
		
		if (courant.getNum() != null) {
			UtilFX.selectInListView( listView, modelEquipeBenevole.getCourant() );
			listView.requestFocus();
			
			label_valide.setText(courant.getEstValide().toString());
			
			if (courant.getEstValide()) {
				button_valid.setDisable(true);
			}
			else {
				button_valid.setDisable(false);
			}
			
			textField_equipe.textProperty().bindBidirectional(courant.libelleProperty());
			
			textField_nbre.textProperty().bindBidirectional(courant.nbreBenevoleProperty(),  new ConverterStringInteger());
			textField_nbre.focusedProperty().addListener(new ListenerFocusValidation(courant.numProperty(), "Un nombre valide est demandé"));
		}
	}
	
	@FXML
	public void doFillGap() {
		modelEquipeBenevole.preparerAjouter();
		modelEquipeBenevole.preparerModifier(listView.getSelectionModel().getSelectedItem() );
		initialize();
	}
	
	@FXML
	public void doValider() throws ParseException {
		courant.setEstValide(true);
		doUpdate();
	}
	
	@FXML
	public void doUpdate() throws ParseException {
		modelEquipeBenevole.validerMiseAJour();
		modelEquipeBenevole.preparerAjouter();
		initialize();
	}
	
	@FXML
	public void doDelete() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			modelEquipeBenevole.supprimer( listView.getSelectionModel().getSelectedItem() );
			initialize();
		}
	}
	
	@FXML
	public void doInitTache() {
		if (comboBox_epreuve.getSelectionModel().getSelectedIndex() > -1) {
			modelEquipeBenevole.preparerModifier(comboBox_epreuve.getSelectionModel().getSelectedItem());
			comboBox_tache.setItems(modelEquipeBenevole.getListeT());
			initialize();
		}
	}
	
	@FXML
	public void doFind() {
		if (!textField_find.getText().isEmpty()) {
			List<EquipeBenevole> maListe = modelEquipeBenevole.find(textField_find.getText());
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
	
}
