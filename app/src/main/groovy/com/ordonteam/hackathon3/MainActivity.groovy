package com.ordonteam.hackathon3

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.arasthel.swissknife.SwissKnife
import com.arasthel.swissknife.annotations.InjectView
import com.arasthel.swissknife.annotations.OnClick
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage
import groovy.transform.CompileStatic

@CompileStatic
class MainActivity extends RoomActivity {

    @InjectView(R.id.textView)
    TextView text
    @InjectView(R.id.editText)
    EditText editText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        SwissKnife.inject(this)
        text.setText('Hello Groovy')
    }

    @Override
    void onConnected(Bundle bundle) {
        super.onConnected(bundle)
        text.setText('Connected')
    }

    @Override
    void onRoomCreationFailure(int statusCode) {
        text.setText('onRoomCreationFailure')
    }

    @Override
    void startGame(String myParticipantId) {
        text.setText('startGame')
    }

    @OnClick(R.id.send)
    public void send() {
        sendUnreliableMessageToOthers(editText.text.toString().bytes)
    }

    @Override
    void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
        text.setText("${realTimeMessage.senderParticipantId} said: ${new String(realTimeMessage.messageData)} to you")
    }

    @Override
    void onConnectFailed(int errorCode) {
        text.setText("onConnectFailed Code = $errorCode")
    }

    @Override
    void onNotSignedIn(int errorCode) {
        text.setText("onNotSignedIn Code = $errorCode")
    }
}