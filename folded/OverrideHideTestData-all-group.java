package data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("ALL")
public class OverrideHideTestData {

    public static void main(String[] args) {
        [0:"Runnable"] runner = new Runnable() {
            [6:"@Override"][6:"\n            "]public void run() {[1:"\n                "][2:"System.out."]println([3:"\"Running in anonymous class\""])[1:";"][1:"\n            "]}
        };
        runner.run();

        [9:"List<Circle>"] people = [10:"new ArrayList<>()"];
        people.sort(new Comparator<Circle>() {
            [12:"@Override"][12:"\n            "]public int compare(Circle c1, Circle c2) {[11:"\n                "][11:"return"][11:" "]Double.compare(c1.calculatePerimeter(), c2.calculateArea())[11:";"][11:"\n            "]}
        });
    }
    class Animal {
        public void makeSound() {[23:"\n            "][24:"System.out."]println([25:"\"Animal makes a sound\""])[23:";"][23:"\n        "]}

        public void eat() {[32:"\n            "][33:"System.out."]println([34:"\"Animal eats food\""])[32:";"][32:"\n        "]}
    }

    class Dog extends Anima[41:"l"] {
        [48:"@Override"][48:"\n        "]public void makeSound() {[43:"\n            "][44:"System.out."]println([45:"\"Dog barks\""])[43:";"][43:"\n        "][42:"}"]

        [59:"@Override"][59:"\n        "]public void eat() {[54:"\n            "][55:"System.out."]println([56:"\"Dog eats kibble\""])[54:";"][54:"\n        "][53:"}"]
    }

    interface Shape {
        double calculateArea();

        double calculatePerimeter();
    }

    [65:"c"]lass Circle implements Shap[64:"e"] {
        private double radius;[65:"\n\n        "][65:"public Circle(double radius) {\n            this.radius = radius;\n        }"]

        [73:"@Override"][73:"\n        "]public double calculateArea() {[72:"\n            "][72:"return"][72:" "]Math.PI * radius * radius[72:";"][72:"\n        "][71:"}"]

        [76:"@Override"][76:"\n        "]public double calculatePerimeter() {[75:"\n            "][75:"return"][75:" "]2 * Math.PI * radius[75:";"][75:"\n        "][74:"}"][65:"\n\n        "][78:"@Override"][78:"\n        "]public String toString() {[77:"\n            "][77:"return"][77:" "]"Circle with radius: [79:"\" + "]radius[77:";"][77:"\n        "]}[65:"\n\n        "][81:"@Override"][81:"\n        "]public boolean equals(Object obj) {
            if [82:"("]this == obj[82:")"] return true;
            if [83:"("]obj == null || [88:"getClass()"] != obj.[89:"getClass()"][83:")"] return false;
            [84:"Circle"] circle = [85:"(Circle) "]obj;
            return Double.compare(circle.radius, radius) == 0;
        }[65:"\n\n        "][93:"@Override"][93:"\n        "]public int hashCode() {[92:"\n            "][92:"return"][92:" "]Double.hashCode(radius)[92:";"][92:"\n        "]}
    }


}

