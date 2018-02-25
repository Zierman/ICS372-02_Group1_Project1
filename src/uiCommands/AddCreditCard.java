package uiCommands;

import userInterface.UI;

/**
 * The command to add a new credit card
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class AddCreditCard implements Command<UI>
{
	private static AddCreditCard singleton;
	private final String LABEL = "Add a new CreditCard.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>AddCreditCard</code> object used when creating a
	 * subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected AddCreditCard() throws Exception
	{
		if (getClass().getName().equals("AddCreditCard"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>AddCreditCard</code> object used to create the
	 * singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private AddCreditCard(int i)
	{
	}

	/**
	 * Gets or creates an instance of the singleton
	 * 
	 * @return an instance of the singleton
	 */
	public static AddCreditCard instance()
	{
		if (singleton == null)
		{
			singleton = new AddCreditCard(1);
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

	/* (non-Javadoc)
	 * @see uiCommands.Command#call(java.lang.Object)
	 */
	@Override
	public void call(UI arg)
	{
		// TODO Auto-generated method stub

	}

}
