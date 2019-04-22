package es.studium.PracticaMVC;

import java.awt.Choice;
import java.awt.GridLayout;

import javax.swing.*;

public class ClaseVistaAltaAsignacion extends JFrame {


	private static final long serialVersionUID = 1L;
	
	Choice listaOfertas = new Choice();
	Choice demandantes = new Choice();
	
	JLabel labelFecha = new JLabel("Fecha:");
	JLabel labelOferta = new JLabel("Oferta:");
	JLabel labelDemandante = new JLabel("Demandante:");

	JTextField txtFecha = new JTextField(10);
	
	JButton btnAceptar = new JButton("Aceptar");
	JButton btnCancelar = new JButton("Cancelar");
	
	JPanel panelFecha = new JPanel();
	JPanel panelOferta = new JPanel();
	JPanel panelDemandante = new JPanel();
	JPanel panelBotones = new JPanel();
	
	
	public ClaseVistaAltaAsignacion() {
		this.setTitle("Alta Asignación");
		this.setSize(450,250);
		this.setLayout(new GridLayout(4,1));
		
		listaOfertas.add("Elegir una...");
		demandantes.add("Elegir uno...");
		
		panelFecha.add(labelFecha);
		panelFecha.add(txtFecha);
		panelOferta.add(labelOferta);
		panelOferta.add(listaOfertas);
		panelDemandante.add(labelDemandante);
		panelDemandante.add(demandantes);
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);
		
		add(panelFecha);
		add(panelOferta);
		add(panelDemandante);
		add(panelBotones);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}
}