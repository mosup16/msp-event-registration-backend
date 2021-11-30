package com.example.mspeventregistration.event.form;

import io.vavr.control.Try;

import java.util.List;
import java.util.Map;

class SupportedConstraints {
    public static final Constraint<String> isIntegerDigit =
            Constraint.of((value, params) -> Try.of(() -> Long.parseLong(value)).isSuccess());

    public static final Constraint<String> isDecimalDigit =
            Constraint.of((value, params) -> Try.of(() -> Double.parseDouble(value)).isSuccess());

    public static final Constraint<String> isGreaterThan = Constraint.of(List.of("compareTo")
            , (value, params) -> isGreaterThan(value, Double.parseDouble(params.get("compareTo"))));

    public static Map<String, Constraint<String>> supportedConstraints = Map.of(
            "IntegerDigit", isIntegerDigit
            , "DecimalDigit", isDecimalDigit
            , "IsGreaterThan", isGreaterThan
    );


    public static Constraint<String> getByName(String name) {
        return SupportedConstraints.supportedConstraints.get(name);
    }

    private static boolean isGreaterThan(String s, double compareTo) {
        return isDecimalDigit.isSatisfied(s) && Double.parseDouble(s) > compareTo;
    }


}
