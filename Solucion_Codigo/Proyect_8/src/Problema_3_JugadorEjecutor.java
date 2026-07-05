import java.util.ArrayList;

/**
 * Problema 3 - Juego de fútbol "Estadísticas"
 *Se desea realizar una aplicación que permita a un periodista deportivo llevar las estadísticas de los 
 *jugadores de un equipo de fútbol para poder valorar su actuación en el partido.

Cada jugador se identifica por su nombre, número de dorsal y Rut

Los jugadores se dividen en tres categorías:

Atacantes
Defensores
Porteros
*Para todos los jugadores se desea contabilizar el número de goles marcados, además en el caso de los 
* jugadores de campo se contabilizan los pases realizados con éxito y el número de balones recuperados. 
* En el caso de los porteros se contabilizan las atajadas realizadas.

Valoración del jugador
Cálculo base para todos los jugadores:

valor_goles = goles * 30
Valor adicional según tipo de jugador:

Atacantes

valor += recuperaciones * 3
Defensores

valor += recuperaciones * 4
Porteros

valor += atajadas * 5
Note

* Se debe aplicar polimorfismo mediante la aplicación de herencia, encapsulamiento de atributos y comportamientos comunes, 
* y especializar comportamiento según el tipo de jugador.
 *
 * @author DENNIS MACAS
 * @version 1.0
 */

abstract class Jugador {
    protected String nombre;
    protected int dorsal;
    protected String rut;
    protected int goles;

    public Jugador(String nombre, int dorsal, String rut, int goles) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.rut = rut;
        this.goles = goles;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getDorsal() { return dorsal; }
    public void setDorsal(int dorsal) { this.dorsal = dorsal; }

    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    public int getGoles() { return goles; }
    public void setGoles(int goles) { this.goles = goles; }

    public abstract double calcularValoracion();

    @Override
    public String toString() {
        return String.format("Jugador: %s | Dorsal: %d | Rut: %s | Goles: %d | Valoración: %.2f",
                nombre, dorsal, rut, goles, calcularValoracion());
    }
}

class Defensor extends Jugador {
    private int pasesExitosos;
    private int recuperaciones;

    public Defensor(String nombre, int dorsal, String rut, int goles, int pasesExitosos, int recuperaciones) {
        super(nombre, dorsal, rut, goles);
        this.pasesExitosos = pasesExitosos;
        this.recuperaciones = recuperaciones;
    }

    public int getPasesExitosos() { return pasesExitosos; }
    public void setPasesExitosos(int pasesExitosos) { this.pasesExitosos = pasesExitosos; }

    public int getRecuperaciones() { return recuperaciones; }
    public void setRecuperaciones(int recuperaciones) { this.recuperaciones = recuperaciones; }

    @Override
    public double calcularValoracion() {
        return (goles * 30) + (recuperaciones * 4);
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" | Pases Exitosos: %d | Recuperaciones: %d", pasesExitosos, recuperaciones);
    }
}

class Atacante extends Jugador {
    private int tirosAlArco;
    private int asistencias;

    public Atacante(String nombre, int dorsal, String rut, int goles, int tirosAlArco, int asistencias) {
        super(nombre, dorsal, rut, goles);
        this.tirosAlArco = tirosAlArco;
        this.asistencias = asistencias;
    }

    public int getTirosAlArco() { return tirosAlArco; }
    public void setTirosAlArco(int tirosAlArco) { this.tirosAlArco = tirosAlArco; }

    public int getAsistencias() { return asistencias; }
    public void setAsistencias(int asistencias) { this.asistencias = asistencias; }

    @Override
    public double calcularValoracion() {
        return (goles * 30) + (asistencias * 5) + (tirosAlArco * 2);
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" | Tiros al Arco: %d | Asistencias: %d", tirosAlArco, asistencias);
    }
}

class Portero extends Jugador {
    private int atajadas;

    public Portero(String nombre, int dorsal, String rut, int goles, int atajadas) {
        super(nombre, dorsal, rut, goles);
        this.atajadas = atajadas;
    }

    public int getAtajadas() { return atajadas; }
    public void setAtajadas(int atajadas) { this.atajadas = atajadas; }

    @Override
    public double calcularValoracion() {
        return (goles * 30) + (atajadas * 5);
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" | Atajadas: %d", atajadas);
    }
}

public class Problema_3_JugadorEjecutor {
    public static void main(String[] args) {
        ArrayList<Jugador> jugadores = new ArrayList<>();

        jugadores.add(new Defensor("Carlos Ruiz", 4, "12345678-9", 1, 25, 10));
        jugadores.add(new Atacante("Luis Torres", 9, "98765432-1", 2, 18, 6));
        jugadores.add(new Portero("Miguel Pérez", 1, "11223344-5", 0, 12));

        for (Jugador jugador : jugadores) {
            System.out.println(jugador);
        }
    }
}

/**
 * run:
 *Jugador: Carlos Ruiz | Dorsal: 4 | Rut: 12345678-9 | Goles: 1 | Valoraci�n: 70,00 | Pases Exitosos: 25 | Recuperaciones: 10
 *Jugador: Luis Torres | Dorsal: 9 | Rut: 98765432-1 | Goles: 2 | Valoraci�n: 126,00 | Tiros al Arco: 18 | Asistencias: 6
 *Jugador: Miguel P�rez | Dorsal: 1 | Rut: 11223344-5 | Goles: 0 | Valoraci�n: 60,00 | Atajadas: 12
 BUILD SUCCESSFUL (total time: 2 seconds)
 *
 */
