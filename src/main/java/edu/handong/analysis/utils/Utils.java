package edu.handong.analysis.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {

	public static ArrayList<String> getLines(String file, boolean removeHeader) {
		ArrayList<String> lines = new ArrayList<String>();
		String row;
		FileReader csvR = null;
		BufferedReader csvReader = null;
		int count = 0;
		
		try {
			csvR = new FileReader(file);
			csvReader = new BufferedReader(csvR);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			while((row = csvReader.readLine()) != null) {
				if(removeHeader) {
					if(count == 0) {}
					else {
						lines.add(row);
					}
				}
				else {
					lines.add(row);
				}
				
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return lines;
	}

	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter(targetFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String s : lines) {
			try {
				csvWriter.write(s);
				csvWriter.append("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			csvWriter.flush();
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
