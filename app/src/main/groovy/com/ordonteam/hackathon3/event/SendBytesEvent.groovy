package com.ordonteam.hackathon3.event

import groovy.transform.CompileStatic
import groovy.transform.TupleConstructor

@CompileStatic
@TupleConstructor
class SendBytesEvent {
    final byte[] bytes
}
