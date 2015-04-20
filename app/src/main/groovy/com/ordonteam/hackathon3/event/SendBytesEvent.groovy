package com.ordonteam.hackathon3.event

import groovy.transform.CompileStatic
import groovy.transform.TupleConstructor

@CompileStatic
@TupleConstructor
class SendBytesEvent implements Bus.Event{
    final byte[] bytes
}
