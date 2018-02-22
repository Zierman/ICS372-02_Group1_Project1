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

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class UI implements Singleton<UI>, Closeable
{

	private static Scanner scanner = new Scanner(System.in);
	private static UI singleton;
	private Theater theater = Theater.instance();
	private static LinkedList<Command<UI>> commandList = new LinkedList<Command<UI>>();

	protected UI() throws Exception
	{
		if (getClass().getName().equals("UI"))
		{
			throw new Exception();
		}
	}

	private UI(int i)
	{
	}

	public static UI instance()
	{
		if (singleton == null)
		{
			singleton = new UI(1);
			commandList.add(ExitApplication.instance()); 	// command 1
			commandList.add(AddClient.instance()); 			// command 2
			commandList.add(RemoveClient.instance()); 		// command 3
			commandList.add(ListAllClients.instance()); 	// command 4
			commandList.add(AddCustomer.instance()); 		// command 5
			commandList.add(RemoveCustomer.instance()); 	// command 6
			commandList.add(AddCreditCard.instance()); 		// command 7
			commandList.add(RemoveCreditCard.instance()); 	// command 8
			commandList.add(ListAllCustomers.instance()); 	// command 9
			commandList.add(AddPlay.instance()); 			// command 10
			commandList.add(ListAllPlays.instance()); 		// command 11
			commandList.add(StoreData.instance()); 			// command 12
			commandList.add(RetrieveData.instance()); 		// command 13
			commandList.add(Help.instance()); 				// command 14
			
			
		}
		return singleton;
	}

	@Override
	public UI readResolve()
	{
		return instance();
	}

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

	public static void outputError(Exception e, String string)
	{
		System.err.println(string);
	}

	public static void outputSuccessMessage(String string)
	{
		System.out.println(string);

	}

	public static void main(String[] args)
	{
		UI ui = UI.instance();
		Command<UI> lastCommand = null;
		String input = "";

		// until a terminating command is issued, keep getting commands
		while (lastCommand == null || !lastCommand.isTerminationCommand())
		{
			try
			{
				input = getInput("Enter command number: ");
				int commandNumber = Integer.parseInt(input) - 1;
				lastCommand = commandList.get(commandNumber);
				lastCommand.call(ui);
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

	public Theater getTheater()
	{
		return theater;
	}

	@Override
	public void close() throws IOException
	{
		scanner.close();
	}

	// TODO finish

}
