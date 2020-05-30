package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EquipeBenevole {
	
	//champs
	
	private final Property<Integer>		num			= new SimpleObjectProperty<>();	
	private final StringProperty   	 	libelle       = new SimpleStringProperty();
	private final Property<Integer>		nbreBenevole	= new SimpleObjectProperty<>();
	private final Property<Boolean> estValide = new SimpleObjectProperty<>(); 

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
	

	public final StringProperty libelleProperty() {
		return this.libelle;
	}
	

	public final String getLibelle() {
		return this.libelleProperty().get();
	}
	

	public final void setLibelle(final String libelle) {
		this.libelleProperty().set(libelle);
	}
	

	public final Property<Integer> nbreBenevoleProperty() {
		return this.nbreBenevole;
	}
	

	public final Integer getNbreBenevole() {
		return this.nbreBenevoleProperty().getValue();
	}
	

	public final void setNbreBenevole(final Integer nbreBenevole) {
		this.nbreBenevoleProperty().setValue(nbreBenevole);
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
		EquipeBenevole other = (EquipeBenevole) obj;
		return Objects.equals(num.getValue(), other.num.getValue());
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
	

	
}
