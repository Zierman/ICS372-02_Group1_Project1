package uiCommands;

import java.util.ArrayList;
import java.util.Date;

import customer.Customer;
import customer.Customer.CreditCard;
import exceptions.NoPlayFoundException;
import exceptions.OutOfBoundsException;
import exceptions.NotEnoughSeatsAvailibleException;
import play.Play;
import theater.Theater;
import ticket.RegularTicket;
import ticket.Ticket;
import ticket.TicketList;
import userInterface.UI;

public class SellRegularTicket implements Command<UI>
{
	private static SellRegularTicket singleton;

	/**
	 * Gets or creates an instance of the singleton
	 * 
	 * @return an instance of the singleton
	 */
	public static SellRegularTicket instance()
	{
		if (singleton == null)
		{
			singleton = new SellRegularTicket(1);
		}
		return singleton;
	}

	private final String LABEL = "Sell Regular Ticket.";

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
	protected SellRegularTicket() throws Exception
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
	private SellRegularTicket(int i)
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
		Customer customer = null;
		CreditCard creditCard = null;
		Date dateOfShow = null;
		Play play = null;
		int qty = 0;
		boolean done = false;

		while (!done)
		{

			try
			{
				// ask for qty tickets to sell
				qty = UI.getIntFromInput("Enter the number of tickets to sell");
				if (qty < 1)
				{
					throw new OutOfBoundsException(
							"number of tickets must be postitive");
				}

				// ask for customer ID
				customer = UI.getCustomerFromInputID();

				// ask for credit card number
				creditCard = UI.getCreditCardFromInput(customer);

				// ask for the date of the show
				dateOfShow = UI
						.getDateFromInput("Enter the date of the showing");

				// check that there is enough seats available
				if (!theater.canSellTickets(qty, dateOfShow))
				{
					throw new NotEnoughSeatsAvailibleException();
				}

				// find the play that shows on that date
				play = theater.getPlay(dateOfShow);

				// create tickets
				ArrayList<Ticket> tickets = new ArrayList<Ticket>();
				for (int i = 0; i < qty; i++)
				{
					tickets.add(new RegularTicket(dateOfShow, play, customer, creditCard));
				}

				// sell tickets
				theater.sell(tickets);

				done = true;
			}
			catch (NotEnoughSeatsAvailibleException e)
			{
				// show error message
				UI.outputError(e,
						"Unable to sell ticket because there are not enough seats availible");

				// ask if user wants to continue and end if the user answers no
				done = !UI.tryAgainCheck();
			}
			catch (OutOfBoundsException e)
			{
				// show error message
				UI.outputError(e,
						"Unable to sell ticket because " + e.getMessage());

				// ask if user wants to continue and end if the user answers no
				done = !UI.tryAgainCheck();
			}
			catch (NoPlayFoundException e)
			{
				// show error message
				UI.outputError(e,
						"Unable to sell ticket because no play is showing on that day");

				// ask if user wants to continue and end if the user answers no
				done = !UI.tryAgainCheck();
			}
			catch (Exception e)
			{
				// show error message
				UI.outputError(e, "Unable to sell ticket");

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
