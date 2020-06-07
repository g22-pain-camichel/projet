package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Club {

	
	//champs
	
	private final Property<Integer>		num			= new SimpleObjectProperty<>();	
	private final StringProperty   	 	nom       = new SimpleStringProperty();
	private final Property<Integer>		numCapitaine	= new SimpleObjectProperty<>();
	private final Property<Integer>		numEquipier	= new SimpleObjectProperty<>();
	private final Property<Integer>		nbRepasReserves	= new SimpleObjectProperty<>();

	private final Property<Integer> categorie = new SimpleObjectProperty<>();
	private final Property<Boolean> estValide = new SimpleObjectProperty<>(); 
	private final Property<Integer> activite = new SimpleObjectProperty<>();
	
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

	
	public final Property<Integer> nbRepasReservesProperty() {
		return this.nbRepasReserves;
	}
	

	public final Integer getNbRepasReserves() {
		return this.nbRepasReservesProperty().getValue();
	}
	

	public final void setNbRepasReserves(final Integer nbRepasReserves) {
		this.nbRepasReservesProperty().setValue(nbRepasReserves);
	}

	public final Property<Integer> categorieProperty() {
		return this.categorie;
	}
	

	public final Integer getCategorie() {
		return this.categorieProperty().getValue();
	}
	

	public final void setCategorie(final Integer categorie) {
		this.categorieProperty().setValue(categorie);
	}
	

	public final Property<Boolean> estValideProperty() {
		return this.estValide;
	}
	

	public final Boolean getEstValide() {
		return this.estValideProperty().getValue();
	}
	

	public final void setEstValide(final Boolean estValide) {
		this.estValideProperty().setValue(estValide);
	}
	

	public final Property<Integer> activiteProperty() {
		return this.activite;
	}
	

	public final Integer getActivite() {
		return this.activiteProperty().getValue();
	}
	

	public final void setActivite(final Integer activite) {
		this.activiteProperty().setValue(activite);
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
	
	
	@Override
	public int hashCode() {
		return Objects.hash(num.getValue());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Club other = (Club) obj;
		return Objects.equals(num.getValue(), other.num.getValue());
	}

	public final Property<Integer> numCapitaineProperty() {
		return this.numCapitaine;
	}
	

	public final Integer getNumCapitaine() {
		return this.numCapitaineProperty().getValue();
	}
	

	public final void setNumCapitaine(final Integer numCapitaine) {
		this.numCapitaineProperty().setValue(numCapitaine);
	}
	

	public final Property<Integer> numEquipierProperty() {
		return this.numEquipier;
	}
	

	public final Integer getNumEquipier() {
		return this.numEquipierProperty().getValue();
	}
	

	public final void setNumEquipier(final Integer numEquipier) {
		this.numEquipierProperty().setValue(numEquipier);
	}
		
}
