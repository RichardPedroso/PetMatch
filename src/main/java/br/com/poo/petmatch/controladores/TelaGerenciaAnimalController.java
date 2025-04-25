package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.controleCena.ControladorDeCena;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaGerenciaAnimalController {

    public static final String FXML_PATH = "tela-gerencia-animal.fxml";

    @FXML
    private Button botaoCadastrarAnimal;

    @FXML
    private Button botaoExcluirAnimal;

    @FXML
    private Button botaoVoltar;

    @FXML
    private void initialize(){
        botaoVoltar.setOnMouseClicked(event -> {
            try{
                voltarParaTelaAnterior();
        }catch (Exception e){
                e.printStackTrace();
            }
        });
        botaoCadastrarAnimal.setOnMouseClicked(event -> {
            try{
                mudarParaTelaCadastroAnimal();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        botaoExcluirAnimal.setOnMouseClicked(event -> {
            try {
                mudarParaTelaExclusaoAnimal();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
    
    private void mudarParaTelaCadastroAnimal() throws Exception{
        ControladorDeCena.trocarCena(TelaCadastroAnimalController.FXML_PATH);
    }

    private void mudarParaTelaExclusaoAnimal() throws Exception{
        ControladorDeCena.trocarCena(TelaAnimalExclusaoController.FXML_PATH);
    }

    private void voltarParaTelaAnterior() throws Exception{
        ControladorDeCena.trocarCena(TelaOpcoesAdministradorController.FXML_PATH);
    }
}
