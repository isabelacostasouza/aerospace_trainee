/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paginaSimulador;

public class SMS {
    
    public static void quebrarString(String str){
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
            SMS.enviar(strAux);
            strAux = "";
            i+=160;
        } while (i<str.length());
    }
     
    public static void enviar(String str){
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
}
