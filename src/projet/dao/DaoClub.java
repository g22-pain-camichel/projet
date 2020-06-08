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
import projet.data.Club;
import projet.data.Epreuve;
import projet.data.Participant;

public class DaoClub {
	
	// Champs

	@Inject
	private DataSource		dataSource;

	// Actions

	public void inserer( Club club ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO club (num, numCapitaine, nbRepasReserves, numEquipier, categorie,"
					+ " activite, estValide, nom) VALUES (?,?,?,?,?,?,?,?)";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, club.getNum());
			stmt.setObject( 2, club.getNumCapitaine() );
			stmt.setObject( 3, club.getNbRepasReserves());
			stmt.setObject( 4, club.getNumEquipier());
			stmt.setObject(5, club.getCategorie());
			stmt.setObject(6, club.getActivite());
			stmt.setObject(7, club.getEstValide());
			stmt.setObject( 8, club.getNom() );
			stmt.executeUpdate();

			// Récupère l'Num généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			club.setNum(rs.getObject( 1, Integer.class ) );
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	
	public void modifier(Club club)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le club
			sql = "UPDATE club SET num = ?, numCapitaine = ?, nbRepasReserves = ?, numEquipier = ?"
					+ "categorie = ?, activite = ?, estValide = ?, nom = ? WHERE num =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, club.getNum());
			stmt.setObject( 2, club.getNumCapitaine() );
			stmt.setObject( 3, club.getNbRepasReserves());
			stmt.setObject( 4, club.getNumEquipier());
			stmt.setObject( 5, club.getCategorie());
			stmt.setObject( 6, club.getActivite());
			stmt.setObject( 7, club.getEstValide());
			stmt.setObject( 8, club.getNom() );
			stmt.setObject( 9, club.getNum());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	
	
	public void supprimer(int idClub)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;


		try {
			cn = dataSource.getConnection();

			// Supprime le participant
			sql = "DELETE FROM club WHERE Num = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idClub );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Club retrouver(int idClub)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM club WHERE num = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idClub);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireClub(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<Club> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM club ORDER BY nom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Club> clubs = new ArrayList<>();
			while (rs.next()) {
				clubs.add( construireClub(rs, false) );
			}
			return clubs;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public List<Club> clubsEpreuve(Epreuve ep)   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM club b, inscrire i, epreuve e WHERE b.num = i.num"
					+ " AND i.nom = e.nom AND e.nom = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, ep.getNom());
			rs = stmt.executeQuery();
			
			List<Club> clubs = new ArrayList<>();
			while (rs.next()) {
				clubs.add( construireClub(rs, false) );
			}
			return clubs;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	private Club construireClub( ResultSet rs, boolean flagComplet ) throws SQLException {
		Club club = new Club();
		club.setNum(rs.getObject( "num", Integer.class ));
		club.setNom(rs.getObject( "nom", String.class ));
		club.setNumCapitaine(rs.getObject("numCapitaine", Integer.class));
		club.setNbRepasReserves(rs.getObject( "nbRepasReserves", Integer.class ));
		club.setNumEquipier(rs.getObject( "numEquipier", Integer.class ));
		club.setCategorie(rs.getObject("categorie", Integer.class));
		club.setActivite(rs.getObject("activite", Integer.class));
		club.setEstValide(rs.getObject("estValide", Boolean.class));
		return club;
	}
	
	public List<Club> listerTout(boolean bool)   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM club WHERE estvalide = ? ORDER BY nom";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, bool);
			rs = stmt.executeQuery();
			
			List<Club> club = new ArrayList<>();
			while (rs.next()) {
				club.add( construireClub(rs, false) );
			}
			return club;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	public List<Club> listerClub(String name)   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM club WHERE nom = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, name);
            rs = stmt.executeQuery();
            
			List<Club> club = new ArrayList<>();
			while (rs.next()) {
				club.add( construireClub(rs, false) );
			}
			return club;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	

}
