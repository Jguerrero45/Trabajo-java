package conversor;
import java.text.DecimalFormat;
public abstract class Padre {
    protected String comboBox2;
    protected String comboBox3;
    protected String texto1;
    protected String texto2;
    protected String textoComboBox;
    protected String formula; // Variable para almacenar la fórmula

    public Padre(String comboBox2, String comboBox3, String texto1, String texto2, String textoComboBox) {
        this.comboBox2 = comboBox2;
        this.comboBox3 = comboBox3;
        this.texto1 = texto1;
        this.texto2 = texto2;
        this.textoComboBox = textoComboBox;
    }

    public void realizarAccion() {
        // Convierte textoComboBox a minúsculas una vez y reutilízalo
        String opcion = textoComboBox.toLowerCase();

        texto2 = opcion.equals("area") ? convertirArea() :
                opcion.equals("angulo plano") ? convertirAnguloPlano() :
                "Conversión no válida";
    }


    private String convertirArea() {
        Area area = new Area(comboBox2, comboBox3, texto1, texto2, textoComboBox);
        area.realizarAccion();
        this.formula = area.getFormula(); // Almacena la fórmula en el campo 'formula'
        return area.getTexto2();
    }

    private String convertirAnguloPlano() {
        AnguloPlano anguloPlano = new AnguloPlano(comboBox2, comboBox3, texto1, texto2, textoComboBox);
        anguloPlano.realizarAccion();
        this.formula = anguloPlano.getFormula(); // Almacena la fórmula en el campo 'formula'
        return anguloPlano.getTexto2();
    }

    // Método para obtener la fórmula (puede ser abstracto si las clases hijas deben implementarlo)
    public String getFormula() {
        return formula; // Devuelve la fórmula calculada
    }

    public String getTexto2() {
        DecimalFormat formato = new DecimalFormat("#,##0.###E0");
        return formato.format(Double.parseDouble(texto2));
    }
}
