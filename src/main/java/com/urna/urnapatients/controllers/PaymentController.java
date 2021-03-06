package com.urna.urnapatients.controllers;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.paytm.pg.merchant.CheckSumServiceHelper;
import com.urna.urnapatients.dto.PaytmDetails;
import com.urna.urnapatients.models.Patient;
import com.urna.urnapatients.models.Payment;
import com.urna.urnapatients.repo.PaymentRepository;

@Controller
public class PaymentController {
	
	@Autowired
	private PaytmDetails paytmDetails;
	@Autowired
	private Environment env;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}

	 @PostMapping(value = "/pgredirect")
	    public ModelAndView getRedirect(@RequestParam(name = "CUST_ID") String customerId,
	                                    @RequestParam(name = "TXN_AMOUNT") String transactionAmount,
	                                    @RequestParam(name = "ORDER_ID") String orderId) throws Exception {

	        ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetails.getPaytmUrl());
	        TreeMap<String, String> parameters = new TreeMap<>();
	        paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
	        parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
	        parameters.put("EMAIL", env.getProperty("paytm.email"));
	        parameters.put("ORDER_ID", orderId);
	        parameters.put("TXN_AMOUNT", transactionAmount);
	        parameters.put("CUST_ID", customerId);
	        String checkSum = getCheckSum(parameters);
	        parameters.put("CHECKSUMHASH", checkSum);
	        modelAndView.addAllObjects(parameters);
	        return modelAndView;
	    }
	 
	 
	 @PostMapping(value = "/pgresponse")
	    public String getResponseRedirect(HttpServletRequest request, Model model) {

	        Map<String, String[]> mapData = request.getParameterMap();
	        TreeMap<String, String> parameters = new TreeMap<String, String>();
	        mapData.forEach((key, val) -> parameters.put(key, val[0]));
	        String paytmChecksum = "";
	        if (mapData.containsKey("CHECKSUMHASH")) {
	            paytmChecksum = mapData.get("CHECKSUMHASH")[0];
	        }
	        String result;

	        boolean isValideChecksum = false;
	        System.out.println("RESULT : "+parameters.toString());
	        try {
	            isValideChecksum = validateCheckSum(parameters, paytmChecksum);
	            if (isValideChecksum && parameters.containsKey("RESPCODE")) {
	                if (parameters.get("RESPCODE").equals("01")) {
	                    result = "Payment Successful";
	                } else {
	                    result = "Payment Failed";
	                }
	            } else {
	                result = "Checksum mismatched";
	            }
	        } catch (Exception e) {
	            result = e.toString();
	        }
	        model.addAttribute("result",result);
	        parameters.remove("CHECKSUMHASH");
	        model.addAttribute("parameters",parameters);
	        return "report";
	    }

	    private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
	        return CheckSumServiceHelper.getCheckSumServiceHelper().verifycheckSum(paytmDetails.getMerchantKey(),
	                parameters, paytmChecksum);
	    }


	private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
		return CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(paytmDetails.getMerchantKey(), parameters);
	}
	@PostMapping("/internal/payment")
	public @Valid Payment createPayment(@Valid @RequestBody Payment payment) {
	    return paymentRepository.save(payment);
	}
	@PutMapping("/internal/payment")
	public @Valid Payment updatePayment(@Valid @RequestBody Payment payment) {
	    return paymentRepository.save(payment);
	}
	@GetMapping("/internal/patient/{patientId}")
	public @Valid Iterable<Payment> getAllPaymentsForPatient(@PathVariable String patientId) {
	    return paymentRepository.findAllPaymentsByPatientId(Integer.parseInt(patientId));
	}
	
	@GetMapping("/internal/patient/active/{patientId}")
	public @Valid Iterable<Payment> getAllActivePaymentsForPatient(@PathVariable String patientId) {
	    return paymentRepository.findAllActivePaymentsByPatientId(Integer.parseInt(patientId), true);
	}
	
	@GetMapping("/internal/doctor/payment")
	public @Valid Iterable<Payment> getAllPaymentsForDoctor(@Valid @RequestBody Payment payment) {
	    return paymentRepository.findAllPaymentsByDoctorId(payment.getDoctorId());
	}
	
}
