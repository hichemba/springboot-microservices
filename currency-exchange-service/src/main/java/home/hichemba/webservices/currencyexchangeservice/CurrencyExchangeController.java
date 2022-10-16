package home.hichemba.webservices.currencyexchangeservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environnement;

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;

	@GetMapping("/currency-exchange/from/{fromC}/to/{toC}")
	public CurrencyExchange getEchangeValue(@PathVariable String fromC, @PathVariable String toC) {

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
