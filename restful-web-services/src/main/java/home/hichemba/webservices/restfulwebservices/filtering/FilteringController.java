package home.hichemba.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {

		SomeBean someBean = new SomeBean("v1", "v2", "v3");
		return createMappingFilter(someBean, "v1", "v2");
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList() {

		List<SomeBean> asList = Arrays.asList(new SomeBean("v1", "v2", "v3"), new SomeBean("v11", "v22", "v33"));
		return createMappingFilter(asList, "v3");
	}

	private MappingJacksonValue createMappingFilter(Object filteredObject, String... fields) {

		MappingJacksonValue jacksonValue = new MappingJacksonValue(filteredObject);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeFilter", filter);
		jacksonValue.setFilters(filterProvider);
		return jacksonValue;
	}
}
