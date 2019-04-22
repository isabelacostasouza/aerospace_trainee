package paginaCodificarImagem;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;


public class Imagem {
    
    public static String comprimirString(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(out)) {
            gzip.write(str.getBytes());
        }
        return out.toString("ISO-8859-1");
    }
    
    public static String codificar(String imagePath_noJPG) throws IOException {
        
        String base64="";
        String imagePath = imagePath_noJPG + ".jpg";
        File file = new File(imagePath);
      
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
        
        String cmp_str = comprimirString(base64);
        System.out.println("\ntamanho string B64 "+ base64.length());
        System.out.println("tamanho string comprimida "+ cmp_str.length());
        System.out.println("Seriam necessarios " + cmp_str.length()/160 + " sms\n");
        /*
        SMS.quebrarString(String string-desejada-para-envio);
        atencao que nesse caso deve-se importar a classe SMS
        so copiar 'import paginaSimulador.SMS;'
        */
        
        String aux;
        aux = "String em base 64 " + base64.length() + " crtrs.";
        aux += " Apos compressao: " + cmp_str.length() + " crtrs.";
        aux += "\nSeriam necessarios " + (cmp_str.length()/160) + " sms para o envio da imagem.\n";
        return aux; 
        /*
        aux nao e a string codificada nem a comprimida
        e' apenas a string q sera exibida na interface
        */
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

    public static void redimensionar(String imagePath_noJPG){//codigo para redimensionar imagem
        
        String imagePath = imagePath_noJPG + ".jpg";
        File file = new File(imagePath);
        String base64="";
        
        try{
        
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

    }

}
