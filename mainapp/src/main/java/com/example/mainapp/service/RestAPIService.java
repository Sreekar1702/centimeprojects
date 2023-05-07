package com.example.mainapp.service;

import java.net.URI;
import java.util.Objects;

import com.example.mainapp.exception.CustomExceptionHandler;
import com.example.mainapp.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.mainapp.constants.StringConstants;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Service
@Log4j2
public class RestAPIService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EurekaClient eurekaClient;
	private URI getURL(String appName, String path) {
		try {
			InstanceInfo appServer = eurekaClient.getApplication(appName).getInstances().get(0);
			String hostName = appServer.getHostName();
			int port = appServer.getPort();
			URI url = URI.create(StringConstants.HTTP + hostName + StringConstants.COLON + port + path);
			return url;
		} catch (Exception e) {
			log.error("Exception while fetching url", e);
			throw new CustomExceptionHandler(StringConstants.URL_FETCH_ERROR,HttpStatus.NOT_FOUND.toString());
		}
	}

	public String postConcatUser(User user) {
		try {
			URI url = getURL(StringConstants.CONCAT_APP, StringConstants.CONCAT_USER);
			log.info("url for {} is {}",StringConstants.CONCAT_APP,url);
			ResponseEntity<String> response = restTemplate.postForEntity(url, user,String.class);
			return validateResponse(response,StringConstants.CONCAT_APP);
		} catch(Exception e) {
			log.error("Exception while sending request to {}",StringConstants.CONCAT_APP, e);
			throw new CustomExceptionHandler(String.format("ERROR in process of %s",StringConstants.CONCAT_APP),HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
	}

	public String getHello() {
		ResponseEntity<String> response = null;
		try {
			URI url = getURL(StringConstants.HELLO_APP, StringConstants.HELLO);
			log.info("url for {} is {}",StringConstants.HELLO_APP,url);
			response = restTemplate.getForEntity(url, String.class);
			return validateResponse(response,StringConstants.HELLO_APP);
		} catch (Exception e) {
			log.error("Exception while sending request to {}",StringConstants.HELLO_APP, e);
			throw new CustomExceptionHandler(String.format("ERROR in process of %s",StringConstants.HELLO_APP),HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
	}

	private String validateResponse(ResponseEntity<String> response, String service) {
		log.info("response is" ,response.getStatusCode().value());
		if(response.getStatusCode().value() ==200 && Objects.nonNull(response)) {
			return returnResponse(Objects.nonNull(response), response.getBody().toString());
		} else {
			throw new CustomExceptionHandler(String.format("ERROR in process of %s",service), response.getStatusCode().toString());
		}
	}

	private static String returnResponse(boolean responseStatus, String response) {
		if (!responseStatus) {
			throw new CustomExceptionHandler(StringConstants.NO_RESPONSE_RECEIVED, HttpStatus.INTERNAL_SERVER_ERROR.toString());
		} else {
			return response;
		}
	}

	private static HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}
