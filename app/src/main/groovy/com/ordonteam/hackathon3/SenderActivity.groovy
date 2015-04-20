package com.ordonteam.hackathon3

import com.ordonteam.hackathon3.event.SendBytesEvent
import de.greenrobot.event.EventBus
import groovy.transform.CompileStatic

@CompileStatic
abstract class SenderActivity extends RoomActivity{
    @Override
    protected void onResume() {
        super.onResume()
        EventBus.default.register(this)
    }

    @Override
    protected void onPause() {
        super.onPause()
        EventBus.default.unregister(this)
    }

    @SuppressWarnings('unused')
    void onEvent(SendBytesEvent event){
        sendUnreliableMessageToOthers(event.bytes)
    }
}
