package home.hichemba.webservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping("/currency-conversion/from/{fromC}/to/{toC}/quantity/{quantity}")
	public CurrencyConversion calculateConversion(@PathVariable String fromC, @PathVariable String toC,
			@PathVariable BigDecimal quantity) {

		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("fromC", fromC);
		uriVariables.put("toC", toC);
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{fromC}/to/{toC}", CurrencyConversion.class,
				uriVariables);

		CurrencyConversion currencyConversion = responseEntity.getBody();

		return new CurrencyConversion(currencyConversion.getId(), fromC, toC,
				currencyConversion.getConversionMultiple(), quantity,
				currencyConversion.getConversionMultiple().multiply(quantity), currencyConversion.getEnv());
	}

	@GetMapping("/currency-conversion-feign/from/{fromC}/to/{toC}/quantity/{quantity}")
	public CurrencyConversion calculateConversionFeign(@PathVariable String fromC, @PathVariable String toC,
			@PathVariable BigDecimal quantity) {

		CurrencyConversion currencyConversion = proxy.getEchangeValue(fromC, toC);

		return new CurrencyConversion(currencyConversion.getId(), fromC, toC,
				currencyConversion.getConversionMultiple(), quantity,
				currencyConversion.getConversionMultiple().multiply(quantity), currencyConversion.getEnv());
	}
}
