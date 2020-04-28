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
import projet.data.Tache;

public class DaoTache {
	
	// Champs

		@Inject
		private DataSource		dataSource;
		
		// Actions

		public void inserer( Tache tache )  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "INSERT INTO tache (libelle, emplacement) VALUES (?,?)";
				stmt = cn.prepareStatement( sql );
				stmt.setObject(1, tache.getLibelle());
				stmt.setObject(2, tache.getEmplacement());
				stmt.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
		
		public void modifier(Tache tache ) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "UPDATE tache SET libelle = ?, emplacement = ? WHERE libelle =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setObject(1, tache.getLibelle());
				stmt.setObject(2, tache.getEmplacement());
				stmt.setObject(3, tache.getLibelle());
				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
		
		public void supprimer(String libelle) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String 				sql;

			try {
				cn = dataSource.getConnection();

				// Supprime les roles
				sql = "DELETE FROM tache WHERE libelle = ? ";
				stmt = cn.prepareStatement(sql);
				stmt.setObject( 1, libelle);
				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
		
		public Tache retrouver( String libelle ) {

			Connection			cn 		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "SELECT * FROM Tache WHERE libelle = ?";
				stmt = cn.prepareStatement( sql );
				stmt.setString(1, libelle);
				rs = stmt.executeQuery();

				if ( rs.next() ) {
					return construireTache( rs );
				} else {
					return null;
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		public List<Tache> listerTout() {

			Connection			cn 		= null;
			PreparedStatement	stmt 	= null;
			ResultSet 			rs		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "SELECT * FROM tache ORDER BY libelle";
				stmt = cn.prepareStatement( sql );
				rs = stmt.executeQuery();

				List<Tache> taches = new LinkedList<>();
				while (rs.next()) {
					taches.add( construireTache( rs ) );
				}
				return taches;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		private Tache construireTache( ResultSet rs ) throws SQLException {
			Tache tache = new Tache();
			tache.setLibelle(rs.getObject( "libelle", String.class ) );
			tache.setEmplacement( rs.getObject( "emplacement", String.class ) );
			return tache;
		}
}
