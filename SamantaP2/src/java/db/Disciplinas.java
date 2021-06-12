/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.util.ArrayList;
import web.DbListener;

/**
 *
 * @author SAMANTA
 */
public class Disciplinas {
    private String nome;
    private String diaDaSemana;
    private String horario;
    private int qtAulas;
    private Double notap1;
    private Double notap2;
    
    
    public static ArrayList<Disciplinas> getDisciplinas() throws Exception{
        ArrayList<Disciplinas> list = new ArrayList<>();
        Connection con = DbListener.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * from disciplinas ORDER BY name");
        while(rs.next()){
            list.add(new Disciplinas(
                    rs.getString("nome"),
                    rs.getString("diadasemana"),
                    rs.getString("horario"),
                    rs.getInt("qtaulas"),
                    rs.getDouble("notap1"),
                    rs.getDouble("notap2")
            ));
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
    
    public static void addDisciplina(String nome, String diaDaSemana, String horario, int qtAulas, Double notap1, Double notap2) throws Exception{
        Connection con = DbListener.getConnection();
        String sql = "INSERT INTO disciplinas(nome, diadasemana, horario, qtaulas, notap1, notap2)"
                + "VALUES(?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setString(2, diaDaSemana);
        stmt.setString(3, horario);
        stmt.setInt(4, qtAulas);
        stmt.setDouble(5, notap1);
        stmt.setDouble(6, notap2);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void updateDisciplina(String nome, Double notap1, Double notap2) throws Exception{
        Connection con = DbListener.getConnection();
        String sql = "UPDATE disciplinas set notap1 = ?, notap2 = ? WHERE nome = ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setDouble(1, notap1);
        stmt.setDouble(2, notap2);
        stmt.setString(3, nome);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void removeDisciplina(String nome) throws Exception{
        Connection con = DbListener.getConnection();
        String sql = "DELETE FROM disciplinas WHERE nome = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.execute();
        stmt.close();
        con.close();
    }

    public Disciplinas(String nome, String diaDaSemana, String horario, int qtAulas, Double notap1, Double notap2) {
        this.nome = nome;
        this.diaDaSemana = diaDaSemana;
        this.horario = horario;
        this.qtAulas = qtAulas;
        this.notap1 = notap1;
        this.notap2 = notap2;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getQtAulas() {
        return qtAulas;
    }

    public void setQtAulas(int qtAulas) {
        this.qtAulas = qtAulas;
    }

    public Double getNotap1() {
        return notap1;
    }

    public void setNotap1(Double notap1) {
        this.notap1 = notap1;
    }

    public Double getNotap2() {
        return notap2;
    }

    public void setNotap2(Double notap2) {
        this.notap2 = notap2;
    }

    
    
}