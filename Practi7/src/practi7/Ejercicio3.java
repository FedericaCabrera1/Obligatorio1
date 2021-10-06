
package practi7;

import java.util.*;

public class Ejercicio3 {
    
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Funcionario> listaFuncionarios = new ArrayList();
        
        
    }
    
    public static ArrayList<Funcionario> sueldosMayores1000(ArrayList<Funcionario> l){
        ArrayList<Funcionario> lista = new ArrayList();
        for (int i=0; i<l.size(); i++){
            Funcionario f = l.get(i);
            if (f.getSueldo()>1000){
                lista.add(f);
            }
        }
        return lista;  
    }
    
    public static double sueldoMayor(ArrayList<Funcionario> l){
        double max = Integer.MIN_VALUE;
        for (int i=0; i<l.size(); i++){
            Funcionario f = l.get(i);
            if (f.getSueldo()>max){
                max = f.getSueldo();
            }
        }
        return max; 
    }
    
    public static int cantidadSueldoMayor(int sueldoMayor, ArrayList<Funcionario> l){
        int contador = 0;
        for (int i=0; i<l.size(); i++){
            Funcionario f = l.get(i);
            if (f.getSueldo()==sueldoMayor){
                contador++;
            }
        }
        return contador;   
    }
            
        
    
}
