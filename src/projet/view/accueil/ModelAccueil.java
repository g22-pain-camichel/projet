package projet.view.accueil;

import java.util.List;

import javax.inject.Inject;

import projet.dao.DaoBenevole;
import projet.dao.DaoClub;
import projet.dao.DaoEquipeBenevole;
import projet.dao.DaoParticipant;
import projet.data.Benevole;
import projet.data.Club;
import projet.data.EquipeBenevole;
import projet.data.Participant;

public class ModelAccueil {
	
	@Inject
	private DaoBenevole daoBenevole;
	private int i;
	@Inject
	private DaoEquipeBenevole daoEquipeBenevole;
	
	public String totalBenevole() {
		List<Benevole> listBenevole = daoBenevole.listerTout();
		return ""+listBenevole.size();
	}
	
	public String totalEquipeBenevole() {
		List<EquipeBenevole> listEquipeBenevole = daoEquipeBenevole.listerTout();
		return ""+listEquipeBenevole.size();
	}
	
	
	@Inject
	private DaoParticipant daoParticipant;
	
	@Inject
	private DaoClub daoClub;
	
	public String totalParticipant() {
		List<Participant> listparticipant = daoParticipant.listerTout();
		return ""+listparticipant.size();
	}
	
	public String totalEquipeParticipant() {
		List<Club> listEquipeParticipant = daoClub.listerTout();
		return ""+listEquipeParticipant.size();
	}
	
	
	
	
	
}
