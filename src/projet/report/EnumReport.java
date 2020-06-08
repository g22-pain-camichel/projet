package projet.report;


public enum EnumReport implements IEnumReport {

	
	// Valeurs
	
	listeBenevole				("benevole/listeBenevole.jrxml"),
	listeEquipeBenevole			("benevole/listeEquipeBenevole.jrxml"),
	listeParticipant			("participant/listeParticipant.jrxml"),
	listeEquipeParticipant		("participant/listeEquipeParticipant.jrxml"),
	listeEpreuve				("epreuve/listeEquipe.jrxml");

	
	// Champs
	
	private String		path;

	
	// Constructeur 
	
	EnumReport( String path ) {
		this.path = path;
	}

	
	// Getters & setters

	@Override
	public String getPath() {
		return path;
	}

}
