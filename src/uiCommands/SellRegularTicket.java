package uiCommands;

import theater.Theater;
import userInterface.UI;

public class SellRegularTicket implements Command<UI>
{
		private static SellRegularTicket singleton;
		/**
		 * Gets or creates an instance of the singleton
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
		 * Constructs a <code>SellRegularTicket</code> object used when creating a subtype singleton
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
		 * Constructs the <code>SellRegularTicket</code> object used to create the singleton.
		 * 
		 * @param i
		 *            an integer with no significance other than giving it a
		 *            different signature than the protected constructor.
		 */
		private SellRegularTicket(int i)
		{
		}

		/* (non-Javadoc)
		 * @see uiCommands.Command#call(java.lang.Object)
		 */
		@Override
		public void call(UI ui)
		{
			Theater theater = ui.getTheater();
			
			// ask for qty tickets to sell
			UI.getIntFromInput("Enter the number of tickets to sell");
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
