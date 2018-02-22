/**
 * 
 */
package theater;

import java.io.IOException;
import java.util.List;

import address.Address;
import client.Client;
import client.ClientList;
import customer.CustomerList;
import client.Client.Name;
import phoneNumber.PhoneNumber;
import play.PlayList;
import singleton.Singleton;
import storage.FileIO;
import storage.Loadable;
import storage.Savable;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Theater implements Singleton<Theater>, Loadable, Savable
{
	private static Theater singleton;
	protected static final String FILENAME = "theater.dat";
	private String name;
	private Integer seatingCapacity;
	
	private ClientList clientList = ClientList.instance();
	private PlayList playList = PlayList.instance();
	private CustomerList customerList = CustomerList.instance();

	public PlayList getPlayList()
	{
		return playList;
	}

	public CustomerList getCustomerList()
	{
		return customerList;
	}

	public Integer getSeatingCapacity()
	{
		return seatingCapacity;
	}

	public void setSeatingCapacity(Integer seatingCapacity)
	{
		this.seatingCapacity = seatingCapacity;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	protected Theater() throws Exception
	{
		if (getClass().getName().equals("Theater"))
		{
			throw new Exception();
		}
	}

	private Theater(int i)
	{
	}

	public static Theater instance()
	{
		if (singleton == null)
		{
			singleton = new Theater(1);
		}
		return singleton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see singleton.Singleton#readResolve()
	 */
	@Override
	public Theater readResolve()
	{
		return instance();
	}

	@Override
	public void load() throws ClassNotFoundException, IOException
	{
		instance().setName((String) FileIO.read(FILENAME));
		instance().setSeatingCapacity((Integer) FileIO.read(FILENAME));
		// TODO finish this
	}

	@Override
	public void save() throws IOException
	{
		FileIO.write(name, FILENAME);
		FileIO.write(seatingCapacity, FILENAME);
		// TODO finish this
	}

	public ClientList getClientList()
	{
		return clientList;
	}
	
	public <Type, ListType extends List<Type>> boolean  add(Type object, ListType list)
	{
		return list.add(object);
	}
}
