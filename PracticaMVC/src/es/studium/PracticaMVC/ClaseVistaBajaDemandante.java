package es.studium.PracticaMVC;

import java.awt.Choice;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClaseVistaBajaDemandante extends JFrame
{
	
	
	private static final long serialVersionUID = 1L;
	
	JLabel lblEtiqueta = new JLabel("Elegir demandante a dar de baja:");
	Choice listademandantes = new Choice();
	JButton btnEliminar = new JButton("Eliminar");
	JButton btnCancelar = new JButton("Cancelar");

	JPanel paneletiqueta = new JPanel();
	JPanel panellista = new JPanel();
	JPanel panelbotones = new JPanel();
	
	public ClaseVistaBajaDemandante() {
		this.setTitle("Baja Demandante");
		this.setSize(300,200);
		this.setLayout(new GridLayout(3,1));
		paneletiqueta.add(lblEtiqueta);
		listademandantes.add("Elegir uno...");
		panellista.add(listademandantes);
		panelbotones.add(btnEliminar);
		panelbotones.add(btnCancelar);
		this.add(paneletiqueta);
		this.add(panellista);
		this.add(panelbotones);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		

	}
}

