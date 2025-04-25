package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.controleCena.ControladorDeCena;
import br.com.poo.petmatch.main.PetMatch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class TelaInicialController {

    public static final String FXML_PATH = "tela-inicial.fxml";

    @FXML
    private Button botaoAvancar;

    @FXML
    private Button botaoSobrePetMatch;

    @FXML
    private ImageView iconeUsuario;

    @FXML
    private ImageView iconeSair;

    @FXML
    private ImageView iconeManual;

    @FXML
    private ImageView iconeMeusPets;

    @FXML
    private Label labelRespostaMeusPets;

    @FXML
    private void initialize(){
        labelRespostaMeusPets.setVisible(false);
        utilitariosUsuario(iconeUsuario, iconeSair, iconeMeusPets, botaoSobrePetMatch, iconeManual);
        botaoAvancar.setOnMouseClicked(event -> {
            try{
                mudarParaTelaPerguntas();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void utilitariosUsuario(ImageView iconeUsuario, ImageView iconeSair, ImageView iconeMeusPets, Button botaoSobrePetMatch, ImageView iconeManual){
        iconeUsuario.setOnMouseClicked(event ->{
            try {
                mudarParaTelaInformacoesUsuario();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        iconeSair.setOnMouseClicked(event -> {
            try{
                deslogarUsuario();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        iconeMeusPets.setOnMouseClicked(event ->{
            try{
                if(PetMatch.cliente.animaisAdotadosCliente.isEmpty()){
                    exibirMensagemRespostaMeusPets("Você não adotou \n nenhum Pet ainda!", Color.RED);
                }else {
                    mudarParaTelaMeusPets();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        botaoSobrePetMatch.setOnMouseClicked(event -> {
            try{
                ControladorDeCena.trocarCena(TelaUtilitariosSobrePetMatchController.FXML_PATH);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        iconeManual.setOnMouseClicked(event -> {
            try{
                mudarParaTelaUtilitariosManual();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void exibirMensagemRespostaMeusPets(String mensagem, Color cor){
        labelRespostaMeusPets.setText(mensagem);
        labelRespostaMeusPets.setTextFill(cor);
        labelRespostaMeusPets.setVisible(true);
    }

    private static void mudarParaTelaMeusPets() throws Exception{
        ControladorDeCena.trocarCena(TelaUtilitariosMeusPetsController.FXML_PATH);
    }

    private static void mudarParaTelaInformacoesUsuario() throws Exception{
        ControladorDeCena.trocarCena(TelaUtilitariosInformacoesUsuarioController.FXML_PATH);
    }

    private static void deslogarUsuario() throws Exception{
        ControladorDeCena.trocarCena(TelaLoginController.FXML_PATH);
    }

    private static void mudarParaTelaUtilitariosManual() throws Exception{
        ControladorDeCena.trocarCena(TelaUtilitariosManualController.FXML_PATH);
    }

    private static void mudarParaTelaPerguntas() throws Exception{
        ControladorDeCena.trocarCena(TelaPerguntasController.FXML_PATH);
        PetMatch.controlePetMatch.animaisCompativeis.clear();
    }
}
