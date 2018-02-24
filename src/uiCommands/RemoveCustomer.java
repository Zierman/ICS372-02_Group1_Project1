package uiCommands;

import keyToken.NoKeyTokenFoundException;
import theater.Theater;
import userInterface.UI;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class RemoveCustomer implements Command<UI>
{
	private static RemoveCustomer singleton;
	private final String LABEL = "Remvoe a customer from the customer list.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	protected RemoveCustomer() throws Exception
	{
		if (getClass().getName().equals("RemoveCustomer"))
		{
			throw new Exception();
		}
	}

	private RemoveCustomer(int i)
	{
	}

	public static RemoveCustomer instance()
	{
		if (singleton == null)
		{
			singleton = new RemoveCustomer(1);
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

	@Override
	public void call(UI ui)
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
		}

	}

}
