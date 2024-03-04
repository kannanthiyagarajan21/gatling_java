package chalets;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class Carousel extends Simulation {

  {
    HttpProtocolBuilder httpProtocol = http
      .baseUrl("https://next-staging.almosafer.com")
      .inferHtmlResources()
      .acceptHeader("*/*")
      .acceptEncodingHeader("gzip, deflate")
      .contentTypeHeader("application/json")
      .userAgentHeader("PostmanRuntime/7.30.0");
    
    Map<CharSequence, String> headers_0 = new HashMap<>();
    headers_0.put("Postman-Token", "a6b5d08a-8471-41a7-90bf-c0c064c969fd");
    headers_0.put("x-authorization", "P4D8u1bsP14GpOHCOJn7jK1S");


    ScenarioBuilder scn = scenario("Carousel")
      .exec(
        http("request_0")
          .post("/api/accommodation/property/carousel")
          .headers(headers_0)
          .body(RawFileBody("chalets/carousel/0000_request.json"))
                .check(status().is(200))
      );

//    setUp(scn.injectOpen(atOnceUsers(1),rampUsers(5).during(5),constantUsersPerSec(5).during(5))).protocols(httpProtocol);
//    setUp(scn.injectOpen(atOnceUsers(1),constantUsersPerSec(1).during(5))).protocols(httpProtocol);
    setUp(scn.injectClosed(constantConcurrentUsers(10).during(20))).protocols(httpProtocol);
  }
}
