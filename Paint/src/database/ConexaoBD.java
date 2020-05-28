package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 

public class ConexaoBD {
 
    private static String driverName = "com.mysql.jdbc.Driver";                        
    private static String serverName = "unigraphics-db.c0tvghz53ngd.sa-east-1.rds.amazonaws.com";  
    //String mydatabase = "";        
    private static String url = "jdbc:mysql://" + serverName; //+ "/" + mydatabase;
    private static String username = "admin";            
    private static String password = "unigraphics";     

    public ConexaoBD() {
        
    }
 
    public static java.sql.Connection getConexaoBD() throws ClassNotFoundException {   
        try {
            Class.forName(driverName);
            return DriverManager.getConnection(url, username, password);
        } 
        catch (SQLException e) {
            //Não conseguindo se conectar ao banco
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");

            return null;
        }
    }
 
    public static boolean FecharConexao() throws ClassNotFoundException {
        try {
            ConexaoBD.getConexaoBD().close();
 
            return true;
 
        } catch (SQLException e) {
            return false;
        }
    }
 
    public static java.sql.Connection ReiniciarConexao() throws ClassNotFoundException {
        FecharConexao();
 
        return ConexaoBD.getConexaoBD();
    }
}