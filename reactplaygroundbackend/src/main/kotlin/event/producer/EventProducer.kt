package event.producer

import event.event.CustomerCreatedEvent
import event.messagechannel.EventMessageChannel
import org.springframework.messaging.support.GenericMessage
import org.springframework.stereotype.Component


@Component
class EventProducer(val events: EventMessageChannel) {
    fun publishCustomerCreatedEvent(event: CustomerCreatedEvent) {
        events.customer().send(GenericMessage<CustomerCreatedEvent>(event))
    }
}