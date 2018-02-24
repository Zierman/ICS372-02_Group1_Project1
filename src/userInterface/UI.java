/**
 * 
 */
package userInterface;

import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import singleton.Singleton;
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
import uiCommands.RemoveClient;
import uiCommands.RemoveCreditCard;
import uiCommands.RemoveCustomer;
import uiCommands.RetrieveData;
import uiCommands.StoreData;

//TODO document all of this
/**
 * A user interface that is responsible for handling interactions between the
 * user and the data.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class UI implements Singleton<UI>, Closeable
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
	 * a <code>Theater</code> object
	 */
	private Theater theater = Theater.instance();

	/**
	 * a <code>boolean</code> that is true if a data command was used while the
	 * application has been running this session.
	 */
	private boolean dataCommandWasUsed = false;

	/**
	 * a <code>LinkedList</code> of all available commands
	 */
	private static LinkedList<Command<UI>> commandList = new LinkedList<Command<UI>>();

	/**
	 * a <code>Command</code> that holds an instance of the help command.
	 */
	private static Command<UI> helpCommand = Help.instance();

	/**
	 * a <code>final boolean</code> that is true if debug mode is on. If debug
	 * mode is on, extra information will be displayed when error output is
	 * shown during a {@link userInterface.UI#outputError(Exception, String)}
	 * call.
	 */
	private static final boolean DEBUG_MODE = true; // TODO turn DEBUG_MODE off

	/**
	 * Constructs a UI used when creating a subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type UI
	 */
	protected UI() throws Exception
	{
		if (getClass().getName().equals("UI"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the UI used to create the singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private UI(int i)
	{
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
			commandList.add(ExitApplication.instance()); // command 1
			commandList.add(AddClient.instance()); // command 2
			commandList.add(RemoveClient.instance()); // command 3
			commandList.add(ListAllClients.instance()); // command 4
			commandList.add(AddCustomer.instance()); // command 5
			commandList.add(RemoveCustomer.instance()); // command 6
			commandList.add(AddCreditCard.instance()); // command 7
			commandList.add(RemoveCreditCard.instance()); // command 8
			commandList.add(ListAllCustomers.instance()); // command 9
			commandList.add(AddPlay.instance()); // command 10
			commandList.add(ListAllPlays.instance()); // command 11
			commandList.add(StoreData.instance()); // command 12
			commandList.add(RetrieveData.instance()); // command 13
			commandList.add(Help.instance()); // command 14

		}
		return singleton;
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

	/** Prints a string to the system's standard output with a line break at the end.
	 * @param string the <code>String</code> to be printed.
	 */
	public static void println(String string)
	{
		System.out.println(string);
	}

	/**
	 * Prints a string to the system's standard output.
	 * @param string the <code>String</code> to be printed.
	 */
	public static void print(String string)
	{
		System.out.print(string);
	}

	public static void outputSuccessMessage(String string)
	{
		System.out.println(string);

	}

	private void setDataCommandUsed()
	{
		instance().dataCommandWasUsed = true;
	}

	public boolean dataCommandWasUsed()
	{
		return hasUsedDataCommand();
	}

	public boolean hasUsedDataCommand()
	{
		return dataCommandWasUsed;
	}

	public Theater getTheater()
	{
		return theater;
	}

	/**
	 * Asks user if they wish to try again and returns true if they do
	 * 
	 * @return true if user wants to try again, else false
	 */
	public static boolean tryAgainCheck()
	{
		String input = "";
		boolean bool = true;
		do
		{
			input = UI.getInput("Try again? (Y/N)").toLowerCase();
		}
		while (!input.startsWith("n") && !input.startsWith("y"));
		if (input.startsWith("n"))
		{
			bool = false;
		}

		return bool;
	}

	/* (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException
	{
		scanner.close();
	}

	/**
	 * Gets the command list.
	 * @return a <code>LinkedList</code> that holds all the commands
	 */
	public LinkedList<Command<UI>> getCommandList()
	{
		return commandList;
	}

	/**
	 * Drives the UI.
	 * @param args a array of string arguments that are not used.
	 */
	public static void main(String[] args)
	{
		UI ui = UI.instance();
		Command<UI> lastCommand = null;
		String input = "";
		helpCommand.call(ui);

		// until a terminating command is issued, keep getting commands
		while (lastCommand == null || !lastCommand.isTerminationCommand())
		{
			try
			{
				input = getInput("Enter command number: ");
				int commandNumber = Integer.parseInt(input) - 1;
				lastCommand = commandList.get(commandNumber);
				lastCommand.call(ui);
				if (lastCommand.isDataCommand())
				{
					ui.setDataCommandUsed();
				}
			}
			catch (Exception e)
			{
				outputError(e, "command \"" + input + "\" failed.");
				lastCommand = null;
			}
		}

		// after the program completes, close the UI.
		try
		{
			ui.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
