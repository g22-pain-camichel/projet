package projet.view.equipeparticipant;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoBenevole;
import projet.dao.DaoClub;
import projet.dao.DaoConstituer;
import projet.dao.DaoEpreuve;
import projet.dao.DaoEquipeBenevole;
import projet.dao.DaoLier;
import projet.dao.DaoParticipant;
import projet.dao.DaoTache;
import projet.data.Benevole;
import projet.data.Club;
import projet.data.Constituer;
import projet.data.Epreuve;
import projet.data.EquipeBenevole;
import projet.data.Lier;
import projet.data.Participant;
import projet.data.Tache;

public class ModelEquipeParticipant {
	private final ObservableList<Club> listClub = FXCollections.observableArrayList();


	@Inject
	private IMapper mapper;
	@Inject
	private DaoClub daoClub;
	private Club courant = new Club();


	public ObservableList<Club> getListe() {
		actualiserListe();
		return listClub;
	}
	public ObservableList<Club> getListe(Boolean bool) {
		modifListe(bool);
		return listClub;
	}

	public Club getCourant() {
		return courant;
	}

	public void setCourant(Club b) {
		courant = b; 
	}


	// actualisation

	public void actualiserListe() {
		listClub.setAll(daoClub.listerTout());
		
	}
	// acions
	public void modifListe(boolean bool) {
		listClub.setAll(daoClub.listerTout(bool));
	}

	public void preparerAjouter() {
		mapper.update( courant, new Club() );
	}




	public void preparerModifier( Club item ) {
		mapper.update( courant, daoClub.retrouver(item.getNum()));
	
	}


	public List<Club> find(String name) {
		List<Club> liste = new ArrayList();
		liste = daoClub.listerClub(name);
		return liste;
	}
	public void supprimer( Club item ) {
		daoClub.supprimer( item.getNum() );
		mapper.update( courant, UtilFX.findNext(listClub, item ) );
	}

	public void validerMiseAJour() throws ParseException {
		// Vérifie la validité des données
	}

}
