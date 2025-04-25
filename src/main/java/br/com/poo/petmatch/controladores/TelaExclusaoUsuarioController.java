package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.controleCena.ControladorDeCena;
import br.com.poo.petmatch.main.PetMatch;
import br.com.poo.petmatch.usuario.Administrador;
import br.com.poo.petmatch.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class TelaExclusaoUsuarioController {

    public static final String FXML_PATH = "tela-exclusao-usuario.fxml";

    private boolean usuarioEncontrado = false;

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoExcluir;

    @FXML
    private Button botaoEncontrar;

    @FXML
    private Label labelRespostaExcluir;

    @FXML
    private Label labelRespostaEncontrar;

    @FXML
    private TextField caixaCpfExclusao;

    @FXML
    private TextField caixaNomeUsuario;

    @FXML
    private TextField caixaCpfUsuario;

    @FXML
    private TextField caixaEmailUsuario;

    @FXML
    private TextField caixaTipoUsuario;

    @FXML
    private void initialize(){
        labelRespostaEncontrar.setVisible(false);
        labelRespostaExcluir.setVisible(false);
        botaoEncontrar.setOnMouseClicked(event -> {
            try {
                encontrarUsuario();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        botaoExcluir.setOnMouseClicked(event -> {
            try{
                checagemExclusaoUsuario();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        botaoVoltar.setOnMouseClicked(event -> {
            try{
                mudarParaTelaAnterior();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void limparDados(){
        caixaCpfUsuario.clear();
        caixaEmailUsuario.clear();
        caixaTipoUsuario.clear();
        caixaNomeUsuario.clear();
    }

    private void definirUsuarioExclusao(Usuario usuario){
        caixaNomeUsuario.setText(usuario.getNome());
        caixaCpfUsuario.setText(usuario.getCpf());
        caixaEmailUsuario.setText(usuario.getEmail());
        if (usuario instanceof Administrador){
            caixaTipoUsuario.setText("Administrador");
        }else{
            caixaTipoUsuario.setText("Cliente");
        }
    }

    public void encontrarUsuario(){
        for(Usuario usuario : PetMatch.controlePetMatch.usuarios){
            if(usuario.getCpf().equals(caixaCpfExclusao.getText())){
                definirUsuarioExclusao(usuario);
                usuarioEncontrado = true;
            }
        }
        if(!usuarioEncontrado){
            if(caixaCpfExclusao.getText().length() != 11){
                exibirRespostaEncontrar("O CPF deve ter 11 dígitos!", Color.RED);
            }else {
                exibirRespostaEncontrar("CPF inválido!", Color.RED);
            }
            labelRespostaExcluir.setVisible(false);
            limparDados();
        }else{
            labelRespostaEncontrar.setVisible(false);
            labelRespostaExcluir.setVisible(false);
        }
    }

    public void checagemExclusaoUsuario(){
        if(!usuarioEncontrado){
            exibirRespostaExcluir("Encontre um usuário primeiro!", Color.RED);
            labelRespostaEncontrar.setVisible(false);
            limparDados();
        }else{
            exibirRespostaExcluir("Encontre um usuário primeiro!", Color.RED);
            labelRespostaEncontrar.setVisible(false);
            limparDados();
            excluirUsuario();
            exibirRespostaExcluir("Usuário excluído com Sucesso!", Color.GREEN);
            usuarioEncontrado = false;
        }
    }

    public void excluirUsuario(){
        PetMatch.controlePetMatch.excluirUsuarioPorCpf(caixaCpfExclusao.getText());
    }

    private void exibirRespostaExcluir(String mensagem, Color cor){
        labelRespostaExcluir.setText(mensagem);
        labelRespostaExcluir.setTextFill(cor);
        labelRespostaExcluir.setVisible(true);
    }

    private void exibirRespostaEncontrar(String mensagem, Color cor){
        labelRespostaEncontrar.setText(mensagem);
        labelRespostaEncontrar.setTextFill(cor);
        labelRespostaEncontrar.setVisible(true);
    }

    public void mudarParaTelaAnterior() throws Exception{
        ControladorDeCena.trocarCena(TelaGerenciaUsuarioController.FXML_PATH);
    }
}
