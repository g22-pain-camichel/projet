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
import projet.data.EquipeBenevole;


public class DaoEquipeBenevole {

		// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer(EquipeBenevole equipeBenevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le equipeBenevole
			sql = "INSERT INTO equipebenevole(num, nbreBenevole, libelle, estValide)"
					+ " VALUES ( ?, ?, ?, ?)";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setObject(	1, equipeBenevole.getNum());
			stmt.setObject(	2, equipeBenevole.getNbreBenevole());
			stmt.setObject(	3, equipeBenevole.getLibelle() );
			stmt.setObject(4, equipeBenevole.getEstValide());
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			equipeBenevole.setNum(rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		// Retourne l'identifiant
		return equipeBenevole.getNum();
	}

	
	public void modifier(EquipeBenevole equipeBenevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le equipeBenevole
			sql = "UPDATE equipeBenevole SET num = ?, nbreBenevole = ?, "
					+ "libelle = ?, estValide = ? WHERE num =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject(	1, equipeBenevole.getNum());
			stmt.setObject(	2, equipeBenevole.getNbreBenevole());
			stmt.setObject(	3, equipeBenevole.getLibelle() );
			stmt.setObject( 4, equipeBenevole.getEstValide());
			stmt.setObject(	5, equipeBenevole.getNum());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public void supprimer(int idEquipeBenevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;


		try {
			cn = dataSource.getConnection();

			// Supprime le equipeBenevole
			sql = "DELETE FROM equipeBenevole WHERE num = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idEquipeBenevole );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public EquipeBenevole retrouver(int idEquipeBenevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM equipeBenevole WHERE num = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idEquipeBenevole);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireEquipeBenevole(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<EquipeBenevole> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM equipeBenevole ORDER BY num";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<EquipeBenevole> benevoles = new ArrayList<>();
			while (rs.next()) {
				benevoles.add( construireEquipeBenevole(rs, false) );
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	private EquipeBenevole construireEquipeBenevole( ResultSet rs, boolean flagComplet ) throws SQLException {
		EquipeBenevole equipeBenevole = new EquipeBenevole();
		equipeBenevole.setNum(rs.getObject( "num", Integer.class ));
		equipeBenevole.setLibelle(rs.getObject( "libelle", String.class ));
		equipeBenevole.setNbreBenevole(rs.getObject( "nbreBenevole", Integer.class ));
		equipeBenevole.setEstValide(rs.getObject("estValide", Boolean.class));
		return equipeBenevole;
	}
	
}
