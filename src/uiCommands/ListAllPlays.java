package uiCommands;

import client.Client;
import client.ClientList;
import play.Play;
import play.PlayList;
import theater.Theater;
import userInterface.UI;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class ListAllPlays implements Command<UI>
{
	private static ListAllPlays singleton;
	private final String LABEL = "Show a list of all plays.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	protected ListAllPlays() throws Exception
	{
		if (getClass().getName().equals("ListAllPlays"))
		{
			throw new Exception();
		}
	}

	private ListAllPlays(int i)
	{
	}

	public static ListAllPlays instance()
	{
		if (singleton == null)
		{
			singleton = new ListAllPlays(1);
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
	public void call(UI ui)
	{

		boolean done = false;
		while (!done)
		{
			try
			{
				Theater theater = ui.getTheater();
				PlayList playList = theater.getPlayList();
				String output = "";
				for (Play play : playList)
				{
					output += "name: " + play.getName() + ",\n" + "start date: "
							+ play.getStartDate() + ",\n" + "end date: "
							+ play.getEndDate() + ",\n" + "Client : "
							+ play.getClient().getName() + " ("
							+ play.getClient().getID() + ")\n" + "\n";
				}
				UI.println(output);
				done = true;
			}
			catch (Exception e)
			{

				// show error message
				UI.outputError(e, "Unable to list all plays");

				// ask if user wants to continue and end if the user answers no
				done = !UI.tryAgainCheck();
			}
		}

	}

}