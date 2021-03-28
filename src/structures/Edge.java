package structures;

public class Edge<V> implements Comparable<Edge<V>>{
	
	private V u;
	
	private V v;
	
	public Edge(V u, V v) {
		this.u = u;
		this.v = v;
	}

	/**
	 * @return the u
	 */
	public V getU() {
		return u;
	}



	/**
	 * @return the v
	 */
	public V getV() {
		return v;
	}



	/**
	 * @param u the u to set
	 */
	public void setU(V u) {
		this.u = u;
	}



	/**
	 * @param v the v to set
	 */
	public void setV(V v) {
		this.v = v;
	}
	
	@Override
	public boolean equals(Object obj) {
		Edge<V> x = (Edge<V>)obj;
		return x.u.equals(u) && x.v.equals(v);
	}

	@Override
	public int compareTo(Edge<V> o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
