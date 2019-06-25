package it.polito.tdp.porto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	private PortoDAO dao;
	private Map<Integer,Author> mappaAutori;
	private Graph<Author,DefaultEdge> grafo;
	
	public Model() {
		dao=new PortoDAO();
		mappaAutori=new HashMap<Integer,Author>();
		for(Author a:this.tuttiAutori()) {
			mappaAutori.put(a.getId(), a);
		}
	}
	
	public List<Author> tuttiAutori(){
		return  dao.getTuttiAutori();
	}
	
	public void creaGrafo() {
		grafo=new SimpleGraph<Author,DefaultEdge>(DefaultEdge.class);
		Graphs.addAllVertices(this.grafo, this.tuttiAutori());
		List<CoppiaAutori > coppie=dao.getCoAutori(this.mappaAutori);
		if(coppie.isEmpty()) {
			throw new RuntimeException("non ci sono coppie di co-autori");
		}
		else {
		    for(CoppiaAutori c:coppie) {
			    grafo.addEdge(c.getA1(), c.getA2());
		    }
		}
		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size());
	}
	
	public List<Author> coautori(Author a){
		return Graphs.neighborListOf(this.grafo,a);
	}

}
