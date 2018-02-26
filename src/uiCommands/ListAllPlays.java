package uiCommands;

import play.Play;
import play.PlayList;
import theater.Theater;
import userInterface.UI;

/**
 * The command to list all plays.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class ListAllPlays implements Command<UI>
{
	private static ListAllPlays singleton;
	private final String LABEL = "Show a list of all plays.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>ListAllPlays</code> object used when creating a subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected ListAllPlays() throws Exception
	{
		if (getClass().getName().equals("ListAllPlays"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>ListAllPlays</code> object used to create the singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private ListAllPlays(int i)
	{
	}

	/**
	 * Gets or creates an instance of the singleton
	 * @return an instance of the singleton
	 */
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

	/* (non-Javadoc)
	 * @see uiCommands.Command#call(java.lang.Object)
	 */
	@Override
	public void call(UI ui)
	{

		boolean done = false;
		while (!done)
		{
			try
			{
				boolean found = false;
				Theater theater = ui.getTheater();
				PlayList playList = theater.getPlayList();
				String output = "";
				for (Play play : playList)
				{
					found = true;
					output += "name: " + play.getName() + ",\n" + "start date: "
							+ play.getStartDate() + ",\n" + "end date: "
							+ play.getEndDate() + ",\n" + "Client : "
							+ play.getOwner().getName() + " ("
							+ play.getOwner().getID() + ")\n" + "\n";
				}
				
				if(!found)
				{
					UI.println("No clients found.");
				}
				else
				{
					UI.println(output);
				}
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