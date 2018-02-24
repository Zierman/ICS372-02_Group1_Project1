package uiCommands;

import userInterface.UI;

//TODO document all of this
/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class AddCreditCard implements Command<UI>
{
	private static AddCreditCard singleton;
	private final String LABEL = "Add a new CreditCard.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	protected AddCreditCard() throws Exception
	{
		if (getClass().getName().equals("AddCreditCard"))
		{
			throw new Exception();
		}
	}

	private AddCreditCard(int i)
	{
	}

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

	/* (non-Javadoc)
	 * @see uiCommands.Command#isTerminateionCommand()
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
