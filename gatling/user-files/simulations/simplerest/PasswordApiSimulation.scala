package simplerest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class PasswordApiSimulation extends Simulation {
    val baseUrl = sys.env("BASE_URL")
    val httpProtocol = http
        .baseUrl(baseUrl)
    val scn = scenario("Get password")
        .exec(http("request_1")
        .get("/mypass"))
    // setUp(scn.inject(atOnceUsers(100)).protocols(httpProtocol))
    setUp(scn.inject(constantUsersPerSec(5) during (2 minutes))).throttle(
        reachRps(150) in (100 seconds),
        holdFor(1 minute),
        jumpToRps(50),
        holdFor(3 minutes)
    ).protocols(httpProtocol)
}