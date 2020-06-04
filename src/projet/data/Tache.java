package projet.data;


import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tache {

	
	//champs
	private final StringProperty	libelle	= new SimpleStringProperty();
	private final StringProperty	emplacement	= new SimpleStringProperty();
	private final StringProperty	hr_deb	= new SimpleStringProperty();
	private final StringProperty hr_fin = new SimpleStringProperty();
	private final Property<Integer> taille = new SimpleObjectProperty<>();
	
	
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
	

	public final Property<Integer> tailleProperty() {
		return this.taille;
	}
	

	public final Integer getTaille() {
		return this.tailleProperty().getValue();
	}
	

	public final void setTaille(final Integer taille) {
		this.tailleProperty().setValue(taille);
	}

	@Override
	public String toString() {
		return getLibelle();
	}	
}
