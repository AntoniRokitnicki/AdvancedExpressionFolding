package com.intellij.advancedExpressionFolding.extension;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public interface Consts {

    Set<String> SUPPORTED_METHODS = new HashSet<>() {
        {
            add("add");
            add("multiply");
            add("divide");
            add("subtract");
            add("remainder");
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
            add("exp");
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
            //Optional
            add("of");
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
            add("println");
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
            add("java.io.PrintStream");
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
    Pattern GENERICS_PATTERN = Pattern.compile("<[^<>]*>");
    Map<Character, Character> SUPERSCRIPT_MAPPING = new HashMap<>() {
        {
            put('0', '⁰');
            put('1', '¹');
            put('2', '²');
            put('3', '³');
            put('4', '⁴');
            put('5', '⁵');
            put('6', '⁶');
            put('7', '⁷');
            put('8', '⁸');
            put('9', '⁹');
            put('(', '⁽');
            put(')', '⁾');
            put('+', '⁺');
            put('⁻', '⁻');
            put('n', 'ⁿ');
            put('i', 'ⁱ');
            put('a', 'ᵃ');
            put('b', 'ᵇ');
            put('c', 'ᶜ');
            put('d', 'ᵈ');
            put('e', 'ᵉ');
            put('f', 'ᶠ');
            put('g', 'ᵍ');
            put('h', 'ʰ');
            put('j', 'ʲ');
            put('k', 'ᵏ');
            put('l', 'ˡ');
            put('m', 'ᵐ');
            put('o', 'ᵒ');
            put('p', 'ᵖ');
            put('r', 'ʳ');
            put('s', 'ˢ');
            put('t', 'ᵗ');
            put('u', 'ᵘ');
            put('w', 'ʷ');
            put('*', 'ˣ');
            put('x', 'ˣ');
            put('y', 'ʸ');
            put('z', 'ᶻ');
            put('A', 'ᴬ');
            put('B', 'ᴮ');
            put('D', 'ᴰ');
            put('E', 'ᴱ');
            put('G', 'ᴳ');
            put('H', 'ᴴ');
            put('I', 'ᴵ');
            put('J', 'ᴶ');
            put('K', 'ᴷ');
            put('L', 'ᴸ');
            put('M', 'ᴹ');
            put('N', 'ᴺ');
            put('O', 'ᴼ');
            put('P', 'ᴾ');
            put('R', 'ᴿ');
            put('T', 'ᵀ');
            put('U', 'ᵁ');
            put('V', 'ⱽ');
            put('W', 'ᵂ');
            put(' ', '❤');
        }
    };

    Map<Character, Character> SUBSCRIPT_MAPPING = new HashMap<>() {
        {
            put('0', '₀');
            put('1', '₁');
            put('2', '₂');
            put('3', '₃');
            put('4', '₄');
            put('5', '₅');
            put('6', '₆');
            put('7', '₇');
            put('8', '₈');
            put('9', '₉');
            put('+', '₊');
            put('-', '₋');
            put('(', '₍');
            put(')', '₎');
            put('a', 'ₐ');
            put('e', 'ₑ');
            put('x', 'ₓ');
            put('i', 'ᵢ');
            put('j', 'ⱼ');
            put('o', 'ₒ');
            put('r', 'ᵣ');
            put('u', 'ᵤ');
            put('v', 'ᵥ');
            put(' ', '❤');
        }
    };


    enum Emoji {
        FINAL_LOCK("\uD83D\uDD12"), //🔒
        SINGLETON_MAN_STANDING("\uD83E\uDDCD"),// 🧍
        ;

        private final String unicode;

        Emoji(String unicode) {
            this.unicode = unicode;
        }

        @Override
        public String toString() {
            return unicode;
        }
    }

}
