package com.ordonteam.hackathon3

import com.ordonteam.hackathon3.event.Bus
import com.ordonteam.hackathon3.event.SendBytesEvent
import groovy.transform.CompileStatic

@CompileStatic
abstract class SenderActivity extends RoomActivity implements Bus.Passenger {
    @Override
    protected void onResume() {
        super.onResume()
        Bus.getIn(this)
    }

    @Override
    protected void onPause() {
        super.onPause()
        Bus.getOut(this)
    }

    @SuppressWarnings('unused')
    void onEvent(SendBytesEvent event) {
        sendUnreliableMessageToOthers(event.bytes)
    }
}
