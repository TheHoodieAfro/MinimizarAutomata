package model;

import java.util.ArrayList;
import java.util.HashMap;
import structures.Vertex;

public class Moore<V, S, R> extends Automata<V, S, R> {
	
	private final HashMap<V, R> response;
	
	public Moore(V v0, R r) {
		super(v0);
		response = new HashMap<>();
		addState(v0, r);
	}
	
	public boolean addState(V v, R r) {
		if(v != null && !response.containsKey(v)) {
			response.put(v, r);
			addVertex(v);
			getResponse().add(r);
			return true;
		}
		return false;
	}
	
	public R response(V v) {
		return response.get(v);
	}
	
	private ArrayList<ArrayList<V>> partition() {
		
		ArrayList<ArrayList<V>> partitions = new ArrayList<>();
		HashMap<R, Integer> responsesI = new HashMap<>();
		ArrayList<V> states = getVerticesArray();
        
		int i = 0;
        
		for (V v : states) {
			if(getVertexColor(v) == Vertex.Color.RED) {
				continue;
			}
			
			R r = response(v);
            
			if (!responsesI.containsKey(r)) {
				responsesI.put(r, i);
				partitions.add(new ArrayList<>());
				i++;
			}
            
			partitions.get(responsesI.get(r)).add(v);
		}
        
		return  super.partition(partitions);
	}

	@Override
	public Automata<V, S, R> minimize() {
		
		BFS(getV0());
		
		ArrayList<ArrayList<V>> partitions = partition();
		V state = partitions.get(0).get(0);
		Moore<V, S, R> mini = new Moore<>(state, response(state));
		
		for (int i = 1; i < partitions.size(); i++) {
			state = partitions.get(i).get(0);
			mini.addState(state, response(state));
		}
		
		for (V v : mini.getVerticesArray()) {
			for (S s : getStimulus()) {
				
				V u = transition(v, s);
				boolean connected = false;
				
				for (int i = 0; i < partitions.size() && !connected; i++) {
					
					ArrayList<V> minis = partitions.get(i);
					
					if (minis.contains(u)) {
						mini.connect(v, minis.get(0), s);
						connected = true;
					}
				}
			}
		}
		
		return mini;
		
	}

}
