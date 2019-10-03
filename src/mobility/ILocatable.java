package mobility;
/**
 * 
 * @author idan ben moshe 308118439 zohar azriav 201454899
 *
 */
public interface ILocatable {
	/**
	 * @return the current location
	 */
	public Point getLocation();

	/**
	 * 
	 * @param location
	 *            the new location
	 * @return true if location is valid, false if not
	 */
	public boolean setLocation(Point location);
}
