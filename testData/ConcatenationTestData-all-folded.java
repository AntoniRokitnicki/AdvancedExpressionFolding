package data;

import java.util.*;
import java.util.stream.Collectors;

public class ConcatenationTestData {
    public static void main(String[] args) {
        val list = Arrays.asList(args);
        list += "one";
        list -= "one";
        System.out.println(list.add("two"));
        System.out.println(list.remove("two"));
        val singleton = Collections.emptyList();
        list += singleton;
        list -= singleton;
        list += args;
        val set = new HashSet<>();
        set += "three";
        set -= "three";
        System.out.println(set);
        val copyOfSet = new HashSet<>();
        set += copyOfSet;
        System.out.println(copyOfSet);
        var streamToList = args*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = args*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = args*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = args*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = args*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = args*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = args*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = args*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = args*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = args*.toUpperCase().toList();
        System.out.println(streamToList);

        streamToList = list.stream()*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        System.out.println(streamToList);
        streamToList = list.stream()*.toUpperCase().toList();
        System.out.println(streamToList);

        val count = streamToList.distinct().count();
        System.out.println(count);
    }
}
