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
import projet.dao.DaoBenevole;
import projet.dao.DaoConstituer;
import projet.dao.DaoEpreuve;
import projet.dao.DaoEquipeBenevole;
import projet.dao.DaoLier;
import projet.dao.DaoTache;
import projet.data.Benevole;
import projet.data.Constituer;
import projet.data.Epreuve;
import projet.data.EquipeBenevole;
import projet.data.Lier;
import projet.data.Tache;

public class ModelEquipeBenevole {
	
	private final ObservableList<EquipeBenevole> listEquipeBenevole = FXCollections.observableArrayList();
	private final ObservableList<Tache> listeT = FXCollections.observableArrayList();
	private final ObservableList<Epreuve> listeE = FXCollections.observableArrayList();
	private final ObservableList<Benevole> listeB = FXCollections.observableArrayList();
	private final ObservableList<Benevole> listB = FXCollections.observableArrayList();
	private final ObservableList<Benevole> listeBE = FXCollections.observableArrayList();
	
	private EquipeBenevole courant = new EquipeBenevole();
	private Epreuve epreuve = new Epreuve();
	private Benevole benevole = new Benevole();
	private Tache tache = new Tache();
	
	@Inject
	private IMapper mapper;
	@Inject
	private DaoEquipeBenevole daoEquipeBenevole;
	@Inject
	private DaoEpreuve daoEpreuve;
	@Inject
	private DaoTache daoTache;
	@Inject
	private DaoBenevole daoBenevole;
	@Inject 
	private DaoConstituer daoConstituer;
	@Inject
	private DaoLier daoLier;
	
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
	public ObservableList<Benevole> getListeB() {
		actualiserListeB();
		return listeB;
	}
	public ObservableList<Benevole> getListB() {
		actualiserListB();
		return listB;
	}
	public ObservableList<Benevole> getListeBE() {
		actualiserListeBE();
		return listeBE;
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
	
	public Benevole getBenevole() {
		return benevole;
	}
	
	public Tache getTache() {
		return tache;
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
	public void actualiserListeB() {
		listeB.setAll(daoBenevole.listerToutDispo());
	}
	public void actualiserListB() {
		listB.setAll(daoBenevole.listerBenevoleEquipe(courant));
	}
	public void actualiserListeBE() {
		listeBE.setAll(daoBenevole.listerBenevoleEquipe(courant));
	}
	
	// acions

	public void modifListe(boolean bool) {
		listEquipeBenevole.setAll(daoEquipeBenevole.listerTout(bool));
	}
	
	public void preparerAjouter() {
		mapper.update( courant, new EquipeBenevole() );
		mapper.update(benevole, new Benevole());
		mapper.update(epreuve, new Epreuve());
		mapper.update(tache, new Tache());
	}
	
	public void preparerModifier( EquipeBenevole item ) {
		mapper.update( courant, daoEquipeBenevole.retrouver( item.getNum()) );
	}
	
	public void preparerModifier( Epreuve ep ) {
		mapper.update( epreuve, daoEpreuve.retrouver(ep.getNom()) );
	}
	
	public void preparerModifier( Benevole b ) {
		mapper.update( benevole, daoBenevole.retrouver(b.getIdentifiant()) );
	}
	
	public void preparerModifier (Tache t) {
		mapper.update(tache, daoTache.retrouver(t.getLibelle()));
	}
	
	public List<EquipeBenevole> find(String name) {
		List<EquipeBenevole> liste = new ArrayList();
		liste = daoEquipeBenevole.listerEquipeBenevoles(name);
		return liste;
	}
	
	public void ajouterBenevoleDansEquipe() {
		Constituer constituer = new Constituer();
		constituer.setNum(courant.getNum());
		constituer.setIdentidant(benevole.getIdentifiant());
		daoConstituer.inserer(constituer);
	}
	public void retirerBenevoleDansEquipe(Benevole b) {
		Constituer constituer = new Constituer();
		constituer.setNum(courant.getNum());
		constituer.setIdentidant(b.getIdentifiant());
		daoConstituer.supprimer(constituer.getIdentidant());
	}
	
	public boolean equipePleine() {
		int total = daoEquipeBenevole.countBenevole(courant);
		int nbre = courant.getNbreBenevole();
		if (nbre <= total) return true;
		else return false;
	}
	
	public void bloquerTache() {
		Lier lier = new Lier();
		lier.setLibelle(tache.getLibelle());
		lier.setNom(epreuve.getNom());
		lier.setStatut(1);
		daoLier.modifier(lier);
	}
	
	public void validerMiseAJour() throws ParseException {
		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( courant.getNom() == null || courant.getNom().isEmpty() ) {
			message.append( "\nLe nom de l'equipe ne doit pas être vide." );
		} 
		else  if ( courant.getNom().length()> 50 ) {
			message.append( "\nLe nom de cette equipe est trop long : 50 maxi." );
		}
		if (courant.getNbreBenevole() == null || courant.getNbreBenevole() <= 1 || courant.getNbreBenevole() >= 20) {
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
