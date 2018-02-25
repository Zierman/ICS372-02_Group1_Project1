/**
 * 
 */
package uiCommands;

import java.text.SimpleDateFormat;
import java.util.Date;

import client.Client;
import keyToken.NoKeyTokenFoundException;
import play.Play;
import theater.Theater;
import userInterface.UI;

/**
 * The command to add new play.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class AddPlay implements Command<UI>
{
	private static AddPlay singleton;
	private final String LABEL = "Add a new play";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>AddPlay</code> object used when creating a subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected AddPlay() throws Exception
	{
		if (getClass().getName().equals("AddPlay"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>AddPlay</code> object used to create the singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private AddPlay(int i)
	{
	}

	/**
	 * Gets or creates an instance of the singleton
	 * @return an instance of the singleton
	 */
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

	/* (non-Javadoc)
	 * @see uiCommands.Command#call(java.lang.Object)
	 */
	@Override
	public void call(UI ui)
	{
		Theater theater = ui.getTheater();
		boolean done = false;
		while (!done)
		{
			try
			{
				// get input needed to create a new play object
				String name = UI.getInput("Enter play's name: ");
				String clientID = UI.getInput("Enter client's ID: ");
				String startDateString = UI.getInput("Enter play's start date (MM/dd/yyyy): ");
				String endDateString = UI.getInput("Enter play's end date (MM/dd/yyyy): ");
				
				// find client from clientID
				Client client = null;
				for(Client c : theater.getClientList())
				{
					if (c.getID().matches(Long.parseLong(clientID)))
					{
						client = c;
						break;
					}
				}
				if(client == null)
				{
					throw new NoKeyTokenFoundException();
				}
				
				// convert from string to dates				
				Date startDate = new SimpleDateFormat("MM/dd/yyyy").parse(startDateString);
				Date endDate = new SimpleDateFormat("MM/dd/yyyy").parse(endDateString);
				
				// create new play object
				Play play = new Play(name, client, startDate, endDate);
				
				// add to list
				theater.add(play);
				
				// show user that it was added
				UI.outputSuccessMessage(name + " added to play list.");
				
				// ask if user wants to continue and end if the user answers no
				done = UI.getInput("Add another play? (Y/N)").toLowerCase().startsWith("n");
			}
			catch (Exception e)
			{
				// show error message
				UI.outputError(e, "Unable to add play");
				
				// ask if user wants to continue and end if the user answers no
				done = !UI.tryAgainCheck();
			}
		}
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
