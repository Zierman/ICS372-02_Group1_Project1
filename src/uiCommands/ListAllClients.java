package uiCommands;

import client.Client;
import client.ClientList;
import theater.Theater;
import userInterface.UI;

//TODO document all of this
/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class ListAllClients implements Command<UI>
{
	private static ListAllClients singleton;
	private final String LABEL = "Show a list of all clients.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	protected ListAllClients() throws Exception
	{
		if (getClass().getName().equals("ListAllClients"))
		{
			throw new Exception();
		}
	}

	private ListAllClients(int i)
	{
	}

	public static ListAllClients instance()
	{
		if (singleton == null)
		{
			singleton = new ListAllClients(1);
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

	@Override
	public void call(UI ui)
	{
		boolean done = false;
		while (!done)
		{
			try
			{
				Theater theater = ui.getTheater();
				ClientList clientList = theater.getClientList();
				String output = "";
				for (Client client : clientList)
				{
					output += "id: " + client.getID() + ",\n" + "name: "
							+ client.getName() + ",\n" + "balance due: "
							+ client.getBalanceDue() + ",\n" + "address: "
							+ client.getAddress() + ",\n" + "phone number: "
							+ client.getPhoneNumber() + "\n" + "\n";
				}
				UI.println(output);
				done = true;
			}
			catch (Exception e)
			{

				// show error message
				UI.outputError(e, "Unable to list all clients");

				// ask if user wants to continue and end if the user answers no
				done = !UI.tryAgainCheck();
			}
		}

	}

}