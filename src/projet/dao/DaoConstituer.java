package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Constituer;

public class DaoConstituer {
	
	// Champs

	@Inject
	private DataSource		dataSource;

	// Actions

	public void inserer( Constituer constituer ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO constituer (identifiant, num) VALUES (?,?)";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, constituer.getIdentidant());
			stmt.setObject( 2, constituer.getNum());
			stmt.executeUpdate();

			// Récupère l'Num généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			constituer.setIdentidant(rs.getObject( 1, Integer.class ) );
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	
	public void modifier(Constituer constituer)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le constituer
			sql = "UPDATE constituer SET num = ?, identifiant = ? WHERE identifiant =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, constituer.getIdentidant());
			stmt.setObject( 2, constituer.getNum());
			stmt.setObject( 3, constituer.getIdentidant());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	
	
	public void supprimer(int idConstituer)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;


		try {
			cn = dataSource.getConnection();

			// Supprime le participant
			sql = "DELETE FROM constituer WHERE identifiant = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idConstituer );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Constituer retrouver(int idConstituer)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM constituer WHERE identifiant = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idConstituer);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireConstituer(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	private Constituer construireConstituer( ResultSet rs, boolean flagComplet ) throws SQLException {
		Constituer constituer = new Constituer();
		constituer.setNum(rs.getObject( "Num", Integer.class ));
		constituer.setIdentidant(rs.getObject("Identifiant", Integer.class));
		return constituer;
	}

}
