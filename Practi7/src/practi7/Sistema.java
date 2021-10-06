package practi7;

import java.util.*;

public class Sistema {

    private ArrayList<Funcionario> listaFuncionarios;
    private ArrayList<Paquete> listaPaquetes;

    public void agregarFuncionario(Funcionario f) {
        listaFuncionarios.add(f);
    }

    public void agregarPaquete(Paquete p) {
        listaPaquetes.add(p);
    }

    public ArrayList<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public ArrayList<Paquete> getListaPaquetes() {
        return listaPaquetes;
    }
    
    public Funcionario maximaCantidadDeEnvios(){
        int contMax = Integer.MIN_VALUE;
        ArrayList<Funcionario> lista = new ArrayList(); 
        for (int i=0; i<listaFuncionarios.size(); i++){
            int contador = 0;
            Funcionario f = listaFuncionarios.get(i);
            for (int j=0; j<listaPaquetes.size(); j++){
                Paquete p = listaPaquetes.get(j);
                if (f.getNumero()==p.getFuncionario().getNumero()){
                    contador++;
                }
            }
            if (contador>contMax){
                lista.clear();
                lista.add(f); 
            }
            else {
                if (contador==contMax){
                    lista.add(f);
                }
            }
        }
        int nroMinimo = Integer.MAX_VALUE;
        Funcionario ret = null;
        for (int i=0; i<lista.size(); i++){
            Funcionario f = lista.get(i);
            if (f.getNumero()<nroMinimo){
                nroMinimo = f.getNumero();
                ret = f;  
            }
        }
        return ret;
    }
    
    public ArrayList<Funcionario> enviosAUnPais(String nombrePais){
        ArrayList<Funcionario> lista = new ArrayList();
        for (int i=0; i<listaPaquetes.size(); i++){
            Paquete p = listaPaquetes.get(i);
            if (p.getPais().equalsIgnoreCase(nombrePais)){
                Funcionario f = p.getFuncionario();
                if (!lista.contains(f)){
                    lista.add(f);
                } 
            }
        }
        return lista;
    }
    
    public int enviosDeUnFuncionario(Funcionario f){
        int contador = 0;
        for (int i=0; i<listaPaquetes.size(); i++){
            Paquete p = listaPaquetes.get(i);
            if (p.getFuncionario().getNumero()==f.getNumero()){
                contador++; 
            }
        }
        return contador;
    }
    
    public ArrayList<Funcionario> listaOrdenadaPorNom(){
        collections.sort(listaFuncionarios);
        return listaFuncionarios;
    }

}
