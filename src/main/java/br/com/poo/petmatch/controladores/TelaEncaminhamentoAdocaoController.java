package br.com.poo.petmatch.controladores;

import br.com.poo.petmatch.animal.Animal;
import br.com.poo.petmatch.controleCena.ControladorDeCena;
import br.com.poo.petmatch.main.PetMatch;
import br.com.poo.petmatch.usuario.Cliente;
import br.com.poo.petmatch.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class TelaEncaminhamentoAdocaoController {

    public static final String FXML_PATH = "tela-encaminhamento-adocao.fxml";

    private static Animal animalAdotado;

    @FXML
    private Button botaoVoltarTelaInicial;

    @FXML
    private ImageView imagemAnimalAdotado;

    @FXML
    private Text fraseNomeAnimal;

    @FXML
    private void initialize(){
        try{
            atualizarFraseNomeAnimal(fraseNomeAnimal, animalAdotado.getNome());
            setFotoAnimalAdotado();
        }catch (Exception e){
            e.printStackTrace();
        }
        botaoVoltarTelaInicial.setOnMouseClicked(event -> {
            try{
                voltarParaTelaInicial();
                PetMatch.cliente.animaisAdotadosCliente.add(animalAdotado);
                PetMatch.controlePetMatch.relatorioAnimaisAdotados.add(animalAdotado);
                PetMatch.controlePetMatch.excluirAnimalPorId(animalAdotado.getId());
                PetMatch.controlePetMatch.animaisCompativeis.clear();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public String pronomeAnimal(){
        String pronome;
        if(animalAdotado.getSexo().equals("Macho")){
            pronome = " do ";
        }else{
            pronome = " da ";
        }
        return pronome;
    }

    private void atualizarFraseNomeAnimal(Text fraseNomeAnimal, String nomeAnimal){
        fraseNomeAnimal.setText(fraseNomeAnimal.getText() + pronomeAnimal() + nomeAnimal);
    }

    public void setFotoAnimalAdotado() {
        if (animalAdotado.getEnderecoImagem() != null) {
            String caminhoImagem = "/br/com/poo/petmatch/imagens/Imagens-animais-adocao/";
            Image imagemCachorro = new Image(caminhoImagem + "Cachorros/" + animalAdotado.getEnderecoImagem());
            imagemAnimalAdotado.setImage(imagemCachorro);
        } else {
            imagemAnimalAdotado.setImage(null);
        }
    }

    public static void setAnimalAdotado(Animal animalAdocao) {
        animalAdotado = animalAdocao;
    }

    private void voltarParaTelaInicial() throws Exception{
        ControladorDeCena.trocarCena(TelaInicialController.FXML_PATH);
    }
}

