package storage;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class FileIO implements Closeable
{
	private ObjectInputStream objectInputStream = null;

	private ObjectOutputStream objectOutputStream = null;

	protected FileIO() throws Exception
	{
		throw new Exception();
	}

	private FileIO(ObjectInputStream objectInputStream)
	{
		this.objectInputStream = objectInputStream;
	}

	private FileIO(ObjectOutputStream objectOutputStream)
	{
		this.objectOutputStream = objectOutputStream;
	}

	public static FileIO startRead(String filename)
			throws FileNotFoundException, IOException
	{
		FileInputStream tmpFileInputStream = new FileInputStream(filename);
		ObjectInputStream tmpObjectInputStream = new ObjectInputStream(
				tmpFileInputStream);
		return new FileIO(tmpObjectInputStream);
	}
	
	public boolean hasMoreToRead() throws IOException
	{
		return objectInputStream.available() > 0 ;
	}

	public Object read() throws IOException, ClassNotFoundException
	{
		return objectInputStream.readObject();
	}
	
	public static FileIO startWrite(String filename)
			throws FileNotFoundException, IOException
	{
		FileOutputStream tmpFileOutputStream = new FileOutputStream(filename);
		ObjectOutputStream tmpObjectOutputStream = new ObjectOutputStream(
				tmpFileOutputStream);
		return new FileIO(tmpObjectOutputStream);
	}

	public void write(Object object) throws IOException
	{
		objectOutputStream.writeObject(object);
	}

	@Override
	public void close() throws IOException
	{
		if (objectInputStream != null)
		{
			objectInputStream.close();
		}
		if (objectOutputStream != null)
		{
			objectOutputStream.close();
		}

	}
}
