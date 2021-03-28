package structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import structures.Vertex.Color;

/**
 * This class models a graph using an Adjacency list
 * @author AED Class # 003 // 2019
 * @version 1.0 - 10/2019
 * @param <V> Abstract data type which represents an object from a natural problem that is going to be modeled as a vertex in a graph representation of the problem
 */
public class AdjacencyListGraph<V> implements IGraph<V>{
	
	/**
	 * Map with all the vertices within the graph.
	 * Key of the map is the Vertex and Value is the position of the vertex in the adjacencyList
	 */
	private HashMap<V,Vertex<V>> vertices;	
	
	/**
	 * A list for each Vertex within the graph which has a list with all its adjacent Vertices 
	 */
	private HashMap<V, ArrayList<Edge<V>>> adjacencyLists;
	
	/**
	 * Property that say if a graph is directed or not
	 */
	private boolean isDirected;
	
	private Vertex<V> prevVertex;
	
	/**
	 * Basic constructor that is initialized with default values
	 */
	public AdjacencyListGraph() {
		initialize();
	}

	/**
	 * Constructor that gets the value for "isDirected" attribute.
	 * True if the graph is Directed or false if it's Indirected
	 * @param id value to set "isDirected"
	 */
	public AdjacencyListGraph(boolean id) {
		initialize();
		isDirected = id;
	}
	
	/**
	 * Initializes all the data structures for this graph.
	 * Set "isDirected" attribute in false
	 */
	private final void initialize() {
		isDirected = false;
		adjacencyLists = new HashMap<>();
		vertices = new HashMap<>();
	}
	
	@Override
	public boolean addVertex(V v) {
		// Check if the vertex is not on the map already
		if(!searchVertex(v)) {
			
			vertices.put(v, new Vertex<V>(v));
			adjacencyLists.put(v, new ArrayList<>());
			
			return true;
		}
		return false;
	}

	/**
	 * checks if a vertex is within the graph
	 * @param v Vertex to be searched
	 * @return True if found or false if not
	 */
	public boolean searchVertex(V v) {
		return vertices.containsKey(v);
	}

	@Override
	public void addEdge(V u, V v) {
		
		addVertex(u);
		addVertex(v);
		Edge<V> edgeU = new Edge<V>(u, v);
		
		ArrayList<Edge<V>> edgesU = adjacencyLists.get(u);
		edgesU.remove(edgeU);
		edgesU.add(edgeU);
		
		if(!isDirected) {
			Edge<V> edgeV = new Edge<>(u, v);
			ArrayList<Edge<V>> edgesV = adjacencyLists.get(v);
			edgesV.remove(edgeV);
			edgesV.add(edgeV);
		}
		
	}

	@Override
	public void addEdge(V u, V v, double w) {
		
	}

	@Override
	public boolean removeVertex(V v) {
		
		// first looks if the vertex exists
		if(searchVertex(v)) {
			
			vertices.remove(v);
			vertices.forEach((V u, Vertex<V> x) -> {
				ArrayList<Edge<V>> erase = new ArrayList<>();
				
				for(Edge<V> e : adjacencyLists.get(x)) {
					if(e.getV().equals(v)) {
						erase.add(e);
					}
				}
				adjacencyLists.get(u).removeAll(erase);
			});
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean removeEdge(V u, V v) {
		
		Vertex<V> U = vertices.get(u);
		Vertex<V> V = vertices.get(v);
		
		if(U != null && V != null) {
			adjacencyLists.get(u).remove(new Edge<V>(U.getData(), V.getData()));
			
			if(!isDirected) {
				adjacencyLists.get(v).remove(new Edge<V>(V.getData(), U.getData()));
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<V> vertexAdjacent(V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean areConnected(V u, V v) {
		
		if(searchVertex(u) && searchVertex(v)) {
			return adjacencyLists.get(u).contains(new Edge<V>(u, v));
		}
		
		return false;
	}

	@Override
	public double[][] weightMatrix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		return isDirected;
	}
	
	/**
	 * 
	 * @return all the vertices within the graph as a map data structure
	 */
	public HashMap<V, Vertex<V>> getVertices(){
		return vertices;		
	}
	
	public ArrayList<V> getVerticesArray(){
		ArrayList<V> vertex = new ArrayList<>();
		vertices.forEach((V x, Vertex<V> y) -> vertex.add(x));
		return vertex;
	}
	
	/**
	 *
	 * @return The graph. A list with lists of vertices and its adjacent vertices
	 */
	public HashMap<V, ArrayList<Edge<V>>> getAdjacencyList(){		
		return adjacencyLists;		
	}
	
	public boolean isEmpty() {
		return vertices.isEmpty();
	}

	@Override
	public int getIndex(V u) {
		return 0;
	}

	@Override
	public int getVertexSize() {
		return vertices.size();
	}
	
	public Vertex<V> getPrevVertex() {
		return prevVertex;
	}
	
	public Color getVertexColor(V v) {
		if(searchVertex(v)) {
			return vertices.get(v).getColor();
		}
		
		return null;
	}

	@Override
	public boolean BFS(V v) {
		
		if(searchVertex(v)) {
			
			Vertex<V> vertex = vertices.get(v);
			prevVertex = vertex;
			
			vertices.forEach((V c, Vertex<V> u) -> {
				u.setColor(Color.RED);
				u.setDist(Integer.MAX_VALUE);
				u.setPrev(null);
			});
			
			vertex.setColor(Color.WHITE);
			vertex.setDist(0);
			Queue<Vertex<V>> queue = new LinkedList<>();
			queue.add(vertex);
			
			try {
				while(!queue.isEmpty()) {
					
					Vertex<V> u = queue.remove();
					ArrayList<Edge<V>> adj = adjacencyLists.get(u.getData());
					
					for(Edge<V> ale: adj) {
						
						Vertex<V> x = vertices.get(ale.getV());
						
						if(x.getColor() == Color.RED) {
							x.setColor(Color.WHITE);
							x.setDist(u.getDist()+1);
							x.setPrev(u);
							queue.add(x);
						}
					}
					
					u.setColor(Color.BLACK);
				}
				
			} catch (Exception emptyQueueException) {
				
			}
			
			return true;
		}
		
		return false;
	}	
}
