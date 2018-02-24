package uiCommands;

import theater.Theater;
import userInterface.UI;

//TODO document all of this
/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class ListAllCustomers implements Command<UI>
{
	private static ListAllCustomers singleton;
	private final String LABEL = "Show a list of all customers.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	protected ListAllCustomers() throws Exception
	{
		if (getClass().getName().equals("ListAllCustomers"))
		{
			throw new Exception();
		}
	}

	private ListAllCustomers(int i)
	{
	}

	public static ListAllCustomers instance()
	{
		if (singleton == null)
		{
			singleton = new ListAllCustomers(1);
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
	 * @see uiCommands.Command#isTerminationCommand()
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