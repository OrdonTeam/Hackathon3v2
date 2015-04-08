ruleset {
    description 'Rules for groovy android project'

    ruleset('rulesets/basic.xml'){
        EmptyMethod(enabled: false)//TODO: This should be removed in future
        ComparisonOfTwoConstants(enabled: false)//TODO: This should be removed in future
//        EmptyCatchBlock(enabled: false)
        ConstantAssertExpression(enabled: false)//TODO: This should be removed in future
    }

    ruleset('rulesets/imports.xml'){
        MisorderedStaticImports(enabled: false)
    }

    ruleset('rulesets/naming.xml'){
        ClassName{
            regex = '^[A-Z][\$a-zA-Z0-9]*$'
        }
        FieldName{
            finalRegex = '^[a-z][a-zA-Z0-9]*$'
            staticFinalRegex = '^logger$|^[A-Z][A-Z_0-9]*$|^serialVersionUID$'
        }
        MethodName{
            regex = '^[a-z][\$_a-zA-Z0-9]*$|^.*\\s.*$'
        }
        VariableName{
            finalRegex = '^[a-z][a-zA-Z0-9]*$'
        }
    }

    ruleset('rulesets/concurrency.xml')

    ruleset('rulesets/convention.xml')

    ruleset('rulesets/design.xml'){
        AbstractClassWithPublicConstructor(enabled: false)
    }

    ruleset('rulesets/dry.xml'){
        DuplicateNumberLiteral(enabled: false)
    }

    ruleset('rulesets/enhanced.xml')

    ruleset('rulesets/formatting.xml'){
        LineLength{
            length = 170
        }

        ClassJavadoc(enabled: false)
        SpaceAroundMapEntryColon(enabled: false)
        SpaceAfterCatch(enabled: false)
        SpaceAfterComma(enabled: false)
        SpaceAfterClosingBrace(enabled: false)
        SpaceAfterFor(enabled: false)
        SpaceAfterIf(enabled: false)
        SpaceAfterOpeningBrace(enabled: false)
        SpaceAfterWhile(enabled: false)
        SpaceAfterSwitch(enabled: false)
        SpaceAroundClosureArrow(enabled: false)
        SpaceBeforeClosingBrace(enabled: false)
        SpaceBeforeOpeningBrace(enabled: false)
    }

    ruleset('rulesets/exceptions.xml') {
        ThrowRuntimeException(enabled: false)
    }

    //ruleset('rulesets/generic.xml') //nice but dont work - https://tenpercentnotcrap.wordpress.com/tag/codenarc/

    ruleset('rulesets/groovyism.xml'){
        ClosureAsLastMethodParameter(enabled: false)
    }

    ruleset('rulesets/junit.xml'){
        JUnitPublicNonTestMethod(enabled: false)
        JUnitAssertAlwaysSucceeds(enabled: false) //TODO: This should be removed in future
    }

    ruleset('rulesets/serialization.xml')

    ruleset('rulesets/size.xml'){
        MethodSize {
            maxLines = 19 // Small methods with good naming improves quality measurably
           // ignoreMethodNames = 'populate*,assert*,mock*,generate*' //These method names are used in tests and for populating large Representations (pojos)
           // doNotApplyToFilesMatching = '.*Representation.groovy'
        }
        CyclomaticComplexity {
            maxMethodComplexity = 7
        }
        AbcMetric {
            maxMethodAbcScore = 20
            maxClassAverageMethodAbcScore = 24
        }
    }

    ruleset('rulesets/unnecessary.xml'){
        UnnecessaryReturnKeyword(enabled: false)
    }

    ruleset('rulesets/unused.xml'){
        UnusedMethodParameter(enabled: false) //TODO: This should be removed in future
    }

}