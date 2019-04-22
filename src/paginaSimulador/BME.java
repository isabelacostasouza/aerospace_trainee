
package paginaSimulador;

import java.util.Random;

public class BME {
        
    private static int randomizarIndex() {
        Random gerador = new Random();
        return gerador.nextInt(5);
    }
     
    public static int getTemperatura() {
        int temperatura[] = {-15, 0, 10, 20, 35};
        return temperatura[randomizarIndex()];
    }
    public static int getPressao() {
        int pressao[] = {410, 535, 700, 973, 1000};
        return pressao[randomizarIndex()];
    }
    public static int getAltitude() {
        int altitude[] = {771, 820, 1200, 1370, 1567};
        return altitude[randomizarIndex()];
    }
    public static int getUmidade() {
        int umidade[] = {50, 62, 69, 74, 81};
        return umidade[randomizarIndex()];
    }
}
