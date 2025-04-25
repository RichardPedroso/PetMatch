package br.com.poo.petmatch.controleCena;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorDeCena {
    private static Stage stage;

    public static void setSceneStage(Stage stage){
        ControladorDeCena.stage = stage;
    }

    public static void trocarCena(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(ControladorDeCena.class.getResource("/br/com/poo/petmatch/telas/" + fxmlPath));
        Parent root = loader.load();
        stage.setTitle("PetMatch");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        //stage.setFullScreen(true);
        stage.setMaximized(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("Ctrl+Alt+Esc"));
        stage.setFullScreenExitHint("");
    }
}
