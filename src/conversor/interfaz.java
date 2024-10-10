package conversor;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.*;

public class interfaz extends javax.swing.JFrame {

    private Padre conversor;
    // Variables para almacenar las últimas selecciones de jComboBox1 y jComboBox2
    private String ultimaSeleccionE1; // Almacena la última selección de jComboBox1
    private String ultimaSeleccionE2; // Almacena la última selección de jComboBox2
    // Instancia de ComboBoxSwitcher que se encargará de evitar selecciones duplicadas
    private ComboBoxSwitcher comboBoxSwitcher;

    public interfaz() {
        initComponents();
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Area", "Angulo plano"}));

        // Inicializar jComboBox1 y jComboBox2 con las opciones de "Area" por defecto
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "Kilómetro cuadrado", "Metro cuadrado", "Milla cuadrada", "Yarda cuadrada",
            "Pie cuadrado", "Pulgada cuadrada", "Hectárea", "Acre"
        }));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "Kilómetro cuadrado", "Metro cuadrado", "Milla cuadrada", "Yarda cuadrada",
            "Pie cuadrado", "Pulgada cuadrada", "Hectárea", "Acre"
        }));

        // Configuración inicial de las selecciones
        ultimaSeleccionE1 = (String) jComboBox1.getSelectedItem(); // Se inicializa con la selección actual de jComboBox1
        ultimaSeleccionE2 = (String) jComboBox2.getSelectedItem(); // Se inicializa con la selección actual de jComboBox2
        comboBoxSwitcher = new ComboBoxSwitcher(); // Inicialización de la clase ComboBoxSwitcher

        // Añadir listeners para los JComboBox
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);

                realizarConversion();
            }
        });

        // Listener para jComboBox1 que actualiza la selección y llama a intercambiarOpciones
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String seleccionAnteriorE1 = ultimaSeleccionE1; // Almacena la selección anterior de jComboBox1
                ultimaSeleccionE1 = (String) jComboBox1.getSelectedItem(); // Actualiza con la nueva selección
                // Llama a intercambiarOpciones para evitar duplicados
                comboBoxSwitcher.intercambiarOpciones(jComboBox1, jComboBox2, seleccionAnteriorE1, ultimaSeleccionE2);
                realizarConversion();
            }
        });

        // Listener para jComboBox2 que actualiza la selección y llama a intercambiarOpciones
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String seleccionAnteriorE2 = ultimaSeleccionE2; // Almacena la selección anterior de jComboBox2
                ultimaSeleccionE2 = (String) jComboBox2.getSelectedItem(); // Actualiza con la nueva selección
                // Llama a intercambiarOpciones para evitar duplicados
                comboBoxSwitcher.intercambiarOpciones(jComboBox2, jComboBox1, seleccionAnteriorE2, ultimaSeleccionE1);
                realizarConversion();
            }
        });

        // Añadir DocumentListener a jTextField1
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                realizarConversion();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                realizarConversion(); // Realiza la conversión al eliminar texto
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

                realizarConversion(); // Realiza la conversión al cambiar el texto
            }
        });
        // Centrar la ventana y deshabilitar el redimensionado
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // Clase estática ComboBoxSwitcher que evita que los dos JComboBox seleccionen el mismo valor
    static class ComboBoxSwitcher {

        // Método que intercambia las opciones si ambas selecciones son iguales
        public void intercambiarOpciones(javax.swing.JComboBox<String> comboBox1, javax.swing.JComboBox<String> comboBox2, String seleccionAnterior1, String seleccionAnterior2) {
            String seleccion1 = (String) comboBox1.getSelectedItem(); // Selección actual de comboBox1
            String seleccion2 = (String) comboBox2.getSelectedItem(); // Selección actual de comboBox2

            // Si ambas selecciones son iguales, restaurar las selecciones anteriores
            // Usamos una expresión ternaria para restaurar las selecciones si son iguales
            // Si seleccion1 no es nulo y es igual a seleccion2, se restauran las selecciones anteriores
            boolean sonIguales = seleccion1 != null && seleccion1.equals(seleccion2);

            comboBox1.setSelectedItem(sonIguales ? seleccionAnterior2 : seleccion1);
            comboBox2.setSelectedItem(sonIguales ? seleccionAnterior1 : seleccion2);

        }
    }

    // Método que actualiza los valores de jComboBox1 y jComboBox2 dependiendo de la selección de jComboBox3
    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedItem = (String) jComboBox3.getSelectedItem();

        // Se establece el modelo del jComboBox1 y jComboBox2 basado en el elemento seleccionado
        String[] areas = new String[]{
            "Kilómetro cuadrado", "Metro cuadrado", "Milla cuadrada", "Yarda cuadrada",
            "Pie cuadrado", "Pulgada cuadrada", "Hectárea", "Acre"
        };

        String[] angulos = new String[]{
            "Grado", "Grado centesimal", "Milirradián", "Minuto de arco",
            "Radián", "Segundo de arco"
        };

        // Asigna los modelos de los comboBox según el elemento seleccionado utilizando expresiones ternarias
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(
                "Area".equals(selectedItem) ? areas
                : "Angulo plano".equals(selectedItem) ? angulos
                : new String[]{} // Valor por defecto si no coincide
        ));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(
                "Area".equals(selectedItem) ? areas
                : "Angulo plano".equals(selectedItem) ? angulos
                : new String[]{} // Valor por defecto si no coincide
        ));
    }

    private void realizarConversion() {
        try {
            String unidadOrigen = (String) jComboBox1.getSelectedItem();
            String unidadDestino = (String) jComboBox2.getSelectedItem();
            String valorTexto = jTextField1.getText();
            String tipoConversion = (String) jComboBox3.getSelectedItem();

            // Instancia el conversor según el tipo de conversión seleccionada usando una expresión ternaria
            conversor = tipoConversion.equals("Area")
                    ? new Area(unidadOrigen, unidadDestino, valorTexto, "", tipoConversion)
                    : new AnguloPlano(unidadOrigen, unidadDestino, valorTexto, "", tipoConversion);

            conversor.realizarAccion();

            jTextField2.setText(conversor.getTexto2());
            jLabel3.setText(conversor.getFormula()); // Muestra la fórmula en el JLabel
        } catch (NumberFormatException e) {
            jTextField2.setText("Error");
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jTextField1.setBackground(new java.awt.Color(102, 102, 102));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setBackground(new java.awt.Color(102, 102, 102));
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jComboBox1.setBackground(new java.awt.Color(102, 102, 102));
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setBackground(new java.awt.Color(102, 102, 102));
        jComboBox2.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORMULA: ");

        jComboBox3.setBackground(new java.awt.Color(102, 102, 102));
        jComboBox3.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setBorder(null);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("k");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, 117, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox2, 0, 117, Short.MAX_VALUE)
                    .addComponent(jTextField2))
                .addGap(157, 157, 157))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
