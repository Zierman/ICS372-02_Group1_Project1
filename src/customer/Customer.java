package customer;

import java.io.IOException;
import java.io.Serializable;

import keyToken.KeyToken;
import storage.Savable;


public class Customer implements Savable, Serializable
{
	
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
	public class ID extends KeyToken<Customer>
	{
		// Nothing should need to be added because it extends KeyToken<Customer>
	}
	
	@Override
	public void save() throws IOException
	{
		// TODO Auto-generated method stub

	}
	
	// TODO Finish

}
