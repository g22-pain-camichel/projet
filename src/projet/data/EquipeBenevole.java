package projet.data;

import java.sql.Time;
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
	private final Property<Time>	hrDbDispo	= new SimpleObjectProperty<>();
	private final Property<Time> hrFinDispo = new SimpleObjectProperty<>();
	
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
	
	public final String getlibelle() {
		return this.libelleProperty().get();
	}
	
	public final void setlibelle(final String libelle) {
		this.libelleProperty().set(libelle);
	}
	
	public final Property<Integer> nbreBenevoleProperty() {
		return this.nbreBenevole;
	}
	
	public final Integer getnbreBenevole() {
		return this.nbreBenevoleProperty().getValue();
	}
	
	public final void setnbreBenevole(final Integer nbreBenevole) {
		this.nbreBenevoleProperty().setValue(nbreBenevole);
	}
	
	public final Property<Time> hrDbDispoProperty() {
		return this.hrDbDispo;
	}
	
	public final Time gethrDbDispo() {
		return this.hrDbDispoProperty().getValue();
	}
	
	public final void sethrDbDispo(final Time hrDbDispo) {
		this.hrDbDispoProperty().setValue(hrDbDispo);
	}
	
	public final Property<Time> hrFinDispoProperty() {
		return this.hrFinDispo;
	}
	
	public final Time gethrFinDispo() {
		return this.hrFinDispoProperty().getValue();
	}
	
	public final void sethrFinDispo(final Time hrFinDispo) {
		this.hrFinDispoProperty().setValue(hrFinDispo);
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
	
	
	
	
	
	


}
