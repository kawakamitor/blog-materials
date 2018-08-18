package com.example.gatlingsample

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GatlingSampleSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://localhost:8080/")
    .basicAuth("user", "password")

  val params = csv("params.csv").random

  val scn = scenario("GatlingSampleSimulation")
    .feed(params)
    .exec(http("get").get("""${pathVariable}?requestParam=${requestParam}"""))

  setUp(
    scn.inject(
      rampUsers(1000) over (60)
    ).protocols(httpConf)
  )
}
