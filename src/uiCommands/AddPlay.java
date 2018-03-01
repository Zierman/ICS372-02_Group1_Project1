/**
 * 
 */
package uiCommands;

import java.text.SimpleDateFormat;
import java.util.Date;

import client.Client;
import currency.Dollar;
import exceptions.ConflictingDatesException;
import exceptions.NoKeyTokenFoundException;
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
		while (!done)
		{
			try
			{
				// get input needed to create a new play object
				String name = UI.getInput("Enter play's name: ");
				String clientID = UI.getInput("Enter client's ID: ");
				Dollar price = null;


				// find client from input client ID
				boolean doneWithID = false;
				while (!doneWithID)
				{
					client = null;
					try
					{
						for (Client c : theater.getClientList())
						{
							try
							{
								if (c.getID().matches(Long.parseLong(clientID)))
								{
									client = c;
									break;
								}
							}
							catch (NumberFormatException e)
							{
								throw new NoKeyTokenFoundException();
							}
						}
						if (client == null)
						{
							throw new NoKeyTokenFoundException();
						}
						else
						{
							doneWithID = true;
						}
					}
					catch (NoKeyTokenFoundException e)
					{
						// show error message
						UI.outputError(e, "Client ID could not be matched.");

						// ask if user wants to continue and end if the user
						// answers no
						doneWithID = !UI.tryAgainCheck();

						if (doneWithID)
						{
							throw new Exception();
						}
					}
				}
				
				// sets regular ticket price from user input
				boolean doneWithRegularTicketPrice = false;
				while (!doneWithRegularTicketPrice)
				{
					try
					{
						String startDateString = UI.getInput(
								"Enter play's regular ticket price: $");
						 price = new Dollar(Double.parseDouble(startDateString));
						 doneWithRegularTicketPrice = true;
					}
					catch(Exception e)
					{
						// show error message
						UI.outputError(e,
								"There was a problem with the entered regular ticket price.");

						// ask if user wants to continue and end if the user
						// answers no
						doneWithRegularTicketPrice = !UI.tryAgainCheck();

						if (doneWithRegularTicketPrice)
						{
							throw new Exception();
						}
					}
				}
				
				// trys to set the dates from input
				boolean doneWithDates = false;
				while (!doneWithDates)
				{
					try
					{
						String startDateString = UI.getInput(
								"Enter play's start date (MM/dd/yyyy): ");
						Date startDate = new SimpleDateFormat("MM/dd/yyyy")
								.parse(startDateString);

						String endDateString = UI.getInput(
								"Enter play's end date (MM/dd/yyyy): ");
						Date endDate = new SimpleDateFormat("MM/dd/yyyy")
								.parse(endDateString);

						// create new play object
						Play play = new Play(name, client, startDate, endDate, price);

						// add to list
						theater.add(play);

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
							throw new Exception();
						}
					}
					catch(Exception e)
					{
						// show error message
						UI.outputError(e,
								"There was a problem with the entered dates.");

						// ask if user wants to continue and end if the user
						// answers no
						doneWithDates = !UI.tryAgainCheck();

						if (doneWithDates)
						{
							throw new Exception();
						}
					}
				}
				
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
