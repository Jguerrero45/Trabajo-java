package conversor;

public class Padre {
    protected String comboBox2;
    protected String comboBox3;
    protected String texto1;
    protected String texto2;
    protected String textoComboBox;

    public Padre(String comboBox2, String comboBox3, String texto1, String texto2, String textoComboBox) {
        this.comboBox2 = comboBox2;
        this.comboBox3 = comboBox3;
        this.texto1 = texto1;
        this.texto2 = texto2;
        this.textoComboBox = textoComboBox;
    }

    public void realizarAccion() {
        // Realiza la conversión según el textoComboBox usando expresiones ternarias
        texto2 = textoComboBox.toLowerCase().equals("area") 
            ? convertirArea() 
            : textoComboBox.toLowerCase().equals("angulo plano") 
                ? convertirAnguloPlano() 
                : "Conversión no válida";
    }

    private String convertirArea() {
        Area area = new Area(comboBox2, comboBox3, texto1, texto2, textoComboBox);
        area.realizarAccion();
        return area.getTexto2();
    }

    private String convertirAnguloPlano() {
        AnguloPlano anguloPlano = new AnguloPlano(comboBox2, comboBox3, texto1, texto2, textoComboBox);
        anguloPlano.realizarAccion();
        return anguloPlano.getTexto2();
    }

    public String getTexto2() {
        return texto2;
    }
}
