package customer;

import java.io.IOException;
import java.io.Serializable;

import keyToken.KeyToken;
import storage.Savable;


public class Customer implements Savable, Serializable
{
	private static Long lastID = Long.MIN_VALUE;
	
	/**
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public class Name
	{
		private String name;

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}
		
		@Override
		public String toString()
		{
			return getName();
		}
	}
	
	/**
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public class ID extends KeyToken<Customer, Long>
	{

		@Override
		protected Long getNextKey()
		{
			return Customer.lastID + 1;
		}

		@Override
		protected Long getLastKey()
		{
			// TODO Auto-generated method stub
			return Customer.lastID;
		}

		@Override
		protected void setLastKey(Long key)
		{
			Customer.lastID = key;
			
		}
	}
	
	@Override
	public void save() throws IOException
	{
		// TODO Auto-generated method stub

	}
	
	// TODO Finish

}
