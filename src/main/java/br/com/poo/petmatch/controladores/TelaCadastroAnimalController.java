package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.controleCena.ControladorDeCena;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaCadastroAnimalController {

    public static final String FXML_PATH = "tela-cadastro-animal.fxml";

    @FXML
    private Button botaoCachorro;

    @FXML
    private Button botaoVoltar;

    @FXML
    private void initialize(){
        botaoCachorro.setOnMouseClicked(event -> {
            try{
                mudarParaTelaCachorroCadastro();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        botaoVoltar.setOnMouseClicked(event -> {
            try {
                voltarParaTelaAnterior();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void mudarParaTelaCachorroCadastro() throws Exception{
        ControladorDeCena.trocarCena(TelaCachorroCadastroController.FXML_PATH);
    }

    private void voltarParaTelaAnterior() throws Exception{
        ControladorDeCena.trocarCena(TelaGerenciaAnimalController.FXML_PATH);
    }
}
