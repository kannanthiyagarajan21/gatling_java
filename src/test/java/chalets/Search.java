package chalets;

import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Search extends Simulation {

  {
    HttpProtocolBuilder httpProtocol = http
      .baseUrl("https://next-staging.almosafer.com")
      .inferHtmlResources()
      .acceptHeader("*/*")
      .acceptEncodingHeader("gzip, deflate")
      .contentTypeHeader("application/json")
      .userAgentHeader("PostmanRuntime/7.30.0");
    
    Map<CharSequence, String> headers_0 = new HashMap<>();
    headers_0.put("Postman-Token", "449903c9-44ce-452d-97cb-9212352ec3e5");
    headers_0.put("x-authorization", "I28JS$s928918982SL344&#lSLKJ3#");


    ScenarioBuilder scn = scenario("Carousel")
      .exec(
        http("request_0")
          .post("/api/accommodation/property/search")
          .headers(headers_0)
          .body(RawFileBody("chalets/carousel/0000_request.json"))
                .check(status().is(200))
      );

	  setUp(scn.injectOpen(atOnceUsers(1),rampUsers(5).during(5),constantUsersPerSec(5).during(5))).protocols(httpProtocol);
  }
}
