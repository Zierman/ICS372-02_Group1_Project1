/**
 * 
 */
package userInterface;

import java.io.Closeable;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import client.Client;
import currency.Dollar;
import customer.Customer;
import customer.Customer.CreditCard;
import exceptions.NoCardFoundException;
import exceptions.NoKeyTokenFoundException;
import exceptions.OutOfBoundsException;
import singleton.ReadResolveable;
import theater.Theater;
import uiCommands.AddClient;
import uiCommands.AddCreditCard;
import uiCommands.AddCustomer;
import uiCommands.AddPlay;
import uiCommands.Command;
import uiCommands.ExitApplication;
import uiCommands.Help;
import uiCommands.ListAllClients;
import uiCommands.ListAllCustomers;
import uiCommands.ListAllPlays;
import uiCommands.ListAllTicketsForDay;
import uiCommands.PayClient;
import uiCommands.RemoveClient;
import uiCommands.RemoveCreditCard;
import uiCommands.RemoveCustomer;
import uiCommands.RetrieveData;
import uiCommands.SellAdvanceTicket;
import uiCommands.SellRegularTicket;
import uiCommands.SellStudentAdvanceTicket;
import uiCommands.StoreData;
import visitor.StandardFormat;

/**
 * A user interface that is responsible for handling interactions between the
 * user and the data.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class UI implements ReadResolveable<UI>, Closeable
{

	/**
	 * a <code>Scanner</code> to get input from user
	 */
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * a <code>UI</code> object to enforce singleton behavior
	 */
	private static UI singleton;

	/**
	 * a <code>LinkedList</code> of all available commands
	 */
	private static LinkedList<Command<UI>> commandList = new LinkedList<Command<UI>>();

	/**
	 * a <code>Command</code> that holds an instance of the help command.
	 */
	private static Command<UI> helpCommand = Help.instance();

	/**
	 * a Command that holds an instance of the retrieve data command.
	 */
	private static Command<UI> retrieveDataCommand = RetrieveData.instance();

	/**
	 * a <code>final boolean</code> that is true if debug mode is on. If debug
	 * mode is on, extra information will be displayed when error output is
	 * shown during a {@link userInterface.UI#outputError(Exception, String)}
	 * call.
	 */
	protected static final boolean DEBUG_MODE = false;
	protected static final SimpleDateFormat DATE_FORMAT_MDY = new SimpleDateFormat("MM/dd/yyyy");
	protected static final SimpleDateFormat DATE_FORMAT_CREDIT_CARD_EXP = new SimpleDateFormat("MM/yy");
	
	public static String format(Date date)
	{
		return DATE_FORMAT_MDY.format(date);
	}
	
	public static String formatCreditCardExp(Date date)
	{
		return DATE_FORMAT_CREDIT_CARD_EXP.format(date);
	}

	/**
	 * The first number in shown in the command list. All commands will be shown
	 * regardless.
	 */
	public static final int COMMAND_LIST_FIRST_NUMBER = 0; // If you want list
															// to start at 1
															// instead of 0
															// enter 1.

	/**
	 * Gets the first number to be shown in the list of commands.
	 * 
	 * @return the first number of the command list
	 */
	public static int getCommandListFirstNumber()
	{
		return COMMAND_LIST_FIRST_NUMBER;
	}

	/**
	 * Gets input from the user
	 * 
	 * @param userPrompt
	 *            a string that is to be displayed to the user before getting
	 *            input.
	 * @return the string that was input from the user.
	 */
	public static String getInput(String userPrompt)
	{
		String input = "";

		System.out.print(userPrompt);
		while (input == "" && scanner.hasNextLine())
		{
			input += scanner.nextLine();
		}
		return input;

	}

	public static Client getClientFromInputID() throws Exception
	{
		Theater theater = Theater.instance();
		Client client = null;
		// find client from input client ID
		boolean done = false;
		while (!done)
		{
			String clientID = UI.getInput("Enter client's ID: ");
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

				done = true;

			}
			catch (NoKeyTokenFoundException e)
			{
				// show error message
				UI.outputError(e, "Client ID could not be matched.");

				// ask if user wants to continue and end if the user
				// answers no
				done = !UI.tryAgainCheck();

				if (done)
				{
					throw new Exception();
				}
			}
		}
		return client;
	}

	public static Customer getCustomerFromInputID() throws Exception
	{
		Theater theater = Theater.instance();
		Customer customer = null;
		// find customer from input customer ID
		boolean done = false;
		while (!done)
		{
			String customerID = UI.getInput("Enter customer's ID: ");
			customer = null;
			try
			{
				for (Customer c : theater.getCustomerList())
				{
					try
					{
						if (c.getID().matches(Long.parseLong(customerID)))
						{
							customer = c;
							break;
						}
					}
					catch (NumberFormatException e)
					{
						throw new NoKeyTokenFoundException();
					}
				}
				if (customer == null)
				{
					throw new NoKeyTokenFoundException();
				}

				done = true;

			}
			catch (NoKeyTokenFoundException e)
			{
				// show error message
				UI.outputError(e, "Customer ID could not be matched.");

				// ask if user wants to continue and end if the user
				// answers no
				done = !UI.tryAgainCheck();

				if (done)
				{
					throw new Exception();
				}
			}
		}
		return customer;
	}

	public static Date getDateFromInput(String prompt) throws ParseException
	{
		boolean done = false;
		Date date = null;

		while (!done)
		{
			try
			{
				String dateString = UI.getInput(prompt + " (MM/dd/yyyy): ");
				date = new SimpleDateFormat("MM/dd/yyyy").parse(dateString);
				done = true;
			}
			catch (ParseException e)
			{
				// show error message
				UI.outputError(e, "Input could not be parsed to Date.");

				// ask if user wants to continue and end if the user
				// answers no
				done = !UI.tryAgainCheck();

				if (done)
				{
					throw e;
				}
			}
		}

		return date;
	}

	public static CreditCard getCreditCardFromInput(Customer customer)
			throws NoCardFoundException
	{
		CreditCard card = null;
		boolean done = false;

		while (!done)
		{

			try
			{
				List<CreditCard> list = customer.getCardList();
				String cardNumber = getInput("Enter credit card number");

				for (CreditCard c : list)
				{
					if (c.matches(cardNumber))
					{
						card = c;
					}
				}
				if (card == null)
				{
					throw new NoCardFoundException();
				}

				done = true;
			}
			catch (NoCardFoundException e)
			{
				// show error message
				UI.outputError(e, "No matching credit card found owned by "
						+ customer.getName() + ".");

				// ask if user wants to continue and end if the user
				// answers no
				done = !UI.tryAgainCheck();

				if (done)
				{
					throw e;
				}
			}
		}

		return card;

	}

	/**
	 * Asks the user for an integer value
	 * @param prompt the prompt to be shown to the user before the input is entered
	 * @param low the lowest value that may be entered or null if there is no lower bound
	 * @param high the lowest value that is too great to be in range or null if there is no upper bound
	 * @return the input integer
	 * @throws OutOfBoundsException if the user enters a value that is not in the range [low, high)
	 * @throws NumberFormatException if the user enters a string that cannot be converted to an integer.
	 */
	public static int getIntFromInput(String prompt, Integer low, Integer high)
			throws OutOfBoundsException, NumberFormatException
	{
		OutOfBoundsException ex = new OutOfBoundsException(
				"Input must be in the range [" + low + "," + high + ")");
		int number = 0;
		boolean done = false;
		while (!done)
		{
			try
			{
				String inputStr = UI.getInput(prompt + ": ");
				number = Integer.parseInt(inputStr);
				if ((high != null && high.compareTo(number) <= 0)
						|| low != null && low.compareTo(number) > 0)
				{
					throw ex;
				}
				done = true;
			}
			catch (OutOfBoundsException e)
			{
				// show error message
				UI.outputError(e, e.getMessage());

				// ask if user wants to continue and end if the user
				// answers no
				done = !UI.tryAgainCheck();

				if (done)
				{
					throw e;
				}
			}
			catch (NumberFormatException e)
			{
				// show error message
				UI.outputError(e,
						"Input could not be parsed to Integer due to invalid number format.");

				// ask if user wants to continue and end if the user
				// answers no
				done = !UI.tryAgainCheck();

				if (done)
				{
					throw e;
				}
			}
		}
		return number;
	}

	public static Dollar getDollarFromInput(String prompt)
	{
		Dollar dollar = null;
		// sets regular ticket price from user input
		boolean done = false;
		while (!done)
		{
			try
			{
				String inputStr = UI.getInput(prompt + ": $");
				dollar = new Dollar(Double.parseDouble(inputStr));
				done = true;
			}
			catch (NumberFormatException e)
			{
				// show error message
				UI.outputError(e,
						"Input could not be parsed to Dollar due to invalid number format.");

				// ask if user wants to continue and end if the user
				// answers no
				done = !UI.tryAgainCheck();

				if (done)
				{
					throw e;
				}
			}
		}
		return dollar;
	}

	/**
	 * Gets or Creates the instance of the singleton UI
	 * 
	 * @return the instance of the singleton UI
	 */
	public static UI instance()
	{
		if (singleton == null)
		{
			singleton = new UI(1);
			commandList.add(ExitApplication.instance());
			commandList.add(AddClient.instance());
			commandList.add(RemoveClient.instance());
			commandList.add(ListAllClients.instance());
			commandList.add(AddCustomer.instance());
			commandList.add(RemoveCustomer.instance());
			commandList.add(AddCreditCard.instance());
			commandList.add(RemoveCreditCard.instance());
			commandList.add(ListAllCustomers.instance());
			commandList.add(AddPlay.instance());
			commandList.add(ListAllPlays.instance());
			commandList.add(StoreData.instance());
			commandList.add(RetrieveData.instance());
			commandList.add(SellRegularTicket.instance());
			commandList.add(SellAdvanceTicket.instance());
			commandList.add(SellStudentAdvanceTicket.instance());
			commandList.add(PayClient.instance());
			commandList.add(ListAllTicketsForDay.instance());
			commandList.add(Help.instance());

		}
		return singleton;
	}

	/**
	 * Asks a question and returns true if user answers no.
	 * 
	 * @param question
	 *            a String that holds a question to ask the user. Should end
	 *            with '?'
	 * @return true if the user answers no.
	 */
	public static boolean noCheck(String question)
	{
		return !yesCheck(question);
	}

	/**
	 * Shows an error message to the user. If
	 * {@link userInterface.UI#DEBUG_MODE} is set to true, the stack trace of
	 * the exception is printed.
	 * 
	 * @param e
	 *            the <code>Exception</code> that caused the error
	 * @param string
	 *            the <code>String</code> that will be shown to the user
	 */
	public static void outputError(Exception e, String string)
	{
		System.err.println(string);
		if (DEBUG_MODE)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Displays a success message to the user.
	 * 
	 * @param string
	 *            the <code>String</code> containing the message to be displayed
	 */
	public static void outputSuccessMessage(String string)
	{
		System.out.println(string);

	}

	/**
	 * Prints a string to the system's standard output.
	 * 
	 * @param string
	 *            the <code>String</code> to be printed.
	 */
	public static void print(String string)
	{
		System.out.print(string);
	}

	/**
	 * Prints a string to the system's standard output with a line break at the
	 * end.
	 * 
	 * @param string
	 *            the <code>String</code> to be printed.
	 */
	public static void println(String string)
	{
		System.out.println(string);
	}

	/**
	 * Runs the UI.
	 * 
	 */
	public static void run()
	{
		// create a user interface instance
		UI ui = UI.instance();

		// lastCommand holds the command last used or null if the last command
		// was not valid or no command was used
		Command<UI> lastCommand = null;

		// input is a string to store user input
		String input = "";

		if (ui.getTheater().canLoad())
		{
			if (UI.yesCheck("Save data was found, retrieve data?"))
			{
				retrieveDataCommand.call(ui);
			}

		}

		// show all commands.
		helpCommand.call(ui);

		// until a terminating command is called, keep getting commands
		while (lastCommand == null || !lastCommand.isTerminationCommand())
		{
			try
			{
				// get the command number from user
				input = getInput("Enter command number: ");
				int commandNumber = Integer.parseInt(input)
						- COMMAND_LIST_FIRST_NUMBER;

				// find the command
				lastCommand = commandList.get(commandNumber);

				// call the command
				lastCommand.call(ui);

				// handle data command usage tracking
				if (lastCommand.isDataCommand())
				{
					ui.setDataCommandUsed();
				}
			}
			catch (Exception e) // if any exceptions are thrown
			{
				// tell the user that the command failed
				outputError(e, "command \"" + input + "\" failed.");
				lastCommand = null;
			}
		}

		// after the program completes, close the user interface.
		try
		{
			ui.close();
		}
		catch (Exception e)
		{
			// if the user interface can't close print the stack trace
			e.printStackTrace();
		}
	}

	/**
	 * Asks user if they wish to try again and returns true if they do
	 * 
	 * @return true if user wants to try again, else false
	 */
	public static boolean tryAgainCheck()
	{
		// input will hold the user input
		String input = "";

		// tryAgain hold a boolean value to show if the user wants to try again
		boolean tryAgain = true;

		// until the user enters a string that starts with an 'n', 'N', 'y', or
		// 'Y'
		do
		{
			// gets a lower case version of the user input and stores it in
			// input
			input = UI.getInput("Try again? (Y/N)").toLowerCase();
		}
		while (!input.startsWith("n") && !input.startsWith("y"));

		// if the user's input started with an 'n' or 'N'
		if (input.startsWith("n"))
		{
			// the user does not wish to try again
			tryAgain = false;
		}

		// otherwise the user wants to try again and tryAgain remains true

		return tryAgain;
	}

	/**
	 * Asks a question and returns true if user answers yes.
	 * 
	 * @param question
	 *            a String that holds a question to ask the user. Should end
	 *            with '?'
	 * @return true if the user answers yes.
	 */
	public static boolean yesCheck(String question)
	{
		// input will hold the user input
		String input = "";

		// tryAgain hold a boolean value to show if the user wants to try again
		boolean yes = true;

		// until the user enters a string that starts with an 'n', 'N', 'y', or
		// 'Y'
		do
		{
			// gets a lower case version of the user input and stores it in
			// input
			input = UI.getInput(question + " (Y/N): ").toLowerCase();
		}
		while (!input.startsWith("n") && !input.startsWith("y"));

		// if the user's input started with an 'n' or 'N'
		if (input.startsWith("n"))
		{
			// the user does not wish to try again
			yes = false;
		}

		// otherwise the user wants to try again and tryAgain remains true

		return yes;
	}

	/**
	 * a <code>Theater</code> object
	 */
	private Theater theater = Theater.instance();

	/**
	 * a <code>boolean</code> that is true if a data command was used while the
	 * application has been running this session.
	 */
	private boolean dataCommandWasUsed = false;

	/**
	 * Constructs a <code>UI</code> used when creating a subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type <code>UI</code>
	 */
	protected UI() throws Exception
	{
		if (getClass().getName().equals("UI"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>UI</code> used to create the singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private UI(int i)
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException
	{
		scanner.close();
	}

	/**
	 * Checks if there has been any data commands used this session.
	 * 
	 * @return true if a data command was used, else false.
	 * @see userInterface.UI#hasUsedDataCommand()
	 */
	public boolean dataCommandWasUsed()
	{
		return hasUsedDataCommand();
	}

	/**
	 * Gets the command list.
	 * 
	 * @return a <code>LinkedList</code> that holds all the commands
	 */
	public LinkedList<Command<UI>> getCommandList()
	{
		return commandList;
	}

	/**
	 * Gets the theater.
	 * 
	 * @return the <code>Theater</code> instance
	 */
	public Theater getTheater()
	{
		return theater;
	}

	/**
	 * Checks if there has been any data commands used this session.
	 * 
	 * @return true if a data command was used, else false.
	 * @see userInterface.UI#dataCommandWasUsed
	 */
	public boolean hasUsedDataCommand()
	{
		return dataCommandWasUsed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see singleton.Singleton#readResolve()
	 */
	@Override
	public UI readResolve()
	{
		return instance();
	}

	/**
	 * Sets the {@link userInterface.UI#dataCommandWasUsed} to true.
	 */
	private void setDataCommandUsed()
	{
		instance().dataCommandWasUsed = true;
	}

	public StandardFormat standardFormat = new StandardFormat();
}
