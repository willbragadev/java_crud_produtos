package factories;

import java.sql.Connection;
import java.sql.DriverManager;

// gerar conexao com o banco de dados postgresql aplicando Design Pattern 'Factory Method'

public class ConnectionFactory {

	private String driver = "org.postgresql.Driver";
	private String host = "jdbc:postgresql://localhost:5432/bd_java_projeto04";
	private String user = "postgres";
	private String password = "coti";
	
	public Connection getConnection() throws Exception {
		//carregando driver para conexao com BD
		Class.forName(driver);
		//abrindo e retornar uma conexao com o PostGreSQL
		return DriverManager.getConnection(host, user, password);
	}
	
}
