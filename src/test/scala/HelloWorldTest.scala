import javax.ws.rs.core.Application

import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.junit.Test
import org.scalatest.Matchers

class HelloWorldTest extends JerseyTest with Matchers {
  override def configure(): Application = {
    new ResourceConfig(classOf[Users])
  }

  @Test
  def get(): Unit = {
    target("/users/123").request.get(classOf[String]) should be ("123")
  }
}
