import javax.ws.rs.core.Application

import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

class HelloWorldTest extends FlatSpec with Matchers with BeforeAndAfterAll {
  private val jersey: JerseyTest = new JerseyTest {
    override def configure(): Application = {
      new ResourceConfig(classOf[Users])
    }
  }


  behavior of classOf[HelloWorld].getName

  it should "return 123" in {
    jersey.target("/users/123").request.get(classOf[String]) should be ("123")
  }

  override protected def beforeAll(): Unit = jersey.setUp()

  override protected def afterAll(): Unit = jersey.tearDown()
}