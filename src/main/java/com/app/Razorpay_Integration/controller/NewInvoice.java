package com.app.Razorpay_Integration.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Invoice;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
public class NewInvoice {

	@PostMapping("/newInvoice")
	public String generateNewInvoice(@RequestParam (name = "c1") String name,
									@RequestParam (name = "c2") String contact,
									@RequestParam (name = "c3") String email,
									@RequestParam (name = "ba1") String line1,
									@RequestParam (name = "ba2") String line2,
									@RequestParam (name = "ba3") String zipcode,
									@RequestParam (name = "ba4") String city,
									@RequestParam (name = "ba5") String state,
									@RequestParam (name = "ba6") String country,
									@RequestParam (name = "t1") Integer amount

									) {
		try {
			RazorpayClient razorpay  = new RazorpayClient("rzp_test_nyhZvcQJQDLCKP", "HFnVLUpAHEvtvwKdcU1SAf5X");
			
			//creating invoice instance
			JSONObject invoiceRequest = new JSONObject();
			invoiceRequest.put("type", "invoice");
			invoiceRequest.put("description", "Invoice for the month of January 2020");
			invoiceRequest.put("partial_payment",true);
			JSONObject customer = new JSONObject();
			customer.put("name",name);
			customer.put("contact",contact);
			customer.put("email",email);
			JSONObject billingAddress = new JSONObject();
			billingAddress.put("line1",line1);
			billingAddress.put("line2",line2);
			billingAddress.put("zipcode",zipcode);
			billingAddress.put("city",city);
			billingAddress.put("state",state);
			billingAddress.put("country",country);
			customer.put("billing_address",billingAddress);
			JSONObject shippingAddress = new JSONObject();
			shippingAddress.put("line1",line1);
			shippingAddress.put("line2",line2);
			shippingAddress.put("zipcode",zipcode);
			shippingAddress.put("city",city);
			shippingAddress.put("state",state);
			shippingAddress.put("country",country);
			customer.put("shipping_address",shippingAddress);
			invoiceRequest.put("customer",customer);
			List<Object> lines = new ArrayList<Object>();
			JSONObject lineItems = new JSONObject();
			lineItems.put("name","Master Cloud Computing in 30 Days");
			lineItems.put("description","Book by Ravena Ravenclaw");
			lineItems.put("amount",amount);
			lineItems.put("currency","INR");
			lineItems.put("quantity",1);
			lines.add(lineItems);
			invoiceRequest.put("line_items",lines);
			invoiceRequest.put("email_notify", 1);
			invoiceRequest.put("sms_notify", 1);
			invoiceRequest.put("currency","INR");
			invoiceRequest.put("expire_by", 1580479829);

			Invoice invoice = razorpay.invoices.create(invoiceRequest);
			
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Generated Successfully";
		
	}
}
