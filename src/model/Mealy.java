package model;

import java.util.ArrayList;
import java.util.HashMap;
import structures.Vertex;

/**
 * @author Usuario
 *
 * @param <V>
 * @param <S>
 * @param <R>
 */
public class Mealy<V, S, R> extends Automata<V, S, R> {
	
	/**
	 * 
	 */
	private final HashMap<V, HashMap<S, R>> response;

	/**
	 * @param v0
	 */
	public Mealy(V v0) {
		super(v0);
		response = new HashMap<>();
	}
	
	/**
	 * @param v
	 * @return
	 */
	public boolean addState(V v) {
		if(v != null) {
			return addVertex(v);
		}
		return false;
	}
	
	/**
	 * @param v
	 * @param s
	 * @return
	 */
	public R response(V v, S s) {
		if(response.containsKey(v)) {
			return response.get(v).get(s);
		}
		return null;
	}
	
	/**
	 * @param u
	 * @param v
	 * @param s
	 * @param r
	 * @return
	 */
	public boolean connect(V u, V v, S s, R r) {
		
		boolean connect = super.connect(u, v, s);
		
		if(connect) {
			if(!response.containsKey(u)) {
				response.put(u, new HashMap<>());
			}
			response.get(u).put(s, r);
			getResponse().add(r);
		}
		
		return connect;
	}
	
	/**
	 * @return
	 */
	private ArrayList<ArrayList<V>> partition() {
		
		ArrayList<ArrayList<V>> partitions = new ArrayList<>();
		HashMap<ArrayList<R>, Integer> responsesI = new HashMap<>();
		ArrayList<V> states = getVerticesArray();
        
		int i = 0;
        
		for (V v : states) {
			if(getVertexColor(v) == Vertex.Color.RED) {
				continue;
			}
            
			ArrayList<R> responsesV = new ArrayList<>();
            
			for(S s : getStimulus()) {
				responsesV.add(response(v, s));
			}
            
			if (!responsesI.containsKey(responsesV)) {
				responsesI.put(responsesV, i);
				partitions.add(new ArrayList<>());
				i++;
			}
            
			partitions.get(responsesI.get(responsesV)).add(v);
		}
        
		return  super.partition(partitions);
	}

	/**
	 *
	 */
	@Override
	public Automata<V, S, R> minimize() {
		
		BFS(getV0());
		
		ArrayList<ArrayList<V>> partitions = partition();
		V state = partitions.get(0).get(0);
		Mealy<V, S, R> mini = new Mealy<>(state);
		
		for (int i = 1; i < partitions.size(); i++) {
			state = partitions.get(i).get(0);
			mini.addState(state);
		}
		
		for (V v : mini.getVerticesArray()) {
			for (S s : getStimulus()) {
				
				V u = transition(v, s);
				R r = response(v, s);
				boolean connected = false;
				
				for (int i = 0; i < partitions.size() && !connected; i++) {
					
					ArrayList<V> minis = partitions.get(i);
					
					if (minis.contains(u)) {
						mini.connect(v, minis.get(0), s, r);
						connected = true;
					}
				}
			}
		}
		
		return mini;
	}

}
