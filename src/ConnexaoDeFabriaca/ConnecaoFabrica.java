package ConnexaoDeFabriaca;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnecaoFabrica {
   private DataSource dadosConnexao;
	
   public ConnecaoFabrica() throws SQLException {
	  
	 ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
     comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/hotel_alura?useTimezone=true&serverTimezone=UTC");
     comboPooledDataSource.setUser("root");
     comboPooledDataSource.setPassword("M@mae32");
    
     this.dadosConnexao = comboPooledDataSource;
}
   public Connection getDadosConnexao() throws SQLException {
	return dadosConnexao.getConnection();
}
 
}
