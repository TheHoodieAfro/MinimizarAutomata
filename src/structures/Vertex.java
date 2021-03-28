package structures;

public class Vertex<E> implements Comparable<Vertex<E>>{
	
	public static enum Color{RED, WHITE, BLACK}
	
	private E data;
	
	private Color color;
	
	private Vertex<E> prev;
	
	private int dist;
	
	public Vertex(E data) {
		this.data = data;
		color = Color.RED;
		setPrev(null);
	}

	/**
	 * @return the data
	 */
	public E getData() {
		return data;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return the prev
	 */
	public Vertex<E> getPrev() {
		return prev;
	}

	/**
	 * @return the dist
	 */
	public int getDist() {
		return dist;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @param prev the prev to set
	 */
	public void setPrev(Vertex<E> prev) {
		this.prev = prev;
	}

	/**
	 * @param dist the dist to set
	 */
	public void setDist(int dist) {
		this.dist = dist;
	}

	@Override
	public int compareTo(Vertex<E> o) {
		return this.dist-o.dist;
	}
	
	@Override
	public boolean equals(Object obj) {
		Vertex<E> x = (Vertex<E>)obj;
		return x.data.equals(data);
	}
	
	

}
