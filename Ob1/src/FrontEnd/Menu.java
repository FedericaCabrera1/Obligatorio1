package FrontEnd;

import BackEnd.Juego;
import BackEnd.Jugador;
import BackEnd.Rectangulo;
import BackEnd.Saltar;
import BackEnd.Sistema;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        menuPrincipal(sistema);
    }

    public static void menuPrincipal(Sistema sistema) {
        Scanner in = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 5) {
            System.out.println("ESTE ES EL MENU: \n 1) Registrar jugador \n 2) Jugar juego Saltar \n 3) Jugar juego Rectángulo \n 4) Bitácora \n 5) Exit");
            opcion = manejarError();
            switch (opcion) {
                case 1:
                    opcion1(sistema);
                    break;
                case 2:
                    opcion2(sistema);
                    break;
                case 3:
                    opcion3(sistema);
                    break;
                case 4:
                    opcion4(sistema);
                    break;
                case 5:
                    System.out.println("Nos vemos pronto!");
                    break;
                default:
                    System.out.println("\u001B[31m" + "La opción ingresada no es correcta. Reingrese" + "\u001B[0m");
            }
        }

    }

    public static void opcion1(Sistema sistema) {
        boolean seguirIngresando = true;
        while (seguirIngresando) {
            System.out.println("REGISTRO DE NUEVO JUGADOR");
            Scanner in = new Scanner(System.in);
            System.out.println("Ingrese Nombre:");
            String nombre = in.nextLine();
            System.out.println("Ingrese Edad:");
            int edad = manejarError();
            System.out.println("Ingrese Alias:");
            String alias = in.nextLine();
            Jugador jugadorAgregado = sistema.crearJugador(nombre, edad, alias);
            boolean esta = sistema.aliasRepetido(jugadorAgregado);
            while (esta) {
                System.out.println("\u001B[31m" + "El alias elegido ya está en uso. Reingrese" + "\u001B[0m");
                alias = in.nextLine();
                jugadorAgregado = sistema.crearJugador(nombre, edad, alias);
                esta = sistema.aliasRepetido(jugadorAgregado);
            }
            sistema.agregarJugador(nombre, edad, alias);
            System.out.println("");
            System.out.println("Jugador Registrado con éxito");
            System.out.println("¿Que desea hacer? \n 1) Registrar otro jugador \n 2) Volver al menú principal \n 3) Exit");
            int opcionIngresada = manejarError();
            if (opcionIngresada == 2) {
                seguirIngresando = false;
            }
        }
        menuPrincipal(sistema);
    }

    public static void opcion2(Sistema sistema) {
        /*primero mostramos la lista de jugadores A MODO DE MENU, le pedimos q elija uno, 
        chequeamos q este todo OK y entonces comienza el juego con ese jugador*/

 /*aca llamamos al metodo epico de la clase sistema 
        q nos conecta con los metodos en la clase SALTAR y arranca el juego*/
        ArrayList<Jugador> listaJugadores = sistema.getListaJugadores();
        if (listaJugadores.size() == 0) {
            System.out.println("\u001B[31m" + "No hay ningun jugador registrado en el sistema, porfavor seleccione la opción 1 del menu para hacerlo." + "\u001B[0m");
            menuPrincipal(sistema);
        } else {
            Scanner lector = new Scanner(System.in);
            System.out.println("BIENVENIDO AL JUEGO SALTAR");
            System.out.println("Lista de jugadores");
            listaJugadores = sistema.getListaJugadores();
            for (int i = 0; i < listaJugadores.size(); i++) {
                System.out.println((i + 1) + ": " + listaJugadores.get(i));
            }
            System.out.println("Seleccione un jugador de la lista para empezar: ");
            int nroJugador = manejarError();
            while (nroJugador < 0 || nroJugador > listaJugadores.size()) {
                System.out.println("\u001B[31m" + "El numero ingresado esta fuera del rango. Reingrese." + "\u001B[0m");
                nroJugador = manejarError();
            }

            System.out.println("");
            System.out.println("Ingrese A para configuracion AL AZAR o P para configuracion PREDETERMINADA");
            char configuracion = (lector.nextLine()).charAt(0);
            while (configuracion != 'A' && configuracion != 'a' && configuracion != 'P' && configuracion != 'p') {
                System.out.println("\u001B[31m" +"La letra ingresada no es valida, reingrese una P o una A." + "\u001B[0m");
                configuracion = (lector.nextLine()).charAt(0);
            }

            Date now = new Date(System.currentTimeMillis());
            SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
            String hora = hour.format(now) + "";

            System.out.println("");
            System.out.println("COMIENZA EL JUEGO SALTAR " + hora);
            Saltar s = sistema.crearSaltar((nroJugador - 1), configuracion, hora);
            sistema.agregarJuego(s);
            jugarASaltar(sistema, s);

        }

    }

    public static void jugarASaltar(Sistema sistema, Saltar s) {
        //while(noSeTermina) 
        boolean seTermina = false;
        while (!seTermina) {
            char[][] mat = s.getMatriz();
            imprimirMatrizSaltar(s);
            if (!(s.quedanDosFichas()) && s.quedanJugadasDisponibles()) {
                //Se le muestra al jugador las columnas que puede mover para ese color
                int[] columnas = sistema.mostrarColumnasAUsuario(s);
                String res = "Las columnas que se pueden mover para el color " + s.getColor() + " son: ";
                int cantidadMovimientos = 0;
                for (int i = 0; i < columnas.length; i++) {
                    if (columnas[i] != 0) {
                        res += "\n" + i + ": " + columnas[i];
                    } else {
                        cantidadMovimientos++;
                    }
                }
                if (cantidadMovimientos != 4) {
                    System.out.println(res);
                    System.out.println("Ingrese una columna de las opciones brindadas");

                    int columna = manejarError();
                    if (columna == -1) {
                        s.calcularPuntaje();
                        seTermina = true;
                    } else {

                        int cantPosiciones = 0;
                        boolean validar = false;
                        for (int i = 0; i < columnas.length; i++) {
                            if (i == columna) {
                                if (columnas[i] != 0) {
                                    validar = true;
                                    cantPosiciones = columnas[i];
                                }
                            }
                        }
                        while (!validar) {
                            System.out.println("\u001B[31m" + "Error, la columna ingresada no esta dentro de las opciones brindadas. Reingrese." + "\u001B[0m");
                            columna = manejarError();
                            if (columna == -1) {
                                s.calcularPuntaje();
                                seTermina = true;
                                validar = true;
                            } else {

                                for (int i = 0; i < columnas.length; i++) {
                                    if (i == columna) {
                                        if (columnas[i] != 0) {
                                            validar = true;
                                            cantPosiciones = columnas[i];
                                        }
                                    }
                                }
                            }
                        }
                        if (!seTermina) {
                            s.hacerMovida(columna, cantPosiciones);
                        }
                    }
                } else {
                    System.out.println("No hay movimientos disponibles para el color " + s.getColor());
                    System.out.println("");
                    s.setColor(s.getColor());
                }
            } else {
                seTermina = true;
            }
            if (s.quedanDosFichas() && !s.quedanJugadasDisponibles()) {
                System.out.println("El juego se termino porque solo quedan dos fichas en el area base y porque no hay mas movimientos para realizar");
            } else {
                if (s.quedanDosFichas()) {
                    System.out.println("El juego se termino porque solo quedan dos fichas en el area base");
                } else {
                    if (!s.quedanJugadasDisponibles()) {
                        System.out.println("El juego se termino porque no hay mas movimientos para realizar");
                    }

                }
            }
        }
        System.out.println("PUNTAJE FINAL: " + s.calcularPuntaje());
        System.out.println("Se retornará al Menu Principal automaticamente");
        System.out.println("");
    }

    public static void imprimirMatrizSaltar(Saltar s) {
        char[][] mat = s.getMatriz();
        String fila = "  +-+-+-+-+";
        String filaNums = "   1 2 3 4 ";
        int[] columna = {60, 40, 30, 20, 10};
        int p = 0;
        for (int i = 0; i < mat.length; i++) {
            System.out.println(fila);
            if (i > 4) {
                System.out.print("  ");
            } else {
                System.out.print(columna[p]);
            }
            for (int k = 0; k < mat[0].length; k++) {
                System.out.print("|");
                if (mat[i][k] == 'R') {
                    System.out.print("\u001B[31m" + '#' + "\u001B[0m");
                } else if (mat[i][k] == 'A') {
                    System.out.print("\u001B[34m" + '#' + "\u001B[0m");
                } else if (mat[i][k] == 'V') {
                    System.out.print("\u001B[32m" + '#' + "\u001B[0m");
                } else if (mat[i][k] == 'M') {
                    System.out.print("\u001B[33m" + '#' + "\u001B[0m");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("|");
            System.out.println("");
            p++;
        }
        System.out.println(fila);
        System.out.println(filaNums);
    }

    public static void opcion3(Sistema sistema) {
        ArrayList<Jugador> listaJugadores = sistema.getListaJugadores();
        if (listaJugadores.size() == 0) {
            System.out.println("\u001B[31m" + "No hay ningun jugador registrado en el sistema, porfavor seleccione la opción 1 del menu para hacerlo." + "\u001B[0m");
            menuPrincipal(sistema);
        } else {
            Scanner lector = new Scanner(System.in);
            System.out.println("BIENVENIDO AL JUEGO RECTANGULO");
            System.out.println("Lista de jugadores");
            listaJugadores = sistema.getListaJugadores();
            for (int i = 0; i < listaJugadores.size(); i++) {
                System.out.println((i + 1) + ": " + listaJugadores.get(i));
            }
            System.out.println("Seleccione un jugador de la lista para empezar: ");
            int nroJugador = manejarError();
            while (nroJugador < 0 || nroJugador > listaJugadores.size()) {
                System.out.println("\u001B[31m" + "El numero ingresado esta fuera del rango. Reingrese." + "\u001B[0m");
                nroJugador = manejarError();
            }

            System.out.println("");
            System.out.println("Ingrese A para configuracion AL AZAR o P para configuracion PREDETERMINADA");
            char configuracion = (lector.nextLine()).charAt(0);
            while (configuracion != 'A' && configuracion != 'a' && configuracion != 'P' && configuracion != 'p') {
                System.out.println("\u001B[31m" + "La letra ingresada no es valida, reingrese una P o una A." + "\u001B[0m");
                configuracion = (lector.nextLine()).charAt(0);
            }

            Date now = new Date(System.currentTimeMillis());
            SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
            String hora = hour.format(now) + "";

            System.out.println("");
            System.out.println("COMIENZA EL JUEGO RECTANGULO " + hora);
            Rectangulo r = sistema.crearRectangulo((nroJugador - 1), configuracion, hora);
            sistema.agregarJuego(r);
            jugarARectangulo(sistema, r);
        }
    }

    public static void imprimirMatrizRectangulo(char[][] mat) {
        String[] col1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "2"};
        String[] col2 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        System.out.print("    ");
        for (int i = 0; i < col1.length; i++) {
            System.out.print(col1[i] + " ");
        }
        System.out.println("");
        System.out.print("    ");
        for (int i = 0; i < col2.length; i++) {
            System.out.print(col2[i] + " ");
        }
        System.out.println("");
        for (int i = 0; i < mat.length; i++) {
            if (i < 9) {
                System.out.print("0" + (i + 1) + " ");
            } else {
                System.out.print((i + 1) + " ");
            }
            System.out.print(" ");
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == ' ') {
                    System.out.print('_');
                } else if (mat[i][j] == '*') {
                    System.out.print('*');
                } else if (mat[i][j] == 'R') {
                    System.out.print("\u001B[31m" + '#' + "\u001B[0m");
                } else if (mat[i][j] == 'A') {
                    System.out.print("\u001B[34m" + '#' + "\u001B[0m");
                } else if (mat[i][j] == 'V') {
                    System.out.print("\u001B[32m" + '#' + "\u001B[0m");
                } else if (mat[i][j] == 'M') {
                    System.out.print("\u001B[33m" + '#' + "\u001B[0m");
                } else if (mat[i][j] == 'B') {
                    System.out.print("\u001B[35m" + '#' + "\u001B[0m");
                } else if (mat[i][j] == 'G') {
                    System.out.print("\u001B[36m" + '#' + "\u001B[0m");
                }
                System.out.print(" ");

            }
            System.out.println("");
        }
    }

    public static void jugarARectangulo(Sistema s, Rectangulo r) {
        int contadorJugadas = 0;
        boolean insertoX = false;
        while (contadorJugadas < 10 && r.quedanJugadas() && !insertoX) {
            Scanner lector = new Scanner(System.in);
            char[][] mat = r.getMatriz();
            System.out.println("");
            imprimirMatrizRectangulo(mat);
            System.out.println("");
            System.out.println("Ingrese coordenadas de su rectangulo");
            String coordenadas = lector.nextLine();
            boolean hayX = hayX(coordenadas);
            if (hayX) {
                r.getPuntaje();
                insertoX = true;
            } else {
                int[] coords = recibirCoordenadas(coordenadas);
                int[] coordsCorrectas = coordCorrectas(coords, coordenadas);
                int[] coordsEntran = coordEntran(coordenadas, coords, coordsCorrectas, r);
                boolean seSuperpone = r.validacionSuperposicion((coordsCorrectas[0] - 1), (coordsCorrectas[1] - 1), coordsCorrectas[2], coordsCorrectas[3]);
                boolean esAdyacente = r.esAdyacente((coordsCorrectas[0] - 1), (coordsCorrectas[1] - 1), coordsCorrectas[2], coordsCorrectas[3]);
                while (seSuperpone || !esAdyacente) {
                    if (seSuperpone && !esAdyacente) {
                        System.out.println("\u001B[31m" + "La matriz ingresada no es correcta. \nSe superpone con una posicion ya ocupada y no es adyacente a la matriz anterior" + "\u001B[0m");
                    } else {
                        if (seSuperpone && esAdyacente) {
                            System.out.println("\u001B[31m" + "La matriz ingresada no es correcta. \nSe superpone con una posicion ya ocupada" + "\u001B[0m");
                        } else {
                            if (!seSuperpone && !esAdyacente) {
                                System.out.println("\u001B[31m" + "La matriz ingresada no es correcta. \nNo es adyacente a la matriz anterior" + "\u001B[0m");
                            }
                        }
                    }

                    System.out.println("REINGRESE LAS COORDENADAS");
                    coordenadas = lector.nextLine();
                    coords = recibirCoordenadas(coordenadas);
                    coordsCorrectas = coordCorrectas(coords, coordenadas);
                    coordsEntran = coordEntran(coordenadas, coords, coordsCorrectas, r);
                    seSuperpone = r.validacionSuperposicion((coordsCorrectas[0] - 1), (coordsCorrectas[1] - 1), (coordsCorrectas[2]), (coordsCorrectas[3]));
                    esAdyacente = r.esAdyacente((coordsCorrectas[0] - 1), (coordsCorrectas[1] - 1), (coordsCorrectas[2]), (coordsCorrectas[3]));
                }
                r.crearNuevaMatriz((coordsCorrectas[0]) - 1, (coordsCorrectas[1]) - 1, (coordsCorrectas[2]), (coordsCorrectas[3]));
                contadorJugadas++;
            }

        }
        imprimirMatrizRectangulo(r.getMatriz());
        System.out.println("");
        if (insertoX) {
            System.out.println("Se termino el juego. Hasta pronto!");
        } else {
            if (contadorJugadas == 10 && !r.quedanJugadas()) {
                System.out.println("Ya se realizaron 10 jugadas y no quedan movimientos disponibles");
            } else {
                if (contadorJugadas < 10 && !r.quedanJugadas()) {
                    System.out.println("No quedan movimientos disponibles");
                } else {
                    if (contadorJugadas == 10 && r.quedanJugadas()) {
                        System.out.println("Ya se realizaron 10 jugadas");
                    }
                }
            }
        }

        System.out.println("PUNTAJE FINAL: " + r.getPuntaje());
        System.out.println("Se retornará al Menu Principal automaticamente");
        System.out.println("");

    }

    public static boolean validacionRangoCoords(int[] coords) {
        boolean correcto = true;
        for (int i = 0; i < coords.length; i++) {
            if (coords[i] < 1 || coords[i] > 20) {
                correcto = false;
            }
        }
        for (int i = 0; i < coords.length; i++) {
            System.out.println("Coords validando2");
            System.out.println(coords[i]);
        }
        return correcto;
    }

    public static boolean hayX(String coordenadas) {
        boolean hayX = false;
        String s = coordenadas.trim();
        if (s.equalsIgnoreCase("x")) {
            hayX = true;
        }
        return hayX;
    }

    public static boolean validarCantidadCoords(int[] coords) {
        boolean correcto = true;
        for (int i = 0; i < coords.length; i++) {
            if (coords[i] == 0) {
                correcto = false;
            }
        }
        return correcto;
    }

    public static int[] coordCorrectas(int[] coords, String coordenadas) {
        Scanner lector = new Scanner(System.in);
        boolean validarRango = validacionRangoCoords(coords);
        boolean validarCant = validarCantidadCoords(coords);
        while (!validarCant || !validarRango) {
            if (!validarCant) {
                System.out.println("\u001B[31m" + "La cantidad de coordenadas ingresadas no es suficiente. Reingrese" + "\u001B[0m");
                coordenadas = lector.nextLine();
                coords = recibirCoordenadas(coordenadas);
                validarCant = validarCantidadCoords(coords);
                validarRango = validacionRangoCoords(coords);
            } else if (!validarRango) {
                System.out.println("\u001B[31m" + "Las coordenadas ingresadas estan fuera de rango. Reingrese" + "\u001B[0m");
                for (int i = 0; i < coords.length; i++) {
                    System.out.println("Coords validando");
                    System.out.println(coords[i]);
                }
                coordenadas = lector.nextLine();
                coords = recibirCoordenadas(coordenadas);
                validarRango = validacionRangoCoords(coords);
                validarCant = validarCantidadCoords(coords);
//                while (!validarCant) {
//                    System.out.println("La cantidad de coordenadas ingresadas no es suficiente. Reingrese");
//                    coordenadas = lector.nextLine();
//                    coords = recibirCoordenadas(coordenadas);
//                    validarCant = validarCantidadCoords(coords);
//                }
            }

        }
        for (int i = 0; i < coords.length; i++) {
            System.out.println(coords[i]);
        }
        return coords;

    }

    public static int[] validarRango(int[] coordenadas) {
        boolean validarRango = validacionRangoCoords(coordenadas);
        Scanner lector = new Scanner(System.in);
        String coords = "";
        while (!validarRango) {
            System.out.println("Las coordenadas ingresadas estan fuera de rango. Reingrese");
            coords = lector.nextLine();
            coordenadas = recibirCoordenadas(coords);
            validarRango = validacionRangoCoords(coordenadas);
        }
        return coordenadas;
    }

    public static int[] validarCantidad(int[] coordenadas) {
        boolean validarCant = validarCantidadCoords(coordenadas);
        Scanner lector = new Scanner(System.in);
        String coords = "";
        while (!validarCant) {
            System.out.println("La cantidad de coordenadas ingresadas no es suficiente. Reingrese");
            coords = lector.nextLine();
            coordenadas = recibirCoordenadas(coords);
            validarCant = validarCantidadCoords(coordenadas);
        }
        return coordenadas;

    }

    public static int[] coordEntran(String coordenadas, int[] coords, int[] coordsCorrectas, Rectangulo r) {
        Scanner lector = new Scanner(System.in);
        boolean excedeTamano = r.outOfBounds((coordsCorrectas[0] - 1), (coordsCorrectas[1] - 1), (coordsCorrectas[2]), (coordsCorrectas[3]));
        while (excedeTamano) {
            for (int i = 0; i < coordsCorrectas.length; i++) {
                System.out.println(coordsCorrectas[i]);
            }
            System.out.println("\u001B[31m" + "La matriz esta fuera de rango. Reingrese" + "\u001B[0m");
            coordenadas = lector.nextLine();
            boolean hayX = hayX(coordenadas);
            if (hayX) {
                coordsCorrectas[0] = -1;
            } else {
                coords = recibirCoordenadas(coordenadas);
                coordsCorrectas = coordCorrectas(coords, coordenadas);
                excedeTamano = r.outOfBounds((coordsCorrectas[0]) - 1, (coordsCorrectas[1]) - 1, (coordsCorrectas[2]), (coordsCorrectas[3]));
            }
        }
        return coordsCorrectas;
    }

    public static int[] recibirCoordenadas(String coordenadas) {
        Scanner lector = new Scanner(System.in);
        int[] coords = new int[4];
        int contador = 0;
        int aux = 0;
        boolean formatoEsCorrecto = false;
        boolean hayX = false;

        for (int i = 0; aux < coordenadas.length() && contador < 4; i++) {
            String res = "";
            if (coordenadas.charAt(aux) != ' ') {
                res += coordenadas.charAt(aux);
                while (aux < (coordenadas.length() - 1) && coordenadas.charAt(aux + 1) != ' ') {
                    res += coordenadas.charAt(aux + 1);
                    aux++;
                }
                if (res.equalsIgnoreCase("x")) {
                    coords[0] = -1;
                    hayX = true;
                } else {
                    try {
                        int numero = Integer.parseInt(res);
                        formatoEsCorrecto = true;
                        coords[contador] = numero;
                        contador++;
                    } catch (NumberFormatException e) {
                        System.out.println("\u001B[31m" + "Error en el formato del número." + "\u001B[0m");
//                            lector.nextLine();
                    }
                    if (!formatoEsCorrecto) {
                        System.out.println("Reingrese las coordenadas");
                        coordenadas = lector.nextLine();
                        System.out.println(coordenadas);
                        aux = 0;
                        contador = 0;
                    }
                }

            }
            if (formatoEsCorrecto || coordenadas.charAt(aux) == ' ') {
                formatoEsCorrecto = false;
                aux++;
            }
        }

        System.out.println("Coords");
        for (int i = 0; i < coords.length; i++) {
            System.out.println(coords[i]);
        }

        return coords;
    }

    public static void opcion4(Sistema sistema) {
        Scanner lector = new Scanner(System.in);
        ArrayList<Juego> listaJuegos = sistema.getListaJuegos();
        if (listaJuegos.size() == 0) {
            System.out.println("\u001B[31m" + "No hay ningun juego registrado en el sistema, porfavor seleccione la opcion 2 o 3 del menu para hacerlo." + "\u001B[0m");
            menuPrincipal(sistema);
        } else {
            System.out.println("Seleccione orden de la lista :" + "\n" + "(A) Por alias creciente" + "\n" + "(B) Por puntaje decreciente");
            char opcion = (lector.nextLine()).charAt(0);
            while (opcion != 'A' && opcion != 'a' && opcion != 'B' && opcion != 'b') {
                System.out.println("\u001B[31m" + "La letra ingresada no es valida, reingrese una A o una B." + "\u001B[0m");
                opcion = (lector.nextLine()).charAt(0);
            }
            if (opcion == 'A' && opcion == 'a') {
                ArrayList<Juego> listaOrdenada = sistema.ordenarXAlias();
                for (int i = 0; i < listaOrdenada.size(); i++) {
                    System.out.println((i + 1) + "-");
                    System.out.println(listaOrdenada.get(i));
                    System.out.println("");
                }
            } else {
                ArrayList<Juego> listaOrdenada = sistema.ordenarXPuntaje();
                for (int i = 0; i < listaOrdenada.size(); i++) {
                    System.out.println((i + 1) + "-");
                    System.out.println(listaOrdenada.get(i));
                    System.out.println("");
                }
            }
        }
    }

    public static int manejarError() {
        boolean esCorrecto = false;
        Scanner in = new Scanner(System.in);
        String opcionIngresada = in.nextLine();
        int resultado = 0;
        if (opcionIngresada.equalsIgnoreCase("x")) {
            resultado = -1;
        } else {
            while (!esCorrecto) {
                try {
                    resultado = Integer.parseInt(opcionIngresada);
                    esCorrecto = true;
                } catch (NumberFormatException e) {
                    System.out.println("\u001B[31m" + "Error en el formato del número. Reingrese" + "\u001B[0m");
                    opcionIngresada = in.nextLine();
                }
            }
        }
        return resultado;
    }

}
