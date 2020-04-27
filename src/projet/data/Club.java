package projet.data;

import java.sql.Time;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Club {

	
	//champs
	
	private final Property<Integer>		num			= new SimpleObjectProperty<>();	
	private final StringProperty   	 	nom_captain       = new SimpleStringProperty();
	private final Property<Integer>		nbrepasreserves	= new SimpleObjectProperty<>();
	private final Property<Integer>		num_participant	= new SimpleObjectProperty<>();
	
	//getters && setters
	public final Property<Integer> numProperty() {
		return this.num;
	}
	
	public final Integer getNum() {
		return this.numProperty().getValue();
	}
	
	public final void setNum(final Integer num) {
		this.numProperty().setValue(num);
	}
	
	public final StringProperty nom_captainProperty() {
		return this.nom_captain;
	}
	
	public final String getNom_captain() {
		return this.nom_captainProperty().get();
	}
	
	public final void setNom_captain(final String nom_captain) {
		this.nom_captainProperty().set(nom_captain);
	}
	
	public final Property<Integer> nbrepasreservesProperty() {
		return this.nbrepasreserves;
	}
	
	public final Integer getNbrepasreserves() {
		return this.nbrepasreservesProperty().getValue();
	}
	
	public final void setNbrepasreserves(final Integer nbrepasreserves) {
		this.nbrepasreservesProperty().setValue(nbrepasreserves);
	}
	
	public final Property<Integer> num_participantProperty() {
		return this.num_participant;
	}
	
	public final Integer getNum_participant() {
		return this.num_participantProperty().getValue();
	}
	
	public final void setNum_participant(final Integer num_participant) {
		this.num_participantProperty().setValue(num_participant);
	}
	
	
	
	
}
