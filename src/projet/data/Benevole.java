package projet.data;

import java.sql.Time;
import java.time.LocalDate;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Benevole {
	
	
	//champs
	private final Property<Integer>		identifiant			= new SimpleObjectProperty<>();	
	private final StringProperty   	 	nom       = new SimpleStringProperty();
	private final StringProperty   		prenom = new SimpleStringProperty();
	private final Property<Integer>		sexe	= new SimpleObjectProperty<>();
	private final Property<LocalDate>	date_naiss	= new SimpleObjectProperty<>();
	private final StringProperty		email	= new SimpleStringProperty();
	private final StringProperty		tel		= new SimpleStringProperty();
	private final StringProperty	type	= new SimpleStringProperty();
	private final Property<Time>	hr_debut_dispo	= new SimpleObjectProperty<>();
	private final Property<Time> hr_fin_dispo = new SimpleObjectProperty<>();
	
	//constructeurs
	public Benevole() {
	
	}
	// hashCode() & equals()
/*
	@Override
	public int hashCode() {
		return Objects.hash( identifiant.getValue() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Memo other = (Memo) obj;
		return Objects.equals( id.getValue(), other.id.getValue() );
	}
*/

	
	//getters && setters

	public final Property<Integer> identifiantProperty() {
		return this.identifiant;
	}
	


	public final Integer getIdentifiant() {
		return this.identifiantProperty().getValue();
	}
	


	public final void setIdentifiant(final Integer identifiant) {
		this.identifiantProperty().setValue(identifiant);
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
	


	public final StringProperty prenomProperty() {
		return this.prenom;
	}
	


	public final String getPrenom() {
		return this.prenomProperty().get();
	}
	


	public final void setPrenom(final String prenom) {
		this.prenomProperty().set(prenom);
	}
	


	public final Property<Integer> sexeProperty() {
		return this.sexe;
	}
	


	public final Integer getSexe() {
		return this.sexeProperty().getValue();
	}
	


	public final void setSexe(final Integer sexe) {
		this.sexeProperty().setValue(sexe);
	}
	


	public final Property<LocalDate> date_naissProperty() {
		return this.date_naiss;
	}
	


	public final LocalDate getDate_naiss() {
		return this.date_naissProperty().getValue();
	}
	


	public final void setDate_naiss(final LocalDate date_naiss) {
		this.date_naissProperty().setValue(date_naiss);
	}
	


	public final StringProperty emailProperty() {
		return this.email;
	}
	


	public final String getEmail() {
		return this.emailProperty().get();
	}
	


	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	


	public final StringProperty telProperty() {
		return this.tel;
	}
	


	public final String getTel() {
		return this.telProperty().get();
	}
	


	public final void setTel(final String tel) {
		this.telProperty().set(tel);
	}
	


	public final StringProperty typeProperty() {
		return this.type;
	}
	


	public final String getType() {
		return this.typeProperty().get();
	}
	


	public final void setType(final String type) {
		this.typeProperty().set(type);
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
