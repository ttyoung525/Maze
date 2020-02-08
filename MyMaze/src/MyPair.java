
public class MyPair implements Pair {

	private int x = 0;
	private int y = 0;
	
	public MyPair() {
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int newVal) {
		x = newVal;
		
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int newVal) {
		y = newVal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MyPair)) {
			return false;
		}
		MyPair other = (MyPair) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

}
