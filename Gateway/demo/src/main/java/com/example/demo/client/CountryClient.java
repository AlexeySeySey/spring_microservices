package com.example.demo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import com.example.demo.client.gen.GetCountryRequest;
import com.example.demo.client.gen.GetCountryResponse;

public class CountryClient extends WebServiceGatewaySupport {

  public GetCountryResponse getCountry(String country) {

    GetCountryRequest request = new GetCountryRequest();
    request.setName(country);

    return (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }

}