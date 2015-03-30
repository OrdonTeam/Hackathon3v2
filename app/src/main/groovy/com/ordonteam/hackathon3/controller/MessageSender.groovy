package com.ordonteam.hackathon3.controller

import groovy.transform.CompileStatic

@CompileStatic
interface MessageSender {

    int sendUnreliableMessageToOthers(byte[] bytes)

}