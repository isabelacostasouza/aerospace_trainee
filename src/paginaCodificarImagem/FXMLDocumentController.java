package paginaCodificarImagem;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.*;



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
    
    public static void decodificar(String base64Image, String pathFile) {
        /*
        cria uma imagem a partir de uma string base64
        */
        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
      // Converting a Base64 String into Image byte array
        byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
        imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
      System.out.println("Image not found" + e);
        } catch (IOException ioe) {
      System.out.println("Exception while reading the Image " + ioe);
        }
    }
    
    {
    /*    
    funcao para redimensionar imagem
    public BufferedImage scale(BufferedImage img, int targetWidth, int targetHeight) {

    int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
    BufferedImage ret = img;
    BufferedImage scratchImage = null;
    Graphics2D g2 = null;

    int w = img.getWidth();
    int h = img.getHeight();

    int prevW = w;
    int prevH = h;

    do {
        if (w > targetWidth) {
            w /= 2;
            w = (w < targetWidth) ? targetWidth : w;
        }

        if (h > targetHeight) {
            h /= 2;
            h = (h < targetHeight) ? targetHeight : h;
        }

        if (scratchImage == null) {
            scratchImage = new BufferedImage(w, h, type);
            g2 = scratchImage.createGraphics();
        }

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);

        prevW = w;
        prevH = h;
        ret = scratchImage;
    } while (w != targetWidth || h != targetHeight);

    if (g2 != null) {
        g2.dispose();
    }

    if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {
        scratchImage = new BufferedImage(targetWidth, targetHeight, type);
        g2 = scratchImage.createGraphics();
        g2.drawImage(ret, 0, 0, null);
        g2.dispose();
        ret = scratchImage;
    }

    return ret;
}
     
    */
    }
    
    public static String comprimeString(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(out)) {
            gzip.write(str.getBytes());
        }
        return out.toString("ISO-8859-1");
    }
 
    String codificar(String imagePath_noJPG) throws IOException {
        
        String base64="";
        String imagePath = imagePath_noJPG + ".jpg";
        File file = new File(imagePath);
        
        /*
        codigo para redimensionar imagem
        
        try{
        String base64Image = "";
        File file = new File(imagePath);
        
        BufferedImage image = ImageIO.read(file);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageWriter writer = (ImageWriter) ImageIO.getImageWritersByFormatName("jpeg").next();

        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.2f); // Change this, float between 0.0 and 1.0

        writer.setOutput(ImageIO.createImageOutputStream(os));
        writer.write(null, new IIOImage(image, null, null), param);
        writer.dispose();
        
        base64 = Base64.getEncoder().encodeToString(os.toByteArray());
        
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
       
        }
        
        imagePath = imagePath_noJPG + "-SMALL.jpg";
        decodificar(base64, imagePath);
        */
        
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64 = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        
        String cmp_str = comprimeString(base64);
        System.out.println("\ntamanho string B64 "+ base64.length());
        System.out.println("tamanho string comprimida "+ cmp_str.length());
        System.out.println("Seriam necessarios " + cmp_str.length()/160 + " sms\n");
        // quebraString(String string-desejada-para-envio);
        
        String aux;
        aux = "String em base 64 " + base64.length() + " crtrs.";
        aux += " Apos compressao: " + cmp_str.length() + " crtrs.";
        aux += "\nSeriam necessarios " + (cmp_str.length()/160) + " sms para o envio da imagem.\n";
        return aux;
    }
    
    void quebraString(String str){
        /*
        funcao que pega uma string e divide ela em string menores de 160 ou menos
        caracteres; chama a funcao enviaSms com as string menores, uma por uma
        */
        int i=160, j=0;
        String strAux = "";
        do{
            while(j<i){
                strAux += str.charAt(j);
                j++;
            }
            enviaSms(strAux);
            strAux = "";
            i+=160;
        } while (i<str.length());
    }
    
    void enviaSms(String str){
        String sms = "";
        
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
            text = codificar(s+"/src"+url[contador]);
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
            text = codificar(s+"/src/imagens/imagem_1");
        } catch (Exception e) {}
        this.textCodified.setText(text);
        contador = 1;
    }
}
