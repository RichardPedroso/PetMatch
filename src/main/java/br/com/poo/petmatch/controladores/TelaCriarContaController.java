package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.controleCena.ControladorDeCena;
import br.com.poo.petmatch.main.PetMatch;
import br.com.poo.petmatch.usuario.Cliente;
import br.com.poo.petmatch.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TelaCriarContaController {

    public static final String FXML_PATH = "tela-criar-conta.fxml";

    @FXML
    private Button botaoCriar;

    @FXML
    private Button botaoVoltar;

    @FXML
    private Label labelRespostaCpf;

    @FXML
    private Label labelRespostaCriar;

    @FXML
    private TextField caixaEmail;

    @FXML
    private TextField caixaSenha;

    @FXML
    private TextField caixaNome;

    @FXML
    private TextField caixaCpf;

    @FXML
    private void initialize() {
        labelRespostaCpf.setVisible(false);
        labelRespostaCriar.setVisible(false);
        botaoCriar.setOnMouseClicked(event ->{
            try{
                checagemNovaConta();
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        botaoVoltar.setOnMouseClicked(event -> {
            try {
                mudarParaTelaLogin();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void checagemNovaConta(){
        boolean cpfExistente = false;
        for(Usuario usuario : PetMatch.controlePetMatch.usuarios){
            if(usuario.getCpf().equals(caixaCpf.getText())) {
                cpfExistente = true;
                break;
            }
        }

        if(caixaNome.getText().isEmpty() || caixaCpf.getText().isEmpty() || caixaEmail.getText().isEmpty() || caixaSenha.getText().isEmpty()){
            exibirMensagemRespostaCriar("Preencha todos os campos!", Color.RED);
            labelRespostaCpf.setVisible(false);
        }else if(cpfExistente){
            exibirMensagemRespostaCpf("CPF já cadastrado!", Color.RED);
            labelRespostaCriar.setVisible(false);
        }else if (caixaCpf.getText().length() != 11){
            exibirMensagemRespostaCpf("CPF deve ter 11 dígitos!", Color.RED);
            labelRespostaCriar.setVisible(false);
        }else{
            Usuario novoUsuario = new Cliente(caixaNome.getText(), caixaCpf.getText(), caixaEmail.getText(), caixaSenha.getText(), new ArrayList<>());
            PetMatch.controlePetMatch.cadastrarUsuario(novoUsuario);
            labelRespostaCpf.setVisible(false);
            labelRespostaCriar.setVisible(false);
            exibirMensagemRespostaCriar("Usuário cadastrado com Sucesso!", Color.GREEN);
        }
    }

    private void exibirMensagemRespostaCriar(String mensagem, Color cor){
        labelRespostaCriar.setText(mensagem);
        labelRespostaCriar.setTextFill(cor);
        labelRespostaCriar.setVisible(true);
    }

    private void exibirMensagemRespostaCpf(String mensagem, Color cor) {
        labelRespostaCpf.setText(mensagem);
        labelRespostaCpf.setTextFill(cor);
        labelRespostaCpf.setVisible(true);
    }

    private void mudarParaTelaLogin() throws Exception {
        ControladorDeCena.trocarCena(TelaLoginController.FXML_PATH);
    }
}
