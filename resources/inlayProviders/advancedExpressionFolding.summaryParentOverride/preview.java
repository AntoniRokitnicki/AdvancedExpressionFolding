interface Shape {
    double area();
}

interface Named {
}

class Circle implements Shape/*<# (1-area) #>*/, Named/*<# (nothing overridden) #>*/ {
    @Override
    public double area() {/*<# // overrides from Shape #>*/
        double radius = 1;
        return Math.PI * radius * radius;
    }
}
