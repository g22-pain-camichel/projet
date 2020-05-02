package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Utilisateur  {

	
	// Données observables
	
	private final Property<Integer>	id			= new SimpleObjectProperty<>();
	private final StringProperty	pseudo		= new SimpleStringProperty();
	private final StringProperty	motDePasse	= new SimpleStringProperty();
	private final StringProperty	email 		= new SimpleStringProperty();
	private final Property<Integer>	role			= new SimpleObjectProperty<>();
	
	
	// Constructeurs
	
	public Utilisateur() {
		
	}
	
	public Utilisateur( int id, String pseudo, String motDePasse, String email, int role) {
		setId(id);
		setPseudo(pseudo);
		setMotDePasse(motDePasse);
		setEmail(email);
		setRole(role);
	}

	public final Property<Integer> idProperty() {
		return this.id;
	}
	
	public final Integer getId() {
		return this.idProperty().getValue();
	}
	


	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	


	public final StringProperty pseudoProperty() {
		return this.pseudo;
	}
	


	public final String getPseudo() {
		return this.pseudoProperty().get();
	}
	


	public final void setPseudo(final String pseudo) {
		this.pseudoProperty().set(pseudo);
	}
	


	public final StringProperty motDePasseProperty() {
		return this.motDePasse;
	}
	


	public final String getMotDePasse() {
		return this.motDePasseProperty().get();
	}
	


	public final void setMotDePasse(final String motDePasse) {
		this.motDePasseProperty().set(motDePasse);
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
	


	public final Property<Integer> roleProperty() {
		return this.role;
	}
	


	public final Integer getRole() {
		return this.roleProperty().getValue();
	}
	


	public final void setRole(final Integer role) {
		this.roleProperty().setValue(role);
	}


	@Override
	public int hashCode() {
		return Objects.hash(id.getValue());
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		return Objects.equals(id.getValue(), other.id.getValue());
	}
	
	
}
