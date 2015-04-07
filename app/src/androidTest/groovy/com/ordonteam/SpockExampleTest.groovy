package com.ordonteam

import spock.lang.Specification

final class SpockExampleTest extends Specification {

    def "Spock example should run and pass"() {
        given:
        int a = 5

        when:
        a = 3

        then:
        3 == a
    }
}
