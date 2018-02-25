package uiCommands;

import keyToken.NoKeyTokenFoundException;
import theater.Theater;
import userInterface.UI;

/**
 * The command to remove a client.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class RemoveClient implements Command<UI>
{
	private static RemoveClient singleton;
	private final String LABEL = "Remvoe a client from the client list.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>RemoveClient</code> object used when creating a subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected RemoveClient() throws Exception
	{
		if (getClass().getName().equals("RemoveClient"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>RemoveClient</code> object used to create the singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private RemoveClient(int i)
	{
	}

	/**
	 * Gets or creates an instance of the singleton
	 * @return an instance of the singleton
	 */
	public static RemoveClient instance()
	{
		if (singleton == null)
		{
			singleton = new RemoveClient(1);
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


	/* (non-Javadoc)
	 * @see uiCommands.Command#call(java.lang.Object)
	 */
	@Override
	public void call(UI ui)
	{
		Theater theater = ui.getTheater();
		Long key = Long.parseLong(UI.getInput("Enter client ID: "));
		try
		{
			theater.removeMatchedClient(key);
			UI.outputSuccessMessage("client removed");
		}
		catch (NoKeyTokenFoundException e)
		{
			UI.outputError(e, "no match found");
		}
		
	}

}
