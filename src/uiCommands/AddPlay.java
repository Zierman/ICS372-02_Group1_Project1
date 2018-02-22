/**
 * 
 */
package uiCommands;

import java.text.SimpleDateFormat;
import java.util.Date;

import client.Client;
import jdk.nashorn.internal.parser.DateParser;
import keyToken.noKeyTokenFoundException;
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
					if (c.getId().matches(Long.parseLong(clientID)))
					{
						client = c;
						break;
					}
				}
				if(client == null)
				{
					throw new noKeyTokenFoundException();
				}
				
				// convert from string to dates				
				Date startDate = new SimpleDateFormat("MM/dd/yyyy").parse(startDateString);
				Date endDate = new SimpleDateFormat("MM/dd/yyyy").parse(endDateString);
				
				// create new play object
				Play play = new Play(name, client, startDate, endDate);
				
				// add to list
				theater.add(play, theater.getPlayList());
				
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
				done = UI.getInput("Try again? (Y/N)").toLowerCase().startsWith("n");
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
