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
import projet.data.Inscrire;

public class DaoInscrire {
	
	// Champs

		@Inject
		private DataSource		dataSource;
		
		// Actions

		public void inserer( Inscrire inscrire )  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "INSERT INTO inscrire (nom, num) VALUES (?,?)";
				stmt = cn.prepareStatement( sql );
				stmt.setObject(1, inscrire.getNom());
				stmt.setObject(2, inscrire.getNum());
				stmt.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
		
		public void modifier(Inscrire inscrire ) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "UPDATE inscrire SET nom = ?, num = ? WHERE nom =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setObject(1, inscrire.getNom());
				stmt.setObject(2, inscrire.getNum());
				stmt.setObject(3, inscrire.getNom());
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
				sql = "DELETE FROM inscrire WHERE nom = ? ";
				stmt = cn.prepareStatement(sql);
				stmt.setObject( 1, nom);
				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
		
		public Inscrire retrouver( String nom ) {

			Connection			cn 		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "SELECT * FROM inscrire WHERE nom = ?";
				stmt = cn.prepareStatement( sql );
				stmt.setString(1, nom);
				rs = stmt.executeQuery();

				if ( rs.next() ) {
					return construireInscrire( rs );
				} else {
					return null;
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		public List<Inscrire> listerTout() {

			Connection			cn 		= null;
			PreparedStatement	stmt 	= null;
			ResultSet 			rs		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "SELECT * FROM inscrire ORDER BY nom";
				stmt = cn.prepareStatement( sql );
				rs = stmt.executeQuery();

				List<Inscrire> inscrires = new LinkedList<>();
				while (rs.next()) {
					inscrires.add( construireInscrire( rs ) );
				}
				return inscrires;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		private Inscrire construireInscrire( ResultSet rs ) throws SQLException {
			Inscrire inscrire = new Inscrire();
			inscrire.setNom(rs.getObject( "nom", String.class ) );
			inscrire.setNum( rs.getObject( "num", Integer.class ) );
			return inscrire;
		}
}
