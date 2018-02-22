package uiCommands;

import theater.Theater;
import userInterface.UI;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Help implements Command<UI>
{
	private static Help singleton;
	private final String LABEL = "Show all commands.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	protected Help() throws Exception
	{
		if (getClass().getName().equals("Help"))
		{
			throw new Exception();
		}
	}

	private Help(int i)
	{
	}

	public static Help instance()
	{
		if (singleton == null)
		{
			singleton = new Help(1);
		}
		return singleton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see userInterface.Command#getLabel()
	 */
	@Override
	public String getLabel()
	{
		return instance().LABEL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see userInterface.Command#isDataCommand()
	 */
	@Override
	public boolean isDataCommand()
	{
		return instance().IS_DATA_COMMAND;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see singleton.Singleton#readResolve()
	 */
	@Override
	public Command<UI> readResolve()
	{
		return instance();
	}

	/* (non-Javadoc)
	 * @see uiCommands.Command#isTerminateionCommand()
	 */
	@Override
	public boolean isTerminationCommand()
	{
		return IS_TERMINATION_COMMAND;
	}


	@Override
	public void call(UI arg)
	{
		// TODO Auto-generated method stub
		
	}

}
