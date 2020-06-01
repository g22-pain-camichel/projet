package projet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Permi;

public class DaoPermi {
	
	// Champs

		@Inject
		private DataSource		dataSource;
		
		// Actions

		public void inserer( Permi permi )  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "INSERT INTO permi (numero, lieu, dateDelivrance) VALUES (?,?,?)";
				stmt = cn.prepareStatement( sql );
				stmt.setObject(1, permi.getNumero());
				stmt.setObject(2, permi.getLieu());
				stmt.setObject(3, permi.getDateDelivrance());
				stmt.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
		
		public void modifier(Permi permi ) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "UPDATE permi SET numero = ?, lieu = ?,"
						+ " dateDelivrance = ? WHERE numero =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setObject(1, permi.getNumero());
				stmt.setObject(2, permi.getLieu());
				stmt.setObject(3, permi.getDateDelivrance());
				stmt.setObject(4, permi.getNumero());
				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
		
		public void supprimer(String numero) {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String 				sql;

			try {
				cn = dataSource.getConnection();

				// Supprime les roles
				sql = "DELETE FROM permi WHERE numero = ? ";
				stmt = cn.prepareStatement(sql);
				stmt.setObject( 1, numero);
				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}
		
		public Permi retrouver( String numero ) {

			Connection			cn 		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "SELECT * FROM permi WHERE numero = ?";
				stmt = cn.prepareStatement( sql );
				stmt.setString(1, numero);
				rs = stmt.executeQuery();

				if ( rs.next() ) {
					return construirePermi( rs );
				} else {
					return null;
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		public List<Permi> listerTout() {

			Connection			cn 		= null;
			PreparedStatement	stmt 	= null;
			ResultSet 			rs		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();
				sql = "SELECT * FROM permi ORDER BY numero";
				stmt = cn.prepareStatement( sql );
				rs = stmt.executeQuery();

				List<Permi> permis = new LinkedList<>();
				while (rs.next()) {
					permis.add( construirePermi( rs ) );
				}
				return permis;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		private Permi construirePermi( ResultSet rs ) throws SQLException {
			Permi permi = new Permi();
			permi.setNumero(rs.getObject( "numero", String.class ) );
			permi.setLieu(rs.getObject( "Lieu", String.class ) );
			permi.setDateDelivrance(rs.getObject("dateDelivrance", Date.class));
			return permi;
		}
}
