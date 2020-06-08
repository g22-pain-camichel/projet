package projet.view.epreuveadmin;

import java.sql.PreparedStatement;
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
import jfox.javafx.util.ConverterStringDouble;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Epreuve;
import projet.data.Tache;
import projet.view.EnumView;
import projet.view.connexion.ModelConnexion;

public class EpreuveAdminController {
	@Inject
	private IManagerGui		managerGui;
	@Inject 
	private ModelConnexion modelConnexion;
	@Inject
	private ModelEpreuveAdmin modelEpreuveAdmin;

	@FXML
	private Label user;
	
	@FXML
	private TextField textField_nom, textField_dist, textField_find;
	
	@FXML
	private JFXTimePicker timePicker_startH, timePicker_endH;
	
	@FXML
	private ListView<Epreuve> listView;
	
	@FXML
	private ComboBox<Tache> comboBox_tache;

	@FXML
	private Button button_create, button_update, button_delete, 
		button_find, button_refresh;
	
	private Tache tache;
	
	private Epreuve epreuve;
	
	@FXML
	private void initialize() {
		// TODO Auto-generated method stub
		user.setText(modelConnexion.getUtilisateurActif().getPseudo());
		epreuve = modelEpreuveAdmin.getEpreuve();
		tache = modelEpreuveAdmin.getTache();
		comboBox_tache.setItems(modelEpreuveAdmin.getListe());
		listView.setItems(modelEpreuveAdmin.getListeE());
		listView.setCellFactory(UtilFX.cellFactory(item -> item.getNom()));
		button_create.setDisable(false);
		
		if (epreuve.getNom() != null) {
			UtilFX.selectInListView( listView, epreuve );
			listView.requestFocus();
			button_create.setDisable(true);
		}
		
		textField_dist.textProperty().bindBidirectional(epreuve.distanceProperty(), new ConverterStringDouble());
		textField_nom.textProperty().bindBidirectional(epreuve.nomProperty());
		timePicker_startH.getEditor().textProperty().bindBidirectional(epreuve.hr_debProperty());
		timePicker_endH.getEditor().textProperty().bindBidirectional(epreuve.hr_finProperty());
	}
	
	
	@FXML
	public void doFillGap() {
		if (listView.getSelectionModel().getSelectedIndex() > -1) {
			modelEpreuveAdmin.preparerAjouter();
			modelEpreuveAdmin.preparerModifier(listView.getSelectionModel().getSelectedItem() );
			initialize();						
		}
	}
	
	@FXML
	public void doRefresh() throws ParseException {
		modelEpreuveAdmin.preparerAjouter();
		initialize();
	}
		
	@FXML
	public void doUpdate() throws ParseException {
		if (button_create.isDisable()) {
			modelEpreuveAdmin.validerMiseAJour(1);
		}
		else 
			modelEpreuveAdmin.validerMiseAJour(0);
		modelEpreuveAdmin.preparerAjouter();
		initialize();
	}
	@FXML
	public void addTacheEp() {
		if (comboBox_tache.getSelectionModel().getSelectedIndex() > -1) {
			modelEpreuveAdmin.preparerModifier(comboBox_tache.getSelectionModel().getSelectedItem());
			modelEpreuveAdmin.lierTache();
			initialize();
		}
	}
	
	@FXML
	public void doDelete() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			if (modelEpreuveAdmin.isDeletable(listView.getSelectionModel().getSelectedItem())) {
				modelEpreuveAdmin.supprimer( listView.getSelectionModel().getSelectedItem() );
				initialize();
			}
			else {
				managerGui.showDialogError("Veuillez enlever les différentes tâches raliées à cette epreuve afin de "
						+ "pouvoir effectuer cette opération");
			}
		}
	}
	
	@FXML
	public void doFind() {
		if (!textField_find.getText().isEmpty()) {
			List<Epreuve> maListe = modelEpreuveAdmin.find(textField_find.getText());
			if (maListe.isEmpty()) {
				managerGui.showDialogMessage("Aucune epreuve possédant ce nom n'a été trouvé");
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
