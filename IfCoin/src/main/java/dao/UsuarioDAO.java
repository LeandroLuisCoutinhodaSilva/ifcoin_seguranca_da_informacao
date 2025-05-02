
package dao;

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


public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO() {
        conn = FactoryConnector.getConection();
    }

    public Usuario login(Usuario usr) {
        PreparedStatement stmt = null;
        Usuario usrReturn = null;

        try {
            String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usr.getNomeUsuario());
            stmt.setString(2, usr.getSenhaUsuario());
            
            ResultSet res = stmt.executeQuery();

            while (res.next()) {

                usrReturn = new Usuario(res.getInt("idusuario"),
                        res.getString("usuario"),
                        res.getString("senha"), 
                        res.getDate("dataUltimaTroca"));
            }

            res.close();
            stmt.close();

            return usrReturn;

        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "-" + e.getMessage());
            return usrReturn;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean insert(Usuario usr) {
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;

        try {
            String sql = "INSERT INTO usuario (usuario, senha, dataUltimaTroca) "
                    + "         VALUES (?,?,?)";

            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usr.getNomeUsuario());
            stmt.setString(2, usr.getSenhaUsuario());
            
            java.sql.Date dataFormatoSql = new java.sql.Date(usr.getDataUltimaTroca().getTime());
            
            stmt.setDate(3, dataFormatoSql);

            int linhasAfetadas = stmt.executeUpdate();
            
            if(linhasAfetadas == 0){
                throw new SQLException("Erro ao inserir o usuário!");
            }
            
            //Recuperando o ID gerado
            generatedKeys = stmt.getGeneratedKeys();
            if(generatedKeys.next()){
                usr.setIdUsuario(generatedKeys.getInt(1));//O número 1 é o índice da coluna do banco, no caso a primeira coluna
            } else {               
                throw new SQLException("Erro ao obter o ID do usuário.");
            }
                      
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "-" + e.getMessage());
            return false;
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean update(Usuario usr) {
        PreparedStatement stmt = null;

        try {
            String sql = "UPDATE usuario SET usuario = ?, senha = ?, dataUltimaTroca = ? WHERE idusuario = ? ";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usr.getNomeUsuario());
            stmt.setString(2, usr.getSenhaUsuario());
            
            java.sql.Date dataFormatoSql = new java.sql.Date(usr.getDataUltimaTroca().getTime());
            
            stmt.setDate(3, dataFormatoSql);
            stmt.setInt(4, usr.getIdUsuario());

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "-" + e.getMessage());
            return false;
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//
    public ArrayList<Usuario> lista() {
        Statement stmt = null;
        ArrayList<Usuario> listaUsuario = new ArrayList<>();

        try {
            String sql = "SELECT * FROM usuario";

            stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Usuario usuario;
                usuario = new Usuario(res.getInt("idusuario"),
                        res.getString("usuario"),
                        res.getString("senha"),
                        res.getDate("dataUltimaTroca"));

                listaUsuario.add(usuario);

            }

            res.close();
            stmt.close();

            return listaUsuario;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "-" + e.getMessage());
            return listaUsuario;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean excluir(int codigo) {

        PreparedStatement stmt = null;

        try {

            String sql = "DELETE FROM usuario where idusuario = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, codigo);
            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
