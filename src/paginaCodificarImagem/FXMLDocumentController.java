package paginaCodificarImagem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btnNovaImagem;

    @FXML
    private ImageView imageToCodify;

    @FXML
    private Label textCodified;

    int contador;
    
    void setImage(String url) {
        Image newImage = new Image(getClass().getResourceAsStream(url));
        imageToCodify.setImage(newImage);
    }
    
     
    String codificar(String imagePath) {
        String base64Image = "";
        File file = new File(imagePath);
        
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        
        return base64Image;
    }
    
    @FXML
    void codificar(ActionEvent event) {
        if(contador == 5) 
            contador = 0;
        
        String url[] = {"/imagens/imagem_1.jpg", "/imagens/imagem_2.jpg", "/imagens/imagem_3.jpg", "/imagens/imagem_4.jpg","/imagens/imagem_5.jpg"};
        setImage(url[contador]);
        
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String text = codificar(s+"/src"+url[contador]);
        this.textCodified.setText(text);
        
        contador++;
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
            Stage stage3 = (Stage) btnNovaImagem.getScene().getWindow();
            stage3.close();
        } catch (IOException ex) {
            Logger.getLogger(paginaMenu.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImage("/imagens/imagem_1.jpg");
        
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String text = codificar(s+"/src/imagens/imagem_1.jpg");
        this.textCodified.setText(text);
        contador = 1;
    }
}
