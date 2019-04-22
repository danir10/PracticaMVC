package es.studium.PracticaMVC;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClaseVistaConsultaOfertas extends JFrame {


	private static final long serialVersionUID = 1L;
	DefaultTableModel modelo = new DefaultTableModel();
	
	JTable tablaOfertas = new JTable(modelo);
	
	JButton btnAceptar = new JButton("Aceptar");
	
	JPanel panelboton = new JPanel();
	
	public ClaseVistaConsultaOfertas() {
		this.setSize(500,200);
		this.setTitle("Consulta Ofertas");
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tablaOfertas),BorderLayout.CENTER);
		panelboton.add(btnAceptar);
		this.add(panelboton,BorderLayout.SOUTH);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}



}