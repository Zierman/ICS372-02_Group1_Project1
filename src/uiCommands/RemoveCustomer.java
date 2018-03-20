package uiCommands;

import exceptions.NoKeyTokenFoundException;
import theater.Theater;
import userInterface.UI;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class RemoveCustomer implements Command<UI>
{
	private static RemoveCustomer singleton;

	/**
	 * Gets or creates an instance of the singleton
	 * 
	 * @return an instance of the singleton
	 */
	public static RemoveCustomer instance()
	{
		if (singleton == null)
		{
			singleton = new RemoveCustomer(1);
		}
		return singleton;
	}

	private final String LABEL = "Remove a customer from the customer list.";
	private final boolean IS_DATA_COMMAND = true;

	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>RemoveCustomer</code> object used when creating a
	 * subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected RemoveCustomer() throws Exception
	{
		if (getClass().getName().equals("RemoveCustomer"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>RemoveCustomer</code> object used to create the
	 * singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private RemoveCustomer(int i)
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
		{
			Theater theater = ui.getTheater();
			Long key = Long.parseLong(UI.getInput("Enter customer ID: "));
			try
			{
				theater.removeMatchedCustomer(key);
				UI.outputSuccessMessage("customer removed");
			}
			catch (NoKeyTokenFoundException e)
			{
				UI.outputError(e, "no match found");
				done = !UI.tryAgainCheck();
			}
			done = !UI.yesCheck("Remove another customer?");
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
	 * 
	 * @author Troy Novak [wh1617wd@metrostate.edu]
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
