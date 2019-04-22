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

    int randomizarIndex() {
        Random gerador = new Random();
        return gerador.nextInt(5);
    }
    
    int getTemperatura() {
        int temperatura[] = {-15, 0, 10, 20, 35};
        return temperatura[randomizarIndex()];
    }
    int getPressao() {
        int pressao[] = {410, 535, 700, 973, 1000};
        return pressao[randomizarIndex()];
    }
    int getAltitude() {
        int altitude[] = {771, 820, 1200, 1370, 1567};
        return altitude[randomizarIndex()];
    }
    int getUmidade() {
        int umidade[] = {50, 62, 69, 74, 81};
        return umidade[randomizarIndex()];
    }
    
    void simular() {
        String sms, temp, prs, alt, umd;
        
        temp = "Temperatura: " + getTemperatura() + "ºC";
        prs = "Pressao: " + getPressao() + " hPa";
        alt = "Altitude: " + getAltitude() + "m";
        umd = "Umidade: " + getUmidade() + "%";
        
        this.temperatura.setText(temp);
        this.pressao.setText(prs);
        this.altitude.setText(alt);
        this.umidade.setText(umd);
        
        sms = temp + " " + prs + " " + alt + " " + umd;
        enviaSms(sms);
               
    }
    
    void enviaSms(String str){
        String sms = "";
        System.out.println("----- Inicio Conteudo SMS:");
        //Set SMS format to ASCII
        sms += "AT+CMGF=1\r\n";
        //Send new SMS command and message number
        sms += "AT+CMGS=\"07194XXXXX\"\r\n";
        //Send SMS content
        sms += str;
        //Send Ctrl+Z / ESC to denote SMS message is complete
        sms += (char)26;        
        /*
        agora sms contem todo o conteudo necessario para enviar 1 mensagem
        e a mensagem pode ser enviada
        */
        System.out.println(sms+"\n----- Fim conteudo SMS.\n");
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
