package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.controleCena.ControladorDeCena;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaAnimalExclusaoController {

    public static final String FXML_PATH = "tela-exclusao-animal.fxml";

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoCachorro;

    @FXML
    private void initialize(){
        botaoVoltar.setOnMouseClicked(event -> {
            try{
                voltarParaTelaGerenciaAnimal();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        botaoCachorro.setOnMouseClicked(event -> {
            try {
                mudarParaTelaExclusaoCachorro();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void mudarParaTelaExclusaoCachorro() throws Exception{
        ControladorDeCena.trocarCena(TelaCachorroExclusaoController.FXML_PATH);
    }

    private void voltarParaTelaGerenciaAnimal() throws Exception{
        ControladorDeCena.trocarCena(TelaGerenciaAnimalController.FXML_PATH);
    }
}
