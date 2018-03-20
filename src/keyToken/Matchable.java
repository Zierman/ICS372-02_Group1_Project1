package keyToken;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <MatchableType>
 *            The type that the Matchable object can be matched by
 */
public interface Matchable<MatchableType>
{
	/**
	 * Checks to see if the parameter object matches this object
	 * 
	 * @param valueToMatch
	 *            the object that is being checked against
	 * @return true if they match else false
	 */
	public boolean matches(MatchableType valueToMatch);
}
