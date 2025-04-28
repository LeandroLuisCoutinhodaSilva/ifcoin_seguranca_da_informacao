/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author leand
 */
public class Historico {
    private int idHistorico;
    private int idUsuario;
    private String senhaAntiga;

    public Historico() {
    }

    public Historico(int idHistorico, int idUsuario, String senhaAntiga) {
        this.idHistorico = idHistorico;
        this.idUsuario = idUsuario;
        this.senhaAntiga = senhaAntiga;
    }

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public void setSenhaAntiga(String senhaAntiga) {
        this.senhaAntiga = senhaAntiga;
    }

    @Override
    public String toString() {
        return "Historico{" + "idHistorico=" + idHistorico + ", idUsuario=" + idUsuario + ", senhaAntiga=" + senhaAntiga + '}';
    }
}
