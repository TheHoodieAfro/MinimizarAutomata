package structures;

public interface Graph<V> {
	
	boolean addVertex(V vertex);
	
	boolean deleteVertex(V vertex);
	
	boolean connect(V vertexIni, V vertexFin);
	
	boolean disconnect(V vertexIni, V vertexFin);
	
	boolean isVertex(V vertex);
	
	boolean isConnected(V vertexIni, V vertexFin);
	
	boolean isEmpty();
	
}
