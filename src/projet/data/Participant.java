package projet.data;

import java.sql.Time;
import java.time.LocalDate;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Participant {
	
	
	//champs
	private final Property<Integer>		identifiant			= new SimpleObjectProperty<>();	
	private final StringProperty   	 	nom       = new SimpleStringProperty();
	private final StringProperty   		prenom = new SimpleStringProperty();
	private final Property<Integer>		sexe	= new SimpleObjectProperty<>();
	private final Property<LocalDate>	datnaiss	= new SimpleObjectProperty<>();
	private final StringProperty		email	= new SimpleStringProperty();
	private final StringProperty		tel		= new SimpleStringProperty();
	private final Property<Integer>		role	= new SimpleObjectProperty<>();
	private final StringProperty		adressepost		= new SimpleStringProperty();
	private final StringProperty		cm		= new SimpleStringProperty();
	
	//constructeurs
	public Participant() {
	
	}

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


	public final Property<Integer> roleProperty() {
		return this.role;
	}
	


	public final Integer getRole() {
		return this.roleProperty().getValue();
	}
	


	public final void setRole(final Integer role) {
		this.roleProperty().setValue(role);
	}
	


	public final Property<LocalDate> datnaissProperty() {
		return this.datnaiss;
	}
	


	public final LocalDate getDatnaiss() {
		return this.datnaissProperty().getValue();
	}
	


	public final void setDate_naiss(final LocalDate datnaiss) {
		this.datnaissProperty().setValue(datnaiss);
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
	


	public final StringProperty adressepostProperty() {
		return this.adressepost;
	}
	


	public final String getAdressepost() {
		return this.adressepostProperty().get();
	}
	


	public final void setAdressepost(final String Adressepost) {
		this.adressepostProperty().set(Adressepost);
	}

	public final StringProperty cmProperty() {
		return this.cm;
	}
	


	public final String getCm() {
		return this.cmProperty().get();
	}
	


	public final void setCm(final String Cm) {
		this.cmProperty().set(Cm);
	}
}




