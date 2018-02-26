package uiCommands;

import userInterface.UI;

/**
 * The command to list all customers.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class ListAllCustomers implements Command<UI>
{
	private static ListAllCustomers singleton;
	private final String LABEL = "Show a list of all customers.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>ListAllCustomers</code> object used when creating a
	 * subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected ListAllCustomers() throws Exception
	{
		if (getClass().getName().equals("ListAllCustomers"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>ListAllCustomers</code> object used to create the
	 * singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private ListAllCustomers(int i)
	{
	}

	/**
	 * Gets or creates an instance of the singleton
	 * 
	 * @return an instance of the singleton
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see uiCommands.Command#call(java.lang.Object)
	 */
	@Override
	public void call(UI arg)
	{
		// TODO Auto-generated method stub

	}

}