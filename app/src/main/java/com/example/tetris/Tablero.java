package com.example.tetris;

import android.graphics.Color;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Tablero {

    private final int alturaTablero = 20;
    private final int anchuraTablero = 10;
    public int tab[][] = new int[anchuraTablero][alturaTablero];
    private final Random random = new Random();
    public ArrayList<Pieza> listaPiezas = new ArrayList<Pieza>();
    private final int numeroPiezas = 7;
    private static int colorCuadrado = 1;
    private static int colorZPieza = 2;
    private static int colorIPieza = 3;
    private static int colorTPieza = 4;
    private static int colorSPieza = 5;
    private static int colorLPieza = 6;
    private static int colorJPieza = 7;
    private Pieza auxTroll;

    public Tablero() {
        listaPiezas.add(new Pieza(random.nextInt(numeroPiezas) + 1, 0));
        listaPiezas.add(new Pieza(random.nextInt(numeroPiezas) + 1, 0));
    }

    public void generarPieza(int altura) {
        listaPiezas.add(new Pieza(random.nextInt(numeroPiezas) + 1, altura));
    }

    public void borrarPieza() {
        listaPiezas.remove(0);
    }
    //transforma numeros de matriz a color


    public static void setColorCuadrado(int colorCuadrado) {
        Tablero.colorCuadrado = colorCuadrado;
    }

    public static void setColorZPieza(int colorZPieza) {
        Tablero.colorZPieza = colorZPieza;
    }

    public static void setColorIPieza(int colorIPieza) {
        Tablero.colorIPieza = colorIPieza;
    }

    public static void setColorTPieza(int colorTPieza) {
        Tablero.colorTPieza = colorTPieza;
    }

    public static void setColorSPieza(int colorSPieza) {
        Tablero.colorSPieza = colorSPieza;
    }

    public static void setColorLPieza(int colorLPieza) {
        Tablero.colorLPieza = colorLPieza;
    }

    public static void setColorJPieza(int colorJPieza) {
        Tablero.colorJPieza = colorJPieza;
    }

    public static int getColorCuadrado() {
        return colorCuadrado;
    }

    public static int getColorZPieza() {
        return colorZPieza;
    }

    public static int getColorIPieza() {
        return colorIPieza;
    }

    public static int getColorTPieza() {
        return colorTPieza;
    }

    public static int getColorSPieza() {
        return colorSPieza;
    }

    public static int getColorLPieza() {
        return colorLPieza;
    }

    public static int getColorJPieza() {
        return colorJPieza;
    }

    public int parseaColor(int x, int y) {
        if (tab[x][y] == 0) return Color.parseColor("#001c21");// azul oscuro fondo
        if (tab[x][y] == 1) return Color.parseColor("#f000ff"); //rosa
        if (tab[x][y] == 2) return Color.parseColor("#4deeea"); //azul claro
        if (tab[x][y] == 3) return Color.parseColor("#001eff"); //azul oscuro
        if (tab[x][y] == 4) return Color.parseColor("#74ee15"); //verde
        if (tab[x][y] == 5) return Color.parseColor("#FF8000"); //naranja
        if (tab[x][y] == 6) return Color.parseColor("#ffe700"); //amarillo
        if (tab[x][y] == 7) return Color.parseColor("#FF0000"); //rojo
        if (tab[x][y] == 8) return Color.parseColor("#acacac"); //gris pieza bloqueo
        if (tab[x][y] == 9) return Color.parseColor("#FFFFFF"); //pieza troll
        if (tab[x][y] == 10) return Color.parseColor("#FFFFFF"); //pieza troll
        if (tab[x][y] == 11) return Color.parseColor("#FF00B9"); //pieza rosa fucsia

        return -1;
    }
    public int[][] changeTab(int[][] tab) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (tab[j][i] != 0 && tab[j][i] != 8 ) {
                    tab[j][i] = 11;
                }
            }
        }
        return tab;
    }

    public void CambiarColores1Linea() {
        int aux = random.nextInt(numeroPiezas) + 1;
        for (int y = 19; y > 0; y--) {
            for (int x = 0; x < 10; x++) {
                if (tab[x][y] != 0 && tab[x][y] != 8 && tab[x][y] != 9 && tab[x][y] != 10) {
                    tab[x][y] = aux;
                }
            }
        }
    }

    public void CambiarColoresMultiLinea() {
        for (int y = 19; y > 0; y--) {
            for (int x = 0; x < 10; x++) {
                if (tab[x][y] != 0 && tab[x][y] != 8 && tab[x][y] != 9 && tab[x][y] != 10) {
                    tab[x][y] = random.nextInt(numeroPiezas) + 1;
                }
            }
        }
    }

    public boolean puedePonerse(Pieza pieza) {
        if (pieza != null) {
            Point xy1 = new Point(pieza.x1, pieza.y1);
            Point xy2 = new Point(pieza.x2, pieza.y2);
            Point xy3 = new Point(pieza.x3, pieza.y3);
            Point xy4 = new Point(pieza.x4, pieza.y4);

            ArrayList<Point> puntos = new ArrayList<>();
            puntos.add(xy1);
            puntos.add(xy2);
            puntos.add(xy3);
            puntos.add(xy4);
            int contador = 0;
            for (Point a : puntos) {
                if (this.tab[a.x][a.y] == 0) {
                    contador++;
                } else if (a.equals(xy1) || a.equals(xy2) || a.equals(xy3) || a.equals(xy4)) {
                    contador++;
                }
            }
            if (contador == 4) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }



    public void comerTablero(int y) {
        //y=0 sustituir mas tarde por altura variada
        for (int j = 0; j < y; j++) {
            for (int x = 0; x < getAnchoTablero(); x++) {
                tab[x][j] = 8;
            }
        }
    }

    public void elDestructor(int fila) {
        ponerFila0(fila);
        for (int y = fila; y >= 1; y--) {
            for (int x = 0; x < getAnchoTablero(); x++) {
                this.tab[x][y] = this.tab[x][y - 1];
            }
        }
    }

    public void ponerFila0(int y) {
        for (int x = 0; x < 10; x++) {
            tab[x][y] = 0;
        }
    }

    public List<Integer> detectarFilas(Pieza troll) {
        int contador = 0;
        List<Integer> l = new ArrayList<>();
        for (int y = 19; y >= 0; y--) {
            contador = 0;
            for (int x = 0; x < getAnchoTablero(); x++) {
                if (tab[x][y] != 0 && tab[x][y] != 8) {
                    contador++;
                }
            }
            if (contador == 10) {
                if (troll != null) {
                    obtenerPosTroll(troll);
                    borrarPieza(troll);
                }
                l.add(y);
                elDestructor(y);
                y++;
            }
        }
        if (troll != null && auxTroll != null) ponerPieza(auxTroll);
        return l;
    }

    private void obtenerPosTroll(Pieza troll) {
        auxTroll = new Pieza(troll.id, troll.getAltura());
        auxTroll.copiarPieza(troll);
    }


    //todas las posiciones a 0 para Thanos
    public void limpiarTablero() {
        for (int y = 0; y < alturaTablero; y++) {
            ponerFila0(y);
        }
    }

    public ArrayList<Pieza> getListaPiezas() {
        return listaPiezas;
    }

    //coge la Piezas actual
    public Pieza getPieza() {
        return listaPiezas.get(0);
    }


    //crear en clase Piezas atributo entero colorCode
    public void ponerPieza(Pieza pieza) {
        tab[pieza.x1][pieza.y1] = pieza.idColor;
        tab[pieza.x2][pieza.y2] = pieza.idColor;
        tab[pieza.x3][pieza.y3] = pieza.idColor;
        tab[pieza.x4][pieza.y4] = pieza.idColor;
    }

    public void borrarPieza(Pieza pieza) {
        tab[pieza.x1][pieza.y1] = 0;
        tab[pieza.x2][pieza.y2] = 0;
        tab[pieza.x3][pieza.y3] = 0;
        tab[pieza.x4][pieza.y4] = 0;
    }


    /*Este metodo por favor no lo toqueis ni borreis porque os crujo
    servira para hacer las rotaciones en un futuro no muy lejano
     */

    //añadir el cambio de la posicion de la pieza

    public void alfredo(Pieza p) {
        switch (p.id) {

            case 1:
                //Cuadrado uso salpicadura
                //No tuvo ningun efecto
                break;

            case 2:
                switch (p.pos) {
                    case 0:
                        p.x1 = p.x1 + 1;
                        p.y1 = p.y1 + 0;
                        p.x2 = p.x2 + 0;
                        p.y2 = p.y2 + 1;
                        p.x3 = p.x3 - 1;
                        p.y3 = p.y3 + 0;
                        p.x4 = p.x4 - 2;
                        p.y4 = p.y4 + 1;
                        break;

                    case 1:
                        p.x1 = p.x1 - 1;
                        p.y1 = p.y1 + 0;
                        p.x2 = p.x2 + 0;
                        p.y2 = p.y2 - 1;
                        p.x3 = p.x3 + 1;
                        p.y3 = p.y3 + 0;
                        p.x4 = p.x4 + 2;
                        p.y4 = p.y4 - 1;
                        break;
                }
                if (p.pos == 1) {
                    p.pos = 0;
                } else {
                    p.pos++;
                }

                break;

            case 3://
                switch (p.pos) {
                    case 0:
                        p.x1 = p.x1 + 0;
                        p.y1 = p.y1 + 0;
                        p.x2 = p.x2 + 1;
                        p.y2 = p.y2 - 1;
                        p.x3 = p.x3 + 2;
                        p.y3 = p.y3 - 2;
                        p.x4 = p.x4 + 3;
                        p.y4 = p.y4 - 3;
                        break;

                    case 1:
                        p.y1 = p.y1 + 0;
                        p.x1 = p.x1 + 0;
                        p.y2 = p.y2 + 1;
                        p.x2 = p.x2 - 1;
                        p.y3 = p.y3 + 2;
                        p.x3 = p.x3 - 2;
                        p.y4 = p.y4 + 3;
                        p.x4 = p.x4 - 3;
                        break;
                }
                if (p.pos == 1) {
                    p.pos = 0;
                } else {
                    p.pos++;
                }
                break;

            case 4:
                switch (p.pos) {

                    case 0:
                        p.x1 = p.x1 + 1;
                        p.y1 = p.y1 + 0;
                        p.x2 = p.x2 + 0;
                        p.y2 = p.y2 + 1;
                        p.x3 = p.x3 - 1;
                        p.y3 = p.y3 + 2;
                        p.x4 = p.x4 - 1;
                        p.y4 = p.y4 + 0;
                        break;

                    case 1:
                        p.x1 = p.x1 + 1;
                        p.y1 = p.y1 + 1;
                        p.x2 = p.x2 + 0;
                        p.y2 = p.y2 + 0;
                        p.x3 = p.x3 - 1;
                        p.y3 = p.y3 - 1;
                        p.x4 = p.x4 + 1;
                        p.y4 = p.y4 - 1;
                        break;

                    case 2:
                        p.x1 = p.x1 - 2;
                        p.y1 = p.y1 + 1;
                        p.x2 = p.x2 - 1;
                        p.y2 = p.y2 + 0;
                        p.x3 = p.x3 + 0;
                        p.y3 = p.y3 - 1;
                        p.x4 = p.x4 + 0;
                        p.y4 = p.y4 + 1;
                        break;

                    case 3:
                        p.x1 = p.x1 + 0;
                        p.y1 = p.y1 - 2;
                        p.x2 = p.x2 + 1;
                        p.y2 = p.y2 - 1;
                        p.x3 = p.x3 + 2;
                        p.y3 = p.y3 + 0;
                        p.x4 = p.x4 + 0;
                        p.y4 = p.y4 + 0;
                        break;


                }
                if (p.pos == 3) {
                    p.pos = 0;
                } else {
                    p.pos++;
                }
                break;

            case 5:
                switch (p.pos) {
                    case 0:
                        p.x1 = p.x1 + 0;
                        p.y1 = p.y1 + 1;
                        p.x2 = p.x2 - 1;
                        p.y2 = p.y2 + 2;
                        p.x3 = p.x3 - 1;
                        p.y3 = p.y3 + 0;
                        p.x4 = p.x4 + 0;
                        p.y4 = p.y4 - 1;
                        break;

                    case 1:
                        p.x1 = p.x1 + 0;
                        p.y1 = p.y1 - 1;
                        p.x2 = p.x2 + 1;
                        p.y2 = p.y2 - 2;
                        p.x3 = p.x3 + 1;
                        p.y3 = p.y3 + 0;
                        p.x4 = p.x4 + 0;
                        p.y4 = p.y4 + 1;
                        break;
                }
                if (p.pos == 1) {
                    p.pos = 0;
                } else {
                    p.pos++;
                }
                break;

            case 6:
                switch (p.pos) {
                    case 0:
                        p.x1 = p.x1 + 1;
                        p.y1 = p.y1 + 0;
                        p.x2 = p.x2 + 0;
                        p.y2 = p.y2 + 1;
                        p.x3 = p.x3 - 1;
                        p.y3 = p.y3 + 0;
                        p.x4 = p.x4 - 2;
                        p.y4 = p.y4 - 1;
                        break;

                    case 1:
                        p.x1 = p.x1 + 0;
                        p.y1 = p.y1 + 2;
                        p.x2 = p.x2 - 1;
                        p.y2 = p.y2 + 1;
                        p.x3 = p.x3 + 0;
                        p.y3 = p.y3 + 0;
                        p.x4 = p.x4 + 1;
                        p.y4 = p.y4 - 1;
                        break;

                    case 2:
                        p.x1 = p.x1 - 2;
                        p.y1 = p.y1 - 1;
                        p.x2 = p.x2 - 1;
                        p.y2 = p.y2 - 2;
                        p.x3 = p.x3 + 0;
                        p.y3 = p.y3 - 1;
                        p.x4 = p.x4 + 1;
                        p.y4 = p.y4 + 0;
                        break;

                    case 3:
                        p.x1 = p.x1 + 1;
                        p.y1 = p.y1 - 1;
                        p.x2 = p.x2 + 2;
                        p.y2 = p.y2 + 0;
                        p.x3 = p.x3 + 1;
                        p.y3 = p.y3 + 1;
                        p.x4 = p.x4 + 0;
                        p.y4 = p.y4 + 2;
                        break;

                }
                if (p.pos == 3) {
                    p.pos = 0;
                } else {
                    p.pos++;
                }
                break;

            case 7:
                switch (p.pos) {
                    case 0:
                        p.x1 = p.x1 + 2;
                        p.y1 = p.y1 + 1;
                        p.x2 = p.x2 + 1;
                        p.y2 = p.y2 + 2;
                        p.x3 = p.x3 + 1;
                        p.y3 = p.y3 + 0;
                        p.x4 = p.x4 + 0;
                        p.y4 = p.y4 - 1;
                        break;

                    case 1:
                        p.x1 = p.x1 - 1;
                        p.y1 = p.y1 + 1;
                        p.x2 = p.x2 - 2;
                        p.y2 = p.y2 + 0;
                        p.x3 = p.x3 + 0;
                        p.y3 = p.y3 + 0;
                        p.x4 = p.x4 + 1;
                        p.y4 = p.y4 - 1;
                        break;

                    case 2:
                        p.x1 = p.x1 - 1;
                        p.y1 = p.y1 + 0;
                        p.x2 = p.x2 + 0;
                        p.y2 = p.y2 - 1;
                        p.x3 = p.x3 + 0;
                        p.y3 = p.y3 + 1;
                        p.x4 = p.x4 + 1;
                        p.y4 = p.y4 + 2;
                        break;

                    case 3:
                        p.x1 = p.x1 + 0;
                        p.y1 = p.y1 - 2;
                        p.x2 = p.x2 + 1;
                        p.y2 = p.y2 - 1;
                        p.x3 = p.x3 - 1;
                        p.y3 = p.y3 - 1;
                        p.x4 = p.x4 - 2;
                        p.y4 = p.y4 + 0;
                        break;

                }
                if (p.pos == 3) {
                    p.pos = 0;
                } else {
                    p.pos++;
                }
                break;
        }
    }

    /*este metodo mueve la pieza despues de comprobarlo abajo,izquierda o derecha segun el char
    que le pasen, para ello usamos comprueba y el tipo de movimiento y en comprueba hay que
    comprobar que la PIEZA  no se salga del tablero y que no choque con otras.
    Puede salirse la matriz auxiiar de rangos pero nunca se puede salir la pieza
     */
    public void moverPiezas(Pieza pieza, char x) {
        if (pieza != null) {
            switch (x) {
                case 'i':
                    if (puedeMoverse(pieza, -1, 0, false)) {
                        borrarPieza(pieza);
                        pieza.mover(-1, 0);
                        ponerPieza(pieza);
                    }
                    break;
                case 'd':
                    if (puedeMoverse(pieza, 1, 0, false)) {
                        borrarPieza(pieza);
                        pieza.mover(1, 0);
                        ponerPieza(pieza);
                    }
                    break;
                case 'a':
                    if (puedeMoverse(pieza, 0, 1, false)) {
                        borrarPieza(pieza);
                        pieza.mover(0, 1);
                        ponerPieza(pieza);
                    }
                    break;
            }
        }

    }

    public boolean puedeMoverse(Pieza pieza, int x, int y, boolean vengoDeRotar) {
        int n = 0; //contador para saber si la pieza entera puede moverse
        if (pieza == null) {
            return true;
        }
        Point xy1 = new Point(pieza.x1, pieza.y1);
        Point xy2 = new Point(pieza.x2, pieza.y2);
        Point xy3 = new Point(pieza.x3, pieza.y3);
        Point xy4 = new Point(pieza.x4, pieza.y4);

        Point aux1 = new Point(pieza.x1 + x, pieza.y1 + y);
        Point aux2 = new Point(pieza.x2 + x, pieza.y2 + y);
        Point aux3 = new Point(pieza.x3 + x, pieza.y3 + y);
        Point aux4 = new Point(pieza.x4 + x, pieza.y4 + y);

        //Creamos un array con los puntos posibles donde se puede mover
        ArrayList<Point> puntos = new ArrayList<Point>();
        puntos.add(aux1);
        puntos.add(aux2);
        puntos.add(aux3);
        puntos.add(aux4);

        //Recorremos el array de los posibles puntos y controlamos que estamos dentro del tablero o si está ocupada la posicion o no
        for (Point a : puntos) {
            if (a.x < anchuraTablero && a.x >= 0 && a.y >= 0 && a.y < alturaTablero && (tab[a.x][a.y] == 0 || tab[a.x][a.y] == 8)) {
                n++;
            } else if (a.equals(xy1) || a.equals(xy2) || a.equals(xy3) || a.equals(xy4)) {
                if (!vengoDeRotar) {
                    n++;
                }
            }
        }
        if (n == 4) {
            return true;
        }

        return false;
    }

    public int getAlturaTablero() {
        return this.alturaTablero;
    }

    public int getAnchoTablero() {
        return this.anchuraTablero;
    }

    public void comprobarRotar(Pieza p) {
        Pieza aux = new Pieza(p.id, 0);

        aux.pos = p.pos;
        aux.x1 = p.x1;
        aux.y1 = p.y1;
        aux.x2 = p.x2;
        aux.y2 = p.y2;
        aux.x3 = p.x3;
        aux.y3 = p.y3;
        aux.x4 = p.x4;
        aux.y4 = p.y4;

        alfredo(aux);
        if (puedeMoverse(aux, 0, 0, true)) {
            alfredo(p);
        }
    }
}