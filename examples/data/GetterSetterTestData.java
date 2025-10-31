package data;

public class GetterSetterTestData {
    public static void main(String[] args) {
        GetterSetterTestData d = new GetterSetterTestData();
        d.setParent(d);
        d.setName("Hello");
        d.getParent().setName("Pum!");
        System.out.println(d.getParent().getName());
        java.util.Locale locale = java.util.Locale.US;
        locale.getISO3Country();
        locale.getISO3Language();
        AcronymGetter obj = new AcronymGetter();
        obj.getURLValue();
        obj.getHTMLTag();
    }

    private GetterSetterTestData parent;
    private String name;

    private void setParent(GetterSetterTestData parent) {
        this.parent = parent;
    }

    private GetterSetterTestData getParent() {
        return parent;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private static class AcronymGetter {
        private String urlValue;
        private String htmlTag;

        private String getURLValue() {
            return urlValue;
        }

        private String getHTMLTag() {
            return htmlTag;
        }
    }
}
