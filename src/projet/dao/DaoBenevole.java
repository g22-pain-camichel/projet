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
import projet.data.Personne;


public class DaoBenevole {

		// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer(Personne personne)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le personne
			sql = "INSERT INTO personne ( idcategorie, nom, prenom ) VALUES ( ?, ?, ? )";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setInt(	1, personne.getCategorie().getId() );
			stmt.setString(	2, personne.getNom() );
			stmt.setString(	3, personne.getPrenom() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			personne.setId( rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		// Retourne l'identifiant
		return personne.getId();
	}

	
	public void modifier(Personne personne)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le personne
			sql = "UPDATE personne SET idcategorie = ?, nom = ?, prenom = ? WHERE idpersonne =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, personne.getCategorie().getId() );
			stmt.setObject( 2, personne.getNom() );
			stmt.setObject( 3, personne.getPrenom() );
			stmt.setObject( 4, personne.getId() );
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public void supprimer(int idPersonne)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;


		try {
			cn = dataSource.getConnection();

			// Supprime le personne
			sql = "DELETE FROM personne WHERE idpersonne = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idPersonne );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Personne retrouver(int idPersonne)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM personne WHERE idpersonne = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idPersonne);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construirePersonne(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<Personne> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM personne ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Personne> personnes = new ArrayList<>();
			while (rs.next()) {
				personnes.add( construirePersonne(rs, false) );
			}
			return personnes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<Personne> listerPourMemo( int idMemo )   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT p.* FROM personne p" 
				+ " INNER JOIN concerner c ON p.idpersonne = c.idpersonne" 
				+ " WHERE c.idmemo = ?" 
				+ " ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idMemo ); 
			rs = stmt.executeQuery();
			
			List<Personne> personnes = new ArrayList<>();
			while (rs.next()) {
				personnes.add( construirePersonne(rs, false) );
			}
			return personnes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

    
    public int compterPourCategorie(int idCategorie) {
    	
		Connection			cn		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;

		try {
			cn = dataSource.getConnection();
            String sql = "SELECT COUNT(*) FROM personne WHERE idcategorie = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setObject( 1, idCategorie );
            rs = stmt.executeQuery();

            rs.next();
            return rs.getInt( 1 );

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
    }
	
	
	// Méthodes auxiliaires
	
	private Personne construirePersonne( ResultSet rs, boolean flagComplet ) throws SQLException {

		Personne personne = new Personne();
		personne.setId(rs.getObject( "idpersonne", Integer.class ));
		personne.setNom(rs.getObject( "nom", String.class ));
		personne.setPrenom(rs.getObject( "prenom", String.class ));
		return personne;
	}
	
}
