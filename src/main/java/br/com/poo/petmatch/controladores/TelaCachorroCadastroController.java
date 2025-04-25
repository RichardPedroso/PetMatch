package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.animal.Animal;
import br.com.poo.petmatch.animal.Cachorro;
import br.com.poo.petmatch.controleCena.ControladorDeCena;
import br.com.poo.petmatch.main.PetMatch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class TelaCachorroCadastroController {

    public static final String FXML_PATH = "tela-cachorro-cadastro.fxml";

    @FXML
    private Button botaoCadastrar;

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoAdicionarImagemCachorro;

    @FXML
    private CheckBox checkBoxAnimalFicticio;

    @FXML
    private ImageView imagemAnimal;

    @FXML
    private Label labelRespostaCadastroCachorro;

    @FXML
    private TextField caixaNome;

    @FXML
    private TextField caixaIdade;

    @FXML
    private TextField caixaRaca;

    @FXML
    private TextField caixaPorte;

    @FXML
    private TextField caixaVacinado;

    @FXML
    private TextField caixaObservacoes;

    @FXML
    private TextField caixaPelagem;

    @FXML
    private TextField caixaPersonalidadeAnimal;

    @FXML
    private TextField caixaTipoMoradia;

    @FXML
    private TextField caixaTamanhoMoradia;

    @FXML
    private TextField caixaTipoAmbiente;

    @FXML
    private TextField caixaPodeFicarComPets;

    @FXML
    private TextField caixaSexo;

    public String enderecoImagemAnimal;

    @FXML
    private void initialize(){
        labelRespostaCadastroCachorro.setVisible(false);
        botaoAdicionarImagemCachorro.setOnMouseClicked(event -> {
            try{
                carregarImagemAnimal();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        botaoVoltar.setOnMouseClicked(event-> {
            try{
                voltarParaTelaAnterior();
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        botaoCadastrar.setOnMouseClicked(event -> {
            cadastroAnimal();
        });
    }

    public void limparDadosCachorro(){
        caixaNome.clear();
        caixaIdade.clear();
        caixaRaca.clear();
        caixaSexo.clear();
        caixaVacinado.clear();
        caixaPorte.clear();
        caixaPelagem.clear();
        caixaObservacoes.clear();
        caixaPersonalidadeAnimal.clear();
        caixaTipoMoradia.clear();
        caixaTamanhoMoradia.clear();
        caixaTipoAmbiente.clear();
        caixaPodeFicarComPets.clear();
        imagemAnimal.setImage(null);
    }

    public void carregarImagemAnimal(){
        FileChooser seletorImagem = new FileChooser();
        seletorImagem.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.png", "*.jpg", "*.jpeg"));
        File imagem = seletorImagem.showOpenDialog(new Stage());
        if(imagem != null){
            enderecoImagemAnimal = imagem.getAbsolutePath();
            Image imagemCachorro = new Image(imagem.toURI().toString());
            imagemAnimal.setImage(imagemCachorro);
            imagemAnimal.setPreserveRatio(false);
        }
    }

    public void cadastroAnimal(){

        String nomeAnimal = caixaNome.getText();
        String pelagem = caixaPelagem.getText();
        String idade = caixaIdade.getText();
        String raca = caixaRaca.getText();
        String vacinado = caixaVacinado.getText();
        String porte = caixaPorte.getText();
        String observacoes = caixaObservacoes.getText();
        String personalidade = caixaPersonalidadeAnimal.getText();
        String tipoMoradia = caixaTipoMoradia.getText();
        String tamanhoMoradia = caixaTamanhoMoradia.getText();
        String podeFicarComOutrosPets = caixaPodeFicarComPets.getText();
        String sexo = caixaSexo.getText();
        String tipoAmbiente = caixaTipoAmbiente.getText();

        if(nomeAnimal.isEmpty() || idade.isEmpty() || raca.isEmpty() || porte.isEmpty() || vacinado.isEmpty() || personalidade.isEmpty() || tipoAmbiente.isEmpty() || tamanhoMoradia.isEmpty() || podeFicarComOutrosPets.isEmpty() || tipoMoradia.isEmpty() || pelagem.isEmpty() || enderecoImagemAnimal == null || sexo.isEmpty()) {
            exibirRespostaCadastroCachorro("Preencha todos os campos!", Color.RED);
        }else if(checkBoxAnimalFicticio.isSelected()){
            Animal cachorro = new Cachorro(Animal.nextId + 1, nomeAnimal, idade, raca, porte, vacinado, observacoes, personalidade, tipoMoradia, tamanhoMoradia, podeFicarComOutrosPets, tipoAmbiente, enderecoImagemAnimal, sexo, true, pelagem);
            cachorro.setEnderecoImagem(enderecoImagemAnimal);
            PetMatch.controlePetMatch.cadastrarAnimal(cachorro);
            exibirRespostaCadastroCachorro("Cachorro fictício cadastrado com Sucesso!", Color.GREEN);
            limparDadosCachorro();
        }else{
            Animal cachorro = new Cachorro(Animal.nextId + 1, nomeAnimal, idade, raca, porte, vacinado, observacoes, personalidade, tipoMoradia, tamanhoMoradia, podeFicarComOutrosPets, tipoAmbiente, enderecoImagemAnimal, sexo, false, pelagem);
            cachorro.setEnderecoImagem(enderecoImagemAnimal);
            PetMatch.controlePetMatch.cadastrarAnimal(cachorro);
            exibirRespostaCadastroCachorro("Cachorro cadastrado com Sucesso!", Color.GREEN);
            limparDadosCachorro();
        }
    }

    public void exibirRespostaCadastroCachorro(String mensagem, Color cor) {;
        labelRespostaCadastroCachorro.setText(mensagem);
        labelRespostaCadastroCachorro.setTextFill(cor);
        labelRespostaCadastroCachorro.setVisible(true);
    }

    public void voltarParaTelaAnterior () throws Exception {
        ControladorDeCena.trocarCena(TelaGerenciaAnimalController.FXML_PATH);
    }
}
