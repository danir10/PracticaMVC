package es.studium.PracticaMVC;

import java.awt.GridLayout;

import javax.swing.*;

public class ClaseVistaEdicionOferta extends JFrame {

	
	private static final long serialVersionUID = 1L;
	String idOf;
	JLabel labelOferta = new JLabel("Oferta:");
	JLabel labelNumOferta = new JLabel("");
	JLabel labelFecha = new JLabel ("Fecha:");
	JLabel labelFechaFin = new JLabel ("Fecha Fin:");
	JLabel labelRequisitos = new JLabel ("Requisitos:");
	JTextField textFieldFecha = new JTextField(10);
	JTextField textFieldFechaFin = new JTextField(10);
	JTextField textfieldRequisitos = new JTextField(10);
	JButton botonActualizar = new JButton("Actualizar");
	JButton botonCancelar = new JButton("Cancelar");

	JPanel panelOferta = new JPanel();
	JPanel panelFecha = new JPanel();
	JPanel panelFechaFin = new JPanel();
	JPanel panelRequisitos = new JPanel();
	JPanel panelBotones = new JPanel();
	
	public ClaseVistaEdicionOferta(int id) {
		this.setTitle("Edición Oferta");
		this.setLayout(new GridLayout(5,1));
		this.setSize(300, 500);
		idOf = Integer.toString(id);
		labelNumOferta.setText(idOf);
		panelOferta.add(labelOferta);
		panelOferta.add(labelNumOferta);
		panelFecha.add(labelFecha);
		panelFecha.add(textFieldFecha);
		panelFechaFin.add(labelFechaFin);
		panelFechaFin.add(textFieldFechaFin);
		panelRequisitos.add(labelRequisitos);
		panelRequisitos.add(textfieldRequisitos);
		panelBotones.add(botonActualizar);
		panelBotones.add(botonCancelar);
		this.add(panelOferta);
		this.add(panelFecha);
		this.add(panelFechaFin);
		this.add(panelRequisitos);
		this.add(panelBotones);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
}