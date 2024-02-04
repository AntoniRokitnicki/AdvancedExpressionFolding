package com.intellij.advancedExpressionFolding;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface Consts {

    Set<String> SUPPORTED_METHODS = new HashSet<>() {
        {
            add("add");
            add("multiply");
            add("divide");
            add("subtract");
            add("remainder");
            /*add("scaleByPowerOfTen");*/
            add("pow");
            add("min");
            add("max");
            add("negate");
            add("plus");
            add("abs");
            add("valueOf");
            add("equals");
            add("and");
            add("gcd");
            add("not");
            add("or");
            add("shiftLeft");
            add("shiftRight");
            add("signum");
            add("xor");
            add("andNot");
            add("mod");
            /*add("modInverse");*/
            /*add("modPow");*/
            add("acos");
            add("asin");
            add("atan");
            add("atan2");
            add("cbrt");
            add("ceil");
            add("cos");
            add("cosh");
            add("floor");
            add("log");
            add("log10");
            /*add("log1p");*/
            add("random");
            add("rint");
            add("round");
            add("sin");
            add("sinh");
            add("sqrt");
            add("tan");
            add("tanh");
            add("toDegrees");
            add("toRadians");
            add("ulp");
            /*add("hypot");*/
            add("exp");
            /*add("expm1");*/
            add("append");
            add("substring");
            add("subList");
            add("contains");
            add("containsKey");
            add("get");
            add("isPresent");
            add("charAt");
            add("put");
            add("set");
            add("asList");
            add("singletonList");
            add("addAll");
            add("removeAll");
            add("remove");
            add("collect");
            add("stream");
            add("unmodifiableSet");
            add("unmodifiableList");
            add("toString");
            add("isBefore");
            add("isAfter");
            // LocalDate literal
            add("of");
            //Optional
            add("map");
            add("flatMap");
            add("orElse");
            add("orElseGet");
            add("ofNullable");
            add("orElseThrow");
            //Stream
            add("filter");
            // Lombok builder
            add("build");
        }
    };
    Set<String> SUPPORTED_CLASSES = new HashSet<>() {
        {
            add("java.math.BigDecimal");
            add("java.math.BigInteger");
            add("java.lang.Math");
            add("java.lang.Long");
            add("java.lang.Integer");
            add("java.lang.Float");
            add("java.lang.Double");
            add("java.lang.Character");
            add("java.lang.String");
            add("java.lang.StringBuilder");
            add("java.lang.AbstractStringBuilder");
            add("java.util.List");
            add("java.util.ArrayList");
            add("java.util.Map");
            add("java.util.HashMap");
            add("java.util.Set");
            add("java.util.HashSet");
            add("java.lang.Object");
            add("java.util.Arrays");
            add("java.util.Optional");
            add("java.util.Collection");
            add("java.util.Collections");
            add("java.util.Objects");
            add("java.util.stream.Stream");
            add("java.time.LocalDate");
        }
    };
    Set<String> UNSUPPORTED_CLASSES_METHODS_EXCEPTIONS = new HashSet<>() {
        {
            add("equals");
            add("compareTo");
        }
    };
    Set<String> SUPPORTED_PRIMITIVE_TYPES = new HashSet<String>() {
        {
            add("int");
            add("long");
            add("float");
            add("double");
            add("char");
            add("java.lang.String");
        }
    };
    Set<String> SUPPORTED_BINARY_OPERATORS = new HashSet<String>() {
        {
            add("+");
            add("-");
            add("*");
            add("/");
        }
    };
    Map<String, Object> SUPPORTED_CONSTANTS = new HashMap<>() {
        {
            put("ZERO", 0);
            put("ONE", 1);
            put("TEN", 10);
            put("PI", "π");
            put("E", "\uD835\uDC52");
        }
    };
}
