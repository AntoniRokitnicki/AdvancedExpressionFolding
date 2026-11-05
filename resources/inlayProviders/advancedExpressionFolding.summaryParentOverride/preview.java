interface Shape {
    double area();
}

interface Named {
}

class Circle implements Shape/*<# (1-area) #>*/, Named/*<# (nothing overridden) #>*/ {
    public double area() {
        return 0;
    }
}
