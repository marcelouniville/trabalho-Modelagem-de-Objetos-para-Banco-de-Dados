package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConexaoDB {

    private static ConexaoDB instance;

    private ConexaoDB(){}

    public synchronized static ConexaoDB getInstance(){
        if(instance == null){
            instance = new ConexaoDB();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:meu_banco.bd";
        Connection con = DriverManager.getConnection(url);
        return con;
    }

}
