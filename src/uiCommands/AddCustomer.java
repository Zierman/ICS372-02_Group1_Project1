package uiCommands;

import userInterface.UI;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class AddCustomer implements Command<UI>
{
	private static AddCustomer singleton;
	private final String LABEL = "Add a customer to the customer list.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>AddCustomer</code> object used when creating a subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected AddCustomer() throws Exception
	{
		if (getClass().getName().equals("AddCustomer"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>AddCustomer</code> object used to create the singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private AddCustomer(int i)
	{
	}

	/**
	 * Gets or creates an instance of the singleton
	 * @return an instance of the singleton
	 */
	public static AddCustomer instance()
	{
		if (singleton == null)
		{
			singleton = new AddCustomer(1);
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
	public void call(UI arg)
	{
		// TODO Auto-generated method stub
		
	}

}
