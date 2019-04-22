package es.studium.PracticaMVC;

import java.awt.Choice;
import java.awt.GridLayout;
import javax.swing.*;

public class ClaseVistaModificacionOferta extends JFrame
{
	JLabel labelModificacion = new JLabel("Elegir oferta a modificar:");
	Choice listaOfertas = new Choice();
	JButton botonEditar = new JButton("Editar");
	JButton botonCancelar = new JButton("Cancelar");
	
	JPanel panelModificacion = new JPanel();
	JPanel panelListaOfertas = new JPanel();
	JPanel panelBotones = new JPanel();
	
	
	private static final long serialVersionUID = 1L;

	public ClaseVistaModificacionOferta() {
		this.setTitle("Modificación Oferta");
		this.setLayout(new GridLayout (3,1));
		this.setSize(500, 200);
		
		panelModificacion.add(labelModificacion);
		panelListaOfertas.add(listaOfertas);
		panelBotones.add(botonEditar);
		panelBotones.add(botonCancelar);
		this.add(panelModificacion);
		this.add(panelListaOfertas);
		this.add(panelBotones);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

}