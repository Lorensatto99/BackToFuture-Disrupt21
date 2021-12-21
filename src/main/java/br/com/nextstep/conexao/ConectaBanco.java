package main.java.br.com.nextstep.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaBanco {

	public static Connection conectar() throws Exception{ 
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(System.getenv("database_url"));
	}
}
