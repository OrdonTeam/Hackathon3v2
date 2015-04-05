package com.ordonteam

import spock.lang.Specification

class CurrySpec extends Specification {

    def "Method handler with curry"() {
        given:
        def ints = ['a', 'b', 'c', 'd']

        when:
        def multiplied = ints.collect(this.&multiple.curry(2))

        then:
        multiplied == ['aa', 'bb', 'cc', 'dd']
    }

    String multiple(int a, String string) {
        return string * a
    }
}
