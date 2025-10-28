package data;

public class GetterSetterTestData {
    public static void main(String[] args) {
        GetterSetterTestData d = new GetterSetterTestData();
        d.parent = d;
        d.name = "Hello";
        d.parent.name = "Pum!";
        System.out.println(d.parent.name);
        java.util.Locale locale = java.util.Locale.US;
        locale.iso3Country;
        locale.iso3Language;
        AcronymGetter obj = new AcronymGetter();
        obj.urlValue;
        obj.htmlTag;
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
