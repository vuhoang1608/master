package jc.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.omg.SendingContext.RunTime;

public class CarWashes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<City> cities;
	public List<Account> accounts;

	public CarWashes() throws FileNotFoundException, IOException {
		try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(new File("Cities.ser")))) {
			cities = (List<City>) (objIn.readObject());
			accounts = (List<Account>) (objIn.readObject());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try (ObjectOutputStream objIn = new ObjectOutputStream(new FileOutputStream(new File("Cities.ser")))) {
					objIn.writeObject(cities);
					objIn.writeObject(accounts);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}));
	}

}
