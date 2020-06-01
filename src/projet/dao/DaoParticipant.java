package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Benevole;
import projet.data.Participant;


public class DaoParticipant {

		// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer(Participant participant)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le participant
			sql = "INSERT INTO participant (num, nom, prenom, sexe, dtNaiss, email, tel, role, adressePost, cm, estValide)"
					+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setObject(	1, participant.getNum());
			stmt.setObject(	2, participant.getNom() );
			stmt.setObject(	3, participant.getPrenom() );
			stmt.setObject(	4, participant.getSexe());
			stmt.setObject(	5, participant.getDtNaiss());
			stmt.setObject(	6, participant.getEmail() );
			stmt.setObject(	7, participant.getTel() );
			stmt.setObject(	8, participant.getRole());
			stmt.setObject(	9, participant.getadressePost());
			stmt.setObject(	10, participant.getCm());
			stmt.setObject(11, participant.getEstValide());
			stmt.executeUpdate();

			// Récupère l'Num généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			participant.setNum(rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		// Retourne l'Num
		return participant.getNum();
	}

	
	public void modifier(Participant participant)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le participant
			sql = "UPDATE participant SET num = ?, nom = ?, prenom = ?, sexe = ?, dtNaiss = ?, email = ?, tel = ?,"
					+ " role = ?, adressePost = ?, cm = ?, estValide = ? WHERE num =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject(	1, participant.getNum());
			stmt.setObject(	2, participant.getNom() );
			stmt.setObject(	3, participant.getPrenom() );
			stmt.setObject(	4, participant.getSexe());
			stmt.setObject(	5, participant.getDtNaiss());
			stmt.setObject(	6, participant.getEmail() );
			stmt.setObject(	7, participant.getTel() );
			stmt.setObject(	8, participant.getRole());
			stmt.setObject(	9, participant.getadressePost());
			stmt.setObject(	10, participant.getCm());
			stmt.setObject( 11, participant.getEstValide());
			stmt.setObject(	12, participant.getNum());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public void supprimer(int idParticipant)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;


		try {
			cn = dataSource.getConnection();

			// Supprime le participant
			sql = "DELETE FROM participant WHERE Num = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idParticipant );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Participant retrouver(int idParticipant)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant WHERE Num = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idParticipant);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireParticipant(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<Participant> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Participant> participants = new ArrayList<>();
			while (rs.next()) {
				participants.add( construireParticipant(rs, false) );
			}
			return participants;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
/* 	public List<Participant> listerPourMemo( int idMemo )   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT p.* FROM participant p" 
				+ " INNER JOIN concerner c ON p.idparticipant = c.idparticipant" 
				+ " WHERE c.idmemo = ?" 
				+ " ORDER BY nom, prenom";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idMemo ); 
			rs = stmt.executeQuery();
			
			List<Participant> participants = new ArrayList<>();
			while (rs.next()) {
				participants.add( construireParticipant(rs, false) );
			}
			return participants;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

 */	
	// Méthodes auxiliaires
	
	private Participant construireParticipant( ResultSet rs, boolean flagComplet ) throws SQLException {
		Participant participant = new Participant();
		participant.setNum(rs.getObject( "Num", Integer.class ));
		participant.setNom(rs.getObject( "nom", String.class ));
		participant.setPrenom(rs.getObject( "prenom", String.class ));
		participant.setSexe(rs.getObject( "sexe", Integer.class ));
		participant.setDtNaiss(rs.getObject( "dtNaiss", LocalDate.class ));
		participant.setEmail(rs.getObject( "email", String.class ));
		participant.setTel(rs.getObject( "tel", String.class ));
		participant.setRole(rs.getObject( "role", Integer.class ));
		participant.setadressePost(rs.getObject( "adressePost", String.class));
		participant.setCm(rs.getObject( "cm", String.class));
		participant.setEstValide(rs.getObject("estValide", Boolean.class));
		return participant;
	}


	public List<Participant> listerParticipant(String name)   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant WHERE nom = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, name);
            rs = stmt.executeQuery();
            
			List<Participant> participants = new ArrayList<>();
			while (rs.next()) {
				participants.add( construireParticipant(rs, false) );
			}
			return participants;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
}
