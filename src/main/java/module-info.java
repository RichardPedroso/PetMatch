module br.com.poo.petmatch {


    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens br.com.poo.petmatch.telas to javafx.fxml;
    opens br.com.poo.petmatch.controladores to javafx.fxml;


    exports br.com.poo.petmatch.controladores;
    exports br.com.poo.petmatch.main;
    exports br.com.poo.petmatch.controlePetMatch;
}