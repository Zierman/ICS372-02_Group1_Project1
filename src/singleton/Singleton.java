/**
 * 
 */
package singleton;

import singleton.ReadResolveable;

/**
 * Represents a singletonObject.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Singleton implements ReadResolveable<Singleton>
{
	/**
	 * a <code>Singleton</code> object to enforce singleton behavior.
	 */
	private static Singleton singleton;


	

	/**
	 * Constructs a Singleton used when creating a subtype singleton
	 * 
	 * @throws Exception if used to try to create a base type Singleton
	 */
	protected Singleton() throws Exception
	{
		if (getClass().getName().equals("Singleton"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the Singleton used to create the singleton.
	 * 
	 * @param i an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private Singleton(int i)
	{
	}

	/**
	 * Gets or Creates the instance of the singleton Singleton
	 * 
	 * @return the instance of the singleton Singleton
	 */
	public static Singleton instance()
	{
		if (singleton == null)
		{
			singleton = new Singleton(1);
		}
		return singleton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see singleton.Singleton#readResolve()
	 */
	@Override
	public Singleton readResolve()
	{
		return instance();
	}
	
}
