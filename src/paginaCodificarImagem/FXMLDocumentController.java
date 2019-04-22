package paginaCodificarImagem;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import java.io.IOException;



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
    
    @FXML
    void codificar(ActionEvent event) {
        String text=null;
        if(contador == 5) 
            contador = 0;
        
        String url[] = {"/imagens/imagem_1", "/imagens/imagem_2", "/imagens/imagem_3", "/imagens/imagem_4","/imagens/imagem_5"};
        setImage(url[contador]+".jpg");
        
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        try {
            text = Imagem.codificar(s+"/src"+url[contador]);
        } catch(Exception e){}
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
        String text = null;
        setImage("/imagens/imagem_1.jpg");
        
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        try{
            text = Imagem.codificar(s+"/src/imagens/imagem_1");
        } catch (Exception e) {}
        this.textCodified.setText(text);
        contador = 1;
    }
}
