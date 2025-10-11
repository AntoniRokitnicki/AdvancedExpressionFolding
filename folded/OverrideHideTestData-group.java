package data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("ALL")
public class OverrideHideTestData {

    public static void main(String[] args) {
        Runnable runner = new Runnable() {
            [0:"@Override"]{[0:"@Override"]}[0:"
            "]{[0:"
            "]}public void run() {
                System.out.println("Running in anonymous class");
            }
        };
        runner.run();

        List<Circle> people = new ArrayList<>();
        people.sort(new Comparator<Circle>() {
            [0:"@Override"]{[0:"@Override"]}[0:"
            "]{[0:"
            "]}public int compare(Circle c1, Circle c2) {
                return Double.compare(c1.calculatePerimeter(), c2.calculateArea());
            }
        });
    }
    class Animal {
        public void makeSound() {
            System.out.println("Animal makes a sound");
        }

        public void eat() {
            System.out.println("Animal eats food");
        }
    }

    class Dog extends Animal {
        [0:"@Override"][0:"
        "]public void makeSound() {
            System.out.println("Dog barks");
        }

        [0:"@Override"][0:"
        "]public void eat() {
            System.out.println("Dog eats kibble");
        }
    }

    interface Shape {
        double calculateArea();

        double calculatePerimeter();
    }

    class Circle implements Shape {
        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        [0:"@Override"][0:"
        "]public double calculateArea() {
            return Math.PI * radius * radius;
        }

        [0:"@Override"][0:"
        "]public double calculatePerimeter() {
            return 2 * Math.PI * radius;
        }

        [0:"@Override"][0:"
        "]public String toString() {
            return "Circle with radius: " + radius;
        }

        [0:"@Override"][0:"
        "]public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Circle circle = (Circle) obj;
            return Double.compare(circle.radius, radius) == 0;
        }

        [0:"@Override"][0:"
        "]public int hashCode() {
            return Double.hashCode(radius);
        }
    }


}

