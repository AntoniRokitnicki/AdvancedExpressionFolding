package data;

import java.util.*;
import java.util.stream.Collectors;

public class ConcatenationTestData {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);
        list += "one";
        list -= "one";
        System.out.println(list.add("two"));
        System.out.println(list.remove("two"));
        List<String> singleton = Collections.emptyList();
        list += singleton;
        list -= singleton;
        list += args;
        Set<String> set = new HashSet<>();
        set += "three";
        set -= "three";
        System.out.println(set);
        Set<String> copyOfSet = new HashSet<>();
        set += copyOfSet;
        System.out.println(copyOfSet);
        List<String> streamToList = args*.toUpperCase().toList();
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

        long count = streamToList.distinct().count();
        System.out.println(count);
    }
}
