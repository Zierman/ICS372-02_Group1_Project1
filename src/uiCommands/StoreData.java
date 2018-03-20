package uiCommands;

import theater.Theater;
import userInterface.UI;

/**
 * The command to store the data to storage.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class StoreData implements Command<UI>
{
	private static StoreData singleton;

	/**
	 * Gets or creates an instance of the singleton
	 * 
	 * @return an instance of the singleton
	 */
	public static StoreData instance()
	{
		if (singleton == null)
		{
			singleton = new StoreData(1);
		}
		return singleton;
	}

	private final String LABEL = "Store all data.";
	private final boolean IS_DATA_COMMAND = true;

	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>StoreData</code> object used when creating a subtype
	 * singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected StoreData() throws Exception
	{
		if (getClass().getName().equals("StoreData"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>StoreData</code> object used to create the
	 * singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private StoreData(int i)
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uiCommands.Command#call(java.lang.Object)
	 */
	@Override
	public void call(UI ui)
	{
		boolean done = false;
		while (!done)
			try
			{
				Theater theater = ui.getTheater();
				theater.save();
				done = true;
			}
			catch (Exception e)
			{
				// show error message
				UI.outputError(e, "Unable to store data");

				// ask if user wants to continue and end if the user answers no
				done = !UI.tryAgainCheck();
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
