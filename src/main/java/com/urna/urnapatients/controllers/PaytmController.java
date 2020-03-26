package com.urna.urnapatients.controllers;

import java.util.Optional;
import java.util.TreeMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.urna.urnapatients.models.Patient;

@CrossOrigin
@RestController
@RequestMapping("/rest/urna/paytm")
public class PaytmController {
	
	public static void setInitialParameters() {/*

		*//** Set Logger Level for Paytm logs *//*
		LibraryConstants.LOGGER.setLevel(Level.ALL);
		*//** setting log file path "/paytm/MyLogFile.log" *//*
		try {
			FileHandler fh = null;
			fh = new FileHandler("/paytm/MyLogFile.log");
			fh.setFormatter(new SimpleFormatter());
			LibraryConstants.LOGGER.addHandler(fh);

			*//** Removing console handler from logger *//*
			LibraryConstants.LOGGER.setUseParentHandlers(false);
		} catch (IOException e) {
			LibraryConstants.LOGGER.log(Level.SEVERE, CommonUtil.getLogMessage(e.toString()), e);
		}

		*//** Initialize mandatory Parameters *//*
		String env = LibraryConstants.STAGING_ENVIRONMENT;
		// merchant id (mid) is provided at the time of onboarding
		String mid = "xxxxxxxxxxxxxxxxxxxx";
		// merchant key is provided at the time of onboarding
		String key = "xxxxxxxxxxxxxxxx";

		String website = "WEBSTAGING";
		String callbackUrl = "http://localhost:8080/PaytmNativeApiJavaIntegration/pgResponse.jsp";

		*//** Setting Initial Parameters *//*
		MerchantProperties.initialize(env, mid, key, website);

		*//** Setting Callback URL *//*
		MerchantProperties.setCallbackUrl(callbackUrl);

		*//** Setting timeout for connection i.e. Connection Timeout *//*
		MerchantProperties.setConnectionTimeout(new Time(5, TimeUnit.MINUTES));
	*/}
	
	@GetMapping("/patient")
	public @ResponseBody String getPaytmCheckSum() {
	/* initialize a TreeMap object */
	TreeMap<String, String> paytmParams = new TreeMap<String, String>();

	/* Find your MID in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys */
	paytmParams.put("MID", "YOUR_MID_HERE");

	/* Find your WEBSITE in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys */
	paytmParams.put("WEBSITE", "YOUR_WEBSITE_HERE");

	/* Find your INDUSTRY_TYPE_ID in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys */
	paytmParams.put("INDUSTRY_TYPE_ID", "YOUR_INDUSTRY_TYPE_ID_HERE");

	/* WEB for website and WAP for Mobile-websites or App */
	paytmParams.put("CHANNEL_ID", "YOUR_CHANNEL_ID");

	/* Enter your unique order id */
	paytmParams.put("ORDER_ID", "YOUR_ORDER_ID");

	/* unique id that belongs to your customer */
	paytmParams.put("CUST_ID", "CUSTOMER_ID");

	/* customer's mobile number */
	paytmParams.put("MOBILE_NO", "CUSTOMER_MOBILE_NUMBER");

	/* customer's email */
	paytmParams.put("EMAIL", "CUSTOMER_EMAIL");

	/**
	* Amount in INR that is payble by customer
	* this should be numeric with optionally having two decimal points
	*/
	paytmParams.put("TXN_AMOUNT", "ORDER_TRANSACTION_AMOUNT");

	/* on completion of transaction, we will send you the response on this URL */
	paytmParams.put("CALLBACK_URL", "YOUR_CALLBACK_URL");

	/**
	* Generate checksum for parameters we have
	* You can get Checksum JAR from https://developer.paytm.com/docs/checksum/
	* Find your Merchant Key in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys 
	*/
	//String checksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum("YOUR_KEY_HERE", paytmParams);
   // return checksum;
	return null;
	}
}
