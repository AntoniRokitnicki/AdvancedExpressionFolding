package data;

import java.util.*;
import java.util.stream.Collectors;

public class ConcatenationTestData {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);
        list.add("one");
        list.remove("one");
        System.out.println(list.add("two"));
        System.out.println(list.remove("two"));
        List<String> singleton = Collections.emptyList();
        list.addAll(singleton);
        list.removeAll(singleton);
        Collections.addAll(list, args);
        Set<String> set = new HashSet<>();
        set.add("three");
        set.remove("three");
        System.out.println(set);
        Set<String> copyOfSet = new HashSet<>();
        set.addAll(copyOfSet);
        System.out.println(copyOfSet);
        List<String> streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);

        streamToList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);
        streamToList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(streamToList);

        long count = streamToList.stream().distinct().count();
        System.out.println(count);
    }
}
