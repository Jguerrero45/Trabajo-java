package conversor;

public class Area extends Padre {

    public Area(String comboBox2, String comboBox3, String texto1, String texto2, String textoComboBox) {
        super(comboBox2, comboBox3, texto1, texto2, textoComboBox);
    }

    @Override
    public void realizarAccion() {
        double valor = Double.parseDouble(texto1);
        double resultado = convertir(valor, comboBox2, comboBox3);
        this.texto2 = String.valueOf(resultado);
    }

    private double convertir(double valor, String unidadOrigen, String unidadDestino) {
        double valorEnMetrosCuadrados = convertirAmetrosCuadrados(valor, unidadOrigen);
        return convertirDesdeMetrosCuadrados(valorEnMetrosCuadrados, unidadDestino);
    }

    // Función recursiva con ternarias para convertir a metros cuadrados
    private double convertirAmetrosCuadrados(double valor, String unidad) {
        formula = ""; // Inicia la fórmula como cadena vacía
        return unidad.equals("Kilómetro cuadrado") ? setFormula(valor * 1e6, "k x 1e6") :
               unidad.equals("Metro cuadrado") ? setFormula(valor, "k") :
               unidad.equals("Milla cuadrada") ? setFormula(valor * 2.59e6, "k x 2.59e6") :
               unidad.equals("Yarda cuadrada") ? setFormula(valor * 0.836127, "k x 0.836127") :
               unidad.equals("Pie cuadrado") ? setFormula(valor * 0.092903, "k x 0.092903") :
               unidad.equals("Pulgada cuadrada") ? setFormula(valor * 0.00064516, "k x 0.00064516") :
               unidad.equals("Hectárea") ? setFormula(valor * 1e4, "k x 1e4") :
               unidad.equals("Acre") ? setFormula(valor * 4046.86, "k x 4046.86") :
               lanzarExcepcionUnidadDesconocida(unidad);
    }

    // Función recursiva con ternarias para convertir desde metros cuadrados
    private double convertirDesdeMetrosCuadrados(double valor, String unidad) {
        return unidad.equals("Kilómetro cuadrado") ? setFormula(valor / 1e6, formula + " / 1e6") :
               unidad.equals("Metro cuadrado") ? setFormula(valor, formula) :
               unidad.equals("Milla cuadrada") ? setFormula(valor / 2.59e6, formula + " / 2.59e6") :
               unidad.equals("Yarda cuadrada") ? setFormula(valor / 0.836127, formula + " / 0.836127") :
               unidad.equals("Pie cuadrado") ? setFormula(valor / 0.092903, formula + " / 0.092903") :
               unidad.equals("Pulgada cuadrada") ? setFormula(valor / 0.00064516, formula + " / 0.00064516") :
               unidad.equals("Hectárea") ? setFormula(valor / 1e4, formula + " / 1e4") :
               unidad.equals("Acre") ? setFormula(valor / 4046.86, formula + " / 4046.86") :
               lanzarExcepcionUnidadDesconocida(unidad);
    }

    // Método auxiliar para establecer la fórmula y devolver el resultado de conversión
    private double setFormula(double resultado, String formulaCalculada) {
        formula = formulaCalculada;
        return resultado;
    }

    // Método para lanzar la excepción de unidad desconocida
    private double lanzarExcepcionUnidadDesconocida(String unidad) {
        throw new IllegalArgumentException("Unidad desconocida: " + unidad);
    }
}
