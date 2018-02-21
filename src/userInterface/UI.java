/**
 * 
 */
package userInterface;

import java.util.LinkedList;

import singleton.Singleton;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class UI implements Singleton
{

	private static UI singleton;

	protected UI() throws Exception
	{
		if (getClass().getName().equals("UI"))
		{
			throw new Exception();
		}
	}

	private UI(int i)
	{
	}
	

	public static UI instance()
	{
		if (singleton == null)
		{
			singleton = new UI(1);
		}
		return singleton;
	}

	@Override
	public Object readResolve()
	{
		return instance();
	}

	// TODO finish

}
