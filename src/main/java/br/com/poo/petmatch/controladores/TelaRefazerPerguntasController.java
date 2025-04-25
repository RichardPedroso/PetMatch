package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.controleCena.ControladorDeCena;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaRefazerPerguntasController {

    public static final String FXML_PATH = "tela-refazer-perguntas.fxml";

    @FXML
    private Button botaoSim;

    @FXML
    private Button botaoNao;

    @FXML
    private void initialize(){
        botaoSim.setOnMouseClicked(event -> {
            try{
                mudarParaTelaPerguntas();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        botaoNao.setOnMouseClicked(event -> {
            try{
                mudarParaTelaInicial();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void mudarParaTelaPerguntas() throws Exception{
        ControladorDeCena.trocarCena(TelaPerguntasController.FXML_PATH);
    }

    public void mudarParaTelaInicial() throws Exception{
        ControladorDeCena.trocarCena(TelaInicialController.FXML_PATH);
    }
}
