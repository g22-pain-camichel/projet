package projet.data;

import java.sql.Date;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Permi {

	private final StringProperty	numero		= new SimpleStringProperty();
	private final StringProperty	lieu	= new SimpleStringProperty();
	private final Property<Date> dateDelivrance = new SimpleObjectProperty<>();
	
	
	
	
	
	
	// Constructeurs
	
	public Permi() {
	}
	
	public Permi( String numero, String lieu,Date dateDelivrance ) {
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
	
	public final Property<Date> dateDelivranceProperty() {
		return this.dateDelivrance;
	}
	
	public final Date getDateDelivrance() {
		return this.dateDelivranceProperty().getValue();
	}
	
	public final void setDateDelivrance(final Date dateDelivrance) {
		this.dateDelivranceProperty().setValue(dateDelivrance);
	}
	
	
	
	
	
	

	


}
