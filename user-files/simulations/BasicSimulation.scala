package simulations
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class SimulationPHP7 extends Simulation {

  val httpProtocol8080 = http
    .baseUrl("http://0.0.0.0:8000") // URL base para o teste na porta 8080

  val scn8080 = scenario("healthCheck")
    .exec(
      http("PHP7")
      .get("/api/healthcheck")
      .check(status.is(200))
    )

  setUp(
    scn8080.inject(
      // constantUsersPerSec(2).during(10.seconds),
      // constantUsersPerSec(500).during(20.seconds),
      // constantUsersPerSec(5).during(15.seconds).randomized,
      rampUsersPerSec(10).to(500).during(30.seconds),
      constantUsersPerSec(500).during(30.seconds),
      // atOnceUsers(500)
    )
  ).protocols(httpProtocol8080)
}
