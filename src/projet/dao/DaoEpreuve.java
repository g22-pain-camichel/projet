package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Epreuve;
import projet.data.EquipeBenevole;

public class DaoEpreuve {
	
	// Champs

		@Inject
		private DataSource		dataSource;
		
		// Actions

		public void inserer( Epreuve epreuve )  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "INTO epreuve (nom, distance, hr_deb, hr_fin) VALUES (?,?,?,?)";
				stmt = cn.prepareStatement( sql );
				stmt.setObject(1, epreuve.getNom());
				stmt.setObject(2, epreuve.getDistance());
				LocalTime t1 = LocalTime.parse(epreuve.getHr_deb());
				LocalTime t2 = LocalTime.parse(epreuve.getHr_fin());
				stmt.setObject(	3, t1);
				stmt.setObject(	4, t2 );
				stmt.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
		
		public void modifier(Epreuve epreuve ) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "UPDATE epreuve SET nom = ?, distance = ?,"
						+ " hr_deb = ?, hr_fin = ? WHERE nom =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setObject(1, epreuve.getNom());
				stmt.setObject(2, epreuve.getDistance());
				LocalTime t1 = LocalTime.parse(epreuve.getHr_deb());
				LocalTime t2 = LocalTime.parse(epreuve.getHr_fin());
				stmt.setObject(	3, t1);
				stmt.setObject(	4, t2 );
				stmt.setObject(5, epreuve.getNom());
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
				sql = "DELETE FROM epreuve WHERE nom = ? ";
				stmt = cn.prepareStatement(sql);
				stmt.setObject( 1, nom);
				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
		
		public Epreuve retrouver( String nom ) {

			Connection			cn 		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "SELECT * FROM epreuve WHERE nom = ?";
				stmt = cn.prepareStatement( sql );
				stmt.setString(1, nom);
				rs = stmt.executeQuery();

				if ( rs.next() ) {
					return construireEpreuve( rs );
				} else {
					return null;
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		public Epreuve retrouverEpEq(EquipeBenevole eb ) {

			Connection			cn 		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "SELECT * FROM equipebenevole eqb, tache t, lier l, epreuve e WHERE"
						+ "eqb.libelle = t.libelle AND t.libelle = l.libelle AND l.nom = e.nom "
						+ "AND eqb.libelle = ?";
				stmt = cn.prepareStatement( sql );
				stmt.setString(1, eb.getLibelle());
				rs = stmt.executeQuery();

				if ( rs.next() ) {
					return construireEpreuve( rs );
				} else {
					return null;
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		public List<Epreuve> listerTout() {

			Connection			cn 		= null;
			PreparedStatement	stmt 	= null;
			ResultSet 			rs		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "SELECT * FROM epreuve ORDER BY nom";
				stmt = cn.prepareStatement( sql );
				rs = stmt.executeQuery();

				List<Epreuve> epreuves = new LinkedList<>();
				while (rs.next()) {
					epreuves.add( construireEpreuve( rs ) );
				}
				return epreuves;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		private Epreuve construireEpreuve( ResultSet rs ) throws SQLException {
			Epreuve epreuve = new Epreuve();
			epreuve.setNom(rs.getObject( "nom", String.class ) );
			epreuve.setDistance(rs.getObject( "distance", Double.class ) );
			Time t1 = rs.getObject("hr_deb", Time.class);
			Time t2 = rs.getObject("hr_fin", Time.class);
			epreuve.setHr_deb(t1.toString());
			epreuve.setHr_fin(t2.toString());
			return epreuve;
		}
}
