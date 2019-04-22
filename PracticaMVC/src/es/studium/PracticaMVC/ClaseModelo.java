package es.studium.PracticaMVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class ClaseModelo 
{
	//conectara la base de datos
	public Connection conectar(String baseDatos, String usuario, String clave)
	{
		String driver = "com.mysql.jdbc.Driver";
		String url ="jdbc:mysql://localhost:3306/"+baseDatos+"?autoReconnect=true&useSSL=false";
		String login = usuario;
		String password = clave;
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(url, login,password);
		}
		catch (ClassNotFoundException cnfe)
		{
			JOptionPane.showMessageDialog(null,cnfe.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (SQLException sqle)
		{
			JOptionPane.showMessageDialog(null,sqle.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		return connection;
	}
	//nos desconectamos de la base de datos
	public void desconectar(Connection c) 
	{
		try
		{
			if(c!=null)
			{
				c.close();
			}
		}
		catch (SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage(),"Algo falló", JOptionPane.ERROR_MESSAGE);
		}
	}
	//ejecutamos la sentencia del Select
	public ResultSet ejecutarSelect(String sentencia, Connection c) 
	{

		try
		{
			Statement statement = c.createStatement();
			ResultSet res = statement.executeQuery(sentencia);
			return res;
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage(),"Algo falló", JOptionPane.ERROR_MESSAGE);
			return null;
		}

	}
	//ejecutamos la sentencia update
	public void ejecutarIDA(String sentencia, Connection c) 
	{
		try
		{
			Statement statement = c.createStatement();
			statement.executeUpdate(sentencia);
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage(),"Algo falló", JOptionPane.ERROR_MESSAGE);
		}

	}
	//saquemos la fecha
	public String Calendario() {
		Calendar horaFecha = Calendar.getInstance();
		int hora,minutos,dia,mes,anyo;

		hora = horaFecha.get(Calendar.HOUR_OF_DAY);
		minutos = horaFecha.get(Calendar.MINUTE);
		dia = horaFecha.get(Calendar.DAY_OF_MONTH);
		mes = horaFecha.get(Calendar.MONTH)+1;
		anyo = horaFecha.get(Calendar.YEAR);

		if(dia<10 && mes<10) 
		{
			return "0"+dia+"/"+"0"+mes+"/"+anyo;
		} else if(dia<10 && mes>=10) 
		{
			return "0"+dia+"/"+mes+"/"+anyo;
		} else if (dia>=10 && mes<10) 
		{
			return dia+"/"+"0"+mes+"/"+anyo;
			
		} else if(dia>=10 && mes<=10) 
		{
			return dia+"/"+mes+"/"+anyo;
		}
		return dia+"/"+mes+"/"+anyo;
		
	}
}