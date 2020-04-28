package projet.data;

import java.time.LocalDate;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Participant {
	
	
	//champs
	private final Property<Integer>		num			= new SimpleObjectProperty<>();	
	private final StringProperty   	 	nom       = new SimpleStringProperty();
	private final StringProperty   		prenom = new SimpleStringProperty();
	private final Property<Integer>		sexe	= new SimpleObjectProperty<>();
	private final Property<LocalDate>	dtNaiss	= new SimpleObjectProperty<>();
	private final StringProperty		email	= new SimpleStringProperty();
	private final StringProperty		tel		= new SimpleStringProperty();
	private final Property<Integer>		role	= new SimpleObjectProperty<>();
	private final StringProperty		adressePost		= new SimpleStringProperty();
	private final StringProperty		cm		= new SimpleStringProperty();
	
	//constructeurs
	public Participant() {
	
	}

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
	

	public final Property<LocalDate> dtNaissProperty() {
		return this.dtNaiss;
	}
	

	public final LocalDate getDtNaiss() {
		return this.dtNaissProperty().getValue();
	}
	

	public final void setDtNaiss(LocalDate dtNaiss) {
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
	


	public final StringProperty adressePostProperty() {
		return this.adressePost;
	}
	


	public final String getadressePost() {
		return this.adressePostProperty().get();
	}
	


	public final void setadressePost(final String adressePost) {
		this.adressePostProperty().set(adressePost);
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
		Participant other = (Participant) obj;
		return Objects.equals(num.getValue(), other.num.getValue());
	}


	
	
}




