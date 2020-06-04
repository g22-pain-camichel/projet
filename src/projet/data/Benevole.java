package projet.data;

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
	private final StringProperty	hrDbDispo	= new SimpleStringProperty();
	private final StringProperty hrFinDispo = new SimpleStringProperty();
	//
	private final StringProperty numero = new SimpleStringProperty();
	private final Property<Boolean> estValide = new SimpleObjectProperty<>(); 

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



	public final LocalDate getDtNaiss() {
		return this.dtNaissProperty().getValue();
	}



	public final void setDtNaiss(final LocalDate dtNaiss) {
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

	public final StringProperty hrDbDispoProperty() {
		return this.hrDbDispo;
	}


	public final String getHrDbDispo() {
		return this.hrDbDispoProperty().get();
	}


	public final void setHrDbDispo(final String hrDbDispo) {
		this.hrDbDispoProperty().set(hrDbDispo);
	}


	public final StringProperty hrFinDispoProperty() {
		return this.hrFinDispo;
	}


	public final String getHrFinDispo() {
		return this.hrFinDispoProperty().get();
	}


	public final void setHrFinDispo(final String hrFinDispo) {
		this.hrFinDispoProperty().set(hrFinDispo);
	}
	

	@Override
	public String toString() {
		return getNom() + " "+ getPrenom();
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
