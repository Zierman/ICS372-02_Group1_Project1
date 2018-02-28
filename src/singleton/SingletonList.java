package singleton;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public abstract class SingletonList<TYPE> extends Singleton implements List<TYPE>
{

	private LinkedList<Type> list;
	public LinkedList<?> getList()
	{
		return list;
	}

	public void setList(LinkedList<?> list)
	{
		SingletonList.list = list;
	}

	protected SingletonList() throws Exception
	{
		super();
	}

	@Override
	public boolean add(TYPE arg0)
	{
		// TODO Auto-generated method stub
		return list.add(arg0);
	}

	@Override
	public void add(int arg0, TYPE arg1)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addAll(Collection<? extends TYPE> arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends TYPE> arg1)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TYPE get(int arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<TYPE> iterator()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<TYPE> listIterator()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<TYPE> listIterator(int arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TYPE remove(int arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TYPE set(int arg0, TYPE arg1)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TYPE> subList(int arg0, int arg1)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
