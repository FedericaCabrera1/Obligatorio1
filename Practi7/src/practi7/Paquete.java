
package practi7;

public class Paquete {
    private int numero;
    private double costo;
    private String pais;
    private Funcionario funcionario;

    public Paquete(int numero, double costo, String pais, Funcionario unFuncionario) {
        this.numero = numero;
        this.costo = costo;
        this.pais = pais;
        this.funcionario = unFuncionario;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    
    
}
