package model.logic;



import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import model.data_structures.ArregloDinamico;
import model.data_structures.Comparendo;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.data_structures.RedBlackBST;

/**
 * Definicion del modelo del mundo
 *
 */

public class Modelo
{
	/**
	 * Atributos del modelo del mundo
	 */


	private ArregloDinamico<Comparendo>array;
	private RedBlackBST<Integer , Comparendo> treeRedBlack;


	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo()
	{
		
		array = new ArregloDinamico<Comparendo>(10000);
		treeRedBlack = new RedBlackBST<Integer, Comparendo>(); 
	}



	public Comparendo[] cargarInfo() throws ParseException{
		Comparendo []res = new Comparendo[2]; 

		try {
			////// tesing

			String path = "./data/Comparendos_DEI_2018_Bogotá_D.C_small.geojson";
			JsonReader reader;


			reader = new JsonReader(new FileReader(path));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray ja = elem.getAsJsonObject().get("features").getAsJsonArray();
			SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
			for(JsonElement e: ja)
			{
				String []fecha1= e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString().split("T");
				int id = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();
				Date fecha = parser.parse(fecha1[0]);
				String Hora = fecha1[1];
				String medio = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETECCION").getAsString();
				String Clasevehi= e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHICULO").getAsString();
				String tipoServicio = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVICIO").getAsString();
				String Infraccion =e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DescInfra=e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRACCION").getAsString();
				String Localidad = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();
				String Municipio = e.getAsJsonObject().get("properties").getAsJsonObject().get("MUNICIPIO").getAsString();


				Comparendo user = new Comparendo(id,fecha,Hora, medio, Clasevehi, tipoServicio, Infraccion, DescInfra, Localidad, Municipio );
				
				array.agregar(user);
				treeRedBlack.put(id, user);
				
			}
			
//			int k=0;
//			Long start = System.currentTimeMillis();
//		
//			Long finish = System.currentTimeMillis();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return res;
	}

	public Comparator<Comparendo> giveComparator(String caracteristicaComparable){

		if(caracteristicaComparable.equals("ID"))
		{

			Comparator<Comparendo> ID = new Comparator<Comparendo>()
			{
				@Override
				public int compare(Comparendo o1, Comparendo o2) 
				{
					if(o1.darID()<o2.darID())return -1;
					else if (o1.darID()>o2.darID())
						return 1;
					return 0;	
				}
			};
			return ID;

		}
		else return null;
	}
	
	public ArregloDinamico<Comparendo> darArreglo(){
		return array;
	}
	public int giveSizeRedBlackBST(){
		return treeRedBlack.size(treeRedBlack.giveRoot());
	}
	public Comparendo getMin(){
		return treeRedBlack.getMin();
	}
	public Comparendo getMax(){
		return treeRedBlack.getMaxValue();
	}
	public Comparendo getKey(int key){
		return treeRedBlack.get(key);
	}
	public void addArregloDinamico(Comparendo multa){
		array.agregar(multa);
	}
	public int giveHeight(){
		return treeRedBlack.height();
	}
	public Queue<Comparendo> comparendosEnRango(int ObjectId_Min, int ObjectId_max){
		Queue<Comparendo>res= new Queue<Comparendo>();
		for(Integer key:treeRedBlack.keys()){
			if(key<=ObjectId_max&&key>=ObjectId_Min)
				res.enqueue(treeRedBlack.get(key));
		}
		return res;
	}
	
}