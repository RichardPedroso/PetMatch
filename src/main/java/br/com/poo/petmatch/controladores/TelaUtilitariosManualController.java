package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.controleCena.ControladorDeCena;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaUtilitariosManualController {

    public static final String FXML_PATH = "tela-utilitarios-manual.fxml";

    @FXML
    private Button botaoVoltar;

    @FXML
    private void initialize(){
        botaoVoltar.setOnMouseClicked(event -> {
            try{
                voltarParaTelaInicial();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void voltarParaTelaInicial() throws Exception{
        ControladorDeCena.trocarCena(TelaInicialController.FXML_PATH);
    }
}
