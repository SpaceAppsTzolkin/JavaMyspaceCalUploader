package org.nasa.Tzolkin.datauploader;

import java.io.IOException;
import java.net.Socket;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;

public class DataUploader
{

	private long satId;

	public DataUploader(long satId)
	{
		this.satId = satId;
	}

	public DataUploader(String satId) throws NumberFormatException
	{
		this.satId = Long.parseLong(satId);
	}

	public long submitFinding(Observation obs) throws IOException,
			HttpException
	{
		HttpParams params = new SyncBasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "UTF-8");
		HttpProtocolParams.setUserAgent(params, "Test/1.1");
		HttpProtocolParams.setUseExpectContinue(params, true);

		HttpProcessor httpproc = new ImmutableHttpProcessor(
				new HttpRequestInterceptor[]
				{
						// Required protocol interceptors
						new RequestContent(), new RequestTargetHost(),
						// Recommended protocol interceptors
						new RequestConnControl(), new RequestUserAgent(),
						new RequestExpectContinue() });

		HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

		HttpContext context = new BasicHttpContext(null);

		// TODO FIX FOR FINAL PROJECT
		HttpHost host = new HttpHost("localhost", 9000);

		DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
		ConnectionReuseStrategy connStrategy = new DefaultConnectionReuseStrategy();

		context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
		context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, host);

		try
		{

			HttpEntity[] requestBodies =
			{ new StringEntity(getCSV(obs), "UTF-8") };

			for (int i = 0; i < requestBodies.length; i++)
			{
				if (!conn.isOpen())
				{
					Socket socket = new Socket(host.getHostName(),
							host.getPort());
					conn.bind(socket, params);
				}
				BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest(
						"POST", "/myspaceapps/satellite/submit?satellite="
								+ this.satId);
				request.setEntity(requestBodies[i]);

				request.setParams(params);
				httpexecutor.preProcess(request, httpproc, context);
				HttpResponse response = httpexecutor.execute(request, conn,
						context);
				response.setParams(params);
				httpexecutor.postProcess(response, httpproc, context);

				//System.out.println("<< Response: " + response.getStatusLine());
				//System.out.println(EntityUtils.toString(response.getEntity()));
				//System.out.println("==============");
				conn.close();
			}
		} finally
		{
			conn.close();
		}

		return -1;
	}

	private String getCSV(Observation obs)
	{
		StringBuffer buf = new StringBuffer("satId,target,ra,dec,revolution,startdate,enddate,lowband,highband,wavelengthgenerated\n");
		buf.append(obs.toCSVString()).append("\n");		
		return buf.toString();
	}

}
