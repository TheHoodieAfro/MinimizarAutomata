package structures;

/**
 * @author Usuario
 *
 * @param <V>
 */
public class Edge<V> implements Comparable<Edge<V>>{
	
	/**
	 * 
	 */
	private V u;
	
	/**
	 * 
	 */
	private V v;
	
	/**
	 * @param u
	 * @param v
	 */
	public Edge(V u, V v) {
		this.u = u;
		this.v = v;
	}

	/**
	 * @return
	 */
	public V getU() {
		return u;
	}

	/**
	 * @return
	 */
	public V getV() {
		return v;
	}

	/**
	 * @param u
	 */
	public void setU(V u) {
		this.u = u;
	}

	/**
	 * @param v
	 */
	public void setV(V v) {
		this.v = v;
	}
	
	/**
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		Edge<V> x = (Edge<V>)obj;
		return x.u.equals(u) && x.v.equals(v);
	}

	/**
	 *
	 */
	@Override
	public int compareTo(Edge<V> o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
