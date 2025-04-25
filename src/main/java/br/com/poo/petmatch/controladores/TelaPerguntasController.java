package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.controleCena.ControladorDeCena;
import br.com.poo.petmatch.main.PetMatch;
import br.com.poo.petmatch.questionario.Questionario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class TelaPerguntasController{

    public static final String FXML_PATH = "tela-perguntas.fxml";

    @FXML
    private Button botaoPetMatch;

    @FXML
    private Label labelRespostaPetMatch;

    @FXML
    private TextField caixaPersonalidade;

    @FXML
    private TextField caixaMoradia;

    @FXML
    private TextField caixaTamanhoMoradia;

    @FXML
    private TextField caixaTemOutrosPets;

    @FXML
    private TextField caixaSexo;

    @FXML
    private TextField caixaAmbiente;

    @FXML
    private TextField caixaTipoPelagem;

    @FXML
    private void initialize() {
        labelRespostaPetMatch.setVisible(false);
        botaoPetMatch.setOnMouseClicked(event -> {
            try {
                labelRespostaPetMatch.setVisible(false);
                if(caixaPersonalidade.getText().isEmpty() || caixaMoradia.getText().isEmpty() || caixaTipoPelagem.getText().isEmpty() || caixaTamanhoMoradia.getText().isEmpty() || caixaTemOutrosPets.getText().isEmpty() || caixaSexo.getText().isEmpty() || caixaAmbiente.getText().isEmpty()){
                    exibirRespostaPetMatch("Preencha todos os campos!", Color.RED);
                }else {
                    String personalidade = caixaPersonalidade.getText().toLowerCase();
                    String moradia = caixaMoradia.getText().toLowerCase();
                    String tamanhoMoradia = caixaTamanhoMoradia.getText().toLowerCase();
                    String podeFicarComOutrosPets = caixaTemOutrosPets.getText().toLowerCase();
                    String sexo = caixaSexo.getText().toLowerCase();
                    String ambiente = caixaAmbiente.getText().toLowerCase();
                    String pelagem = caixaTipoPelagem.getText().toLowerCase();
                    Questionario questionario = new Questionario(personalidade, moradia, pelagem, tamanhoMoradia, podeFicarComOutrosPets, sexo, ambiente);
                    PetMatch.controlePetMatch.animaisCompativeis.clear();
                    PetMatch.controlePetMatch.adicionarAnimaisPorMatch(questionario);
                    mudarParaTelaAnimaisCompativeis();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void exibirRespostaPetMatch(String mensagem, Color cor){
        labelRespostaPetMatch.setText(mensagem);
        labelRespostaPetMatch.setTextFill(cor);
        labelRespostaPetMatch.setVisible(true);
    }

    private void mudarParaTelaAnimaisCompativeis() throws Exception{
        if(PetMatch.controlePetMatch.animaisCompativeis.isEmpty()){
            ControladorDeCena.trocarCena(TelaRefazerPerguntasController.FXML_PATH);
        }else{
            ControladorDeCena.trocarCena(TelaAnimaisCompativeisController.FXML_PATH);
        }
    }
}