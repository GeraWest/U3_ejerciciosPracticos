package Rey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Aplicación para gestionar personas presentes usando Set y Swing.
 * 
 */
public class EntornoApp extends JFrame {

    private Set<String> personas = new HashSet<>();

    // Componentes
    private DefaultListModel<String> modeloLista;
    private JList<String> listaPersonas;
    private JTextField txtNombre;
    private JButton btnAgregar, btnEliminar, btnLimpiar, btnVerificar;

    public EntornoApp() {
        setTitle("Gestión de Personas Presentes");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🎨 PALETA VERDE
        Color verdeClaro = new Color(185, 245, 185);
        Color verdeMedio = new Color(76, 175, 80);
        Color verdeOscuro = new Color(27, 94, 32);

        // Panel principal con fondo VERDE
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(verdeClaro);
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(panelPrincipal);

        // Lista de personas
        modeloLista = new DefaultListModel<>();
        listaPersonas = new JList<>(modeloLista);
        listaPersonas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(listaPersonas);
        scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(verdeOscuro, 2),
                "Personas Presentes"));

        panelPrincipal.add(scroll, BorderLayout.CENTER);

        // Panel de controles
        JPanel panelControles = new JPanel();
        panelControles.setLayout(new GridLayout(2, 2, 10, 10));
        panelControles.setBackground(verdeClaro);

        txtNombre = new JTextField();
        txtNombre.setBorder(BorderFactory.createTitledBorder("Nombre"));

        btnAgregar = crearBoton("Agregar", verdeMedio, verdeOscuro);
        btnEliminar = crearBoton("Eliminar", verdeMedio, verdeOscuro);
        btnLimpiar = crearBoton("Limpiar", verdeMedio, verdeOscuro);
        btnVerificar = crearBoton("Verificar Persona", verdeMedio, verdeOscuro);

        panelControles.add(txtNombre);
        panelControles.add(btnAgregar);
        panelControles.add(btnEliminar);
        panelControles.add(btnVerificar);

        panelPrincipal.add(panelControles, BorderLayout.SOUTH);

        // Botón limpiar arriba
        JButton btnLimpiarSuperior = crearBoton("Limpiar Todo", verdeMedio, verdeOscuro);
        btnLimpiarSuperior.addActionListener(e -> limpiarPersonas());
        panelPrincipal.add(btnLimpiarSuperior, BorderLayout.NORTH);

        // Eventos
        btnAgregar.addActionListener(e -> agregarPersona());
        btnEliminar.addActionListener(e -> eliminarPersona());
        btnLimpiar.addActionListener(e -> limpiarPersonas());
        btnVerificar.addActionListener(e -> verificarPersona());
    }

    // 🔹 Crear botón con efecto hover en VERDE
    private JButton crearBoton(String txt, Color colorBase, Color colorHover) {
        JButton b = new JButton(txt);

        b.setBackground(colorBase);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(colorHover, 2));

        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { b.setBackground(colorHover); }

            @Override
            public void mouseExited(MouseEvent e) { b.setBackground(colorBase); }
        });

        return b;
    }

    // Agregar persona
    private void agregarPersona() {
        String nombre = txtNombre.getText().trim();
        if (!nombre.isEmpty()) {
            if (personas.add(nombre)) {
                modeloLista.addElement(nombre);
            } else {
                JOptionPane.showMessageDialog(this, 
                        "La persona ya está en la lista.", 
                        "Información", 
                        JOptionPane.WARNING_MESSAGE);
            }
            txtNombre.setText("");
        }
    }

    // Eliminar persona
    private void eliminarPersona() {
        String seleccion = listaPersonas.getSelectedValue();
        if (seleccion != null) {
            personas.remove(seleccion);
            modeloLista.removeElement(seleccion);
        } else {
            JOptionPane.showMessageDialog(this, 
                    "Seleccione una persona para eliminar.", 
                    "Aviso", 
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // Limpiar lista
    private void limpiarPersonas() {
        personas.clear();
        modeloLista.clear();
    }

    // Verificar persona
    private void verificarPersona() {
        String nombre = txtNombre.getText().trim();
        if (!nombre.isEmpty()) {
            boolean existe = personas.contains(nombre);
            JOptionPane.showMessageDialog(this,
                    nombre + (existe ? " está presente." : " NO está presente."),
                    "Verificación",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EntornoApp().setVisible(true));
    }
}

