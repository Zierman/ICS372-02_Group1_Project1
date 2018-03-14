/**
 * 
 */
package uiCommands;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import client.Client;
import currency.Dollar;
import exceptions.ConflictingDatesException;
import exceptions.NoKeyTokenFoundException;
import exceptions.OutOfBoundsException;
import play.Play;
import theater.Theater;
import userInterface.UI;

/**
 * The command to add new play.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class AddPlay implements Command<UI>
{
	private static AddPlay singleton;
	/**
	 * Gets or creates an instance of the singleton
	 * 
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
	private final String LABEL = "Add a new play";
	private final boolean IS_DATA_COMMAND = true;

	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>AddPlay</code> object used when creating a subtype
	 * singleton
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see uiCommands.Command#call(java.lang.Object)
	 */
	@Override
	public void call(UI ui)
	{
		Theater theater = ui.getTheater();
		boolean done = false;
		Client client = null;
		Play play = null;
		Dollar price = null;
		String name = "";
		int seatingCapacity = 0;
		
		while (!done)
		{
			try
			{
				// get input needed to create a new play object
				name = UI.getInput("Enter play's name: ");
				client = UI.getClientFromInputID();
				price = UI.getDollarFromInput("Enter the regular ticket price");
				seatingCapacity = UI.getIntFromInput("Enter the seating capacity", 1, null);
				
				// trys to set the dates from input
				boolean doneWithDates = false;
				while (!doneWithDates)
				{
					try
					{
						Date startDate = UI.getDateFromInput("Enter play's start date");
						Date endDate = UI.getDateFromInput("Enter play's end date");;

						// create new play object
						play = new Play(name, client, startDate, endDate, price, seatingCapacity);
						
						doneWithDates = true;
					}
					catch (ParseException e)
					{
						throw e;
					}
					catch (ConflictingDatesException e)
					{
						// show error message
						UI.outputError(e,
								"Dates conflict with other plays in the list.");

						// ask if user wants to continue and end if the user
						// answers no
						doneWithDates = !UI.tryAgainCheck();
						
						if (doneWithDates)
						{
							throw e;
						}
					}
					catch(Exception e)
					{
						// show error message
						UI.outputError(e,
								"There was a problem creating the play object.");

						// ask if user wants to continue and end if the user
						// answers no
						doneWithDates = !UI.tryAgainCheck();

						if (doneWithDates)
						{
							throw e;
						}
					}
				}

				// add to list
				theater.add(play);
				
				// show user that it was added
				UI.outputSuccessMessage(name + " added to play list.");

				// ask if user wants to continue and end if the user answers no
				done = !UI.yesCheck("Add another play?");

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
	 * @see uiCommands.Command#isTerminationCommand()
	 */
	@Override
	public boolean isTerminationCommand()
	{
		return IS_TERMINATION_COMMAND;
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

}
