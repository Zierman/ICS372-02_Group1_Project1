package ticket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import customer.Customer;
import play.Play;
import storage.FileIO;

public class RagularTicket extends Ticket
{

	public RagularTicket(Date dateOfShow, Play play, Customer owner)
	{
		super(dateOfShow, play, owner);
		
	}

	//TODO delete all bellow
	
	public static void println(LinkedList<RagularTicket> rL)
	{
		for(RagularTicket r: rL)
		{
			System.out.println(r.getKey() +", "+ r.getLast());
		}
		System.out.println("---------------");
	}
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		FileIO f;
		LinkedList<RagularTicket> rL = new LinkedList<RagularTicket>();
		
		
		
		
		println(rL);
		rL.add(new RagularTicket(null, null, null));
		println(rL);
		rL.add(new RagularTicket(null, null, null));
		println(rL);
		rL.add(new RagularTicket(null, null, null));
		println(rL);
		rL.remove();
		println(rL);
		RagularTicket r2 = new RagularTicket(null, null, null); // 4 out of list
		println(rL);
		rL.add(new RagularTicket(null, null, null));
		println(rL);
		rL.add(rL.remove(0));
		println(rL);
		
		f = FileIO.startWrite("T.dat");
		f.write(rL.size());
		for(RagularTicket r: rL)
		{
			f.write(r);
		}
		f.close();
		
		rL.clear();
		
		f = FileIO.startRead("T.dat");
		int size = (int) f.read();
		for(int i = 0; i < size; i++)
		{
			rL.add((RagularTicket)f.read());
		}
		f.close();
		println(rL);
		
	}

}
