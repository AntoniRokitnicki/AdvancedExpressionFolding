package data;

import java.util.*;
import java.util.stream.Collectors;

public class ConcatenationTestData {
    public static void main(String[] args) {
        val list = Arrays.asList(args);
        list += "one";
        list -= "one";
        println(list.add("two"));
        println(list.remove("two"));
        val singleton = Collections.emptyList();
        list += singleton;
        list -= singleton;
        list += args;
        val set = new HashSet<>();
        set += "three";
        set -= "three";
        println(set);
        val copyOfSet = new HashSet<>();
        set += copyOfSet;
        println(copyOfSet);
        var streamToList = args*.toUpperCase().toList();
        println(streamToList);
        streamToList = args*.toUpperCase().toList();
        println(streamToList);
        streamToList = args*.toUpperCase().toList();
        println(streamToList);
        streamToList = args*.toUpperCase().toList();
        println(streamToList);
        streamToList = args*.toUpperCase().toList();
        println(streamToList);
        streamToList = args*.toUpperCase().toList();
        println(streamToList);
        streamToList = args*.toUpperCase().toList();
        println(streamToList);
        streamToList = args*.toUpperCase().toList();
        println(streamToList);
        streamToList = args*.toUpperCase().toList();
        println(streamToList);
        streamToList = args*.toUpperCase().toList();
        println(streamToList);

        streamToList = list.stream()*.toUpperCase().toList();
        println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        println(streamToList);

        val count = streamToList.distinct().count();
        println(count);
    }
}
