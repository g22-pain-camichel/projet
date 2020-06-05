package projet.view.epreuve;


import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.commun.IMapper;
import projet.dao.DaoEquipeBenevole;
import projet.dao.DaoEpreuve;
import projet.dao.DaoClub;
import projet.data.EquipeBenevole;
import projet.data.Epreuve;
import projet.data.Club;

public class ModelEpreuve {
	
	private final ObservableList<EquipeBenevole> listEquipeB = FXCollections.observableArrayList();
	private final ObservableList<Club> listClub = FXCollections.observableArrayList();
	private final ObservableList<Epreuve> listeE = FXCollections.observableArrayList();
	
	private Epreuve epreuve = new Epreuve();
	
	@Inject
	private IMapper mapper;
	@Inject
	private DaoEquipeBenevole daoEquipeBenevole;
	@Inject
	private DaoClub daoClub;
	@Inject
	private DaoEpreuve daoEpreuve;
	
	public ObservableList<EquipeBenevole> getListeB() {
		actualiserListeEB();
		return listEquipeB;
	}
	public ObservableList<Club> getListeC() {
		actualiserListeC();
		return listClub;
	}
	public ObservableList<Epreuve> getListeE() {
		actualiserListeE();
		return listeE;
	}
	
	public Epreuve getEpreuve() {
		return epreuve;
	}
	
	
	// actualisation
	
	public void actualiserListeEB() {
		listEquipeB.setAll(daoEquipeBenevole.equipesEpreuve(epreuve));
	}
	public void actualiserListeC() {
		listClub.setAll(daoClub.clubsEpreuve(epreuve));
	}
	public void actualiserListeE() {
		listeE.setAll(daoEpreuve.listerTout());
	}
	
	// acions
	
	public void preparerAjouter() {
		mapper.update( epreuve, new Epreuve() );
	}
	
	public void preparerModifier( Epreuve item ) {
		mapper.update( epreuve, daoEpreuve.retrouver(item.getNom()));
	}
}
