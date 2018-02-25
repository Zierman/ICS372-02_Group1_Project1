package ownership;

/**
 * An interface for an owned object allowing a method to return an owner.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <Type> the type of the owner
 */
public interface Owned<Type>
{
	/**Gets the owner.
	 * @return the owner object
	 */
	public Type getOwner();
}
