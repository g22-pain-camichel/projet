package projet.data;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Benevole {
	int i;
	
	//champs
	private final Property<Integer>		identifiant			= new SimpleObjectProperty<>();	
	private final StringProperty   	 	nom       = new SimpleStringProperty();
	private final StringProperty   		prenom = new SimpleStringProperty();
	private final Property<Integer>		sexe	= new SimpleObjectProperty<>();
	private final Property<LocalDate>	dtNaiss	= new SimpleObjectProperty<>();
	private final StringProperty		email	= new SimpleStringProperty();
	private final StringProperty		tel		= new SimpleStringProperty();
	private final StringProperty	type	= new SimpleStringProperty();
	private final Property<Time>	hrDbDispo	= new SimpleObjectProperty<>();
	private final Property<Time> hrFinDispo = new SimpleObjectProperty<>();
	
	//constructeurs
	public Benevole() {
	
	}
	
	//getters && setters

	@Override
	public int hashCode() {
		return Objects.hash(identifiant.getValue());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Benevole other = (Benevole) obj;
		return Objects.equals(identifiant.getValue(), other.identifiant.getValue());
	}



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
	


	public final Property<LocalDate> dtNaissProperty() {
		return this.dtNaiss;
	}
	


	public final LocalDate getdtNaiss() {
		return this.dtNaissProperty().getValue();
	}
	


	public final void setdtNaiss(final LocalDate dtNaiss) {
		this.dtNaissProperty().setValue(dtNaiss);
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
	


	public final Property<Time> hrDbDispoProperty() {
		return this.hrDbDispo;
	}
	


	public final Time gethrDbDispo() {
		return this.hrDbDispoProperty().getValue();
	}
	


	public final void sethrDbDispo(Time hrDbDispo) {
		this.hrDbDispoProperty().setValue(hrDbDispo);
	}
	


	public final Property<Time> hrFinDispoProperty() {
		return this.hrFinDispo;
	}
	


	public final Time gethrFinDispo() {
		return this.hrFinDispoProperty().getValue();
	}
	


	public final void sethrFinDispo(Time hrFinDispo) {
		this.hrFinDispoProperty().setValue(hrFinDispo);
	}
	


}
