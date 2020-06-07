package projet.view.epreuve;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.data.Club;
import projet.data.Epreuve;
import projet.data.EquipeBenevole;
import projet.view.EnumView;
import projet.view.connexion.ModelConnexion;

public class EpreuveController {
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelEpreuve modelEpreuve;
	@Inject 
	private ModelConnexion modelConnexion;
	
	@FXML
	private ComboBox<Epreuve> comboBox_epreuve;

	@FXML
	private Button button_display;
	
	@FXML
	private Label label_ep, label_dist, label_hrDeb, label_hrFin, user;

	@FXML
	private TableView<EquipeBenevole> tableView_b;
	
	@FXML
	private TableView<Club> tableView_p;
	
	@FXML
	private TableColumn<EquipeBenevole, String> tv_idB, tv_nomB, tv_nbre, tv_tache;
	
	@FXML
	private TableColumn<Club, String> tv_idP, tv_nomP, tv_cap, tv_actv;
	
	private Epreuve epreuve;
	
	@FXML
	private void initialize() {

		user.setText(modelConnexion.getUtilisateurActif().getPseudo());
		epreuve = modelEpreuve.getEpreuve();
		// initialisation des tableaux
		
		/** TABLEAU DES EQUIPES BENEVOLES **/
		tv_idB.setCellValueFactory(new PropertyValueFactory<EquipeBenevole, String>("num"));
		tv_nomB.setCellValueFactory(new PropertyValueFactory<EquipeBenevole, String>("nom"));
		tv_nbre.setCellValueFactory(new PropertyValueFactory<EquipeBenevole, String>("nbreBenevole"));
		tv_tache.setCellValueFactory(new PropertyValueFactory<EquipeBenevole, String>("libelle"));
					
		/** TABLEAU DES EQUIPES PARTICIPANTS **/
		tv_idP.setCellValueFactory(new PropertyValueFactory<Club, String>("num"));
		tv_nomP.setCellValueFactory(new PropertyValueFactory<Club, String>("nom"));
		tv_cap.setCellValueFactory(new PropertyValueFactory<Club, String>("nomCapitain"));
		tv_actv.setCellValueFactory(new PropertyValueFactory<Club, String>("activite"));
		
		if (epreuve.getNom() != null) {
			label_ep.setText(epreuve.getNom());
			label_dist.setText(epreuve.getDistance().toString()+" km");
			label_hrDeb.setText(epreuve.getHr_deb());
			label_hrFin.setText(epreuve.getHr_fin());
			
			tableView_b.setItems(modelEpreuve.getListeB());
			tableView_p.setItems(modelEpreuve.getListeC());
		}
		else {
			comboBox_epreuve.setItems(modelEpreuve.getListeE());
			comboBox_epreuve.setCellFactory(UtilFX.cellFactory(item -> item.getNom()));
		}
	}
	
	@FXML
	public void doDisplay() {
		if (comboBox_epreuve.getSelectionModel().getSelectedIndex() > -1) {
			modelEpreuve.preparerAjouter();
			modelEpreuve.preparerModifier(comboBox_epreuve.getSelectionModel().getSelectedItem());
			initialize();			
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
