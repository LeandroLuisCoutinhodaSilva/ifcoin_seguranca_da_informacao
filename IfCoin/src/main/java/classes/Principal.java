
package classes;

import view.TelaLogin;

public class Principal {
    
    public static Usuario usuarioLogado = new Usuario();
    
    public static void main(String[] args) {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }
}
