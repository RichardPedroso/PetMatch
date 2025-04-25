package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.controleCena.ControladorDeCena;
import br.com.poo.petmatch.main.PetMatch;
import br.com.poo.petmatch.usuario.Administrador;
import br.com.poo.petmatch.usuario.Cliente;
import br.com.poo.petmatch.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TelaCadastroUsuarioController {

    public static final String FXML_PATH = "tela-cadastro-usuario.fxml";

    private boolean cpfExistente;

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoCadastrar;

    @FXML
    private CheckBox checkBoxAdministrador;

    @FXML
    private CheckBox checkBoxCliente;

    @FXML
    private TextField caixaNome;

    @FXML
    private TextField caixaCpf;

    @FXML
    private TextField caixaEmail;

    @FXML
    private TextField caixaSenha;

    @FXML
    private Label labelRespostaCadastro;

    @FXML
    private Label labelRespostaCpf;

    @FXML
    private void initialize(){
        labelRespostaCpf.setVisible(false);
        cpfExistente = false;
        botaoCadastrar.setOnMouseClicked(event -> {
            try {
                cadastrarUsuario();
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

    public void cadastrarUsuario(){
        if((!checkBoxAdministrador.isSelected() && !checkBoxCliente.isSelected()) || (checkBoxAdministrador.isSelected() && checkBoxCliente.isSelected())) {
            exibirMensagemRespostaCadastro("Selecione um tipo de usuário!", Color.RED);
            labelRespostaCpf.setVisible(false);
        }else if (caixaNome.getText().isEmpty() || caixaCpf.getText().isEmpty() || caixaEmail.getText().isEmpty() || caixaSenha.getText().isEmpty()){
            exibirMensagemRespostaCadastro("Preencha todos os campos!", Color.RED);
            labelRespostaCpf.setVisible(false);
        }else if(caixaCpf.getText().length() != 11){
            exibirMensagemRespostaCpf("CPF deve ter 11 dígitos", Color.PURPLE);
            labelRespostaCadastro.setVisible(false);
        }else if(cpfExistente == !checagemCpfExistente()){
            exibirMensagemRespostaCpf("CPF já cadastrado!", Color.PURPLE);
            labelRespostaCadastro.setVisible(false);
        }else if (checkBoxAdministrador.isSelected()) {
            Usuario usuarioAdministrador = new Administrador(caixaNome.getText(), caixaCpf.getText(), caixaEmail.getText(), caixaSenha.getText());
            PetMatch.controlePetMatch.cadastrarUsuario(usuarioAdministrador);
            labelRespostaCpf.setVisible(false);
            exibirMensagemRespostaCadastro("Usuário cadastrado com Sucesso!", Color.GREEN);
        } else if (checkBoxCliente.isSelected()) {
            Usuario usuarioCliente = new Cliente(caixaNome.getText(), caixaCpf.getText(), caixaEmail.getText(), caixaSenha.getText(), new ArrayList<>());
            PetMatch.controlePetMatch.cadastrarUsuario(usuarioCliente);
            labelRespostaCpf.setVisible(false);
            exibirMensagemRespostaCadastro("Usuário cadastrado com Sucesso!", Color.GREEN);
        }
    }

    public boolean checagemCpfExistente(){
        boolean cpfExiste = false;
        for(Usuario usuario : PetMatch.controlePetMatch.usuarios){
            if(caixaCpf.getText().equals(usuario.getCpf())){
                cpfExiste = true;
            }
        }
        return cpfExiste;
    }

    private void exibirMensagemRespostaCadastro(String mensagem, Color cor){
        labelRespostaCadastro.setText(mensagem);
        labelRespostaCadastro.setTextFill(cor);
        labelRespostaCadastro.setVisible(true);
    }

    private void exibirMensagemRespostaCpf(String mensagem, Color cor) {
        labelRespostaCpf.setText(mensagem);
        labelRespostaCpf.setTextFill(cor);
        labelRespostaCpf.setVisible(true);
    }

        public void mudarParaTelaAnterior() throws Exception{
        ControladorDeCena.trocarCena(TelaGerenciaUsuarioController.FXML_PATH);
    }
}
