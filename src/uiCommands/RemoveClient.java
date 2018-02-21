package uiCommands;

import theater.Theater;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class RemoveClient implements Command<Theater>
{
	private static RemoveClient singleton;
	private final String LABEL = "Remvoe a client from the client list.";
	private final boolean IS_DATA_COMMAND = true;

	protected RemoveClient() throws Exception
	{
		if (getClass().getName().equals("RemoveClient"))
		{
			throw new Exception();
		}
	}

	private RemoveClient(int i)
	{
	}

	public RemoveClient instance()
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
	public Command<Theater> readResolve()
	{
		return instance();
	}

	@Override
	public void call(Theater arg)
	{
		// TODO Auto-generated method stub

	}

}
