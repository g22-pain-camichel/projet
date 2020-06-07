package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Utilisateur;


public class DaoRole {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public void insererPourUtilisateur( Utilisateur utilisateur )  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO role (idutilisateur, role) VALUES (?,?)";
			stmt = cn.prepareStatement( sql );
			for( String role : utilisateur.getRoles() ) {
				stmt.setObject( 1, utilisateur.getId() );
				stmt.setObject( 2, role );
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public void supprimerPourUtilisateur( int idutilisateur ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime les roles
			sql = "DELETE FROM role  WHERE idutilisateur = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idutilisateur );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public List<String> listerPourUtilisateur( Utilisateur utilisateur ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM role WHERE idutilisateur = ? ORDER BY role";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, utilisateur.getId() );
			rs = stmt.executeQuery();

			List<String> roles = new ArrayList<>();
			while (rs.next()) {
				roles.add( rs.getObject("role", String.class) );
			}
			return roles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

}
