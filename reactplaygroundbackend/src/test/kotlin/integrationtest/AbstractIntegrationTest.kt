package integrationtest

import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
abstract class AbstractIntegrationTest {
    lateinit var client: WebTestClient

    @Before
    fun setup() {
        client = WebTestClient
                .bindToServer().baseUrl("http://localhost:8080")
                .build()
    }
}