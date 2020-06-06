package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Benevole;
import projet.data.Epreuve;
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
				sql = "INSERT INTO tache (libelle, emplacement, hr_deb, hr_fin, taille) VALUES (?,?,?,?,?)";
				stmt = cn.prepareStatement( sql );
				stmt.setObject(1, tache.getLibelle());
				stmt.setObject(2, tache.getEmplacement());
				LocalTime t1 = LocalTime.parse(tache.getHr_deb());
				LocalTime t2 = LocalTime.parse(tache.getHr_fin());
				stmt.setObject(	3, t1);
				stmt.setObject(	4, t2 );
				stmt.setObject(5, tache.getTaille());
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
				sql = "UPDATE tache SET libelle = ?, emplacement = ?, hr_deb = ?,"
						+ " hr_fin = ?, taille = ? WHERE libelle =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setObject(1, tache.getLibelle());
				stmt.setObject(2, tache.getEmplacement());
				LocalTime t1 = LocalTime.parse(tache.getHr_deb());
				LocalTime t2 = LocalTime.parse(tache.getHr_fin());
				stmt.setObject(	3, t1);
				stmt.setObject(	4, t2 );
				stmt.setObject(5, tache.getTaille());
				stmt.setObject(6, tache.getLibelle());
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
					return construireTache( rs, false );
				} else {
					return null;
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		public List<Tache> listerTout()   {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT * FROM tache ORDER BY libelle";
				stmt = cn.prepareStatement(sql);
				rs = stmt.executeQuery();
				
				List<Tache> taches = new ArrayList<>();
				while (rs.next()) {
					taches.add( construireTache(rs, false) );
				}
				return taches;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		public List<Tache> listerTout(Epreuve epreuve) {

			Connection			cn 		= null;
			PreparedStatement	stmt 	= null;
			ResultSet 			rs		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "SELECT * FROM tache t, lier l, epreuve e WHERE t.libelle = l.libelle"
						+ " AND l.nom = e.nom AND e.nom = ? AND statut <> 1 ORDER BY l.libelle";
				stmt = cn.prepareStatement( sql );
				stmt.setObject(1, epreuve.getNom());
				rs = stmt.executeQuery();

				List<Tache> taches = new LinkedList<>();
				while (rs.next()) {
					taches.add( construireTache( rs, false ) );
				}
				return taches;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		

		public List<Tache> listerTaches(String value)   {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "SELECT * FROM tache WHERE libelle LIKE ('%' || ? || '%')";
				stmt = cn.prepareStatement(sql);
				stmt.setObject( 1, value);
	            rs = stmt.executeQuery();
	            
				List<Tache> taches = new ArrayList<>();
				while (rs.next()) {
					taches.add( construireTache(rs, false) );
				}
				return taches;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}

		
		private Tache construireTache( ResultSet rs, boolean flagComplet  ) throws SQLException {
			Tache tache = new Tache();
			tache.setLibelle(rs.getObject( "libelle", String.class ) );
			tache.setEmplacement( rs.getObject( "emplacement", String.class ) );
			Time t1 = rs.getObject("hr_deb", Time.class);
			Time t2 = rs.getObject("hr_fin", Time.class);
			tache.setHr_deb(t1.toString());
			tache.setHr_fin(t2.toString());
			tache.setTaille(rs.getObject("taille", Integer.class));
			return tache;
		}
}
