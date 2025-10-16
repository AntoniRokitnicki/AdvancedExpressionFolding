package data;

[0:"p"]ublic class GetterSetterTestData {
    public static void main(String[] args) {
        [1:"GetterSetterTestData"] d = new GetterSetterTestData();
        d.[2:"setParent("]d[2:")"];
        d.[3:"setName("]"Hello"[3:")"];
        d.[5:"getParent()"].[4:"setName("]"Pum!"[4:")"];
        [6:"System.out."]println(d.[8:"getParent()"].[7:"getName()"]);
    }

    private GetterSetterTestData parent;
    private String name;[0:"\n\n    "][0:"private void setParent(GetterSetterTestData parent) {\n        this.parent = parent;\n    }"][0:"\n\n    "][0:"private GetterSetterTestData getParent() {\n        return parent;\n    }"][0:"\n\n    "][0:"private String getName() {\n        return name;\n    }"][0:"\n\n    "][0:"private void setName(String name) {\n        this.name = name;\n    }"]
}
