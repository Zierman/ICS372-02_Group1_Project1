package uiCommands;

import client.Client;
import theater.Theater;
import userInterface.UI;

public class PayClient implements Command<UI>
{
		private static PayClient singleton;
		/**
		 * Gets or creates an instance of the singleton
		 * @return an instance of the singleton
		 */
		public static PayClient instance()
		{
			if (singleton == null)
			{
				singleton = new PayClient(1);
			}
			return singleton;
		}
		private final String LABEL = "Pay Client.";
		
		private final boolean IS_DATA_COMMAND = true;

		private final boolean IS_TERMINATION_COMMAND = false;

		/**
		 * 
		 * Constructs a <code>SellRegularTicket</code> object used when creating a subtype singleton
		 * 
		 * @throws Exception
		 *             if used to try to create a base type
		 */
		protected PayClient() throws Exception
		{
			if (getClass().getName().equals("SellRegularTicket"))
			{
				throw new Exception();
			}
		}
		
		/**
		 * Constructs the <code>SellRegularTicket</code> object used to create the singleton.
		 * 
		 * @param i
		 *            an integer with no significance other than giving it a
		 *            different signature than the protected constructor.
		 */
		private PayClient(int i)
		{
		}

		/* (non-Javadoc)
		 * @see uiCommands.Command#call(java.lang.Object)
		 */
		@Override
		public void call(UI ui)
		{
			Theater theater = ui.getTheater();
			// TODO finish this
			
			// use UI.getClientFromInputID() to ask for a client id (returns a client object reference)
			
			// use UI.getDollarFromInput("Enter amount to pay client") to ask for a dollar amount (returns a dollar object)
						
			// use theater.pay(client, dollars) to pay the client. will throw OverpayingClientException if trying to pay too much
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

		/* (non-Javadoc)
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
