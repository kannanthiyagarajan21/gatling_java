package chalets;

import common.Utils;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.util.HashMap;
import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class CarouselDynamic extends Simulation {

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
          .post("/api/accommodation/property/carousel?src=gatling")
          .headers(headers_0)
          .body(StringBody(session -> Utils.getJsonPayload()))
                .check(status().is(200))
                .transformResponse((response, session) -> response)
      );

//    setUp(scn.injectOpen(atOnceUsers(1),rampUsers(5).during(5),constantUsersPerSec(5).during(5))).protocols(httpProtocol);
//    setUp(scn.injectOpen(atOnceUsers(1),constantUsersPerSec(1).during(5))).protocols(httpProtocol);
    setUp(scn.injectClosed(constantConcurrentUsers(1).during(5))).protocols(httpProtocol);
  }
}
