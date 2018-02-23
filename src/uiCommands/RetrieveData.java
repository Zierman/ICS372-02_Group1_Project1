package uiCommands;

import theater.Theater;
import userInterface.UI;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class RetrieveData implements Command<UI>
{
	private static RetrieveData singleton;
	private final String LABEL = "Retrieve all data.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	protected RetrieveData() throws Exception
	{
		if (getClass().getName().equals("RetrieveData"))
		{
			throw new Exception();
		}
	}

	private RetrieveData(int i)
	{
	}

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

	/* (non-Javadoc)
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
		if(ui.hasUsedDataCommand())
		{
			UI.outputError(new Exception(), "cannot retrieve data because data command has been used already");
		}
		boolean done = false;
		while(!done)
		try
		{
			Theater theater = ui.getTheater();
			theater.load();
			done = true;
		}
		catch (Exception e)
		{

			// show error message
			UI.outputError(e, "Unable to retrieve data");
			
			// ask if user wants to continue and end if the user answers no
			done = !UI.tryAgainCheck();
		}
	}

}
