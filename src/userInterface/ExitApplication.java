/**
 * 
 */
package userInterface;

import ui.UI;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class ExitApplication implements Command<UI>
{
	private static ExitApplication singleton;
	private final String LABEL = "Exit the application";
	private final boolean IS_DATA_COMMAND = true;

	protected ExitApplication() throws Exception
	{
		if (getClass().getName().equals("ExitApplication"))
		{
			throw new Exception();
		}
	}

	private ExitApplication(int i)
	{
	}
	
	public ExitApplication instance()
	{
		if (singleton == null)
		{
			singleton = new ExitApplication(1);
		}
		return singleton;
	}


	/* (non-Javadoc)
	 * @see userInterface.Command#getLabel()
	 */
	@Override
	public String getLabel()
	{
		return instance().LABEL;
	}


	/* (non-Javadoc)
	 * @see userInterface.Command#isDataCommand()
	 */
	@Override
	public boolean isDataCommand()
	{
		return instance().IS_DATA_COMMAND;
	}

	/* (non-Javadoc)
	 * @see singleton.Singleton#readResolve()
	 */
	@Override
	public Command<UI> readResolve()
	{
		return instance();
	}

	@Override
	public void call(UI ui)
	{
		// TODO Auto-generated method stub
		
	}

}


