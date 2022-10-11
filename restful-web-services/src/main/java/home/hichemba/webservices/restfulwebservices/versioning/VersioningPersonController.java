package home.hichemba.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getV1Person() {
		return new PersonV1("Hichem Ben Abdallah");
	}

	@GetMapping("/v2/person")
	public PersonV2 getV2Person() {
		return new PersonV2(new Name("Hichem", "Ben Abdallah"));
	}

	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getPersonParameterV1() {
		return new PersonV1("Hichem Ben Abdallah");
	}

	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getPersonParameterV2() {
		return new PersonV2(new Name("Hichem", "Ben Abdallah"));
	}

	@GetMapping(path = "/person/header", headers = "x-api-version=1")
	public PersonV1 getPersonHeaderV1() {
		return new PersonV1("Hichem Ben Abdallah");
	}
	
	@GetMapping(path = "/person/header", headers = "x-api-version=2")
	public PersonV2 getPersonHeaderV2() {
		return new PersonV2(new Name("Hichem", "Ben Abdallah"));
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vnd.api.v1+json")
	public PersonV1 getPersonAcceptV1() {
		return new PersonV1("Hichem Ben Abdallah");
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vnd.api.v2+json")
	public PersonV2 getPersonAcceptV2() {
		return new PersonV2(new Name("Hichem", "Ben Abdallah"));
	}
}
