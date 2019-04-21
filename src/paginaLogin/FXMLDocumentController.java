package paginaLogin;

import arquivos.CarregarArquivo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;
    
    private String masterUser;
    private String masterPassword;
    
    @FXML
    void enviar(ActionEvent event) {
        enviar();
    }

    @FXML
    void voltar(MouseEvent event) {
       Parent root;
       try {
           root = FXMLLoader.load(getClass().getResource("/paginaMenu/FXMLDocument.fxml"));
           Scene scene = new Scene(root);
           Stage stage = new Stage();
           Image icone = new Image(getClass().getResourceAsStream("/imagens/icon.png"));
           stage.getIcons().add(icone);
           stage.setTitle("CubeSat Simulator");
           stage.setScene(scene);
           stage.show();
           
           Stage stage3 = (Stage) user.getScene().getWindow();
           stage3.close();
       } catch (IOException ex) {
           Logger.getLogger(paginaMenu.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public void enviar() {
        carregarMaster();
        if(this.masterUser.equals(this.user.getText()) && this.masterPassword.equals(this.password.getText())) {
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
                Stage stage3 = (Stage) user.getScene().getWindow();
                stage3.close();
            } catch (IOException ex) {
                Logger.getLogger(paginaMenu.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Dados incorretos!");
        }
    }
    
    public void carregarMaster() {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String caminhoArquivo = s+"\\masterLogin.txt";

        FileReader f;
        try {
            f = new FileReader(caminhoArquivo);
            BufferedReader readerf = new BufferedReader(f);
            String linha;
            try {
                linha = readerf.readLine();
                ArrayList<String> linhas = new ArrayList();
                while (linha != null) {
                linhas.add(linha);
                linha = readerf.readLine();
                }    

                this.masterUser = linhas.get(0);
                this.masterPassword = linhas.get(1);
                
                readerf.close();
            } catch (IOException ex) {
                Logger.getLogger(CarregarArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CarregarArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    int contador = 0;
    
    @FXML
    void conferirEnter(KeyEvent event) {
       if (event.getCode() == KeyCode.ENTER){
           if(contador > 0)
                enviar();
           contador++;
         }
    }
    
    @FXML
    void conferirEnter2(KeyEvent event) {
       if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB){
            password.requestFocus();
         }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
