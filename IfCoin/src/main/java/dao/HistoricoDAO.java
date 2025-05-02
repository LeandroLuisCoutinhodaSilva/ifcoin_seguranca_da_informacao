/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import classes.Historico;
import classes.Usuario;
import factory.FactoryConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leand
 */
public class HistoricoDAO {
    
    private Connection conn;

    public HistoricoDAO() {
        conn = FactoryConnector.getConection();
    }

    public boolean insert(Historico historico) {
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO historico (idusuario, senha) VALUES (?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, historico.getIdUsuario());
            stmt.setString(2, historico.getSenhaAntiga());

            int linhasAfetadas = stmt.executeUpdate(); 

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir histórico: " + e.getErrorCode() + " - " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(HistoricoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean update(Historico historico) {
        PreparedStatement stmt = null;

        try {
            String sql = "UPDATE historico SET idusuario = ?, senha = ?, dataUltimaTroca = ? WHERE idhistorico = ? ";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, historico.getIdUsuario());
            stmt.setString(2, historico.getSenhaAntiga());
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis())); // Data atual
            stmt.setInt(4, historico.getIdHistorico());

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "-" + e.getMessage());
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HistoricoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Historico> lista() {
        Statement stmt = null;
        ArrayList<Historico> listaHistoricos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM historico";

            stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Historico historico;
                historico = new Historico(res.getInt("idhistorico"),
                        res.getInt("idusuario"),
                        res.getString("senha"));

                listaHistoricos.add(historico);
            }

            res.close();
            stmt.close();

            return listaHistoricos;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "-" + e.getMessage());
            return listaHistoricos;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HistoricoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean excluir(int codigo) {

        PreparedStatement stmt = null;

        try {

            String sql = "DELETE FROM historico where idhistorico = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, codigo);
            stmt.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(HistoricoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                if(stmt != null){
                     stmt.close();
                }              
            } catch (SQLException ex) {
                Logger.getLogger(HistoricoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


