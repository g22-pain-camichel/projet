package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Utilisateur;


public class DaoUtilisateur {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer( Utilisateur utilisateur )  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le utilisateur
			sql = "INSERT INTO utilisateur ( pseudo, motdepasse, email, role ) VALUES ( ?, ?, ?, ?)";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS ); 
			stmt.setObject( 1, utilisateur.getPseudo() );
			stmt.setObject( 2, utilisateur.getMotDePasse() );
			stmt.setObject( 3, utilisateur.getEmail() );
			stmt.setObject( 4, utilisateur.getRole() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			utilisateur.setId( rs.getObject( 1, Integer.class) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		// Retourne l'identifiant
		return utilisateur.getId();
	}
	

	public void modifier( Utilisateur utilisateur )  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le utilisateur
			sql = "UPDATE utilisateur SET pseudo = ?, motdepasse = ?, email = ?, role = ?"
					+ " WHERE idutilisateur =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, utilisateur.getPseudo() );
			stmt.setObject( 2, utilisateur.getMotDePasse() );
			stmt.setObject( 3, utilisateur.getEmail() );
			stmt.setObject( 4, utilisateur.getRole() );
			stmt.setObject( 5, utilisateur.getId() );
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	

	public void supprimer( int idUtilisateur )  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;
		
		try {
			cn = dataSource.getConnection();

			// Supprime le utilisateur
			sql = "DELETE FROM utilisateur WHERE idutilisateur = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, idUtilisateur );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	

	public Utilisateur retrouver( int idUtilisateur )  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM utilisateur WHERE idutilisateur = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setObject( 1, idUtilisateur );
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireUtilisateur( rs );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	

	public List<Utilisateur> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM utilisateur ORDER BY pseudo";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Utilisateur> utilisateurs = new ArrayList<>();
			while ( rs.next() ) {
				utilisateurs.add( construireUtilisateur(rs) );
			}
			return utilisateurs;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public Utilisateur validerAuthentification( String pseudo, String motDePasse )  {
		
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM utilisateur WHERE pseudo = ? AND motdepasse = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, pseudo );
			stmt.setObject( 2, motDePasse );
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireUtilisateur( rs );			
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public boolean verifierUnicitePseudo( String pseudo, Integer idUtilisateur )   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		if ( idUtilisateur == null ) idUtilisateur = 0;
		
		try {
			cn = dataSource.getConnection();

			sql = "SELECT COUNT(*) = 0 AS unicite"
					+ " FROM utilisateur WHERE pseudo = ? AND idutilisateur <> ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject(	1, pseudo );
			stmt.setObject(	2, idUtilisateur );
			rs = stmt.executeQuery();
			
			rs.next();
			return rs.getBoolean( "unicite" );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Utilisateur construireUtilisateur( ResultSet rs ) throws SQLException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId( rs.getObject( "idutilisateur", Integer.class ) );
		utilisateur.setPseudo( rs.getObject( "pseudo", String.class ) );
		utilisateur.setMotDePasse( rs.getObject( "motdepasse", String.class ) );
		utilisateur.setEmail( rs.getObject( "email", String.class ) );
		utilisateur.setRole(rs.getObject("role", Integer.class));
		return utilisateur;
	}
	
}
