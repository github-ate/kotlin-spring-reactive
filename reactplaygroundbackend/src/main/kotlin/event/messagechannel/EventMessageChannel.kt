package event.messagechannel

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel


interface EventMessageChannel {
    @Output
    fun customer(): MessageChannel
}