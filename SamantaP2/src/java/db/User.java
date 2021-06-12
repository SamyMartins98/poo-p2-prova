/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.ArrayList;
import java.sql.*;
import web.DbListener;

/**
 *
 * @author MARCOSESTEVAODASILVA
 */
public class User {
    private String nome;
    private String login;
    
    public static ArrayList<User> getUsers() throws Exception{
        ArrayList<User> list = new ArrayList<>();
        Connection con = DbListener.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * from users");
        while(rs.next()){
            list.add(new User(
                    rs.getString("nome")
                    , rs.getString("login")
            ));
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
    
    public static User getUser(String login, String pass) throws Exception{
        User user = null;
        Connection con = DbListener.getConnection();
        String sql = "SELECT * from users WHERE login=? and password_hash=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, pass);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            user = new User(
                    rs.getString("nome")
                    , rs.getString("login")
            );
        }
        rs.close();
        stmt.close();
        con.close();
        return user;
    }
    
    public static void addUser(String login, String nome, String password) throws Exception{
        Connection con = DbListener.getConnection();
        String sql = "INSERT INTO users(login, nome, password_hash)"
                + "VALUES(?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, nome);
        stmt.setString(3, password);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void removeUser(String login) throws Exception{
        Connection con = DbListener.getConnection();
        String sql = "DELETE FROM users WHERE login = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public User(String nome, String login) {
        this.nome = nome;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return nome;
    }

    public void setName(String nome) {
        this.nome = nome;
    }
    
}