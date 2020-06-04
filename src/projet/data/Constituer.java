package projet.data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Constituer {
	//champs
	private final Property<Integer>		num			= new SimpleObjectProperty<>();	
	private final Property<Integer>		identidant			= new SimpleObjectProperty<>();
	
	public final Property<Integer> numProperty() {
		return this.num;
	}
	
	public final Integer getNum() {
		return this.numProperty().getValue();
	}
	
	public final void setNum(final Integer num) {
		this.numProperty().setValue(num);
	}
	
	public final Property<Integer> identidantProperty() {
		return this.identidant;
	}
	
	public final Integer getIdentidant() {
		return this.identidantProperty().getValue();
	}
	
	public final void setIdentidant(final Integer identidant) {
		this.identidantProperty().setValue(identidant);
	}
}
