package projet.view.tache;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoTache;
import projet.dao.DaoPermi;
import projet.data.Tache;
import projet.data.Permi;

public class ModelTache {
	
	private final ObservableList<Tache> listTache = FXCollections.observableArrayList();
	
	private Tache tache = new Tache();
	
	@Inject
	private IMapper mapper;
	@Inject
	private DaoTache daoTache;
	
	public ObservableList<Tache> getListe() {
		actualiserListe();
		return listTache;
	}
	
	public Tache getTache() {
		return tache;
	}
	
	// actualisation
	
	public void actualiserListe() {
		listTache.setAll(daoTache.listerTout());
	}
	
	// acions

	public void preparerAjouter() {
		mapper.update( tache, new Tache() );
	}
	
	public void preparerModifier( Tache item ) {
		mapper.update( tache, daoTache.retrouver( item.getLibelle()) );
	}
	
	public List<Tache> find(String name) {
		List<Tache> liste = new ArrayList();
		liste = daoTache.listerTaches(name);
		return liste;
	}
	
	public void validerMiseAJour(int a) throws ParseException {
		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( tache.getLibelle() == null || tache.getLibelle().isEmpty() ) {
			message.append( "\nLe Libelle ne doit pas être vide." );
		} 
		else  if ( tache.getLibelle().length()> 20 ) {
			message.append( "\nLe Libelle est trop long : 50 maxi." );
		}
		
		if( tache.getEmplacement() == null || tache.getEmplacement().isEmpty() ) {
			message.append( "\nL'emplacement ne doit pas être vide." );
		} 
		else  if ( tache.getEmplacement().length()> 50 ) {
			message.append( "\nL'emplacement est trop long : 50 maxi." );
		}
		
		if (tache.getTaille() <= 1 || tache.getTaille() >= 20) {
			message.append("\n une équipe est contitué de 2 à 20 membres");
		} 
		
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		// Effectue la mise à jour
		
		if ( a == 0 ) {
			// Insertion
			daoTache.inserer( tache );
		} else {
			// modficiation
			daoTache.modifier( tache );
		}
	}
	
	
	public void supprimer( Tache item ) {
		System.out.println(item);
		daoTache.supprimer( item.getLibelle() );
		mapper.update( tache, UtilFX.findNext(listTache, item ) );
	}
}
