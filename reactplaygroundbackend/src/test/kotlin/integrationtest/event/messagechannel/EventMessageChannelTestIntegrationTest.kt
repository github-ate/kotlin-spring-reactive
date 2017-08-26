package integrationtest.event.messagechannel

import configuration.EventConfiguration
import event.messagechannel.EventMessageChannel
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.stream.test.binder.MessageCollector
import org.springframework.cloud.stream.test.binder.TestSupportBinderAutoConfiguration
import org.springframework.messaging.Message
import org.springframework.messaging.support.GenericMessage
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertTrue


@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(EventConfiguration::class, TestSupportBinderAutoConfiguration::class), properties = arrayOf("server.port=-1"))
@DirtiesContext
class EventMessageChannelTest {
    @Autowired
    lateinit var messageCollector: MessageCollector

    @Autowired
    lateinit var processor: EventMessageChannel

    @Test
    fun `Test message on channel`() {
        val message = GenericMessage("hello")
        this.processor.customer().send(message)
        val received = this.messageCollector.forChannel(this.processor.customer()).poll() as Message<String>
        assertTrue(received.payload.contentEquals("hello"))
    }
}