package projet.view.epreuveadmin;

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
import projet.dao.DaoLier;
import projet.dao.DaoTache;
import projet.data.Epreuve;
import projet.data.Lier;
import projet.data.Tache;

public class ModelEpreuveAdmin {
	
	private final ObservableList<Tache> listTache = FXCollections.observableArrayList();
	private final ObservableList<Epreuve> listeE = FXCollections.observableArrayList();
	
	private Epreuve epreuve = new Epreuve();
	private Tache tache = new Tache();
	
	@Inject
	private IMapper mapper;
	@Inject
	private DaoTache daoTache;
	@Inject
	private DaoEpreuve daoEpreuve;
	@Inject
	private DaoLier daoLier;
	
	public ObservableList<Tache> getListe() {
		actualiserListe();
		return listTache;
	}
	public ObservableList<Epreuve> getListeE() {
		actualiserListeE();
		return listeE;
	}

	public Epreuve getEpreuve() {
		return epreuve;
	}
	
	public Tache getTache() {
		return tache;
	}
	
	// actualisation
	
	public void actualiserListe() {
		listTache.setAll(daoTache.listerToutEp(epreuve));
	}
	public void actualiserListeE() {
		listeE.setAll(daoEpreuve.listerTout());
	}
	
	// acions

	public void preparerAjouter() {
		mapper.update( epreuve, new Epreuve() );
		mapper.update( tache, new Tache() );
	}
	
	public void preparerModifier( Epreuve item ) {
		mapper.update( epreuve, daoEpreuve.retrouver(item.getNom()));
	}
	
	public void preparerModifier (Tache t) {
		mapper.update(tache, daoTache.retrouver(t.getLibelle()));
	}
	
	public List<Epreuve> find(String name) {
		List<Epreuve> liste = new ArrayList();
		liste = daoEpreuve.listerEpreuves(name);
		return liste;
	}
	
	public boolean isDeletable(Epreuve e) {
		return daoEpreuve.deletableConstest(e);
	}
	
	public void lierTache() {
		Lier lier = new Lier();
		lier.setLibelle(tache.getLibelle());
		lier.setNom(epreuve.getNom());
		lier.setStatut(0);
		daoLier.inserer(lier);
	}
	
	public void validerMiseAJour(int a) throws ParseException {
		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( epreuve.getNom() == null || epreuve.getNom().isEmpty() ) {
			message.append( "\nLe Nom ne doit pas être vide." );
		} 
		else  if ( epreuve.getNom().length()> 20 ) {
			message.append( "\nLe Nom est trop long : 50 maxi." );
		}
		
		if ( epreuve.getDistance() == null || epreuve.getDistance() <= 20) {
			message.append("\n la distance de l'epreuve doit etre strictement supérieur à 20 km");
		} 
		
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		// Effectue la mise à jour
		
		if ( a == 0 ) {
			// Insertion
			daoEpreuve.inserer( epreuve );
		} else {
			// modficiation
			daoEpreuve.modifier( epreuve );
		}
	}
	
	
	public void supprimer( Epreuve item ) {
		System.out.println(item);
		daoEpreuve.supprimer( item.getNom() );
		mapper.update( epreuve, UtilFX.findNext(listeE, item ) );
	}
}
