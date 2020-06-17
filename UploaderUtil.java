import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class UploaderUtil {

private static final String POST_URL = "https://api.clevertap.com/1/upload";
	
	public static void main(String[] args)throws IOException {

		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(POST_URL);
		httpPost.addHeader("X-CleverTap-Account-Id", "W67-774-7Z5Z");
		httpPost.addHeader("X-CleverTap-Passcode", "IMQ-BMA-IHKL");
		httpPost.addHeader("Content-Type", "application/json; charset=utf-8");

		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

		System.out.println("POST Response Status:: "
				+ httpResponse.getStatusLine().getStatusCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				httpResponse.getEntity().getContent()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();

		// print result
		System.out.println(response.toString());
		httpClient.close();

	}

}
