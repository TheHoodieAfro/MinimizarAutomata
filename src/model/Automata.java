package model;

import java.util.HashMap;
import java.util.HashSet;
import structures.AdjacencyListGraph;

public class Automata<V, S, R> extends AdjacencyListGraph<V>{
	
	private final V v0;
	
	private final HashSet<S> stimulus;
	
	private final HashSet<S> response;
	
	private final HashMap<V, HashMap<S, V>> F;
	
	public Automata(V v0) {
		super(true);
		this.v0 = v0;
		stimulus = new HashSet<>();
		response = new HashSet<>();
		F = new HashMap<>();
		addVertex(v0);
	}
	
	public boolean connect(V u, V v, S s) {
		if(!F.containsKey(u)) {
			F.put(v, new HashMap<>());
		}
		if(!F.get(u).containsKey(s) && searchVertex(u) && searchVertex(v) && s != null) {
			super.addEdge(u, v);
			F.get(u).put(s, v);
			stimulus.add(s);
			return true;
		}
		return false;
	}
	
}
