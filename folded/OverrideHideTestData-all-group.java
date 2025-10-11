package data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("ALL")
public class OverrideHideTestData {

    public static void main(String[] args) {
        [0:"Runnable"][13:"Runnable"] runner = new Runnable() {
            [6:"@Override"][16:"@Override"][6:"\n            "][16:"\n            "]public void run() {[1:"\n                "][13:"\n                "][2:"System.out."][4:"System.out."][7:"System.out."][14:"System.out."][15:"System.out."][17:"System.out."][19:"System.out."]println([3:"\"Running in anonymous class\""][5:"\"Running in anonymous class\""][8:"\"Running in anonymous class\""][15:"\"Running in anonymous class\""][16:"\"Running in anonymous class\""][18:"\"Running in anonymous class\""][20:"\"Running in anonymous class\""])[1:";"][13:";"][1:"\n            "][13:"\n            "]}
        };
        runner.run();

        [9:"List<Circle>"][21:"List<Circle>"] people = [10:"new ArrayList<>()"][22:"new ArrayList<>()"];
        people.sort(new Comparator<Circle>() {
            [12:"@Override"][22:"@Override"][12:"\n            "][22:"\n            "]public int compare(Circle c1, Circle c2) {[11:"\n                "][22:"\n                "][11:"return"][22:"return"][11:" "][22:" "]Double.compare(c1.calculatePerimeter(), c2.calculateArea())[11:";"][22:";"][11:"\n            "][22:"\n            "]}
        });
    }
    class Animal {
        public void makeSound() {[23:"\n            "][24:"System.out."][26:"System.out."][28:"System.out."][30:"System.out."]println([25:"\"Animal makes a sound\""][27:"\"Animal makes a sound\""][29:"\"Animal makes a sound\""][31:"\"Animal makes a sound\""])[23:";"][23:"\n        "]}

        public void eat() {[32:"\n            "][33:"System.out."][35:"System.out."][37:"System.out."][39:"System.out."]println([34:"\"Animal eats food\""][36:"\"Animal eats food\""][38:"\"Animal eats food\""][40:"\"Animal eats food\""])[32:";"][32:"\n        "]}
    }

    class Dog extends Anima[41:"l"] {
        [48:"@Override"][48:"\n        "]public void makeSound() {[43:"\n            "][44:"System.out."][46:"System.out."][49:"System.out."][51:"System.out."]println([45:"\"Dog barks\""][47:"\"Dog barks\""][50:"\"Dog barks\""][52:"\"Dog barks\""])[43:";"][43:"\n        "][42:"}"]

        [59:"@Override"][59:"\n        "]public void eat() {[54:"\n            "][55:"System.out."][57:"System.out."][60:"System.out."][62:"System.out."]println([56:"\"Dog eats kibble\""][58:"\"Dog eats kibble\""][61:"\"Dog eats kibble\""][63:"\"Dog eats kibble\""])[54:";"][54:"\n        "][53:"}"]
    }

    interface Shape {
        double calculateArea();

        double calculatePerimeter();
    }

    [65:"c"]lass Circle implements Shap[64:"e"] {
        private double radius;[65:"\n\n        "][65:"public Circle(double radius) {\n            this.radius = radius;\n        }"]us"][68:"radius"][69:"radius"][70:"radius"][66:";"][66:"\n        "]}

        [73:"@Override"][73:"\n        "]public double calculateArea() {[72:"\n            "][72:"return"][72:" "]Math.PI * radius * radius[72:";"][72:"\n        "][71:"}"]

        [76:"@Override"][76:"\n        "]public double calculatePerimeter() {[75:"\n            "][75:"return"][75:" "]2 * Math.PI * radius[75:";"][75:"\n        "][74:"}"][65:"\n\n        "][78:"@Override"]rride\n        public String toString() {\n            return \"Circle with radius: \" + radius;\n        }"] radius: [79:"\" + "][79:"\" + "][80:"\" + "][80:"\" + "]radius[77:";"][79:";"][80:";"][77:"\n        "]}[65:"\n\n        "][81:"@Override"]rride\n        public boolean equals(Object obj) {\n            if (this == obj) return true;\n            if (obj == null || getClass() != obj.getClass()) return false;\n            Circle circle = (Circle) obj;\n            return Double.compare(circle.radius, radius) == 0;\n        }"]rcle"] circle = [85:"(Circle) "][91:"(Circle) "]obj;
            return Double.compare(circle.radius, radius) == 0;
        }[65:"\n\n        "][93:"@Override"]rride\n        public int hashCode() {\n            return Double.hashCode(radius);\n        }"]le.hashCode(radius)[92:";"][92:"\n        "]}
    }


}

