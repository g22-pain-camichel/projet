package projet.data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lier {

	
		//champs
	private final StringProperty   	 	nom       = new SimpleStringProperty();
	private final StringProperty   	 	libelle       = new SimpleStringProperty();
	private final Property<Integer>		statut	= new SimpleObjectProperty<Integer>();
	
	
	public final StringProperty nomProperty() {
		return this.nom;
	}
	
	public final String getNom() {
		return this.nomProperty().get();
	}
	
	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	
	public final StringProperty libelleProperty() {
		return this.libelle;
	}
	
	public final String getLibelle() {
		return this.libelleProperty().get();
	}
	
	public final void setLibelle(final String libelle) {
		this.libelleProperty().set(libelle);
	}

	public final Property<Integer> statutProperty() {
		return this.statut;
	}
	

	public final Integer getStatut() {
		return this.statutProperty().getValue();
	}
	

	public final void setStatut(final Integer statut) {
		this.statutProperty().setValue(statut);
	}
	
}
