package singleton;

//TODO document all of this
/**
 * An interface to force the use of {@link singleton.Singleton#readResolve()} when creating a singleton class. Unfortunately, simply implementing this does not functionally make it a singleton.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * 
 */
public interface Singleton<Type>
{
	/**
	 * Resolves a read by having it use the required static instance method call
	 * @return an instance of the singleton
	 */
	public Type readResolve();
}
