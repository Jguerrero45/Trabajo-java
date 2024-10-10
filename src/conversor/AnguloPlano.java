package conversor;

public class AnguloPlano extends Padre {

    public AnguloPlano(String comboBox2, String comboBox3, String texto1, String texto2, String textoComboBox) {
        super(comboBox2, comboBox3, texto1, texto2, textoComboBox);
    }

    @Override
    public void realizarAccion() {
        double valor = Double.parseDouble(texto1);
        double resultado = convertir(valor, comboBox2, comboBox3);
        this.texto2 = String.valueOf(resultado);
    }

    private double convertir(double valor, String unidadOrigen, String unidadDestino) {
        double valorEnGrados = convertirAgrados(valor, unidadOrigen);
        return convertirDesdeGrados(valorEnGrados, unidadDestino);
    }

    // Función recursiva con ternarias para convertir a grados
    private double convertirAgrados(double valor, String unidad) {
        formula = ""; // Inicia la fórmula como cadena vacía
        return unidad.equals("Grado") ? setFormula(valor, "k") :
               unidad.equals("Grado centesimal") ? setFormula(valor * 0.9, "k x 0.9") :
               unidad.equals("Milirradián") ? setFormula(valor * 0.0572958, "k x 0.0572958") :
               unidad.equals("Minuto de arco") ? setFormula(valor / 60, "k / 60") :
               unidad.equals("Radián") ? setFormula(valor * 57.2958, "k x 57.2958") :
               unidad.equals("Segundo de arco") ? setFormula(valor / 3600, "k / 3600") :
               lanzarExcepcionUnidadDesconocida(unidad);
    }

    // Función recursiva con ternarias para convertir desde grados
    private double convertirDesdeGrados(double valor, String unidad) {
        return unidad.equals("Grado") ? setFormula(valor, formula) :
               unidad.equals("Grado centesimal") ? setFormula(valor / 0.9, formula + " / 0.9") :
               unidad.equals("Milirradián") ? setFormula(valor / 0.0572958, formula + " / 0.0572958") :
               unidad.equals("Minuto de arco") ? setFormula(valor * 60, formula + " x 60") :
               unidad.equals("Radián") ? setFormula(valor / 57.2958, formula + " / 57.2958") :
               unidad.equals("Segundo de arco") ? setFormula(valor * 3600, formula + " x 3600") :
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
