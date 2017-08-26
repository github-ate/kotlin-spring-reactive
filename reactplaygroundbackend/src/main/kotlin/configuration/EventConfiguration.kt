package configuration

import event.messagechannel.EventMessageChannel
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.context.annotation.Configuration

@Configuration
@EnableBinding(EventMessageChannel::class)
class EventConfiguration