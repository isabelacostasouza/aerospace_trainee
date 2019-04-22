package paginaSimulador;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btnSimular;

    @FXML
    private Label temperatura;

    @FXML
    private Label pressao;

    @FXML
    private Label altitude;

    @FXML
    private Label umidade;

    void simular() {
        String sms, temp, prs, alt, umd;
        
        temp = "Temperatura: " + BME.getTemperatura() + "ºC";
        prs = "Pressao: " + BME.getPressao() + " hPa";
        alt = "Altitude: " + BME.getAltitude() + "m";
        umd = "Umidade: " + BME.getUmidade() + "%";
        
        this.temperatura.setText(temp);
        this.pressao.setText(prs);
        this.altitude.setText(alt);
        this.umidade.setText(umd);
        
        sms = temp + " " + prs + " " + alt + " " + umd;
        SMS.enviar(sms);
               
    }
    
    
    @FXML
    void simular(ActionEvent event) {
        simular();
    }
   
    @FXML
    void voltar(MouseEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/paginaUser/FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            Image icone = new Image(getClass().getResourceAsStream("/imagens/icon.png"));
            stage.getIcons().add(icone);
            stage.setTitle("CubeSat Simulator");
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest( e -> {
                Parent root2;
                try {
                     root2 = FXMLLoader.load(getClass().getResource("/paginaMenu/FXMLDocument.fxml"));
                     Scene scene2 = new Scene(root2);
                     Stage stage2 = new Stage();
                     stage2.setResizable(false);
                     Image icone2 = new Image(getClass().getResourceAsStream("/imagens/icon.png"));
                     stage2.getIcons().add(icone2);
                     stage2.setTitle("CubeSat Simulator");
                     stage2.setScene(scene2);
                     stage2.show();
                } catch (IOException ex) {
                     Logger.getLogger(paginaMenu.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
           });
            Stage stage3 = (Stage) btnSimular.getScene().getWindow();
            stage3.close();
        } catch (IOException ex) {
            Logger.getLogger(paginaMenu.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        simular();
    }
}
