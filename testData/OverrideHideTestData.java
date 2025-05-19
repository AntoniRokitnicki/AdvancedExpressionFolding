package data;

import <fold text='...' expand='false'>java.util.ArrayList;
import java.util.Comparator;
import java.util.List;</fold>

@SuppressWarnings("ALL")
public class OverrideHideTestData {

    public static void main(String[] args) <fold text='{...}' expand='true'>{
        Runnable runner = <fold text='run() → { ' expand='false'>new Runnable() {
            <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
            </fold>public void run() {
                </fold>System.out.println("Running in anonymous class");<fold text=' }' expand='false'>
            }
        }</fold>;
        runner.run();

        List<Circle> people = new ArrayList<>();
        people.sort(<fold text='compare(c1, c2) → {' expand='false'>new Comparator<Circle>() {
            <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
            </fold>public int compare(Circle c1, Circle c2) {</fold>
                return Double.compare(c1.calculatePerimeter(), c2.calculateArea());
        <fold text='}' expand='false'>    }
        }</fold>);
    }</fold>
    class Animal <fold text='{...}' expand='true'>{
        public void makeSound()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Animal makes a sound");<fold text=' }' expand='false'>
        }</fold>

        public void eat()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Animal eats food");<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    class Dog extends Animal <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public void makeSound()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Dog barks");<fold text=' }' expand='false'>
        }</fold>

        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public void eat()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Dog eats kibble");<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    interface Shape <fold text='{...}' expand='true'>{
        double calculateArea();

        double calculatePerimeter();
    }</fold>

    class Circle implements Shape <fold text='{...}' expand='true'>{
        private double radius;

        public Circle(double radius)<fold text=' { ' expand='false'> {
            </fold>this.radius = radius;<fold text=' }' expand='false'>
        }</fold>

        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public double calculateArea()<fold text=' { ' expand='false'> {
            </fold>return Math.PI * radius * radius;<fold text=' }' expand='false'>
        }</fold>

        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public double calculatePerimeter()<fold text=' { ' expand='false'> {
            </fold>return 2 * Math.PI * radius;<fold text=' }' expand='false'>
        }</fold>

        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public String toString()<fold text=' { ' expand='false'> {
            </fold>return "Circle with radius: " + radius;<fold text=' }' expand='false'>
        }</fold>

        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public boolean equals(Object obj) <fold text='{...}' expand='true'>{
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Circle circle = (Circle) obj;
            return Double.compare(circle.radius, radius) == 0;
        }</fold>

        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public int hashCode()<fold text=' { ' expand='false'> {
            </fold>return Double.hashCode(radius);<fold text=' }' expand='false'>
        }</fold>
    }</fold>


}

