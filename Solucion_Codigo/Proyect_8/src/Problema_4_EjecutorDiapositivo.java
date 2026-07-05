import java.util.ArrayList;

/**
 * Problema 4 - Sistema de monitoreo de impactos del cambio climático en Ecuador
 * Una red de monitoreo ambiental tiene como objetivo registrar, analizar y reportar los 
 * impactos del cambio climático en diferentes regiones. En cada ubicación se instalan dispositivos 
 * capaces de medir distintos indicadores climáticos como temperatura, precipitación, c
 * alidad del aire, y humedad del suelo. Dependiendo de la región (costa, sierra y oriente), 
 * los dispositivos pueden variar en capacidades y protocolos de recolección.

 *Los datos recolectados deben almacenarse y analizarse periódicamente. Además, 
 * ciertas ubicaciones requieren generar reportes personalizados que destaquen riesgos ambientales como sequías, 
 * deslizamientos o contaminación del aire. Algunos dispositivos pueden comportarse de forma 
 * especializada para detectar únicamente ciertos tipos de indicadores dependiendo de la región (costa, sierra y oriente).

 *Requisitos funcionales:
 *Representar diferentes tipos de dispositivos y sus especializaciones, para la costa, sierra y oriente.
 *Implementar métodos polimórficos que permitan procesar los datos según los tipos de dispositivos y sus especializaciones, 
 * para la costa, sierra y oriente.
 *Generar reportes dinámicos en función del tipo de riesgo ambiental detectado según la región
Note

 *Plantee una solución polimórfica dada una jerarquía de clases con ventajas de herencia. Y para la generación de reportería, 
 * use los toString() base.
 *
 * @author DENNIS MACAS
 * @version 1.0
 */

abstract class Dispositivo {
    private String codigo;
    private String ubicacion;
    private double temperatura;
    private double precipitacion;
    private double calidadAire;
    private double humedadSuelo;

    public Dispositivo(String codigo, String ubicacion, double temperatura, double precipitacion, double calidadAire, double humedadSuelo) {
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.temperatura = temperatura >= 0 ? temperatura : 0;
        this.precipitacion = precipitacion >= 0 ? precipitacion : 0;
        this.calidadAire = calidadAire >= 0 ? calidadAire : 0;
        this.humedadSuelo = humedadSuelo >= 0 ? humedadSuelo : 0;
    }

    public String getCodigo() { return codigo; }
    public String getUbicacion() { return ubicacion; }
    public double getTemperatura() { return temperatura; }
    public double getPrecipitacion() { return precipitacion; }
    public double getCalidadAire() { return calidadAire; }
    public double getHumedadSuelo() { return humedadSuelo; }

    public abstract void procesarDatos();
    public abstract String generarReporte();

    @Override
    public String toString() {
        return String.format("Código: %s | Ubicación: %s | Temp: %.2f°C | Precipitación: %.2fmm | Aire: %.2f | Humedad: %.2f",
                codigo, ubicacion, temperatura, precipitacion, calidadAire, humedadSuelo);
    }
}

class DispositivoCosta extends Dispositivo {
    private boolean riesgoSequia;
    private static final double LIMITE_PRECIPITACION = 20;
    private static final double LIMITE_HUMEDAD = 30;

    public DispositivoCosta(String codigo, String ubicacion, double temperatura, double precipitacion, double calidadAire, double humedadSuelo) {
        super(codigo, ubicacion, temperatura, precipitacion, calidadAire, humedadSuelo);
    }

    @Override
    public void procesarDatos() {
        riesgoSequia = (getPrecipitacion() < LIMITE_PRECIPITACION && getHumedadSuelo() < LIMITE_HUMEDAD);
    }

    @Override
    public String generarReporte() {
        return toString() + (riesgoSequia ? " | Riesgo: Sequía" : " | Riesgo: Bajo");
    }
}

class DispositivoSierra extends Dispositivo {
    private boolean riesgoDeslizamiento;
    private static final double LIMITE_PRECIPITACION = 100;
    private static final double LIMITE_HUMEDAD = 70;

    public DispositivoSierra(String codigo, String ubicacion, double temperatura, double precipitacion, double calidadAire, double humedadSuelo) {
        super(codigo, ubicacion, temperatura, precipitacion, calidadAire, humedadSuelo);
    }

    @Override
    public void procesarDatos() {
        riesgoDeslizamiento = (getPrecipitacion() > LIMITE_PRECIPITACION && getHumedadSuelo() > LIMITE_HUMEDAD);
    }

    @Override
    public String generarReporte() {
        return toString() + (riesgoDeslizamiento ? " | Riesgo: Deslizamiento" : " | Riesgo: Bajo");
    }
}

class DispositivoOriente extends Dispositivo {
    private boolean riesgoContaminacion;
    private static final double LIMITE_CALIDAD_AIRE = 150;

    public DispositivoOriente(String codigo, String ubicacion, double temperatura, double precipitacion, double calidadAire, double humedadSuelo) {
        super(codigo, ubicacion, temperatura, precipitacion, calidadAire, humedadSuelo);
    }

    @Override
    public void procesarDatos() {
        riesgoContaminacion = (getCalidadAire() > LIMITE_CALIDAD_AIRE);
    }

    @Override
    public String generarReporte() {
        return toString() + (riesgoContaminacion ? " | Riesgo: Contaminación" : " | Riesgo: Bajo");
    }
}

class Reporte {
    private ArrayList<Dispositivo> dispositivos;

    public Reporte() {
        this.dispositivos = new ArrayList<>();
    }

    public void agregarDispositivo(Dispositivo dispositivo) {
        if (dispositivo != null) {
            dispositivos.add(dispositivo);
        }
    }

    public String generarReportes() {
        StringBuilder sb = new StringBuilder();
        for (Dispositivo d : dispositivos) {
            d.procesarDatos();
            sb.append(d.generarReporte()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return generarReportes();
    }
}

public class Problema_4_EjecutorDiapositivo {
    public static void main(String[] args) {
        Reporte reporte = new Reporte();

        Dispositivo costa = new DispositivoCosta("C001", "Guayaquil", 34.5, 15.0, 80.0, 25.0);
        Dispositivo sierra = new DispositivoSierra("S001", "Quito", 18.0, 120.0, 60.0, 75.0);
        Dispositivo oriente = new DispositivoOriente("O001", "Tena", 28.0, 90.0, 160.0, 50.0);

        reporte.agregarDispositivo(costa);
        reporte.agregarDispositivo(sierra);
        reporte.agregarDispositivo(oriente);

        System.out.println(reporte);
    }
}

/**
 * run:
 *C�digo: C001 | Ubicaci�n: Guayaquil | Temp: 34,50�C | Precipitaci�n: 15,00mm | Aire: 80,00 | Humedad: 25,00 | Riesgo: Sequ�a
 *C�digo: S001 | Ubicaci�n: Quito | Temp: 18,00�C | Precipitaci�n: 120,00mm | Aire: 60,00 | Humedad: 75,00 | Riesgo: Deslizamiento
 *C�digo: O001 | Ubicaci�n: Tena | Temp: 28,00�C | Precipitaci�n: 90,00mm | Aire: 160,00 | Humedad: 50,00 | Riesgo: Contaminaci�n

BUILD SUCCESSFUL (total time: 2 seconds)
 *
 */


