package uiCommands;

import client.Client;
import client.ClientList;
import theater.Theater;
import userInterface.UI;

/**
 * The command to list alll clients
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class ListAllClients implements Command<UI>
{
	private static ListAllClients singleton;
	/**
	 * Gets or creates an instance of the singleton
	 * 
	 * @return an instance of the singleton
	 */
	public static ListAllClients instance()
	{
		if (singleton == null)
		{
			singleton = new ListAllClients(1);
		}
		return singleton;
	}
	private final String LABEL = "Show a list of all clients.";
	private final boolean IS_DATA_COMMAND = true;

	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>ListAllClients</code> object used when creating a
	 * subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected ListAllClients() throws Exception
	{
		if (getClass().getName().equals("ListAllClients"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>ListAllClients</code> object used to create the
	 * singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private ListAllClients(int i)
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
		boolean done = false;
		while (!done)
		{
			try
			{
				boolean found = false;
				Theater theater = ui.getTheater();
				ClientList clientList = theater.getClientList();
				String output = "";
				for (Client client : clientList)
				{
					found = true;
					output += "id: " + client.getID() + ",\n" + "name: "
							+ client.getName() + ",\n" + "balance due: "
							+ client.getBalanceDue() + ",\n" + "address: "
							+ client.getAddress() + ",\n" + "phone number: "
							+ client.getPhoneNumber() + "\n" + "\n";
				}

				if (!found)
				{
					UI.println("No clients found.");
				}
				else
				{
					UI.println(output);
				}
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