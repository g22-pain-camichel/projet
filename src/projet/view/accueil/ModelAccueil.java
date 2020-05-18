package projet.view.accueil;

import java.util.List;

import javax.inject.Inject;

import projet.dao.DaoBenevole;
import projet.dao.DaoEquipeBenevole;
import projet.data.Benevole;
import projet.data.EquipeBenevole;

public class ModelAccueil {
	
	@Inject
	private DaoBenevole daoBenevole;
	
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
}
