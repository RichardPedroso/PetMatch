package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.animal.Animal;
import br.com.poo.petmatch.animal.Cachorro;
import br.com.poo.petmatch.controleCena.ControladorDeCena;
import br.com.poo.petmatch.main.PetMatch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TelaAnimaisCompativeisController {

    public static final String FXML_PATH = "tela-animais-compativeis.fxml";

    String caminhoImagem = "/br/com/poo/petmatch/imagens/Imagens-animais-adocao/";

    private Animal animalAtual;

    private int indiceAtual = 0;

    @FXML
    private Button botaoAdotar;

    @FXML
    private Button botaoProximo;

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoVoltarTela;

    @FXML
    private ImageView imagemAnimal;

    @FXML
    private Label labelAnimalFicticio;

    @FXML
    private Label labelRespostaVoltar;

    @FXML
    private Label labelRespostaProximo;

    @FXML
    private TextField caixaNome;

    @FXML
    private TextField caixaRaca;

    @FXML
    private TextField caixaPorte;

    @FXML
    private TextField caixaIdade;

    @FXML
    private TextField caixaVacinado;

    @FXML
    private TextField caixaSexo;

    @FXML
    private TextField caixaObservacoes;

    @FXML
    private void initialize() {
        labelRespostaProximo.setVisible(false);
        labelRespostaVoltar.setVisible(false);
        labelAnimalFicticio.setVisible(true);
        mostrarAnimal();
        botaoAdotar.setOnMouseClicked(event -> {
            try {
                mudarParaTelaAdocao();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        botaoProximo.setOnMouseClicked(event -> {
            proximoAnimal();
        });
        botaoVoltar.setOnMouseClicked(event -> {
            animalAnterior();
        });
        botaoVoltarTela.setOnMouseClicked(event -> {
            try{
                voltarParaTelaInicial();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void mostrarAnimal(){
        animalAtual = PetMatch.controlePetMatch.animaisCompativeis.get(indiceAtual);
        atualizarAnimal(animalAtual);
    }

    private void atualizarAnimal(Animal animalAtual){
        definirAnimal();
        if(animalAtual instanceof Cachorro cachorro) {
            if (cachorro.getEnderecoImagem() != null) {
                Image imagemCachorroAtual = new Image(caminhoImagem + "Cachorros/" + animalAtual.getEnderecoImagem());
                imagemAnimal.setImage(imagemCachorroAtual);
                imagemAnimal.setPreserveRatio(false);
            } else {
                imagemAnimal.setImage(null);
            }
            if(animalAtual.isAnimalFicticio()){
                exibirRespostaAnimalFicticio("Animal Fictício", Color.RED);
            }else {
                exibirRespostaAnimalFicticio("Animal Real", Color.RED);
            }
        }
    }

    private void definirAnimal(){
        if(animalAtual.getSexo().toLowerCase().contains("fêmea | femea")){
            caixaSexo.setText("Fêmea");
        }else {
            caixaSexo.setText(animalAtual.getSexo());
        }
        caixaNome.setText(animalAtual.getNome());
        caixaRaca.setText(animalAtual.getRaca());
        caixaPorte.setText(animalAtual.getPorte());
        caixaIdade.setText(animalAtual.getIdade());
        caixaVacinado.setText(animalAtual.getVacinado());
        caixaObservacoes.setText(animalAtual.getObservacoes());
    }

    private void proximoAnimal(){
        if(indiceAtual < PetMatch.controlePetMatch.animaisCompativeis.size()-1) {
            indiceAtual++;
            if (indiceAtual < PetMatch.controlePetMatch.animaisCompativeis.size()) {
                animalAtual = PetMatch.controlePetMatch.animaisCompativeis.get(indiceAtual);
                atualizarAnimal(animalAtual);
            }
        }else{
            exibirRespostaProximo("Não há animais para próximo!", Color.RED);
        }
        labelRespostaVoltar.setVisible(false);
    }

    private void animalAnterior(){
        if (indiceAtual != 0) {
            indiceAtual--;
            if (indiceAtual < PetMatch.controlePetMatch.animaisCompativeis.size()) {
                animalAtual = PetMatch.controlePetMatch.animaisCompativeis.get(indiceAtual);
                atualizarAnimal(animalAtual);
            }
        }else{
            exibirRespostaVoltar("Não há animais para voltar!", Color.RED);
        }
        labelRespostaProximo.setVisible(false);
    }

    private void exibirRespostaProximo(String mensagem, Color cor){
        labelRespostaProximo.setText(mensagem);
        labelRespostaProximo.setTextFill(cor);
        labelRespostaProximo.setVisible(true);
    }

    private void exibirRespostaVoltar(String mensagem, Color cor){
        labelRespostaVoltar.setText(mensagem);
        labelRespostaVoltar.setTextFill(cor);
        labelRespostaVoltar.setVisible(true);
    }

    private void exibirRespostaAnimalFicticio(String mensagem, Color cor){
        labelAnimalFicticio.setText(mensagem);
        labelAnimalFicticio.setTextFill(cor);
        labelAnimalFicticio.setVisible(true);
    }

    private void mudarParaTelaAdocao() throws Exception {
        if (animalAtual.isAnimalFicticio()) {
            TelaAdocaoAnimalFicticioController.setAnimalAdotado(animalAtual);
            ControladorDeCena.trocarCena(TelaAdocaoAnimalFicticioController.FXML_PATH);
        } else {
            TelaEncaminhamentoAdocaoController.setAnimalAdotado(animalAtual);
            ControladorDeCena.trocarCena(TelaEncaminhamentoAdocaoController.FXML_PATH);
        }
    }

    private void voltarParaTelaInicial() throws Exception{
        ControladorDeCena.trocarCena(TelaInicialController.FXML_PATH);
    }
}
