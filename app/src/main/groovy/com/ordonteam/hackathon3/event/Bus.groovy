package com.ordonteam.hackathon3.event

import de.greenrobot.event.EventBus
import groovy.transform.CompileStatic

@CompileStatic
class Bus {

    static void getIn(Passenger passenger) {
        EventBus.default.register(passenger)
    }

    static void getOut(Passenger passenger) {
        EventBus.default.unregister(passenger)
    }

    static void informPassengers(Event event) {
        EventBus.default.post(event)
    }

    interface Event {

    }

    interface Passenger {

    }
}
