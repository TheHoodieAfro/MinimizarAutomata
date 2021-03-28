package structures;

/**
 * @author Usuario
 *
 * @param <E>
 */
public class Vertex<E> implements Comparable<Vertex<E>>{
	
	/**
	 * @author Usuario
	 *
	 */
	public static enum Color{RED, WHITE, BLACK}
	
	/**
	 * 
	 */
	private E data;
	
	/**
	 * 
	 */
	private Color color;
	
	/**
	 * 
	 */
	private Vertex<E> prev;
	
	/**
	 * 
	 */
	private int dist;
	
	/**
	 * @param data
	 */
	public Vertex(E data) {
		this.data = data;
		color = Color.RED;
		setPrev(null);
	}

	/**
	 * @return
	 */
	public E getData() {
		return data;
	}

	/**
	 * @return
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return
	 */
	public Vertex<E> getPrev() {
		return prev;
	}

	/**
	 * @return
	 */
	public int getDist() {
		return dist;
	}

	/**
	 * @param data
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @param prev
	 */
	public void setPrev(Vertex<E> prev) {
		this.prev = prev;
	}

	/**
	 * @param dist
	 */
	public void setDist(int dist) {
		this.dist = dist;
	}

	/**
	 *
	 */
	@Override
	public int compareTo(Vertex<E> o) {
		return this.dist-o.dist;
	}
	
	/**
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		Vertex<E> x = (Vertex<E>)obj;
		return x.data.equals(data);
	}
	
	

}
