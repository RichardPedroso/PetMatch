package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.controleCena.ControladorDeCena;
import br.com.poo.petmatch.main.PetMatch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class TelaOpcoesAdministradorController {

    public static final String FXML_PATH = "tela-opcoes-administrador.fxml";

    @FXML
    private Button botaoAnimais;

    @FXML
    private Button botaoUsuarios;

    @FXML
    private Button botaoVoltarParaTelaLogin;

    @FXML
    private Button botaoRelatorioAnimaisAdotados;

    @FXML
    private Label labelRespostaRelatorio;
    
    
    @FXML
    private void initialize(){
        labelRespostaRelatorio.setVisible(false);
        botaoAnimais.setOnMouseClicked(event -> {
            try{
                mudarParaTelaGerenciaAnimais();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        botaoUsuarios.setOnMouseClicked(event -> {
            try{
                mudarParaTelaGerenciaUsuarios();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        botaoVoltarParaTelaLogin.setOnMouseClicked(event -> {
            try{
                voltarParaTelaLogin();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        botaoRelatorioAnimaisAdotados.setOnMouseClicked(event -> {
            try{
                mudarParaTelaRelatorioAnimaisAdotados();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void exibirRespostaRelatorio(String mensagem, Color cor){
        labelRespostaRelatorio.setText(mensagem);
        labelRespostaRelatorio.setTextFill(cor);
        labelRespostaRelatorio.setVisible(true);
    }
    
    private void voltarParaTelaLogin() throws Exception{
        ControladorDeCena.trocarCena(TelaLoginController.FXML_PATH);
    }

    private void mudarParaTelaGerenciaAnimais() throws Exception {
        ControladorDeCena.trocarCena(TelaGerenciaAnimalController.FXML_PATH);
    }
    private void mudarParaTelaGerenciaUsuarios() throws Exception {
        ControladorDeCena.trocarCena(TelaGerenciaUsuarioController.FXML_PATH);
    }

    private void mudarParaTelaRelatorioAnimaisAdotados() throws Exception {
        if(PetMatch.controlePetMatch.relatorioAnimaisAdotados.isEmpty()){
            exibirRespostaRelatorio("Nenhum animal foi adotado!", Color.RED);
        }else {
            ControladorDeCena.trocarCena(TelaRelatorioAnimaisAdotadosController.FXML_PATH);
        }
    }
}
