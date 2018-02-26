package uiCommands;

import theater.Theater;
import userInterface.UI;

/**
 * The command to retrieve data from storage.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class RetrieveData implements Command<UI>
{
	private static RetrieveData singleton;
	private final String LABEL = "Retrieve all data.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>RetrieveData</code> object used when creating a
	 * subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected RetrieveData() throws Exception
	{
		if (getClass().getName().equals("RetrieveData"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>RetrieveData</code> object used to create the
	 * singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private RetrieveData(int i)
	{
	}

	/**
	 * Gets or creates an instance of the singleton
	 * 
	 * @return an instance of the singleton
	 */
	public static RetrieveData instance()
	{
		if (singleton == null)
		{
			singleton = new RetrieveData(1);
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
	 * @see uiCommands.Command#call(java.lang.Object)
	 */
	@Override
	public void call(UI ui)
	{
		if (ui.hasUsedDataCommand())
		{
			Exception e = new Exception();
			UI.outputError(e,
					"cannot retrieve data because data command has been used already");

		}
		else
		{
			boolean done = false;
			while (!done)
				try
				{
					Theater theater = ui.getTheater();
					theater.load();
					done = true;
					UI.outputSuccessMessage("data retrieved from storage.");
				}
				catch (Exception e)
				{

					// show error message
					UI.outputError(e, "Unable to retrieve data");

					// ask if user wants to continue and end if the user answers
					// no
					done = !UI.tryAgainCheck();
				}
		}
	}

}
