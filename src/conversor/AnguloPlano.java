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

    private double convertirAgrados(double valor, String unidad) {
        switch (unidad) {
            case "Grado":
                return valor;
            case "Grado centesimal":
                return valor * 0.9;
            case "Milirradián":
                return valor * 0.0572958;
            case "Minuto de arco":
                return valor / 60;
            case "Radián":
                return valor * 57.2958;
            case "Segundo de arco":
                return valor / 3600;
            default:
                throw new IllegalArgumentException("Unidad desconocida: " + unidad);
        }
    }

    private double convertirDesdeGrados(double valor, String unidad) {
        switch (unidad) {
            case "Grado":
                return valor;
            case "Grado centesimal":
                return valor / 0.9;
            case "Milirradián":
                return valor / 0.0572958;
            case "Minuto de arco":
                return valor * 60;
            case "Radián":
                return valor / 57.2958;
            case "Segundo de arco":
                return valor * 3600;
            default:
                throw new IllegalArgumentException("Unidad desconocida: " + unidad);
        }
    }
}