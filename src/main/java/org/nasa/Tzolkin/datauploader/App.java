package org.nasa.Tzolkin.datauploader;

import java.io.IOException;

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
		if (args.length == 9)
		{
			Observation obs = new Observation();
			obs.setSatId(Long.parseLong(args[0]));
			obs.setTarget(args[1]);
			obs.setRa(args[2]);
			obs.setDec(args[3]);
			obs.setRevolution(Integer.parseInt(args[4]));
			obs.setStartTime(args[5]);
			obs.setEndTime(args[6]);
			obs.setHighBand(Double.parseDouble(args[7]));
			obs.setLowBand(Double.parseDouble(args[8]));
			DataUploader up = new DataUploader(1);
			try
			{
				up.submitFinding(obs);
			} catch (Exception e)
			{
				System.exit(-1);
			}
			System.exit(0);
		}
		DataUploader du = new DataUploader(1);
		du.confirmFinding(1);
		du.deleteFinding(1);
		System.exit(-2);

	}

}
