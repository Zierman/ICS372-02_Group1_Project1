package storage;

/**
 * An interface to force the use of
 * {@link storage.ReadResolveable#readResolve()} when creating a singleton
 * class. Unfortunately, simply implementing this does not functionally make the
 * class a singleton.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * 
 */
public interface ReadResolveable<Type>
{
	/**
	 * Resolves a read by having it use the required static instance method call
	 * 
	 * @return an instance of the singleton
	 */
	public Type readResolve();
}
