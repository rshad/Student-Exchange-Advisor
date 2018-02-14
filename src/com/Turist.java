package com;
import java.io.IOException;
import java.util.ArrayList;

/*Librerías asociadas a web scraping*/

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Turist extends WebScraping {
	
	private Document doc;
	private ArrayList<String> salida;
	private String paisBuscar;
	
	public Turist(String city){
		try {
			doc = Jsoup.connect("http://whc.unesco.org/en/list").get();
			paisBuscar = city.toLowerCase();
			salida = new ArrayList();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	void buscar(){

        String pagina = "http://whc.unesco.org";

		Elements todo = doc.select("div[id=\"acc\"]");

		Elements paises = todo.select("h4");

		Elements listas = todo.select("div[class=\"list_site\"]");

		int indice = 0;

		for (Element pais:paises) {

		    if(pais.text().toLowerCase().contains(paisBuscar)){
		        break;
		    }

		    indice++;
		}

		if(indice != listas.size()){
			Elements cosas = listas.get(indice).select("li");
	
			Elements enlaces = cosas.select("a");
			
			String titulo = enlaces.get(0).text();
	
			String enlace = enlaces.get(0).attr("href");
	
			for (Element a:enlaces) {
			    if(!a.toString().contains("title")) {
			    	salida.add( a.text());
			    	salida.add( pagina+a.attr("href"));
			    }
			}
		}
		else{
			salida = null;
		}
		
	}
	
	ArrayList<String> mostrarSalida(){
		return salida;
	}
}
