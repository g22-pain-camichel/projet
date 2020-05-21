package projet;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

import jfox.commun.context.ContextLocal;
import jfox.commun.context.IContext;
import jfox.dao.jdbc.DataSourceSingleConnection;
import projet.commun.IMapperImpl;
import projet.report.ManagerReport;
import projet.view.ManagerGui;


public class MainProjet {

	// main()
	
	public static void main(String[] args) {   
		
		try {

			// JDBC - DataSource	
			DataSource dataSource = new DataSourceSingleConnection( "META-INF/jdbc.properties" );
			
			// Context
			IContext context = new ContextLocal();
			context.addBean( dataSource );
			context.addBean( new IMapperImpl() );

			// Configure le ManagerReport
			context.getBean( ManagerReport.class).setDirReports( "etats" );;
			
			// Démarre l'application
			context.getBean( ManagerGui.class ).launch();
			
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Impossible de démarrer l'application.", "", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		System.exit(0);
    }
  
}