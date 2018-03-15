package uiCommands;

import java.util.Date;

import play.Play;
import theater.Theater;
import ticket.RegularTicket;
import ticket.Ticket;
import ticket.TicketList;
import userInterface.UI;

public class ListAllTicketsForDay implements Command<UI>
{
	private static ListAllTicketsForDay singleton;

	/**
	 * Gets or creates an instance of the singleton
	 * 
	 * @return an instance of the singleton
	 */
	public static ListAllTicketsForDay instance()
	{
		if (singleton == null)
		{
			singleton = new ListAllTicketsForDay(1);
		}
		return singleton;
	}

	private final String LABEL = "List All Tickets For a Day.";

	private final boolean IS_DATA_COMMAND = true;

	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>SellRegularTicket</code> object used when creating a
	 * subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected ListAllTicketsForDay() throws Exception
	{
		if (getClass().getName().equals("SellRegularTicket"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>SellRegularTicket</code> object used to create the
	 * singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private ListAllTicketsForDay(int i)
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
		TicketList ticketList = theater.getTicketList();
		String output = "";
		boolean done = false;
		boolean found = false;
		while (!done)
		{
			try
			{
				// TODO finish this
		
				Date inputDate = UI.getDateFromInput("Enter date of the showing: ");
		
				for (Ticket ticket : ticketList)
				{
					if (UI.format(ticket.getDateOfShow()).contentEquals(UI.format(inputDate)))
					{						
						output += format(ui, ticket);
						found = true;
					}
				}

				if (!found)
				{
					UI.println("No plays found.");
				}
				else
				{
					UI.println(output);
				}
				
				done = true;
			}
			catch (Exception e) {

				// show error message
				UI.outputError(e, "Unable to list all plays");

				// ask if user wants to continue and end if the user answers no
				done = !UI.tryAgainCheck();
			}
		}
	}

	/**
	 * Formats a ticket into a standard formated string for output.
	 * 
	 * @param ui
	 *            the user interface that will do the formated output
	 * @param ticket
	 *            the ticket to be formated for output
	 * @return a string of the formated ticket
	 */
	private String format(UI ui, Ticket ticket)
	{
		ui.getStandardFormat().visit(ticket);
		return ui.getStandardFormat().toString();
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
	 * @see uiCommands.Command#isTerminateionCommand()
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
