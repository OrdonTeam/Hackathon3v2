package com.ordonteam.hackathon3.controller

import groovy.transform.CompileStatic

@CompileStatic
interface MessageSender {

    void sendUnreliableMessageToOthers(byte[] bytes)

}
