package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.controleCena.ControladorDeCena;
import br.com.poo.petmatch.main.PetMatch;
import br.com.poo.petmatch.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class TelaUtilitariosInformacoesUsuarioController {

    public static final String FXML_PATH = "tela-utilitarios-informacoes-usuario.fxml";

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoSalvarAlteracoes;

    @FXML
    private Button botaoExclusaoConta;

    @FXML
    private Label labelRespostaSalvar;

    @FXML
    private Label labelRespostaExcluir;

    @FXML
    private TextField caixaNome;

    @FXML
    private TextField caixaCpf;

    @FXML
    private TextField caixaEmail;

    @FXML
    private TextField caixaSenha;

    @FXML
    private void initialize() {
        labelRespostaExcluir.setVisible(false);
        labelRespostaSalvar.setVisible(false);
        definirUsuario(PetMatch.controlePetMatch.usuarioLogado);
        botaoSalvarAlteracoes.setOnMouseClicked(event -> {
            try {
                salvarAlteracoes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        botaoVoltar.setOnMouseClicked(event -> {
            try {
                ControladorDeCena.trocarCena(TelaInicialController.FXML_PATH);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        botaoExclusaoConta.setOnMouseClicked(event -> {
            try {
                excluirUsuario();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void salvarAlteracoes(){
        if (caixaNome.getText().isEmpty() || caixaCpf.getText().isEmpty() || caixaEmail.getText().isEmpty() || caixaSenha.getText().isEmpty()) {
            exibirMensagemRespostaSalvar("Não deixe campos em branco!", Color.RED);
        } else {
            alterarDadosUsuario(PetMatch.controlePetMatch.usuarioLogado);
            exibirMensagemRespostaSalvar("Alterações salvas com Sucesso!", Color.GREEN);
        }
    }

    private void excluirUsuario(){
        if(caixaNome.getText().isEmpty() || caixaCpf.getText().isEmpty() || caixaEmail.getText().isEmpty() || caixaSenha.getText().isEmpty()){
            exibirMensagemRespostaExcluir("Preencha o usuário primeiro!", Color.RED);
        }else {
            PetMatch.controlePetMatch.excluirUsuarioPorCpf(caixaCpf.getText());
            try {
                ControladorDeCena.trocarCena(TelaLoginController.FXML_PATH);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private void alterarDadosUsuario(Usuario usuario){
        usuario.setNome(caixaNome.getText());
        usuario.setCpf(caixaCpf.getText());
        usuario.setEmail(caixaEmail.getText());
        usuario.setSenha(caixaSenha.getText());
    }

    private void definirUsuario(Usuario usuario){
        caixaNome.setText(usuario.getNome());
        caixaCpf.setText(usuario.getCpf());
        caixaEmail.setText(usuario.getEmail());
        caixaSenha.setText(usuario.getSenha());
    }

    public void exibirMensagemRespostaSalvar(String mensagem, Color cor) {
        labelRespostaSalvar.setText(mensagem);
        labelRespostaSalvar.setTextFill(cor);
        labelRespostaSalvar.setVisible(true);
    }

    public void exibirMensagemRespostaExcluir(String mensagem, Color cor) {
        labelRespostaExcluir.setText(mensagem);
        labelRespostaExcluir.setTextFill(cor);
        labelRespostaExcluir.setVisible(true);
    }
}
