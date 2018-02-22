/**
 * 
 */
package uiCommands;

import play.Play;
import theater.Theater;
import userInterface.UI;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class AddPlay implements Command<UI>
{
	private static AddPlay singleton;
	private final String LABEL = "Add a new play";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	protected AddPlay() throws Exception
	{
		if (getClass().getName().equals("AddPlay"))
		{
			throw new Exception();
		}
	}

	private AddPlay(int i)
	{
	}

	public static AddPlay instance()
	{
		if (singleton == null)
		{
			singleton = new AddPlay(1);
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

	@Override
	public void call(UI ui)
	{
		//TODO finish working on this
//		Theater theater = ui.getTheater();
//		boolean done = false;
//		while (!done)
//		{
//			try
//			{
//				String playName = UI.getInput("Enter Play's Name: ");
//				String playAddress = UI.getDate("Enter Play's Address: ");
//				String playPhoneNumber = UI.getInput("Enter Play's Phone Number: ");
//				theater.getPlayList().add(new Play(playName,playAddress,playPhoneNumber));
//				UI.outputSuccessMessage(playName + " added to play list.");
//				
//				// the loop is done if the user answers no
//				done = UI.getInput("Add another play? (Y/N)").toLowerCase().startsWith("n");
//			}
//			catch (Exception e)
//			{
//				UI.outputError(e, "Unable to add play");
//				
//				// the loop is done if the user answers no
//				done = UI.getInput("Try again? (Y/N)").toLowerCase().startsWith("n");
//			}
//		}
	}

	/* (non-Javadoc)
	 * @see uiCommands.Command#isTerminationCommand()
	 */
	@Override
	public boolean isTerminationCommand()
	{
		return IS_TERMINATION_COMMAND;
	}

}
