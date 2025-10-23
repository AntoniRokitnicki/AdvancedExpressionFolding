package data;

interface Visitor {
    void visit(Shape shape);
    void visit(Circle circle);
    void visit(Rectangle rectangle);
}

abstract class Shape {
    public abstract void accept(Visitor visitor);
}

class Circle extends Shape {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Rectangle extends Shape {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
