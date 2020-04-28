package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Lier;

public class DaoLier {
	
	// Champs

		@Inject
		private DataSource		dataSource;
		
		// Actions

		public void inserer( Lier lier )  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "INSERT INTO lier (nom, libelle) VALUES (?,?)";
				stmt = cn.prepareStatement( sql );
				stmt.setObject(1, lier.getNom());
				stmt.setObject(2, lier.getLibelle());
				stmt.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
		
		public void modifier(Lier lier ) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "UPDATE lier SET nom = ?, libelle = ? WHERE nom =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setObject(1, lier.getNom());
				stmt.setObject(2, lier.getLibelle());
				stmt.setObject(3, lier.getNom());
				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
		
		public void supprimer(String nom) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String 				sql;

			try {
				cn = dataSource.getConnection();

				// Supprime les roles
				sql = "DELETE FROM lier WHERE nom = ? ";
				stmt = cn.prepareStatement(sql);
				stmt.setObject( 1, nom);
				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
		
		public Lier retrouver( String nom ) {

			Connection			cn 		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "SELECT * FROM lier WHERE nom = ?";
				stmt = cn.prepareStatement( sql );
				stmt.setString(1, nom);
				rs = stmt.executeQuery();

				if ( rs.next() ) {
					return construireLier( rs );
				} else {
					return null;
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		public List<Lier> listerTout() {

			Connection			cn 		= null;
			PreparedStatement	stmt 	= null;
			ResultSet 			rs		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "SELECT * FROM lier ORDER BY nom";
				stmt = cn.prepareStatement( sql );
				rs = stmt.executeQuery();

				List<Lier> liers = new LinkedList<>();
				while (rs.next()) {
					liers.add( construireLier( rs ) );
				}
				return liers;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		private Lier construireLier( ResultSet rs ) throws SQLException {
			Lier lier = new Lier();
			lier.setNom(rs.getObject( "nom", String.class ) );
			lier.setLibelle( rs.getObject( "libelle", String.class ) );
			return lier;
		}
}
