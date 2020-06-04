package projet.view.equipebenevole;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoEpreuve;
import projet.dao.DaoEquipeBenevole;
import projet.dao.DaoLier;
import projet.dao.DaoTache;
import projet.data.Epreuve;
import projet.data.EquipeBenevole;
import projet.data.Tache;

public class ModelEquipeBenevole {
	
	private final ObservableList<EquipeBenevole> listEquipeBenevole = FXCollections.observableArrayList();
	private final ObservableList<Tache> listeT = FXCollections.observableArrayList();
	private final ObservableList<Epreuve> listeE = FXCollections.observableArrayList();
	
	private EquipeBenevole courant = new EquipeBenevole();
	private Epreuve epreuve = new Epreuve();
	
	@Inject
	private IMapper mapper;
	@Inject
	private DaoEquipeBenevole daoEquipeBenevole;
	@Inject
	private DaoEpreuve daoEpreuve;
	@Inject
	private DaoTache daoTache;
	
	public ObservableList<EquipeBenevole> getListe() {
		actualiserListe();
		return listEquipeBenevole;
	}
	public ObservableList<Epreuve> getListeE() {
		actualiserListeE();
		return listeE;
	}
	public ObservableList<Tache> getListeT() {
		actualiserListeT();
		return listeT;
	}
	
	public ObservableList<EquipeBenevole> getListe(boolean bool) {
		modifListe(bool);
		return listEquipeBenevole;
	}
	
	public EquipeBenevole getCourant() {
		return courant;
	}
	
	public Epreuve getEpreuve() {
		return epreuve;
	}
	
	// actualisation
	
	public void actualiserListe() {
		listEquipeBenevole.setAll(daoEquipeBenevole.listerTout());
	}
	public void actualiserListeE() {
		listeE.setAll(daoEpreuve.listerTout());
	}
	public void actualiserListeT() {
		listeT.setAll(daoTache.listerTout(epreuve));
	}
	
	// acions

	public void modifListe(boolean bool) {
		listEquipeBenevole.setAll(daoEquipeBenevole.listerTout(bool));
	}
	
	public void preparerAjouter() {
		mapper.update( courant, new EquipeBenevole() );
		mapper.update(epreuve, new Epreuve());
	}
	
	public void preparerModifier( EquipeBenevole item ) {
		mapper.update( courant, daoEquipeBenevole.retrouver( item.getNum()) );
	}
	
	public void preparerModifier( Epreuve ep ) {
		mapper.update( epreuve, daoEpreuve.retrouver(ep.getNom()) );
	}
	
	public List<EquipeBenevole> find(String name) {
		List<EquipeBenevole> liste = new ArrayList();
		liste = daoEquipeBenevole.listerEquipeBenevoles(name);
		return liste;
	}
	
	public void validerMiseAJour() throws ParseException {
		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( courant.getLibelle() == null || courant.getLibelle().isEmpty() ) {
			message.append( "\nLe Libelle ne doit pas être vide." );
		} 
		else  if ( courant.getLibelle().length()> 50 ) {
			message.append( "\nLe Libelle est trop long : 50 maxi." );
		}
		if (courant.getNbreBenevole() <= 1 || courant.getNbreBenevole() >= 20) {
			message.append("\n une équipe est contitué de 2 à 20 membres");
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		// Effectue la mise à jour
		
		if ( courant.getNum() == null ) {
			// Insertion
			courant.setNum( daoEquipeBenevole.inserer( courant ) );
		} else {
			// modficiation
			daoEquipeBenevole.modifier( courant );
		}
	}
	
	
	public void supprimer( EquipeBenevole item ) {
		daoEquipeBenevole.supprimer( item.getNum() );
		mapper.update( courant, UtilFX.findNext(listEquipeBenevole, item ) );
	}
}
