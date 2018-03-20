package uiCommands;

import userInterface.UI;

/**
 * The help command to display all commands.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Help implements Command<UI>
{
	private static Help singleton;

	/**
	 * Gets or creates an instance of the singleton
	 * 
	 * @return an instance of the singleton
	 */
	public static Help instance()
	{
		if (singleton == null)
		{
			singleton = new Help(1);
		}
		return singleton;
	}

	private final String LABEL = "Show all commands.";
	private final boolean IS_DATA_COMMAND = true;

	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>Help</code> object used when creating a subtype
	 * singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected Help() throws Exception
	{
		if (getClass().getName().equals("Help"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>Help</code> object used to create the singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private Help(int i)
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uiCommands.Command#call(java.lang.Object)
	 */
	@Override
	public void call(UI arg)
	{
		boolean done = false;
		while (!done)
		{
			try
			{
				UI ui = UI.instance();
				int i = UI.getCommandListFirstNumber();
				for (Command c : ui.getCommandList())
				{
					UI.println(i++ + ") " + c.getLabel());
				}
				done = true;
			}
			catch (Exception e)
			{
				// show error message
				UI.outputError(e, "Unable to show commands");

				// ask if user wants to continue and end if the user answers no
				done = !UI.tryAgainCheck();
			}
		}

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
	 * @see uiCommands.Command#isTerminateionCommand()
	 */
	@Override
	public boolean isTerminationCommand()
	{
		return IS_TERMINATION_COMMAND;
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

}
