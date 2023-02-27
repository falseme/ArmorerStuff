package com.falseme.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;

import com.falseme.main.Main;

public class Config implements Serializable {
	private static final long serialVersionUID = 1l;

	public static Config userConfig;

	private String skinpath;
	private static String mainpath;

	public static void init() {

		userConfig = new Config();

		// Load file
		try {

			ObjectInputStream input = new ObjectInputStream(new FileInputStream(mainpath));
			userConfig = (Config) input.readObject();
			input.close();

		} catch (Exception e) {
			System.out.println("No se ha podido leer archivo de configuracion del usuario");
//			userConfig.serialize(); // no hace falta actualmente
		}

	}

	private Config() {

		defaultValues();

	}

	private void defaultValues() {

		try {
			File thisJarFile = new File(
					Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			skinpath = thisJarFile.getParent();
			mainpath = skinpath + File.separator + "config.dat";
		} catch (URISyntaxException e) {
			e.printStackTrace();
			skinpath = null;
		}

	}

	public static void serialize() {

		try {

			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(mainpath));
			output.writeObject(userConfig);
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getSkinPath() {
		return userConfig.skinpath;
	}

	public static void setSkinPath(String skinPath) {
		userConfig.skinpath = skinPath;
	}

}
