package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Benevole;
import projet.data.EquipeBenevole;


public class DaoBenevole {

		// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer(Benevole benevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le Benevole
			sql = "INSERT INTO benevole (identifiant, nom, prenom, sexe, dtNaiss, email, tel, type,"
					+ " hrDbDispo, hrFinDispo, numero, estValide) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setObject(	1, benevole.getIdentifiant());
			stmt.setObject(	2, benevole.getNom() );
			stmt.setObject(	3, benevole.getPrenom() );
			stmt.setObject(	4, benevole.getSexe());
			stmt.setObject(	5, benevole.getDtNaiss());
			stmt.setObject(	6, benevole.getEmail() );
			stmt.setObject(	7, benevole.getTel() );
			stmt.setObject(	8, benevole.getType());
			LocalTime t1 = LocalTime.parse(benevole.getHrDbDispo());
			LocalTime t2 = LocalTime.parse(benevole.getHrFinDispo());
			stmt.setObject(	9, t1);
			stmt.setObject(	10, t2 );
			stmt.setObject(11, benevole.getNumero());
			stmt.setObject(12, benevole.getEstValide());
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			benevole.setIdentifiant(rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		// Retourne l'identifiant
		return benevole.getIdentifiant();
	}

	
	public void modifier(Benevole benevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le benevole
			sql = "UPDATE Benevole SET identifiant = ?, nom = ?, prenom = ?, sexe = ?, dtNaiss = ?, email = ?, tel = ?,"
					+ " type = ?, hrDbDispo = ?, hrFinDispo = ?, numero = ?,"
					+ " estValide = ? WHERE identifiant =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject(	1, benevole.getIdentifiant());
			stmt.setObject(	2, benevole.getNom() );
			stmt.setObject(	3, benevole.getPrenom() );
			stmt.setObject(	4, benevole.getSexe());
			stmt.setObject(	5, benevole.getDtNaiss());
			stmt.setObject(	6, benevole.getEmail() );
			stmt.setObject(	7, benevole.getTel() );
			stmt.setObject(	8, benevole.getType());
			LocalTime t1 = LocalTime.parse(benevole.getHrDbDispo());
			LocalTime t2 = LocalTime.parse(benevole.getHrFinDispo());
			stmt.setObject(	9, t1);
			stmt.setObject(	10, t2 );
			stmt.setObject(11, benevole.getNumero());
			stmt.setObject(12, benevole.getEstValide());
			stmt.setObject(	13, benevole.getIdentifiant());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public void supprimer(int idBenevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;


		try {
			cn = dataSource.getConnection();

			// Supprime le benevole
			sql = "DELETE FROM benevole WHERE identifiant = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idBenevole );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Benevole retrouver(int idBenevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM benevole WHERE identifiant = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idBenevole);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireBenevole(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public List<Benevole> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM benevole ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Benevole> benevoles = new ArrayList<>();
			while (rs.next()) {
				benevoles.add( construireBenevole(rs, false) );
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public List<Benevole> listerToutDispo()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM benevole b WHERE identifiant NOT IN (SELECT identifiant FROM"
					+ " constituer)";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			List<Benevole> benevoles = new ArrayList<>();
			while (rs.next()) {
				benevoles.add( construireBenevole(rs, false) );
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public List<Benevole> listerBenevoleEquipe(EquipeBenevole eq)   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM benevole b, constituer c, equipebenevole eb WHERE "
					+ "b.identifiant = c.identifiant AND c.num = eb.num AND eb.num = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, eq.getNum());
			rs = stmt.executeQuery();
			List<Benevole> benevoles = new ArrayList<>();
			while (rs.next()) {
				benevoles.add( construireBenevole(rs, false) );
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public List<Benevole> listerTout(boolean bool)   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM benevole WHERE estValide = ? ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, bool);
			rs = stmt.executeQuery();
			
			List<Benevole> benevoles = new ArrayList<>();
			while (rs.next()) {
				benevoles.add( construireBenevole(rs, false) );
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	public List<Benevole> listerBenevoles(String value)   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			
			try {
				int i = Integer.parseInt(value);
				sql = "SELECT * FROM benevole WHERE identifiant = ?";
				stmt = cn.prepareStatement(sql);
				stmt.setObject( 1, i);
			}
			catch(Exception e) {
				sql = "SELECT * FROM benevole WHERE nom LIKE ('%' || ? || '%')";
				stmt = cn.prepareStatement(sql);
				stmt.setObject( 1, value);
			}
            rs = stmt.executeQuery();
            
			List<Benevole> benevoles = new ArrayList<>();
			while (rs.next()) {
				benevoles.add( construireBenevole(rs, false) );
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	// Méthodes auxiliaires
	
	private Benevole construireBenevole( ResultSet rs, boolean flagComplet ) throws SQLException {
		Benevole benevole = new Benevole();
		benevole.setIdentifiant(rs.getObject( "identifiant", Integer.class ));
		benevole.setNom(rs.getObject( "nom", String.class ));
		benevole.setPrenom(rs.getObject( "prenom", String.class ));
		benevole.setSexe(rs.getObject( "sexe", Integer.class ));
		benevole.setDtNaiss(rs.getObject( "dtNaiss", LocalDate.class ));
		benevole.setEmail(rs.getObject( "email", String.class ));
		benevole.setTel(rs.getObject( "tel", String.class ));
		benevole.setType(rs.getObject( "type", String.class ));
		Time t1 = rs.getObject("hrDbdispo", Time.class);
		Time t2 = rs.getObject("hrFindispo", Time.class);
		benevole.setHrDbDispo(t1.toString());
		benevole.setHrFinDispo(t2.toString());
		benevole.setNumero(rs.getObject("numero", String.class));
		benevole.setEstValide(rs.getObject("estValide", Boolean.class));
		return benevole;
	}
	
}
