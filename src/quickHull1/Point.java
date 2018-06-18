package quickHull1;

public class Point {
	private int x;
	private int y;

	public Point() {
		x = 0;
		y = 0;
	}
	public Point(int xx, int yy) {
		x = xx;
		y = yy;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setX_Y(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
