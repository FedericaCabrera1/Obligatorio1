/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practi7;

/**
 *
 * @author María Mercedes
 */
public class Funcionario implements Comparable<Funcionario> {
    private String nombre;
    private int numero;
    private double sueldo; 

    public Funcionario(String nombre, int numero, double sueldo) {
        this.nombre = nombre;
        this.numero = numero;
        this.sueldo = sueldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    
    @Override
    public boolean equals (Object o){
        Funcionario f2 = (Funcionario)o;
        return this.getNombre().equalsIgnoreCase(f2.getNombre());
    }

    @Override
    public int compareTo(Funcionario o) {
        return this.getNombre().compareTo(o.getNombre()); 
    }
        
    
    
    
}
