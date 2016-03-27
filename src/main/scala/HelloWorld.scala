import java.net.URI
import javax.ws.rs._
import javax.ws.rs.core.{MediaType, UriBuilder}

import org.apache.logging.log4j.{LogManager, Logger}
import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.glassfish.jersey.media.sse.{EventOutput, OutboundEvent, SseFeature}
import org.glassfish.jersey.server.{ChunkedOutput, ResourceConfig}

import scala.concurrent.{ExecutionContext, Future}

@Path("/users/{id}")
case class Users() {

  val log: Logger = LogManager.getLogger(getClass)

  @GET
  @Produces(Array(MediaType.TEXT_PLAIN))
  def get(@PathParam("id") id: Int): String = {
    s"$id"
  }

  @GET
  @Path("roles")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def roles(@PathParam("id") id: Int): String = {
    s"Roles for user $id"
  }

  @POST
  @Path("roles")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def addRole(@PathParam("id") id: Int, body: String) = {
    body
  }

  @GET
  @Path("events")
  @Produces(Array(SseFeature.SERVER_SENT_EVENTS))
  def event(): EventOutput = {
    val output: EventOutput = new EventOutput
    Future {
      (1 to 5).foreach { i =>
        val builder: OutboundEvent.Builder = new OutboundEvent.Builder()
        builder.name("event")
        builder.data(classOf[String], i.toString)
        output.write(builder.build())
        Thread.sleep(1000)
      }
      output.close()
    }(ExecutionContext.global)
    output
  }

  @GET
  @Path("chunked")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def chuncked() : ChunkedOutput[String] = {
    val output = new ChunkedOutput[String](classOf[String])
    Future {
      (1 to 5).foreach { i =>
        if (i == 1)
          output.write("[")
        output.write(i.toString)
        if (i < 5)
          output.write(",")
        else {
          output.write("]")
        }
        Thread.sleep(1000)
      }
      output.close()
    }(ExecutionContext.global)
    output
  }
}

object HelloWorld extends App {
  System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager")

  private val uri: URI = UriBuilder.fromUri("http://localhost/").port(8080).build()
  val config: ResourceConfig = new ResourceConfig(
    classOf[Users]
  )
  private val server: HttpServer = GrizzlyHttpServerFactory.createHttpServer(uri, config)
  server.start()

  sys.addShutdownHook(server.shutdownNow())
  Thread.sleep(Integer.MAX_VALUE)
}
