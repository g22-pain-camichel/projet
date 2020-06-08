package projet.view.participant;

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
import projet.dao.DaoParticipant;
import projet.data.Participant;

public class ModelParticipantListe {
	
	private final ObservableList<Participant> listParticipant = FXCollections.observableArrayList();
	
	private Participant courant = new Participant();
	
	@Inject
	private IMapper mapper;
	@Inject
	private DaoParticipant daoParticipant;
	
	public ObservableList<Participant> getListe() {
		actualiserListe();
		return listParticipant;
	}
	public ObservableList<Participant> getListe(Boolean bool) {
		modifListe(bool);
		return listParticipant;
	}
	
	public Participant getCourant() {
		return courant;
	}
	
	public void setCourant(Participant b) {
		courant = b; 
	}
	
	// actualisation
	
	public void actualiserListe() {
		listParticipant.setAll(daoParticipant.listerTout());
	}
	
	// acions
	public void modifListe(boolean bool) {
		listParticipant.setAll(daoParticipant.listerTout(bool));
	}
	public void preparerAjouter() {
		mapper.update( courant, new Participant() );
	}
	
	public void preparerModifier( Participant item ) {
		mapper.update( courant, daoParticipant.retrouver(item.getNum()));
	}
	
	public List<Participant> find(String name) {
		List<Participant> liste = new ArrayList();
		liste = daoParticipant.listerParticipant(name);
		return liste;
	}
	
	public void validerMiseAJour() throws ParseException {
		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( courant.getNom() == null || courant.getNom().isEmpty() ) {
			message.append( "\nLe nom ne doit pas être vide." );
		} 
		else  if ( courant.getNom().length()> 50 ) {
			message.append( "\nLe nom est trop long : 50 maxi." );
		}
		
		if( courant.getPrenom() == null || courant.getPrenom().isEmpty() ) {
			message.append( "\nLe prenom ne doit pas être vide." );
		} 
		else  if ( courant.getPrenom().length()> 50 ) {
			message.append( "\nLe prenom est trop long : 50 maxi." );
		}
		
		if( courant.getEmail() == null || courant.getEmail().isEmpty() ) {
			message.append( "\nL'email ne doit pas être vide." );
		} 
		else  if ( courant.getEmail().length()> 50 ) {
			message.append( "\nL'email est trop long : 50 maxi." );
		}
		else if (!isValidEmail(courant.getEmail()))
			message.append( "\nL'email entré n'est pas valide" );
		
		if( courant.getTel() == null || courant.getTel().isEmpty() ) {
			message.append( "\nLe numéro de téléphone ne doit pas être vide." );
		} 
		else  if ( courant.getTel().length()> 50 ) {
			message.append( "\nLe numéro de téléphone est trop long : 50 maxi." );
		}
		else if (!isValidePhoneNumber(courant.getTel()))
			message.append( "\nLe numéro de téléphone entré n'est pas valide" );
		
		LocalDate date1 = LocalDate.of(1980, 1, 1);
		LocalDate date2 = LocalDate.of(2012, 12, 31);
		
		if (courant.getDtNaiss() == null) message.append("\n La date de naissance ne peut être vide");
		else if (date1.isAfter(courant.getDtNaiss()) || date2.isBefore(courant.getDtNaiss()))
			message.append("\n La date d'un bénvévole doit être comprise entre 01/01/1980 et 31/12/2012");
		
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		// Effectue la mise à jour
		
		if ( courant.getNum()== null ) {
			// Insertion
			courant.setNum( daoParticipant.inserer( courant ) );
		} else {
			// modficiation
			daoParticipant.modifier( courant );
		}
	}
	
	
	public void supprimer( Participant item ) {
		daoParticipant.supprimer( item.getNum() );
		mapper.update( courant, UtilFX.findNext(listParticipant, item ) );
	}
	
	private boolean isValidEmail(String email) {
		Pattern p = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
		Matcher m = p.matcher(email);
		if (m.matches()) return true;
		else return false;
	}
	
	private boolean isValidePhoneNumber(String phone) {
		Pattern p = Pattern.compile("^\\+(?:[0-9] ?){6,14}[0-9]$");
		Matcher m = p.matcher(phone);
		if (m.matches()) return true;
		else return false;
	}
}
