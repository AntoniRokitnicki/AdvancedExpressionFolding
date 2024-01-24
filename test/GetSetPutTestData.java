import java.util.*;

public class GetSetPutTestData {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("one", "two");
        list.set(1,"three" );
        System.out.println(list.get(list.size() - 1));
        System.out.println(args[args.length - 1]);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        System.out.println(map.get("two"));
        List<String> singleton = java.util.Collections.singletonList("one");
        System.out.println(singleton);
        List<String> asList = Arrays.asList("one", "two");
        System.out.println(asList);
        List<String> copy = new ArrayList<>(Arrays.asList("one", "two"));
        System.out.println(copy);
        List<String> unmodifiable = Collections.unmodifiableList(Arrays.asList("one", "two"));
        System.out.println(unmodifiable);
        Set<String> set = new HashSet<String>() {
            {
                add("one");
                add("two");
            }
        };
        System.out.println(set);
        Set<String> copyOfSet = Collections.unmodifiableSet(new HashSet<String>() {
            {
                add("one");
                add("two");
            }
        });
        System.out.println(copyOfSet);
        String[] strings = new String[] {"one", "two"};
        System.out.println(Arrays.toString(strings));
    }
}
