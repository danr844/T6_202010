package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.data_structures.Comparendo;
import model.data_structures.Queue;
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

			case 3:
				view.printMessage("------------------------------------------------------------------------\n Ingrese el ID minimo que desea buscar: \n------------------------------------------------------------------------");
				int IdMinimo = lector.nextInt();
				view.printMessage("------------------------------------------------------------------------\n Ingrese el ID maximo que desea buscar: \n------------------------------------------------------------------------");
				int IdMaximo = lector.nextInt();
				Queue<Comparendo>datos= modelo.comparendosEnRango(IdMinimo, IdMaximo);
				while(!datos.isEmpty()){
					Comparendo buscado1=datos.dequeue();
					view.printMessage("------------------------------------------------------------------------\n El comparendo encontrado es:\n" +buscado1.darID() +" \n "  +buscado1.darFecha() +"\n " + buscado1.darTipoServicio()+"\n "+ buscado1.darClaseVehiculo()+"\n"+buscado1.darInfraccion()+"\n"+"------------------------------------------------------------------------");

				}

				break;
			case 4:
				
				view.printMessage(modelo.giveSizeRedBlackBST()+" Es el tamanio del RedBlackBST ");
				view.printMessage(modelo.giveHeight()+" es la altura del RedBlackBST ");
				view.printMessage(modelo.alturaPromedio()+" es la altura promedio del RedBlackBST \n \n \n");
				break;
			case 5:
				view.printMessage("El programa fue cerrado.");
				lector.close();
				fin=true;
				break;

			default: 
				view.printMessage("--------------------------------------------------------------- \n Opcion Invalida !! \n---------------------------------------------------------------");
				break;
			}

		}


		lector.close();

	}	
}