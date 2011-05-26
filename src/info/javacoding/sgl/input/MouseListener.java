package info.javacoding.sgl.input;

public interface MouseListener {
	/**
	 * Fired when the state of a button is changed.
	 * 
	 * @param e
	 */
	public void mouseClicked(MouseEvent e);

	/**
	 * Fired when the mouse moves.
	 * 
	 * @param e
	 */
	public void mouseMoved(MouseEvent e);

	/**
	 * Fired when the state of the wheel is changed.
	 * 
	 * @param e
	 */
	public void wheelChanged(MouseEvent e);
}
