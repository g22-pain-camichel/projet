package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Epreuve {

	
	
	//champs
	private final Property<Double>		distance			= new SimpleObjectProperty<Double>();	
	private final StringProperty   	 	nom       = new SimpleStringProperty();
	private final StringProperty	hr_deb	= new SimpleStringProperty();
	private final StringProperty hr_fin = new SimpleStringProperty();
	
	//getters && setters
	
	public final StringProperty nomProperty() {
		return this.nom;
	}
	
	public final String getNom() {
		return this.nomProperty().get();
	}
	
	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	public final StringProperty hr_debProperty() {
		return this.hr_deb;
	}
	
	public final String getHr_deb() {
		return this.hr_debProperty().get();
	}
	
	public final void setHr_deb(final String hr_deb) {
		this.hr_debProperty().set(hr_deb);
	}
	
	public final StringProperty hr_finProperty() {
		return this.hr_fin;
	}
	
	public final String getHr_fin() {
		return this.hr_finProperty().get();
	}
	
	public final void setHr_fin(final String hr_fin) {
		this.hr_finProperty().set(hr_fin);
	}

	public final Property<Double> distanceProperty() {
		return this.distance;
	}
	

	public final Double getDistance() {
		return this.distanceProperty().getValue();
	}
	

	public final void setDistance(final Double distance) {
		this.distanceProperty().setValue(distance);
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Epreuve other = (Epreuve) obj;
		return Objects.equals(getNom(), other.getNom());
	}

	@Override
	public String toString() {
		return getNom();
	}
	
}
