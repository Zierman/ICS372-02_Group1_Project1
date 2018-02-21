package storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class FileIO
{
	public static Object read(String filename)
			throws IOException, ClassNotFoundException
	{
		Object returnObject;
		FileInputStream fileInputStream = new FileInputStream(filename);
		ObjectInputStream objectInputStream = new ObjectInputStream(
				fileInputStream);
		returnObject = objectInputStream.readObject();
		objectInputStream.close();
		return returnObject;
	}

	public static void write(Object object, String filename) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream("save");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				fileOutputStream);
		objectOutputStream.writeObject(object);
		objectOutputStream.close();
	}
}
