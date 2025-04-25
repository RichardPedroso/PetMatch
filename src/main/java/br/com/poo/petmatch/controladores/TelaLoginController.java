package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.controleCena.ControladorDeCena;
import br.com.poo.petmatch.main.PetMatch;
import br.com.poo.petmatch.usuario.Administrador;
import br.com.poo.petmatch.usuario.Cliente;
import br.com.poo.petmatch.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class TelaLoginController {

    public static final String FXML_PATH = "tela-login.fxml";

    @FXML
    private Button botaoEntrar;

    @FXML
    private Button botaoNaoTenhoConta;

    @FXML
    public Label labelRespostaLogin;

    @FXML
    private TextField caixaEmail;

    @FXML
    private TextField caixaSenha;

    @FXML
    private void initialize() {
        labelRespostaLogin.setVisible(false);
        botaoEntrar.setOnMouseClicked(event -> {
            try{
                checagemLogin();
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        botaoNaoTenhoConta.setOnMouseClicked(event -> {
            try{
                mudarParaTelaNaoPossuiConta();
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void checagemLogin() throws Exception{
        Usuario usuarioLogin = PetMatch.controlePetMatch.verificarLoginUsuario(caixaEmail.getText(), caixaSenha.getText());
        if (usuarioLogin != null) {
            if (usuarioLogin instanceof Administrador) {
                mudarParaTelaOpcoesAdministrador();
            } else if (usuarioLogin instanceof Cliente) {
                mudarParaTelaInicial();
                PetMatch.controlePetMatch.setUsuarioLogado(usuarioLogin);
            }
        }else{
            exibirMensagemRespostaLogin("Email ou Senha Incorretos!", Color.RED);
        }
    }

    public void exibirMensagemRespostaLogin(String mensagem, Color cor) {
        labelRespostaLogin.setText(mensagem);
        labelRespostaLogin.setTextFill(cor);
        labelRespostaLogin.setVisible(true);
    }

    private void mudarParaTelaInicial() throws Exception{
        ControladorDeCena.trocarCena(TelaInicialController.FXML_PATH);
    }

    private void mudarParaTelaNaoPossuiConta() throws Exception {
        ControladorDeCena.trocarCena(TelaCriarContaController.FXML_PATH);
    }

    private void mudarParaTelaOpcoesAdministrador() throws Exception {
        ControladorDeCena.trocarCena(TelaOpcoesAdministradorController.FXML_PATH);
    }
}