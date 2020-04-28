package projet.data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Epreuve {

	
	
	//champs
	private final Property<Integer>		distance			= new SimpleObjectProperty<>();	
	private final StringProperty   	 	nom       = new SimpleStringProperty();
	
	//getters && setters
	public final Property<Integer> distanceProperty() {
		return this.distance;
	}
	public final Integer getDistance() {
		return this.distanceProperty().getValue();
	}
	
	public final void setDistance(final Integer distance) {
		this.distanceProperty().setValue(distance);
	}
	
	public final StringProperty nomProperty() {
		return this.nom;
	}
	
	public final String getNom() {
		return this.nomProperty().get();
	}
	
	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	
}
