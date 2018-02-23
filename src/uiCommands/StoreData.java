package uiCommands;

import java.io.IOException;

import theater.Theater;
import userInterface.UI;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class StoreData implements Command<UI>
{
	private static StoreData singleton;
	private final String LABEL = "Store all data.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	protected StoreData() throws Exception
	{
		if (getClass().getName().equals("StoreData"))
		{
			throw new Exception();
		}
	}

	private StoreData(int i)
	{
	}

	public static StoreData instance()
	{
		if (singleton == null)
		{
			singleton = new StoreData(1);
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
		boolean done = false;
		while(!done)
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

}
