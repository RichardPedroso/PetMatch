package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.controleCena.ControladorDeCena;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaGerenciaUsuarioController {

    public static final String FXML_PATH = "tela-gerencia-usuario.fxml";

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoCadastrarUsuario;

    @FXML
    private Button botaoExcluirUsuario;

    @FXML
    private void initialize(){
        botaoCadastrarUsuario.setOnMouseClicked(event -> {
            try {
                mudarParaTelaCadastroUsuario();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        botaoExcluirUsuario.setOnMouseClicked(event -> {
            try {
                mudarParaTelaExclusaoUsuario();
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

    public void mudarParaTelaCadastroUsuario() throws Exception{
        ControladorDeCena.trocarCena(TelaCadastroUsuarioController.FXML_PATH);
    }

    public void mudarParaTelaExclusaoUsuario() throws Exception{
        ControladorDeCena.trocarCena(TelaExclusaoUsuarioController.FXML_PATH);
    }

    public void mudarParaTelaAnterior() throws Exception{
        ControladorDeCena.trocarCena(TelaOpcoesAdministradorController.FXML_PATH);
    }
}
