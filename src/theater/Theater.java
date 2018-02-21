/**
 * 
 */
package theater;

import java.io.IOException;

import singleton.Singleton;
import storage.FileIO;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Theater implements Singleton<Theater>, Loadable, Savable
{
	private static Theater singleton;
	private static final String filename = "theater.dat";
	private String name;
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	protected Theater() throws Exception
	{
		if (getClass().getName().equals("Theater"))
		{
			throw new Exception();
		}
	}

	private Theater(int i)
	{
	}

	public static Theater instance()
	{
		if (singleton == null)
		{
			singleton = new Theater(1);
		}
		return singleton;
	}

	/* (non-Javadoc)
	 * @see singleton.Singleton#readResolve()
	 */
	@Override
	public Theater readResolve()
	{
		return instance();
	}

	@Override
	public void load() throws ClassNotFoundException, IOException
	{
		instance().setName((String)FileIO.read(filename));
		// TODO finish this
	}

	@Override
	public void save() throws IOException
	{
		FileIO.write(name,filename);
		// TODO finish this
	}

}
