package ownership;

/**
 * An interface for an owned object allowing a method to return an owner.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <OwnerType> the type of the owner
 */
public interface Owned<OwnerType>
{
	/**Gets the owner.
	 * @return the owner object
	 */
	public OwnerType getOwner();

	/**Gets the owner.
	 * @return the owner object
	 */
	public void setOwner(OwnerType owner);
}
