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
import uiCommands.Command;
import uiCommands.ExitApplication;
import uiCommands.RemoveClient;

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
			commandList.add(ExitApplication.instance());
			commandList.add(AddClient.instance());
			commandList.add(RemoveClient.instance());
			// TODO make the rest of this work

			// commandList.add(ListAllClients.instance());
			// commandList.add(AddCustomer.instance());
			// commandList.add(RemoveCustomer.instance());
			// commandList.add(AddCreditCard.instance());
			// commandList.add(RemoveCreditCard.instance());
			// commandList.add(ListAllCustomers.instance());
			// commandList.add(AddPlay.instance());
			// commandList.add(ListAllPlays.instance());
			// commandList.add(StoreData.instance());
			// commandList.add(RetrieveData.instance());
			// commandList.add(Help.instance());
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
		catch (IOException e)
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
