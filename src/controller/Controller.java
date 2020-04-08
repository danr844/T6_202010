package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.data_structures.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() throws ParseException 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		Integer respuesta = 0;

		while( !fin ){
			view.printMenu();
			int option = lector.nextInt();
			switch(option)
			{
			case 1:
				Comparendo[] primeroYultimo = new Comparendo[2];
				view.printMessage("------------------------------------------------------------------------\n Se esta cargando la informacion \n------------------------------------------------------------------------");
				try 
				{
					primeroYultimo= modelo.cargarInfo();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				view.printMessage("Se cargaron"+ modelo.darNumeroElementosLinear());
				if(primeroYultimo[0]!=null&& primeroYultimo[1]!= null){
					view.printMessage("El primer comparendo es:" + primeroYultimo[0].darID()+ "\n "+ primeroYultimo[0].darFecha() + "\n "+ primeroYultimo[0].darLocalidad()+ "\n "+ primeroYultimo[0].darInfraccion()  );
					view.printMessage("El ultimo comparendo es:" + primeroYultimo[1].darID()+ "\n "+ primeroYultimo[1].darFecha() + "\n "+ primeroYultimo[1].darLocalidad()+ "\n "+ primeroYultimo[1].darInfraccion()  );
					view.printMessage("El numero de duplas en Linear Probing es:"+ modelo.darNumeroElementosLinear()+"\n"+ "El numero de duplas en separate chaining es:"+ modelo.darNumeroElementosSeparate());
					view.printMessage("El tamanio inicial de linear probing y separate chaning es de 53");
					view.printMessage("El tamanio final de linear probing  es de:"+ modelo.darTamaniotablaLinear());
					view.printMessage("El tamanio final de separate chaning  es de:"+ modelo.darTamaniotablaSeparate());
					view.printMessage("El factor de carga de Linear Probing es: 75% \n El factor de carga de separate chaning es: 50%");
					view.printMessage("Se hicieron 0 rehashes para linear probing \n Se hicieron 0 rehashes para separate chaning");


				}
				break;
			case 2:
				view.printMessage("------------------------------------------------------------------------\n Ingrese la key que desea buscar, toda pegada: \n------------------------------------------------------------------------");
				String key = lector.next();
				if(!modelo.existeLlaveLinearProbing(key))
				{
					view.printMessage("------------------------------------------------------------------------\n Ingreso una key invalida \n------------------------------------------------------------------------");
					break;
				}
				ArrayList<Comparendo> buscados=modelo.buscarPorKeyLinearProbing(key);

				for(int i=0; i<buscados.size(); i++)
				{
					view.printMessage("------------------------------------------------------------------------\n"+"OBJECTID: "+buscados.get(i).darID() +" // FECHA_HORA: "+buscados.get(i).darFecha()+" // CLASE_VEHI: "+buscados.get(i).darClaseVehiculo()+" // INFRACCION: "+buscados.get(i).darInfraccion()+"\n------------------------------------------------------------------------");
				}

				break;

			case 3:
				view.printMessage("------------------------------------------------------------------------\n Fecha, Clase Vehiculo e Infraccion en el formato: FechaClaseVehiculoInfraccion: \n------------------------------------------------------------------------");
				String fechavehiculoInfraccion = lector.next();
				ArrayList<Comparendo> res = modelo.darComparendosFeClaInfSeparateChaning(fechavehiculoInfraccion);
				if(res.get(0)==null)
					view.printMessage(" no existen comparendos con la localidad dada.");
				else
				{
					for(Comparendo e: res){
						view.printMessage("El primer Comparendo es: "+ e.darID() +" " + e.darFecha()+ " "+e.darInfraccion()+ " "+ " "+ e.darClaseVehiculo()+" "+e.darTipoServicio()+" "+e.darLocalidad()+ "\n---------------------------");

					}
				}
				break;
				//			case 3:
				//				view.printMessage("------------------------------------------------------------------------\n Ingrese la fecha que desea usar en el formato yyyy/MM/dd: \n------------------------------------------------------------------------");
				//				String fechaS = lector.next();
				//				SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
				//				Date fecha = parser.parse(fechaS);
				//				ArregloDinamico<Comparendo> nuevo = modelo.darComparendosFechaHora(fecha);
				//				int i =0;
				//				while(i<nuevo.darTamano()){
				//					view.printMessage("------------------------------------------------------------------------\n"+nuevo.darElemento(i).darID()+" " +nuevo.darElemento(i).darFecha()+" " +nuevo.darElemento(i).darInfraccion()+" " +nuevo.darElemento(i).darClaseVehiculo()+" " +nuevo.darElemento(i).darTipoServicio()+" " +nuevo.darElemento(i).darLocalidad()+" " +"\n------------------------------------------------------------------------");
				//					i++;
				//				}
				//				view.printMessage("el numero total de comparendos para esta fecha es: "+ nuevo.darTamano()  );
				//
				//				break;
				//
				//			case 4:
				//
				//				view.printMessage("------------------------------------------------------------------------\n Ingrese la infraccion que desea buscar: \n------------------------------------------------------------------------");
				//				String pInfraccion = lector.next();//m
				//				Comparendo res1 = modelo.darPrimerComparendoPorInfraccion(pInfraccion);
				//				if(res1!=null)
				//					view.printMessage("El primer Comparendo es: "+ res1.darID() +" " + res1.darFecha()+ " "+res1.darInfraccion()+ " "+ " "+ res1.darClaseVehiculo()+" "+res1.darTipoServicio()+" "+res1.darLocalidad()+ "\n---------------------------");
				//				else
				//					view.printMessage("No se encontro un comparendo con la infraccion dada");
				//				break;
				//
				//			case 5:
				//				view.printMessage("------------------------------------------------------------------------\n Ingrese las fechas que desea usar en el formato yyyy/MM/dd: \n------------------------------------------------------------------------");
				//				String fechaS2 = lector.next();
				//				SimpleDateFormat parser2 = new SimpleDateFormat("yyyy/MM/dd");
				//				Date fecha2 = parser2.parse(fechaS2);
				//				String fechaS3 = lector.next();
				//				SimpleDateFormat parser3 = new SimpleDateFormat("yyyy/MM/dd");
				//				Date fecha3 = parser3.parse(fechaS3);
				//				ArrayList<ArregloDinamico<Comparendo>> nuevo1 = modelo.darComparendosDosfechas(fecha2, fecha3);
				//				ArregloDinamico<Comparendo> datosFecha1 = nuevo1.get(0);
				//				ArregloDinamico<Comparendo> datosfecha2 = nuevo1.get(1);
				//				int j =0;
				//				view.printMessage("Infraccion     |"+ fecha2 + "      |"+ fecha3);
				//
				//				//				while(j<nuevo1.size()){
				//				//					view.printMessage("------------------------------------------------------------------------\n"+nuevo.darElemento(j).darID()+" " +nuevo.darElemento(j).darFecha()+" " +nuevo.darElemento(j).darInfraccion()+" " +nuevo.darElemento(j).darClaseVehiculo()+" " +nuevo.darElemento(j).darTipoServicio()+" " +nuevo.darElemento(j).darLocalidad()+" " +"\n------------------------------------------------------------------------");
				//				//					j++;
				//				//				}
				//				//				view.printMessage("el numero total de comparendos para esta fecha es: "+ nuevo.darTamano()  );
				//				//				
				//				break;
				//			case 6:
				//				view.printMessage("------------------------------------------------------------------------\n Ingrese la infraccion que desea buscar: \n------------------------------------------------------------------------");
				//				String ifraccion2 = lector.next();
				//				ArregloDinamico<Comparendo> nuevo2 = modelo.comparendosConInfraccion(ifraccion2);
				//				int w =0;
				//				while(w<nuevo2.darTamano()){
				//					view.printMessage("------------------------------------------------------------------------\n"+nuevo2.darElemento(w).darID()+" " +nuevo2.darElemento(w).darFecha()+" " +nuevo2.darElemento(w).darInfraccion()+" " +nuevo2.darElemento(w).darClaseVehiculo()+" " +nuevo2.darElemento(w).darTipoServicio()+" " +nuevo2.darElemento(w).darLocalidad()+" " +"\n------------------------------------------------------------------------");
				//					w++;
				//				}
				//				view.printMessage("el numero total de comparendos para esta fecha es: "+ nuevo2.darTamano()  );
				//
				//
				//				break;
				//
				//			case 7:
				//				String[]tabla = modelo.darComparendosInfraccionesPublico().split(";");
				//				int y =0;
				//				while(y<tabla.length){
				//					view.printMessage(tabla[y]);
				//					y++;
				//				}
				//				break;
				//
				//			case 8: 
				//				view.printMessage("------------------------------------------------------------------------\n Cerrando el programa: \n------------------------------------------------------------------------");
				//				lector.close();
				//				fin = true;

			default: 
				view.printMessage("--------------------------------------------------------------- \n Opcion Invalida !! \n---------------------------------------------------------------");
				break;
			}
		}




	}	
}