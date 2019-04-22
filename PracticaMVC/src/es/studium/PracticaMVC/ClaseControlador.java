package es.studium.PracticaMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ClaseControlador  implements ActionListener, WindowListener{

	ClaseModelo Model;
	MenuPrincipal MenuPrinci;
	ClaseVistaBajaDemandante BajaDeman;
	ClaseVistaModificacionOferta ModifiO;
	ClaseVistaEdicionOferta EdiOfer;
	ClaseVistaAltaAsignacion AltaAsig;
	ClaseVistaConsultaOfertas ConsOfer;

	int ofertaSeleccionada = 0;
	int demandanteSeleccionado = 0;


	public ClaseControlador ( ClaseModelo mo, MenuPrincipal meP) {
		Model = mo;
		MenuPrinci = meP;
		meP.mniDemandantesBaja.addActionListener(this);
		meP.mniOfertasModificacion.addActionListener(this);
		meP.mniOfertasConsulta.addActionListener(this);
		meP.mniGestionAlta.addActionListener(this);
		meP.addWindowListener(this);

	}


	public void windowActivated(WindowEvent arg0) {


	}


	public void windowClosed(WindowEvent arg0) {


	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {


	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {


	}

	@Override
	public void windowIconified(WindowEvent arg0) {


	}

	@Override
	public void windowOpened(WindowEvent arg0) {


	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		//Creamos la baja demandante
		if(MenuPrinci.mniDemandantesBaja.equals(arg0.getSource())) {
			BajaDeman = new ClaseVistaBajaDemandante();
			MenuPrinci.setVisible(false);
			BajaDeman.addWindowListener(this);
			BajaDeman.btnEliminar.addActionListener(this);
			BajaDeman.btnCancelar.addActionListener(this);
			ResultSet dm = Model.ejecutarSelect("SELECT * FROM demandantes", Model.conectar("practicamvc","root","Studium2018;"));
			try {
				while(dm.next())
				{
					String demandantes = Integer.toString(dm.getInt("idDemandante"));
					demandantes = demandantes+".-"+ dm.getString("nombreDemandante")+" "+ dm.getString("apellidosDemandante")+" - "+dm.getString("dniDemandante");
					BajaDeman.listademandantes.add(demandantes);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Algo salio mal", JOptionPane.ERROR_MESSAGE);
			}
			Model.desconectar(Model.conectar("practicamvc","root" ,"Studium2018;"));
		}
		try {
			if(BajaDeman.btnEliminar.equals(arg0.getSource())) {
				int seleccion = JOptionPane.showOptionDialog( null,"¿Estas Seguro que quiere eliminar el demandante?","Eliminar demandante",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] { "Eliminar", "Cancelar"},"Cancelar");

				if (seleccion == 0){
					try {
						String[] array= BajaDeman.listademandantes.getSelectedItem().toString().split(".-");
						demandanteSeleccionado = Integer.parseInt(array[0]);
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null,"Introduzca demandante válido","Error de demandante", JOptionPane.ERROR_MESSAGE);
					}
					Model.ejecutarIDA("DELETE FROM demandantes where idDemandante ="+demandanteSeleccionado+";", Model.conectar("practicamvc", "root", "Studium2018;"));
				} else if(seleccion == 1) {

				}
			}
			if(BajaDeman.btnCancelar.equals(arg0.getSource())) {
				BajaDeman.setVisible(false);
				MenuPrinci.setVisible(true);
			}
		} catch(NullPointerException npe) {

		}
		
		//MODIFICAR OFERTA
				if(MenuPrinci.mniOfertasModificacion.equals(arg0.getSource())) {
					ModifiO = new ClaseVistaModificacionOferta();
					MenuPrinci.setVisible(false);
					ModifiO.addWindowListener(this);
					ModifiO.botonEditar.addActionListener(this);
					ModifiO.botonCancelar.addActionListener(this);

					ResultSet of = Model.ejecutarSelect("SELECT * FROM ofertas", Model.conectar("practicamvc","root","Studium2018;"));
					try {
						while(of.next())
						{
							String ofertas=Integer.toString(of.getInt("idOferta"));
							ofertas = ofertas+".-"+"Fecha Oferta:"+of.getString("fechaOferta")+" Fecha Fin Oferta:"+ of.getString("fechaFinOferta");
							ModifiO.listaOfertas.add(ofertas);
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
					}
					Model.desconectar(Model.conectar("practicamvc","root" ,"Studium2018;"));
				}
	
	//EDICIÓN OFERTA
			try {
				if(ModifiO.botonEditar.equals(arg0.getSource())) {
					String[] array= ModifiO.listaOfertas.getSelectedItem().toString().split(".-");
					ofertaSeleccionada = Integer.parseInt(array[0]);
					EdiOfer = new ClaseVistaEdicionOferta(ofertaSeleccionada);
					EdiOfer.botonActualizar.addActionListener(this);
					EdiOfer.botonCancelar.addActionListener(this);
					EdiOfer.addWindowListener(this);
					EdiOfer.setVisible(false);
					ResultSet ofSel = Model.ejecutarSelect("SELECT * FROM ofertas where idOferta ="+ofertaSeleccionada+";", Model.conectar("practicamvc","root","Studium2018;"));
					try {
						ofSel.next();
						EdiOfer.textFieldFecha.setText(ofSel.getString("fechaOferta"));
						EdiOfer.textFieldFechaFin.setText(ofSel.getString("fechaFinOferta"));
						EdiOfer.textfieldRequisitos.setText(ofSel.getString("requisitosOferta"));

					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
					}
					Model.desconectar(Model.conectar("practicamvc","root" ,"Studium2018;"));
				}
				else if(ModifiO.botonCancelar.equals(arg0.getSource())) {
					ModifiO.setVisible(false);
					MenuPrinci.setVisible(true);
				}

				if(EdiOfer.botonActualizar.equals(arg0.getSource())) {
					String Fecha = EdiOfer.textFieldFecha.getText();
					String[] arrayFecha = Fecha.split("/");
					String FechaFin = EdiOfer.textFieldFechaFin.getText();
					String[] arrayFechaFin = FechaFin.split("/");
					try {
						Fecha = arrayFecha[2]+"-"+arrayFecha[1]+"-"+arrayFecha[0];
						FechaFin = arrayFechaFin[2]+"-"+arrayFechaFin[1]+"-"+arrayFechaFin[0];
					} catch (ArrayIndexOutOfBoundsException ai) {

					}
					Model.ejecutarIDA("UPDATE ofertas SET fechaOferta ='"+Fecha+"', fechaFinOferta='"+FechaFin+"', requisitosOferta ='"+EdiOfer.textfieldRequisitos.getText()+"' WHERE idOferta ="+ofertaSeleccionada+";", Model.conectar("practicamvc","root" ,"Studium2018;"));
					JOptionPane.showMessageDialog(null,"Oferta Modificada con éxito","Oferta Modificada", JOptionPane.INFORMATION_MESSAGE);
				}


			} catch(NullPointerException np) {

			}
			
			try {
				
				if(MenuPrinci.mniOfertasConsulta.equals(arg0.getSource())) {
					ConsOfer = new ClaseVistaConsultaOfertas();
					MenuPrinci.setVisible(false);
					ConsOfer.addWindowListener(this);
					ConsOfer.btnAceptar.addActionListener(this);
					ConsOfer.modelo.addColumn("Número");
					ConsOfer.modelo.addColumn("Fecha");
					ConsOfer.modelo.addColumn("Fecha Fin");
					ConsOfer.modelo.addColumn("Requisitos");
					
					
					ResultSet Co = Model.ejecutarSelect("SELECT * FROM ofertas", Model.conectar("practicamvc","root" ,"Studium2018;"));
					try {
						// Bucle para cada resultado en la consulta
						while (Co.next())
						{
						   // Se crea un array que será una de las filas de la tabla. 
						   Object [] fila = new Object[4]; // Hay tres columnas en la tabla

						   // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
						   for (int i=0;i<4;i++)
						      fila[i] = Co.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

						   // Se añade al modelo la fila completa.
						   ConsOfer.modelo.addRow(fila); 
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
					}
					Model.desconectar(Model.conectar("practicamvc","root" ,"Studium2018;"));
					ConsOfer.tablaOfertas.setEnabled(false);
					
				}
				if(ConsOfer.btnAceptar.equals(arg0.getSource())) {
					ConsOfer.setVisible(false);
					MenuPrinci.setVisible(true);
				}
				
				
				
				
				
			} catch(NullPointerException npe) {

			}

			//ALTA ASIGNACIÓN
			try {

				if(MenuPrinci.mniGestionAlta.equals(arg0.getSource())) {
					AltaAsig = new ClaseVistaAltaAsignacion();
					MenuPrinci.setVisible(false);
					AltaAsig.btnAceptar.addActionListener(this);
					AltaAsig.btnCancelar.addActionListener(this);
					AltaAsig.addWindowListener(this);

					//CHOICE OFERTAS
					ResultSet Aof = Model.ejecutarSelect("SELECT * FROM ofertas", Model.conectar("practicamvc","root","Studium2018;"));
					try {
						while(Aof.next())
						{
							String ofertas=Integer.toString(Aof.getInt("idOferta"));
							ofertas = ofertas+".-"+"Fecha Oferta:"+Aof.getString("fechaOferta")+" Fecha Fin Oferta:"+ Aof.getString("fechaFinOferta");
							AltaAsig.listaOfertas.add(ofertas);
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
					}
					Model.desconectar(Model.conectar("practicamvc","root" ,"Studium2018;"));

					//CHOICE DEMANDANTES
					ResultSet Adm = Model.ejecutarSelect("SELECT * FROM demandantes", Model.conectar("practicamvc","root","Studium2018;"));
					try {
						while(Adm.next())
						{
							String demandantes=Integer.toString(Adm.getInt("idDemandante"));
							demandantes = demandantes+".-"+ Adm.getString("nombreDemandante")+" "+ Adm.getString("apellidosDemandante")+" - "+Adm.getString("dniDemandante");
							AltaAsig.demandantes.add(demandantes);
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
					}
					Model.desconectar(Model.conectar("practicamvc","root" ,"Studium2018;"));
					AltaAsig.txtFecha.setText(Model.Calendario());
				}
				if(AltaAsig.btnAceptar.equals(arg0.getSource())) {

					String Fecha = AltaAsig.txtFecha.getText();
					String[] arrayFecha = Fecha.split("/");
					try {
						Fecha = arrayFecha[2]+"-"+arrayFecha[1]+"-"+arrayFecha[0];
					} catch (ArrayIndexOutOfBoundsException ai) {}

					String[] arrayOferta= AltaAsig.listaOfertas.getSelectedItem().toString().split(".-");
					ofertaSeleccionada = Integer.parseInt(arrayOferta[0]);
					String[] arrayDemanda= AltaAsig.demandantes.getSelectedItem().toString().split(".-");
					demandanteSeleccionado = Integer.parseInt(arrayDemanda[0]);

					Model.ejecutarIDA("INSERT INTO asignaciones VALUES (null,'"+Fecha+"', '"+ofertaSeleccionada+"', '"+demandanteSeleccionado+"');",Model.conectar("practicamvc","root","Studium2018;"));
					JOptionPane.showMessageDialog(null,"Asignación añadida con éxito","Asignación añadida", JOptionPane.INFORMATION_MESSAGE);
				} else if (AltaAsig.btnCancelar.equals(arg0.getSource())) {
					MenuPrinci.setVisible(true);
					AltaAsig.setVisible(false);
				}

			} catch(NullPointerException npe) {

			}

}
	
	
}
