package org.nasa.Tzolkin.datauploader;

import java.io.IOException;
import java.util.Calendar;

import org.apache.http.HttpException;

public class App
{

	/**
	 * @param args
	 * @throws HttpException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, HttpException
	{
		Observation obs = new Observation();
		obs.setSatId(1);
		obs.setRa(4.0);
		obs.setDec(4.0);
		obs.setRevolution(100);
		obs.setStartTime(Calendar.getInstance().getTime());
		obs.setEndTime(Calendar.getInstance().getTime());
		obs.setHighBand(100.0);
		obs.setLowBand(1.0);
		
		DataUploader up = new DataUploader(1);
		up.submitFinding(obs);
	}

}
