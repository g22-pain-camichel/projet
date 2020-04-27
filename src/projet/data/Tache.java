package projet.data;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tache {

	
	//champs
	private final StringProperty	libelle	= new SimpleStringProperty();
	private final StringProperty	emplacement	= new SimpleStringProperty();
	
	//getters && setters
	public final StringProperty libelleProperty() {
		return this.libelle;
	}
	
	public final String getLibelle() {
		return this.libelleProperty().get();
	}
	
	public final void setLibelle(final String libelle) {
		this.libelleProperty().set(libelle);
	}
	
	public final StringProperty emplacementProperty() {
		return this.emplacement;
	}
	
	public final String getEmplacement() {
		return this.emplacementProperty().get();
	}
	
	public final void setEmplacement(final String emplacement) {
		this.emplacementProperty().set(emplacement);
	}
	
	
	
}
