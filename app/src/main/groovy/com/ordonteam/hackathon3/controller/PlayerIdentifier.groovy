package com.ordonteam.hackathon3.controller

import groovy.transform.Canonical
import groovy.transform.CompileStatic

import java.util.concurrent.ConcurrentHashMap

/**
 * Created by kasper on 4/20/15.
 */
@CompileStatic
@Canonical
class PlayerIdentifier {

    private static final String MY_ID = "MY_ID"
    private static final Map<String,PlayerIdentifier> cache = new ConcurrentHashMap<>()

    final String participantId

    static PlayerIdentifier forParticipantId(String participantId) {
        if(!cache.containsKey(participantId) ){
            PlayerIdentifier identifier = new PlayerIdentifier(participantId)
            cache.put(participantId,identifier)
            return identifier
        }
        return cache.get(participantId)
    }

    static PlayerIdentifier my(){
       return forParticipantId(MY_ID)
    }
}
