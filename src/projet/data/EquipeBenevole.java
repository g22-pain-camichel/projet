package projet.data;

import java.sql.Time;


import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EquipeBenevole {
	
	//champs
	
	private final Property<Integer>		num			= new SimpleObjectProperty<>();	
	private final StringProperty   	 	libele       = new SimpleStringProperty();
	private final Property<Integer>		nombre_de_benevole	= new SimpleObjectProperty<>();
	private final Property<Time>	hr_debut_dispo	= new SimpleObjectProperty<>();
	private final Property<Time> hr_fin_dispo = new SimpleObjectProperty<>();
	
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
	
	public final StringProperty libeleProperty() {
		return this.libele;
	}
	
	public final String getLibele() {
		return this.libeleProperty().get();
	}
	
	public final void setLibele(final String libele) {
		this.libeleProperty().set(libele);
	}
	
	public final Property<Integer> nombre_de_benevoleProperty() {
		return this.nombre_de_benevole;
	}
	
	public final Integer getNombre_de_benevole() {
		return this.nombre_de_benevoleProperty().getValue();
	}
	
	public final void setNombre_de_benevole(final Integer nombre_de_benevole) {
		this.nombre_de_benevoleProperty().setValue(nombre_de_benevole);
	}
	
	public final Property<Time> hr_debut_dispoProperty() {
		return this.hr_debut_dispo;
	}
	
	public final Time getHr_debut_dispo() {
		return this.hr_debut_dispoProperty().getValue();
	}
	
	public final void setHr_debut_dispo(final Time hr_debut_dispo) {
		this.hr_debut_dispoProperty().setValue(hr_debut_dispo);
	}
	
	public final Property<Time> hr_fin_dispoProperty() {
		return this.hr_fin_dispo;
	}
	
	public final Time getHr_fin_dispo() {
		return this.hr_fin_dispoProperty().getValue();
	}
	
	public final void setHr_fin_dispo(final Time hr_fin_dispo) {
		this.hr_fin_dispoProperty().setValue(hr_fin_dispo);
	}
	
	
	
	
	
	
	
	
	
	


}
