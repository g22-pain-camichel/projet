package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Club {

	
	//champs
	
	private final Property<Integer>		num			= new SimpleObjectProperty<>();	
	private final StringProperty   	 	nomCapitain       = new SimpleStringProperty();
	private final Property<Integer>		nbRepasReserves	= new SimpleObjectProperty<>();
	private final Property<Integer>		numParticipant	= new SimpleObjectProperty<>();
	
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
	
	public final StringProperty nomCapitainProperty() {
		return this.nomCapitain;
	}
	
	public final String getnomCapitain() {
		return this.nomCapitainProperty().get();
	}
	
	public final void setnomCapitain(final String nomCapitain) {
		this.nomCapitainProperty().set(nomCapitain);
	}
	
	public final Property<Integer> nbRepasReservesProperty() {
		return this.nbRepasReserves;
	}
	
	public final Integer getnbRepasReserves() {
		return this.nbRepasReservesProperty().getValue();
	}
	
	public final void setnbRepasReserves(final Integer nbRepasReserves) {
		this.nbRepasReservesProperty().setValue(nbRepasReserves);
	}
	
	public final Property<Integer> numParticipantProperty() {
		return this.numParticipant;
	}
	
	public final Integer getnumParticipant() {
		return this.numParticipantProperty().getValue();
	}
	
	public final void setnumParticipant(final Integer numParticipant) {
		this.numParticipantProperty().setValue(numParticipant);
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
	
	
	
	
}
