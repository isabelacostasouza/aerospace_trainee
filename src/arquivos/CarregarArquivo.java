package arquivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarregarArquivo {
    private int quantidadeLinhas;
    
    public int tamanho() {
        return quantidadeLinhas;
    }
    
    public void escreverArquivo(String linha, String caminhoArquivo) {
        FileWriter fw;
        try {
            fw = new FileWriter(caminhoArquivo, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write(linha);
            conexao.newLine();
            conexao.close();
        } catch (IOException ex) {
            Logger.getLogger(CarregarArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void limparArquivo(){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String caminhoArquivo = s+"\\estoque.txt";
        
        File file = new File(caminhoArquivo);
        file.delete();
        File f = new File(caminhoArquivo);
        try {
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(CarregarArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
