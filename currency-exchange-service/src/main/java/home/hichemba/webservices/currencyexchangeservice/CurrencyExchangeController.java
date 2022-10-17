package home.hichemba.webservices.currencyexchangeservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	private Environment environnement;

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;

	@GetMapping("/currency-exchange/from/{fromC}/to/{toC}")
	public CurrencyExchange getEchangeValue(@PathVariable String fromC, @PathVariable String toC) {
		
		logger.info("Get exchange value from {} to {}", fromC, toC);

		CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromCAndToC(fromC, toC);

		if (currencyExchange == null) {
			throw new RuntimeException("Unable to find exchange value for " + fromC + " to " + toC);
		}

		String port = environnement.getProperty("local.server.port");
		currencyExchange.setEnv(port);
		return currencyExchange;
	}

	@GetMapping("/currency-exchange")
	public List<CurrencyExchange> getCurrencyExchanges() {

		String port = environnement.getProperty("local.server.port");
		List<CurrencyExchange> findAll = currencyExchangeRepository.findAll();
		findAll.stream().forEach(c -> c.setEnv(port));

		return findAll;
	}

}
