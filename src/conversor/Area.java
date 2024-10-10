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

    private double convertirAmetrosCuadrados(double valor, String unidad) {
        formula = ""; // Inicia la fórmula como cadena vacía
        switch (unidad) {
            case "Kilómetro cuadrado":
                formula = "k x " + 1e6;
                return valor * 1e6;
            case "Metro cuadrado":
                formula = "k ";
                return valor;
            case "Milla cuadrada":
                formula = "k x " + 2.59e6;
                return valor * 2.59e6;
            case "Yarda cuadrada":
                formula = "k x " + 0.836127;
                return valor * 0.836127;
            case "Pie cuadrado":
                formula = "k x " + 0.092903;
                return valor * 0.092903;
            case "Pulgada cuadrada":
                formula = "k x " + 0.00064516;
                return valor * 0.00064516;
            case "Hectárea":
                formula = "k x " + 1e4;
                return valor * 1e4;
            case "Acre":
                formula = "k x " + 4046.86;
                return valor * 4046.86;
            default:
                throw new IllegalArgumentException("Unidad desconocida: " + unidad);
        }
    }

    private double convertirDesdeMetrosCuadrados(double valor, String unidad) {
        switch (unidad) {
            case "Kilómetro cuadrado":
                formula += " / " + 1e6;
                return valor / 1e6;
            case "Metro cuadrado":
                formula += "";
                return valor;
            case "Milla cuadrada":
                formula += " / " + 2.59e6;
                return valor / 2.59e6;
            case "Yarda cuadrada":
                formula += " / " + 0.836127;
                return valor / 0.836127;
            case "Pie cuadrado":
                formula += " / " + 0.092903;
                return valor / 0.092903;
            case "Pulgada cuadrada":
                formula += " / " + 0.00064516;
                return valor / 0.00064516;
            case "Hectárea":
                formula += " / " + 1e4;
                return valor / 1e4;
            case "Acre":
                formula += " / " + 4046.86;
                return valor / 4046.86;
            default:
                throw new IllegalArgumentException("Unidad desconocida: " + unidad);
        }
    }
}
