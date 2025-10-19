package data;

import <fold text='...' expand='false'>java.util.ArrayList;
import java.util.Comparator;
import java.util.List;</fold>

@SuppressWarnings("ALL")
public class OverrideHideTestData {

    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>Runnable</fold> runner = <fold text='run() → { ' expand='false'>new Runnable() {
            <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
            </fold>public void run() {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Running in anonymous class"' expand='true'>"Running in anonymous class"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}
        }</fold>;
        runner.run();

        <fold text='val' expand='false'>List<Circle></fold> people = <fold text='[]' expand='false'>new ArrayList<>()</fold>;
        people.sort(<fold text='compare(c1, c2) → {' expand='false'>new Comparator<Circle>() {
            <fold text='' expand='true'>@Override<fold text='' expand='true'></fold>
            </fold>public int compare(Circle c1, Circle c2) {<fold text=' ' expand='true'></fold>
                </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Double.compare(c1.calculatePerimeter(), c2.calculateArea())<fold text='' expand='true'>;</fold>
        <fold text='}' expand='false'>    }
        }</fold>);
    }</fold>
    class Animal <fold text='{...}' expand='true'>{
        public void makeSound()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Animal makes a sound"' expand='true'>"Animal makes a sound"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public void eat()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='false'></fold>System.out.</fold>println(<fold text='"Animal eats food"' expand='true'>"Animal eats food"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    class Dog extends Anima<fold text='l(2-makeSound, eat)' expand='true'>l</fold> <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public void makeSound()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Dog barks"' expand='true'>"Dog barks"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        <fold text='} // overrides from Animal' expand='true'></fold>}</fold></fold>

        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public void eat()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Dog eats kibble"' expand='true'>"Dog eats kibble"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold><fold text='} // overrides from Animal' expand='true'>}</fold></fold>
    }</fold>

    interface Shape <fold text='{...}' expand='true'>{
        double calculateArea();

        double calculatePerimeter();
    }</fold>

    <fold text='@AllArgsConstructor @ToString @EqualsAndHashCode c' expand='false'>c</fold>lass Circle implements Shap<fold text='e(2-calculateArea, calculatePerimeter)' expand='true'>e</fold> <fold text='{...}' expand='true'>{
        private double radius;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public Circle(double radius)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.radius = <fold text='<<' expand='true'>radius</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public double calculateArea()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Math.PI * radius * radius<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        <fold text='} // overrides from Shape' expand='true'></fold>}</fold></fold>

        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public double calculatePerimeter()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>2 * Math.PI * radius<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold><fold text='} // overrides from Shape' expand='true'>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public String toString()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>"Circle with radius: <fold text='$' expand='false'>" + </fold>radius<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public boolean equals(Object obj) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>this == obj<fold text='' expand='false'>)</fold> return true;
            if <fold text='' expand='false'>(</fold>obj == null || <fold text='class' expand='false'>getClass()</fold> != obj.<fold text='class' expand='false'>getClass()</fold><fold text='' expand='false'>)</fold> return false;
            <fold text='val' expand='false'>Circle</fold> circle = <fold text='' expand='false'>(Circle) </fold>obj;
            return Double.compare(circle.radius, radius) == 0;
        }<fold text='' expand='false'></fold></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public int hashCode()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Double.hashCode(radius)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>


}

