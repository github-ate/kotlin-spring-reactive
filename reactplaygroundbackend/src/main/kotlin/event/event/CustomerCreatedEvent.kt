package event.event

class CustomerCreatedEvent(val id: Long?) : CustomerEvent {
    override fun equals(other: Any?): Boolean {
        if (other !is CustomerCreatedEvent || id == null || other.id == null) {
            return false
        }
        return id == other.id
    }
}