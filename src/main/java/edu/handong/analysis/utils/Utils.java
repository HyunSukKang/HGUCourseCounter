package edu.handong.analysis.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Utils {

	public static ArrayList<String> getLines(String file, boolean removeHeader) {
		ArrayList<String> lines = new ArrayList<String>();
		ObjectInputStream inputStream = new ObjectInputStream();
		
		String line = inputStream.readLine();
		
		
		return lines;
	}

	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		// TODO Auto-generated method stub
		
	}

}
