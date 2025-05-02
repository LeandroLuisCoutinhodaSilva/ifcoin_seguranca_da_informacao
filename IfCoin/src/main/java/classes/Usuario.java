/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.Date;

/**
 *
 * @author leand
 */
public class Usuario {
    private int idUsuario;
    private String nomeUsuario;
    private String senhaUsuario;
    private Date dataUltimaTroca;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nomeUsuario, String senhaUsuario, Date dataUltimaTroca) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.dataUltimaTroca = dataUltimaTroca;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public Date getDataUltimaTroca() {
        return dataUltimaTroca;
    }

    public void setDataUltimaTroca(Date dataUltimaTroca) {
        this.dataUltimaTroca = dataUltimaTroca;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nomeUsuario=" + nomeUsuario + ", senhaUsuario=" + senhaUsuario + ", dataUltimaTroca=" + dataUltimaTroca + '}';
    }

}
