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
        switch (unidad) {
            case "Kilómetro cuadrado":
                return valor * 1e6;
            case "Metro cuadrado":
                return valor;
            case "Milla cuadrada":
                return valor * 2.59e6;
            case "Yarda cuadrada":
                return valor * 0.836127;
            case "Pie cuadrado":
                return valor * 0.092903;
            case "Pulgada cuadrada":
                return valor * 0.00064516;
            case "Hectárea":
                return valor * 1e4;
            case "Acre":
                return valor * 4046.86;
            default:
                throw new IllegalArgumentException("Unidad desconocida: " + unidad);
        }
    }

    private double convertirDesdeMetrosCuadrados(double valor, String unidad) {
        switch (unidad) {
            case "Kilómetro cuadrado":
                return valor / 1e6;
            case "Metro cuadrado":
                return valor;
            case "Milla cuadrada":
                return valor / 2.59e6;
            case "Yarda cuadrada":
                return valor / 0.836127;
            case "Pie cuadrado":
                return valor / 0.092903;
            case "Pulgada cuadrada":
                return valor / 0.00064516;
            case "Hectárea":
                return valor / 1e4;
            case "Acre":
                return valor / 4046.86;
            default:
                throw new IllegalArgumentException("Unidad desconocida: " + unidad);
        }
    }
}