package br.com.poo.petmatch.main;

import br.com.poo.petmatch.controlePetMatch.ControlePetMatch;
import br.com.poo.petmatch.usuario.Cliente;
import br.com.poo.petmatch.usuario.Usuario;
import br.com.poo.petmatch.controleCena.ControladorDeCena;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class PetMatch extends Application {

    public static final ControlePetMatch controlePetMatch = new ControlePetMatch();
    public static Cliente cliente = (Cliente) PetMatch.controlePetMatch.usuarioLogado;

    public static void setCliente(Usuario cliente) {
        Cliente usuarioLogado = (Cliente) cliente;
        PetMatch.cliente = usuarioLogado;
    }

    @Override
    public void start(Stage stage) throws IOException {
        ControladorDeCena.setSceneStage(stage);
        ControladorDeCena.trocarCena("tela-login.fxml");
        stage.show();
    }

    public static void main(String[] args) {

        controlePetMatch.instanciarUsuarios();
        controlePetMatch.instanciarAnimais();
        launch();
    }
}