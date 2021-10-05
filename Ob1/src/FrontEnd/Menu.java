
package FrontEnd;


public class Menu {
    public static void main (String[] args){
        MenuPrincipal();
    }
    
    
    public void MenuPrincipal(){
        Sistema s = new Sistema();
        Scanner in = new Scanner(System.in);
        
        int opcion = 0;
        while(opcion != 4){
        System.out.println("ESTE ES EL MENU: \n 1) Registrar jugador \n 2) Jugar juego Saltar \n 3) Jugar juego Rectángulo \n 4) Exit");
        opcion = ManejarError();
        switch(opcion) {
            case 1: Opcion1();
            break;
            case 2: Opcion2();
            break;
            case 3: Opcion3();
            break; 
            case 4: System.out.println("Nos vemos pronto!");
            break; 
            default: System.out.println("La opción ingresada no es correcta. Reingrese");
        }
        }
        
    }
    
        public void Opcion1(){
        boolean seguirIngresando = true;
        while(seguirIngresando){
            System.out.println("REGISTRO DE NUEVO JUGADOR");
            Scanner in = new Scanner(System.in);
            System.out.println("Ingrese Nombre:");
            String nombre = in.nextLine();
            System.out.println("Ingrese Edad:");
            String edad = in.nextInt();
            System.out.println("Ingrese Alias:");
            String alias = in.nextLine();
            s.agregarJugador(nombre, edad, alias);
            System.out.println("Jugador Registrado con éxito");
            System.out.println("?Que desea hacer? \n 1) Registrar otro jugador \n 2) Volver al menú principal \n 3) Exit");
            opcionIngresada = ManejarError();
            if(opcionIngresada == 2){
                seguirIngresando = false;
            }
        }
        MenuPrincipal();
    }
        
    public void Opcion2(){
        /*primero mostramos la lista de jugadores A MODO DE MENU, le pedimos q elija uno, 
        chequeamos q este todo OK y entonces comienza el juego con ese jugador*/
        
        /*aca llamamos al metodo epico de la clase sistema 
        q nos conecta con los metodos en la clase SALTAR y arranca el juego*/
    }
    
    public void Opcion3(){
        /*aca llamamos al metodo epico de la clase sistema 
        q nos conecta con los metodos en la clase RECTANGULO y arranca el juego*/
    }
    
    public int ManejarError(){
        boolean esCorrecto = false;
        while(!esCorrecto){
            try {
                opcionIngresada = in.nextInt();
                esCorrecto = true;
                
            } catch (InputMismatchException e){
                    System.out.println("Error en el formato del número. Reingrese");
                    input.nextLine();
            }
        }
        return opcionIngresada;
    }
}
