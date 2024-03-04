package chalets;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class SearchNew extends Simulation {

  {
    HttpProtocolBuilder httpProtocol = http
      .baseUrl("https://next-staging.almosafer.com")
      .inferHtmlResources()
      .acceptHeader("*/*")
      .acceptEncodingHeader("gzip, deflate")
      .contentTypeHeader("application/json")
      .userAgentHeader("PostmanRuntime/7.30.0");
    
    Map<CharSequence, String> headers_0 = new HashMap<>();
    headers_0.put("Postman-Token", "546a146c-54e5-4044-ab09-e2117d5b6529");
    headers_0.put("x-authorization", "I28JS$s928918982SL344&#lSLKJ3#");


    ScenarioBuilder scn = scenario("SearchNew")
      .exec(
        http("request_0")
          .post("/api/accommodation/property/search")
          .headers(headers_0)
          .body(RawFileBody("chalets/searchnew/0000_request.json"))
      );

    setUp(scn.injectClosed(constantConcurrentUsers(20).during(30))).protocols(httpProtocol);
  }
}
