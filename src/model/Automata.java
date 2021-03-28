package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import structures.AdjacencyListGraph;

public abstract class Automata<V, S, R> extends AdjacencyListGraph<V>{
	
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
	
	/**
	 * @return the v0
	 */
	public V getV0() {
		return v0;
	}

	/**
	 * @return the stimulus
	 */
	public HashSet<S> getStimulus() {
		return stimulus;
	}

	/**
	 * @return the response
	 */
	public HashSet<S> getResponse() {
		return response;
	}

	/**
	 * @return the f
	 */
	public HashMap<V, HashMap<S, V>> getF() {
		return F;
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
	
	public V transition(V u, S s) {
		if(F.containsKey(u)) {
			return F.get(u).get(s);
		}
		return null;
	}
	
	public abstract Automata<V, S, R> minimize();
	
	public ArrayList<ArrayList<V>> Partition2_3(ArrayList<ArrayList<V>> partitions) {
		
		//Preparations
		ArrayList<ArrayList<V>> prev;
		boolean end = false;
		
		while (!end) {
			prev = partitions;
			
			for (S s : getStimulus()) {
				HashMap<Integer, HashMap<Integer, ArrayList<V>>> original = new HashMap<>();
				int count = 0;
				
				for (int i = 0; i < partitions.size(); i++) {
					HashMap<Integer, ArrayList<V>> result = refine(partitions, i, s);
					original.put(i, result);
					count += result.size();
				}
				
				if (count > partitions.size()) {
					partitions = new ArrayList<>();
					
					for (int i = 0; i < original.size(); i++) {
						partitions.addAll(original.get(i).values());
					}
					
					break;
				}
			}
			end = prev.size() == partitions.size();
		}
		return partitions;
	}
	
	public HashMap<Integer, ArrayList<V>> refine(ArrayList<ArrayList<V>> partitions, int set, S s) {
		
		//Preparations
		ArrayList<V> partition = partitions.get(set);
		HashMap<Integer, ArrayList<V>> refinedPartition = new HashMap<>();
		
		for (V u : partition) {
			V v = transition(u, s);
			boolean exists = false;
			int c = 0;
			
			for (int j = 0; j < partitions.size() && !exists; j++) {
				ArrayList<V> actualPartition = partitions.get(j);
				exists = actualPartition.contains(v);
				c = j;
			}
			
			if (!refinedPartition.containsKey(c)) {
				refinedPartition.put(c, new ArrayList<>());
			}
			
			refinedPartition.get(c).add(u);
		}
		return refinedPartition;
	}
	
}
