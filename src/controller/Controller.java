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
				view.printMessage("------------------------------------------------------------------------\n Se esta cargando la informacion \n------------------------------------------------------------------------");
				modelo.cargarInfo();
				view.printMessage("Se cargaron"+ modelo.giveSizeRedBlackBST());
				Comparendo maximo = modelo.getMax();
				Comparendo minimo = modelo.getMin();
				if(maximo!=null&& minimo != null){
					view.printMessage("El primer comparendo es:" + maximo.darID()+ "\n "+maximo.darFecha() + "\n "+ maximo.darLocalidad()+ "\n "+ maximo.darInfraccion()  );
					view.printMessage("El ultimo comparendo es:" + minimo.darID()+ "\n "+ minimo.darFecha() + "\n "+ minimo.darLocalidad()+ "\n "+ minimo.darInfraccion()  );
				

				}
				break;
							case 2:
								view.printMessage("------------------------------------------------------------------------\n Ingrese la key que desea buscar, toda pegada: \n------------------------------------------------------------------------");
								int key = lector.nextInt();
								if(modelo.getKey(key)==null)
								{
									view.printMessage("------------------------------------------------------------------------\n Ingreso una key invalida \n------------------------------------------------------------------------");
									break;
								}
								else{
									Comparendo buscado  =modelo.getKey(key);
									view.printMessage("------------------------------------------------------------------------\n El comparendo encontrado es:\n" +buscado.darID() +" \n "  +buscado.darFecha() +"\n " + buscado.darTipoServicio()+"\n "+ buscado.darClaseVehiculo()+"\n"+buscado.darInfraccion()+"\n"+"------------------------------------------------------------------------");
								}
								break;
				
				//			case 3:
				//				view.printMessage("------------------------------------------------------------------------\n Fecha, Clase Vehiculo e Infraccion en el formato: FechaClaseVehiculoInfraccion: \n------------------------------------------------------------------------");
				//				String fechavehiculoInfraccion = lector.next();
				//				ArrayList<Comparendo> res = modelo.darComparendosFeClaInfSeparateChaning(fechavehiculoInfraccion);
				//				if(res.get(0)==null)
				//					view.printMessage(" no existen comparendos con la localidad dada.");
				//				else
				//				{
				//					for(Comparendo e: res){
				//						view.printMessage("El primer Comparendo es: "+ e.darID() +" " + e.darFecha()+ " "+e.darInfraccion()+ " "+ " "+ e.darClaseVehiculo()+" "+e.darTipoServicio()+" "+e.darLocalidad()+ "\n---------------------------");
				//
				//					}
				//				}
				//				break;
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