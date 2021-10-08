package practi7;

import java.util.*;

public class Ejercicio4 {

    public static void main(String[] args) {
        ArrayList<Paquete> listaPaquetes = new ArrayList();

    }

    public static boolean existePaquete(ArrayList<Paquete> l, int nro) {
        boolean esta = false;
        for (int i = 0; i < l.size() && !esta; i++) {
            Paquete p = l.get(i);
            if (p.getNumero() == nro) {
                esta = true;
            }
        }
        return esta;
    }

    public static double costoPais(ArrayList<Paquete> l, String nombrePais) {
        double costoTotal = 0;
        for (int i = 0; i < l.size(); i++) {
            Paquete p = l.get(i);
            if (p.getPais().equalsIgnoreCase(nombrePais)) {
                costoTotal+=p.getCosto();
            }
        }
        return costoTotal;
    }

}
