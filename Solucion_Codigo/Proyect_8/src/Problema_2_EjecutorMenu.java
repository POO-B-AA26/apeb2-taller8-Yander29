import java.util.ArrayList;

/**
*Problema 2 - Gestión de menus en un Restaurant
*En un restaurant se tiene diferentes tipos de menú para ofrecer a los clientes. 
* Una cuenta por pagar está compuesta por características como: nombre del cliente, 
* listado de todos las cartas(menú) solicitados por el cliente, valor a cancelar total, subtotal, Iva.

Los tipos de menú del restaurant son:

Menú a la carta

nombre del plato
valor del menú
valor inicial del menú
valor de porción de guarnición
valor de bebida
porcentaje adicional por servicio en relación del valor inicial del menú
Menú del día

nombre del plato
valor del menú
valor inicial del menú
valor de postre
valor de bebida
Menú de niños

nombre del plato
valor del menú
valor inicial del menú
valor de porción de helado
valor de porción de pastel
Menú económico

nombre del plato
valor del menú
valor inicial del menú
porcentaje de descuento, en referencia al valor inicial del menú
Note

Para solucionar lo anterior se debe generar lo siguiente:

Un diagrama exclusivo que involucren las clases de tipo Menú (usar polimorfismo)
Una solución en lenguaje de programación Java. Usar Polimorfismo en la solución. 
* Hacer uso del método toString() para presentar toda la información posible del objeto (nombre del cliente, 
* subtotal, iva, listado de todos los menú, valor a cancelar a total.
 * @author Dennis Macas
 * @version 1.0
 */

public class Problema_2_EjecutorMenu {
    private static ArrayList<Menu> registroMenus = new ArrayList<>();
    private static Cuenta cuenta;

    public static void main(String[] args) {
        cuenta = new Cuenta("Pedro Gómez");

        registrarMenuDirecto(new MenuCarta("Filete de res", 10.0, 3.0, 2.0, 10.0));
        registrarMenuDirecto(new MenuDia("Pollo al horno", 8.0, 2.0, 1.5));
        registrarMenuDirecto(new MenuNinos("Hamburguesa", 5.0, 1.0, 1.5));
        registrarMenuDirecto(new MenuEconomico("Arroz con huevo", 4.0, 20.0));

        cuenta.calcularSubtotal();
        cuenta.calcularIva();
        cuenta.calcularTotal();

        mostrarCuenta();
    }

    private static void registrarMenuDirecto(Menu menu) {
        registroMenus.add(menu);
        cuenta.agregarMenu(menu);
    }

    private static void mostrarCuenta() {
        System.out.println(cuenta);
    }
}

abstract class Menu {
    protected String nombrePlato;
    protected double valorInicial;
    protected double valorMenu;

    public Menu(String nombrePlato, double valorInicial) {
        this.nombrePlato = nombrePlato;
        this.valorInicial = valorInicial;
    }

    public abstract void calcularValorMenu();

    public String getNombrePlato() {
        return nombrePlato;
    }

    public double getValorInicial() {
        return valorInicial;
    }

    public double getValorMenu() {
        return valorMenu;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    @Override
    public String toString() {
        return String.format("Plato: %s | Valor Inicial: %.2f | Valor Menu: %.2f",
                nombrePlato, valorInicial, valorMenu);
    }
}

class MenuCarta extends Menu {
    private double valorGuarnicion;
    private double valorBebida;
    private double porcentajeServicio;

    public MenuCarta(String nombrePlato, double valorInicial,
                     double valorGuarnicion, double valorBebida, double porcentajeServicio) {
        super(nombrePlato, valorInicial);
        this.valorGuarnicion = valorGuarnicion;
        this.valorBebida = valorBebida;
        this.porcentajeServicio = porcentajeServicio;
        calcularValorMenu();
    }

    public double getValorGuarnicion() {
        return valorGuarnicion;
    }

    public void setValorGuarnicion(double valorGuarnicion) {
        this.valorGuarnicion = valorGuarnicion;
    }

    public double getValorBebida() {
        return valorBebida;
    }

    public void setValorBebida(double valorBebida) {
        this.valorBebida = valorBebida;
    }

    public double getPorcentajeServicio() {
        return porcentajeServicio;
    }

    public void setPorcentajeServicio(double porcentajeServicio) {
        this.porcentajeServicio = porcentajeServicio;
    }

    @Override
    public void calcularValorMenu() {
        valorMenu = valorInicial + valorGuarnicion + valorBebida
                + (valorInicial * porcentajeServicio / 100);
    }

    @Override
    public String toString() {
        return "MenuCarta{" + super.toString() +
                String.format(" | Guarnición: %.2f | Bebida: %.2f | Servicio: %.2f%%",
                        valorGuarnicion, valorBebida, porcentajeServicio) + "}";
    }
}

class MenuDia extends Menu {
    private double valorPostre;
    private double valorBebida;

    public MenuDia(String nombrePlato, double valorInicial,
                   double valorPostre, double valorBebida) {
        super(nombrePlato, valorInicial);
        this.valorPostre = valorPostre;
        this.valorBebida = valorBebida;
        calcularValorMenu();
    }

    public double getValorPostre() {
        return valorPostre;
    }

    public void setValorPostre(double valorPostre) {
        this.valorPostre = valorPostre;
    }

    public double getValorBebida() {
        return valorBebida;
    }

    public void setValorBebida(double valorBebida) {
        this.valorBebida = valorBebida;
    }

    @Override
    public void calcularValorMenu() {
        valorMenu = valorInicial + valorPostre + valorBebida;
    }

    @Override
    public String toString() {
        return "MenuDia{" + super.toString() +
                String.format(" | Postre: %.2f | Bebida: %.2f", valorPostre, valorBebida) + "}";
    }
}

class MenuNinos extends Menu {
    private double valorHelado;
    private double valorPastel;

    public MenuNinos(String nombrePlato, double valorInicial,
                     double valorHelado, double valorPastel) {
        super(nombrePlato, valorInicial);
        this.valorHelado = valorHelado;
        this.valorPastel = valorPastel;
        calcularValorMenu();
    }

    public double getValorHelado() {
        return valorHelado;
    }

    public void setValorHelado(double valorHelado) {
        this.valorHelado = valorHelado;
    }

    public double getValorPastel() {
        return valorPastel;
    }

    public void setValorPastel(double valorPastel) {
        this.valorPastel = valorPastel;
    }

    @Override
    public void calcularValorMenu() {
        valorMenu = valorInicial + valorHelado + valorPastel;
    }

    @Override
    public String toString() {
        return "MenuNinos{" + super.toString() +
                String.format(" | Helado: %.2f | Pastel: %.2f", valorHelado, valorPastel) + "}";
    }
}

class MenuEconomico extends Menu {
    private double porcentajeDescuento;

    public MenuEconomico(String nombrePlato, double valorInicial, double porcentajeDescuento) {
        super(nombrePlato, valorInicial);
        this.porcentajeDescuento = porcentajeDescuento;
        calcularValorMenu();
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public void calcularValorMenu() {
        valorMenu = valorInicial - (valorInicial * porcentajeDescuento / 100);
    }

    @Override
    public String toString() {
        return "MenuEconomico{" + super.toString() +
                String.format(" | Descuento: %.2f%%", porcentajeDescuento) + "}";
    }
}

class Cuenta {
    private String nombreCliente;
    private ArrayList<Menu> listaMenus;
    private double subtotal;
    private double iva;
    private double total;

    public Cuenta(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        listaMenus = new ArrayList<>();
    }

    public void agregarMenu(Menu menu) {
        listaMenus.add(menu);
    }

    public void calcularSubtotal() {
        subtotal = 0;
        for (Menu m : listaMenus) {
            subtotal += m.getValorMenu();
        }
    }

    public void calcularIva() {
        iva = subtotal * 0.12;
    }

    public void calcularTotal() {
        total = subtotal + iva;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIva() {
        return iva;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(nombreCliente).append("\n");
        for (Menu m : listaMenus) {
            sb.append(m.toString()).append("\n");
        }
        sb.append(String.format("Subtotal: %.2f | IVA: %.2f | Total: %.2f",
                subtotal, iva, total));
        return sb.toString();
    }
}

/**
 * 
 * run:
Cliente: Pedro G�mez
MenuCarta{Plato: Filete de res | Valor Inicial: 10,00 | Valor Menu: 16,00 | Guarnici�n: 3,00 | Bebida: 2,00 | Servicio: 10,00%}
MenuDia{Plato: Pollo al horno | Valor Inicial: 8,00 | Valor Menu: 11,50 | Postre: 2,00 | Bebida: 1,50}
MenuNinos{Plato: Hamburguesa | Valor Inicial: 5,00 | Valor Menu: 7,50 | Helado: 1,00 | Pastel: 1,50}
MenuEconomico{Plato: Arroz con huevo | Valor Inicial: 4,00 | Valor Menu: 3,20 | Descuento: 20,00%}
Subtotal: 38,20 | IVA: 4,58 | Total: 42,78
BUILD SUCCESSFUL (total time: 2 seconds)
* 
*/