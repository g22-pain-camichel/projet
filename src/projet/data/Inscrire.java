package projet.data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Inscrire {
	//champs
	private final Property<Integer>		num			= new SimpleObjectProperty<>();	
	private final StringProperty   	 	nom       = new SimpleStringProperty();
	public final Property<Integer> numProperty() {
		return this.num;
	}
	
	public final Integer getNum() {
		return this.numProperty().getValue();
	}
	
	public final void setNum(final Integer num) {
		this.numProperty().setValue(num);
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
