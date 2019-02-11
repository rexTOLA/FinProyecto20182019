package main;

/**
 * Clase Main del proyecto
 * Ventana con el panel main donde van a ir el resto de los paneles
 * @author rexTOLA
 *
 */
import javax.swing.*;

import database.DataBase;
import rutas.Programa;
import windowTypes.VentanaAPP;
import windowTypes.VentanaPath;

import java.awt.*;
import java.io.LineNumberInputStream;
import java.sql.ResultSet;
import java.util.List;

public class VentanaMain extends JFrame{
	static DefaultListModel<Programa> listModelApp = new DefaultListModel();
	JList<Programa> listaApp = new JList(listModelApp);
	static DefaultListModel<Programa> listModelRuta = new DefaultListModel();
	JList<Programa> listaRuta = new JList(listModelRuta);

	private static DataBase DB = new DataBase();
	private static List<Programa> programas;

	public VentanaMain(){

		//Inicializaciï¿½n
		javax.swing.border.Border compound, raisedbevel, loweredbevel;
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);	//lineas de separaciï¿½n
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(475, 900);
		setResizable(false);
		setLocationRelativeTo(null);

		//Creaciï¿½n de Componentes y contenedores
		JButton bAniadirAPP = new JButton("Añaadir Programa");		//Boton para aï¿½adir APP
		JButton bEjecutarApp = new JButton("Ejecutar");				//Boton para ejecutar App
		JButton bEliminarApp = new JButton("Eliminar");				//Boton para eliminar App
		JButton bAniadirRuta = new JButton("Añadir Ruta");			//Boton para aï¿½adir Ruta
		JButton bEjecutarRuta = new JButton("Ejecutar");			//Boton para ejecutar Ruta
		JButton bEliminarRuta = new JButton("Eliminar");			//Boton para eliminar Ruta

		JPanel pBotonera1 = new JPanel();							//Panel para el boton
		JPanel pBotonera2 = new JPanel();
		JPanel pListaApp = new JPanel(new BorderLayout());				//Panel para la lista de programas
		pListaApp.setPreferredSize(new Dimension(475, 425));			//Dimensiones del panel
		pListaApp.setBorder(compound);
		JPanel pListaRuta = new JPanel(new BorderLayout());
		pListaRuta.setPreferredSize(new Dimension(475, 425));			//Dimensiones del panel
		pListaRuta.setBorder(compound);

		JScrollPane listaScrollerApp = new JScrollPane(listaApp);
		JScrollPane listaScrollerRuta = new JScrollPane(listaRuta);

		//Layout
		getContentPane().setLayout(new BorderLayout());

		//Asignaciï¿½n de componentes y contenedores
		getContentPane().add(pListaApp, BorderLayout.NORTH);
		getContentPane().add(pListaRuta, BorderLayout.SOUTH);
		pBotonera1.add(bAniadirAPP);
		pBotonera1.add(bEjecutarApp);
		pBotonera1.add(bEliminarApp);
		pBotonera2.add(bAniadirRuta);
		pBotonera2.add(bEjecutarRuta);
		pBotonera2.add(bEliminarRuta);
		pListaApp.add(pBotonera1, BorderLayout.NORTH);
		pListaRuta.add(pBotonera2, BorderLayout.NORTH);
		pListaApp.add(listaScrollerApp);
		pListaRuta.add(listaScrollerRuta);


		bAniadirAPP.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bAniadirAPPActionPerformed(evt);
			}
		});

		bAniadirRuta.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bAniadirRutaActionPerformed(evt);

			}
		});

		bEjecutarApp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bEjecutarAppactionPerformed(evt, listaApp);
			}
		});

		bEliminarApp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bEliminarActionPerformedApp(evt);
			}
		});

		bEjecutarRuta.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bEjecutarRutaactionPerformed(evt, listaRuta);
			}
		});

		bEliminarRuta.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bEliminarActionPerformedRuta(evt);
			}
		});

	}

	private void bAniadirAPPActionPerformed(java.awt.event.ActionEvent evt){
		VentanaAPP vr = new VentanaAPP(listModelApp);
		vr.main(null);
		vr.setVisible(true);

	}

	private void bAniadirRutaActionPerformed(java.awt.event.ActionEvent evt){
		VentanaPath vp = new VentanaPath(listModelRuta);
		vp.main(null);
		vp.setVisible(true);

	}

	private void bEjecutarAppactionPerformed(java.awt.event.ActionEvent evt, JList listaApp){
		Runtime r = Runtime.getRuntime();
		Process p = null;
		//		String ruta = ((Programa) listaApp.getSelectedValue()).getPath();
		//		System.out.println(ruta);
		listaApp.getModel();
		int indexApp = listaApp.getSelectedIndex();
		listModelApp.get(indexApp);

		try{
			ResultSet rs = DB.getStatement().executeQuery("SELECT * FROM PROGRAMAS WHERE NOMBRE LIKE 'APP - %'");
			while(rs.next()){
				// Leer el resultset
				System.out.println(rs.getString("NOMBRE"));

				String nombre, path;

				nombre = rs.getString("NOMBRE");
				path = rs.getString("DIRECCION");


				p = r.exec(path);				
				System.out.println(p);
			}
		}catch (Exception e) {
			System.out.println("Error al ejecutar");

		}
	}

	private void bEjecutarRutaactionPerformed(java.awt.event.ActionEvent evt, JList listaRuta){
		Runtime r = Runtime.getRuntime();
		Process p = null;
		//		String ruta = ((Programa) listaRuta.getSelectedValue()).getPath();
		//		System.out.println(ruta);

		try{
			ResultSet rs = DB.getStatement().executeQuery("SELECT * FROM PROGRAMAS WHERE NOMBRE LIKE 'CARPETA - %'");
			while(rs.next()){
				// Leer el resultset
				System.out.println(rs.getString("NOMBRE"));

				String nombre, path;

				nombre = rs.getString("NOMBRE");
				path = rs.getString("DIRECCION");


				p = r.exec(path);				
				System.out.println(p);
			}
		}catch (Exception e) {
			System.out.println("Error al ejecutar");
		}
	}

	private void bEliminarActionPerformedApp(java.awt.event.ActionEvent evt){
		
		int indexApp = listaApp.getSelectedIndex();

		System.out.println(listaApp.getModel().getElementAt(indexApp).getClass().getSimpleName()); 
		
		String sql = " DELETE FROM PROGRAMAS WHERE NOMBRE = '"+((Programa) listaApp.getSelectedValue()).getNombre()+"';"; 
		
		listModelApp.remove(indexApp);

		

		try {
			DB.getStatement().executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void bEliminarActionPerformedRuta(java.awt.event.ActionEvent evt){
		 
		int indexRuta = listaRuta.getSelectedIndex();
		
		System.out.println(listaRuta.getModel().getElementAt(indexRuta).getClass().getSimpleName());
		
		String sql = " DELETE FROM PROGRAMAS WHERE NOMBRE = '"+((Programa) listaRuta.getSelectedValue()).getNombre()+"';";
		
		listModelRuta.remove(indexRuta);

		

		try {
			DB.getStatement().executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void cerrarBD(java.awt.event.WindowEvent e) {
		DB.close();
		System.exit(0);
	}

	private static void aniadirPrograma() {
		try {
			ResultSet rsApp = DB.getStatement().executeQuery("SELECT * FROM PROGRAMAS WHERE NOMBRE LIKE 'APP - %'");
			while(rsApp.next()){
				// Leer el resultset
				System.out.println(rsApp.getString("NOMBRE"));

				String nombre, path;

				nombre = rsApp.getString("NOMBRE");
				path = rsApp.getString("DIRECCION");

				listModelApp.addElement(new Programa(nombre, path));
			}

			ResultSet rsRuta = DB.getStatement().executeQuery("SELECT * FROM PROGRAMAS WHERE NOMBRE LIKE 'CARPETA - %'");
			while(rsRuta.next()){
				// Leer el resultset
				System.out.println(rsRuta.getString("NOMBRE"));

				String nombre, path;

				nombre = rsRuta.getString("NOMBRE");
				path = rsRuta.getString("DIRECCION");

				listModelRuta.addElement(new Programa(nombre, path));

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		JFrame f = new VentanaMain();
		f.setVisible(true);
		try {
			DB.initBD("E:\\Ander\\Documentos\\+Estudios\\2018 - 2019\\Prog III\\ProyectoProgIII - 2\\AdminAppDataBase.bd");
			//DB.crearTablaBD();
			VentanaMain.aniadirPrograma();
			//DB.close();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}
}
