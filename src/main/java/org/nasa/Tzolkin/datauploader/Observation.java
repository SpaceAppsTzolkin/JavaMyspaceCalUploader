package org.nasa.Tzolkin.datauploader;

import java.util.Date;

/**
 * 
 * @author jono
 * 
 */
public class Observation
{
	private static final String COMMA = ",";

	private long satId;

	private String observationId;

	private String target;

	private double lowBand;

	private double highBand;

	private String ra;

	private String dec;

	private int revolution;

	private String startTime;

	private String endTime;

	private boolean isWaveLengthGeneric;

	public long getSatId()
	{
		return satId;
	}

	public void setSatId(long satId)
	{
		this.satId = satId;
	}

	public String getObservationId()
	{
		return observationId;
	}

	public void setObservationId(String observationId)
	{
		this.observationId = observationId;
	}

	public String getTarget()
	{
		return target;
	}

	public void setTarget(String target)
	{
		this.target = target;
	}

	public double getLowBand()
	{
		return lowBand;
	}

	public void setLowBand(double lowBand)
	{
		this.lowBand = lowBand;
	}

	public double getHighBand()
	{
		return highBand;
	}

	public void setHighBand(double highBand)
	{
		this.highBand = highBand;
	}

	public int getRevolution()
	{
		return revolution;
	}

	public void setRevolution(int revolution)
	{
		this.revolution = revolution;
	}



	public String getRa()
	{
		return ra;
	}

	public void setRa(String ra)
	{
		this.ra = ra;
	}

	public String getDec()
	{
		return dec;
	}

	public void setDec(String dec)
	{
		this.dec = dec;
	}

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public boolean isWaveLengthGeneric()
	{
		return isWaveLengthGeneric;
	}

	public void setWaveLengthGeneric(boolean isWaveLengthGeneric)
	{
		this.isWaveLengthGeneric = isWaveLengthGeneric;
	}
	
	public void setWaveLengthGeneric(String isWaveLengthGeneric)
	{
		this.isWaveLengthGeneric = Boolean.parseBoolean(isWaveLengthGeneric);
	}

	public Object toCSVString()
	{
		return satId + COMMA + target + COMMA + ra + COMMA + dec + COMMA
				+ revolution + COMMA + startTime + COMMA + endTime + COMMA
				+ highBand + COMMA + lowBand + COMMA
				+ Boolean.toString(isWaveLengthGeneric);
	}

}
