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

	private double ra;

	private double dec;

	private int revolution;

	private Date startTime;

	private Date endTime;

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

	public double getRa()
	{
		return ra;
	}

	public void setRa(double ra)
	{
		this.ra = ra;
	}

	public double getDec()
	{
		return dec;
	}

	public void setDec(double dec)
	{
		this.dec = dec;
	}

	public int getRevolution()
	{
		return revolution;
	}

	public void setRevolution(int revolution)
	{
		this.revolution = revolution;
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public Object toCSVString()
	{
		return satId + COMMA + target + COMMA + ra + COMMA + dec + COMMA
				+ revolution + COMMA + startTime + COMMA + endTime + COMMA
				+ highBand + COMMA + lowBand + COMMA
				+ Boolean.toString(isWaveLengthGeneric);
	}

}
