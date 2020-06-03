package projet.data;

import java.time.LocalDate;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Permi {

	private final StringProperty	numero		= new SimpleStringProperty();
	private final StringProperty	lieu	= new SimpleStringProperty();
	private final Property<LocalDate> dateDelivrance = new SimpleObjectProperty<LocalDate>();
	
	// Constructeurs
	
	public Permi() {
	}
	
	public Permi( String numero, String lieu, LocalDate dateDelivrance ) {
		setNumero(numero);
		setLieu(lieu);
		setDateDelivrance(dateDelivrance);
	}
	
	public final StringProperty numeroProperty() {
		return this.numero;
	}
	
	public final String getNumero() {
		return this.numeroProperty().get();
	}
	
	public final void setNumero(final String numero) {
		this.numeroProperty().set(numero);
	}
	
	public final StringProperty lieuProperty() {
		return this.lieu;
	}
	
	public final String getLieu() {
		return this.lieuProperty().get();
	}
	
	public final void setLieu(final String lieu) {
		this.lieuProperty().set(lieu);
	}
	
	public final Property<LocalDate> dateDelivranceProperty() {
		return this.dateDelivrance;
	}
	
	public final LocalDate getDateDelivrance() {
		return this.dateDelivranceProperty().getValue();
	}
	
	public final void setDateDelivrance(final LocalDate dateDelivrance) {
		this.dateDelivranceProperty().setValue(dateDelivrance);
	}


}
