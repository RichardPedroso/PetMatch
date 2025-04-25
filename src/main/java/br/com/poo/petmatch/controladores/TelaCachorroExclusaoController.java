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
import javafx.scene.paint.Color;

public class TelaCachorroExclusaoController {

    public static final String FXML_PATH = "tela-cachorro-exclusao.fxml";

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoExcluir;

    @FXML
    private Button botaoEncontrar;

    @FXML
    private TextField caixaIdExclusao;

    @FXML
    private TextField caixaNomeCachorro;

    @FXML
    private TextField caixaIdadeCachorro;

    @FXML
    private TextField caixaRacaCachorro;

    @FXML
    private TextField caixaSexoCachorro;

    @FXML
    private TextField caixaVacinadoCachorro;

    @FXML
    private TextField caixaPorteCachorro;

    @FXML
    private TextField caixaPelagemCachorro;

    @FXML
    private TextField caixaObservacoesCachorro;

    @FXML
    private ImageView imagemCachorro;

    @FXML
    private Label labelRespostaId;

    @FXML
    private Label labelRespostaExclusao;

    private boolean cachorroEncontrado;

    private String idAnimal;

    @FXML
    private void initialize(){
        labelRespostaId.setVisible(false);
        labelRespostaExclusao.setVisible(false);
        botaoEncontrar.setOnMouseClicked(event -> {
            try{
                encontrarAnimal();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        botaoExcluir.setOnMouseClicked(event -> {
            try{
                excluirAnimal();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        botaoVoltar.setOnMouseClicked(event -> {
            try{
                voltarParaTelaAnterior();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void limparDadosCachorro(){
        caixaNomeCachorro.clear();
        caixaIdadeCachorro.clear();
        caixaRacaCachorro.clear();
        caixaSexoCachorro.clear();
        caixaVacinadoCachorro.clear();
        caixaPorteCachorro.clear();
        caixaPelagemCachorro.clear();
        caixaObservacoesCachorro.clear();
        imagemCachorro.setImage(null);
    }

    public void encontrarAnimal(){
        cachorroEncontrado = false;
        labelRespostaExclusao.setVisible(false);
        for(Animal animal : PetMatch.controlePetMatch.animais){
            if(animal instanceof Cachorro cachorro){
                if(Integer.toString(animal.getId()).equals(caixaIdExclusao.getText())){
                    idAnimal = Integer.toString(animal.getId());
                    definirCachorroExclusao(cachorro);
                    cachorroEncontrado = true;
                }
            }
        }
        if(!cachorroEncontrado){
            exibirRespostaId("ID inválido!", Color.RED);
            limparDadosCachorro();
        }else{
            labelRespostaId.setVisible(false);
            labelRespostaExclusao.setVisible(false);
        }
    }

    public void excluirAnimal(){
        if(cachorroEncontrado && idAnimal.equals(caixaIdExclusao.getText())){
            PetMatch.controlePetMatch.excluirAnimalPorId(Integer.parseInt(caixaIdExclusao.getText()));
            cachorroEncontrado = false;
            labelRespostaId.setVisible(false);
            exibirRespostaEncontreExclusao("Cachorro excluído com Sucesso!", Color.GREEN);
            limparDadosCachorro();
        }else{
            exibirRespostaEncontreExclusao("Encontre um cachorro primeiro!", Color.RED);
            limparDadosCachorro();
        }
    }

    private void definirCachorroExclusao(Cachorro cachorroAtual){
        String caminhoImagem = "/br/com/poo/petmatch/imagens/Imagens-animais-adocao/";
        caixaNomeCachorro.setText(cachorroAtual.getNome());
        caixaIdadeCachorro.setText(cachorroAtual.getIdade());
        caixaRacaCachorro.setText(cachorroAtual.getRaca());
        caixaSexoCachorro.setText(cachorroAtual.getSexo());
        caixaVacinadoCachorro.setText(cachorroAtual.getVacinado());
        caixaPorteCachorro.setText(cachorroAtual.getPorte());
        caixaPelagemCachorro.setText(cachorroAtual.getPelagem());
        caixaObservacoesCachorro.setText(cachorroAtual.getObservacoes());

        if (cachorroAtual.getEnderecoImagem() != null) {
            Image imagemCachorroAtual = new Image(caminhoImagem + "Cachorros/" + cachorroAtual.getEnderecoImagem());
            imagemCachorro.setImage(imagemCachorroAtual);
            imagemCachorro.setPreserveRatio(false);
        } else {
            imagemCachorro.setImage(null);
        }
    }

    private void exibirRespostaId(String mensagem, Color cor){
        labelRespostaId.setText(mensagem);
        labelRespostaId.setTextFill(cor);
        labelRespostaId.setVisible(true);
    }

    private void exibirRespostaEncontreExclusao(String mensagem, Color cor){
        labelRespostaExclusao.setText(mensagem);
        labelRespostaExclusao.setTextFill(cor);
        labelRespostaExclusao.setVisible(true);
    }

    public void voltarParaTelaAnterior() throws Exception{
        ControladorDeCena.trocarCena(TelaGerenciaAnimalController.FXML_PATH);
    }
}
