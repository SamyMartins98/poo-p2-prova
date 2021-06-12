/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author SAMANTA
 */
public class DbListener implements ServletContextListener {

    public static final String CLASS_NAME = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:C:\\Users\\MARCOSESTEVAODASILVA\\Documents\\Samanta -Prova\\samantap2.db";
    
    public static String step = null;
    public static Exception exception = null;
    
    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(URL);
    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try{
            step = "Conectando com a base";
            Class.forName(CLASS_NAME);
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            //stmt.execute("DROP TABLE users");
            step = "Criando tabela de usuários - nome, login, password_hash";
            String sql = "CREATE TABLE IF NOT EXISTS users("
                    + "nome VARCHAR(200) NOT NULL,"
                    + "login VARCHAR(50) UNIQUE NOT NULL,"
                    + "password_hash VARCHAR(50)"
                    + ")";
            stmt.execute(sql);
            if(User.getUsers().isEmpty()){
                step = "Inserindo usuários";
                sql = "INSERT INTO users(nome, login, password_hash) "
                    + "VALUES('Administrador', 'admin', '1234')";
                stmt.execute(sql);
                sql = "INSERT INTO users(nome, login, password_hash) "
                    + "VALUES('Fulano da Silva', 'fulano', '1234')";
                stmt.execute(sql);
            }
            
            step = "Criando tabela de disciplinas - nome, dia da semana, horário, quantidade de aulas, nota da p1 e nota da p2";
            sql = "CREATE TABLE IF NOT EXISTS disciplinas("
                    + "nome VARCHAR(50) PRIMARY KEY,"
                    + "diadasemana VARCHAR(200) NOT NULL,"
                    + "horario VARCHAR(200) NOT NULL,"
                    + "qtaulas VARCHAR(200) NOT NULL,"
                    + "notap1 double NOT NULL,"
                    + "notap2 double NOT NULL"
                    + ")";
            stmt.execute(sql);
            
            stmt.close();
            con.close();
        }catch(Exception ex){
            exception = ex;
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
