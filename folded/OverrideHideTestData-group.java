package data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("ALL")
public class OverrideHideTestData {

    public static void main(String[] args) {
        Runnable runner = new Runnable() {
            [0:"@Override"][0:"\n            "]public void run() {
                System.out.println("Running in anonymous class");
            }
        };
        runner.run();

        List<Circle> people = new ArrayList<>();
        people.sort(new Comparator<Circle>() {
            [1:"@Override"][1:"\n            "]public int compare(Circle c1, Circle c2) {
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
        [2:"@Override"][2:"\n        "]public void makeSound() {
            System.out.println("Dog barks");
        }

        [3:"@Override"][3:"\n        "]public void eat() {
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

        [4:"@Override"][4:"\n        "]public double calculateArea() {
            return Math.PI * radius * radius;
        }

        [5:"@Override"][5:"\n        "]public double calculatePerimeter() {
            return 2 * Math.PI * radius;
        }

        [6:"@Override"][6:"\n        "]public String toString() {
            return "Circle with radius: " + radius;
        }

        [7:"@Override"][7:"\n        "]public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Circle circle = (Circle) obj;
            return Double.compare(circle.radius, radius) == 0;
        }

        [8:"@Override"][8:"\n        "]public int hashCode() {
            return Double.hashCode(radius);
        }
    }


}

