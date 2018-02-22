/**
 * 
 */
package userInterface;

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
public class UI implements Singleton
{

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
			
			//TODO make the rest of this work
			
//			commandList.add(ListAllClients.instance());
//			commandList.add(AddCustomer.instance());
//			commandList.add(RemoveCustomer.instance());
//			commandList.add(AddCreditCard.instance());
//			commandList.add(RemoveCreditCard.instance());
//			commandList.add(ListAllCustomers.instance());
//			commandList.add(AddPlay.instance());
//			commandList.add(ListAllPlays.instance());
//			commandList.add(StoreData.instance());
//			commandList.add(RetrieveData.instance());
//			commandList.add(Help.instance());
		}
		return singleton;
	}

	@Override
	public Object readResolve()
	{
		return instance();
	}

	public static String getInput(String userPrompt)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print(userPrompt);
		String input = scanner.nextLine();
		scanner.close();
		return input;

	}

	public static void outputError(Exception e, String string)
	{
		System.out.println(string);
	}

	public static void outputSuccessMessage(String string)
	{
		System.out.println(string);
		
	}
	
	public static void main(String[] args)
	{
		UI ui = UI.instance();
		Command lastCommand = null;
		
		while(lastCommand == null || lastCommand.isTerminationCommand())
		{
			lastCommand = commandList.get(Integer.parseInt(getInput("Enter command number: ")));
			lastCommand.call(ui);
		}
	}

	public Theater getTheater()
	{
		return theater;
	}


	// TODO finish

}
