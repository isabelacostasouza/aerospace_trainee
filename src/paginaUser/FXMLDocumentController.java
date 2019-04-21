package paginaUser;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable{

    @FXML
    private Label nomeUser;

    @FXML
    private Button btnSimular;

    @FXML
    private Button btnCodificar;

    @FXML
    void alterarLogin(MouseEvent event) {
       Parent root;
       try {
           root = FXMLLoader.load(getClass().getResource("/paginaAlterarLogin/FXMLDocument.fxml"));
           Scene scene = new Scene(root);
           Stage stage = new Stage();
           Image icone = new Image(getClass().getResourceAsStream("/imagens/icon.png"));
           stage.getIcons().add(icone);
           stage.setTitle("Alterar login");
           stage.setScene(scene);
           stage.setOnCloseRequest( e -> {
                Parent root2;
                try {
                     root2 = FXMLLoader.load(getClass().getResource("/paginaUser/FXMLDocument.fxml"));
                     Scene scene2 = new Scene(root2);
                     Stage stage2 = new Stage();
                     stage2.setResizable(false);
                     Image icone2 = new Image(getClass().getResourceAsStream("/imagens/icon.png"));
                     stage2.getIcons().add(icone2);
                     stage2.setTitle("Login");
                     stage2.setScene(scene2);
                     stage2.setOnCloseRequest( ex -> {
                        Parent root3;
                        try {
                             root3 = FXMLLoader.load(getClass().getResource("/paginaMenu/FXMLDocument.fxml"));
                             Scene scene3 = new Scene(root2);
                             Stage stage3 = new Stage();
                             stage3.setResizable(false);
                             Image icone3 = new Image(getClass().getResourceAsStream("/imagens/icon.png"));
                             stage2.getIcons().add(icone2);
                             stage2.setTitle("CubeSat Simulator");
                             stage2.setScene(scene2);
                             stage2.show();
                        } catch (IOException exe) {
                             Logger.getLogger(paginaMenu.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   });
                     stage2.show();
                } catch (IOException ex) {
                     Logger.getLogger(paginaMenu.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
           });
           stage.show();
           Stage stage3 = (Stage) btnSimular.getScene().getWindow();
           stage3.close();
       } catch (IOException ex) {
           Logger.getLogger(paginaMenu.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    void sair(MouseEvent event) {
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
           Stage stage3 = (Stage) btnSimular.getScene().getWindow();
           stage3.close();
       } catch (IOException ex) {
           Logger.getLogger(paginaMenu.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    @FXML
    void simular(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/paginaSimulador/FXMLDocument.fxml"));
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
                     root2 = FXMLLoader.load(getClass().getResource("/paginaUser/FXMLDocument.fxml"));
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
    
    @FXML
    void codificarImagem(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/paginaCodificarImagem/FXMLDocument.fxml"));
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
                     root2 = FXMLLoader.load(getClass().getResource("/paginaUser/FXMLDocument.fxml"));
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
        String nomeUser = "";
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

                nomeUser = linhas.get(0);
                
                readerf.close();
            } catch (IOException ex) {
                Logger.getLogger(arquivos.CarregarArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(arquivos.CarregarArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       this.nomeUser.setText("Bem vindo(a) de volta, " + nomeUser);
    }

}
