package bulldozer

open class Event {
    private var cancelled = false

    fun setCancelled(state: Boolean){
        cancelled = state
    }

    fun isCancelled(): Boolean{
        return cancelled
    }
}