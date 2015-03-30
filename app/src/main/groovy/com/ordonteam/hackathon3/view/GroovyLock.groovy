package com.ordonteam.hackathon3.view

import groovy.transform.CompileStatic

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

@CompileStatic
class GroovyLock {
    Lock lock = new ReentrantLock()

    void withLock(Closure doWithLock){
        lock.lockInterruptibly()
        try{
            doWithLock()
        }finally {
            lock.unlock()
        }
    }
}
