package data<fold text='' expand='false'>;</fold>

import java.util.Arrays<fold text='' expand='false'>;</fold>

public class ControlFlowSingleStatementTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>args.length > 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println(Arrays.asList(args))<fold text='' expand='false'>;</fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>args.length == 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println("...")<fold text='' expand='false'>;</fold>
        }</fold> else <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println("...")<fold text='' expand='false'>;</fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>args.length == 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println("...")<fold text='' expand='false'>;</fold>
        System.out.println("...")<fold text='' expand='false'>;</fold>
        }</fold> else <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println("...")<fold text='' expand='false'>;</fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>args.length > 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println("...")<fold text='' expand='false'>;</fold>
        }</fold> else <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println("...")<fold text='' expand='false'>;</fold>
        System.out.println("...")<fold text='' expand='false'>;</fold>
        }</fold>
        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>String</fold> arg : args<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println(arg)<fold text='' expand='false'>;</fold>
        }</fold>
        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>int</fold> i<fold text=' : [' expand='false'> = </fold>0<fold text=', ' expand='false'><fold text='' expand='false'>;</fold> i < </fold>args.length<fold text='' expand='false'><fold text=')' expand='false'>;</fold> i++</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println(args[i])<fold text='' expand='false'>;</fold>
        }</fold>
        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>int</fold> i<fold text=' : [' expand='false'> = </fold>0<fold text='' expand='false'><fold text=', ' expand='false'>;</fold> i < </fold>args.length<fold text='' expand='false'><fold text=')' expand='false'>;</fold> i++</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println(i)<fold text='' expand='false'>;</fold>
        System.out.println(args[i])<fold text='' expand='false'>;</fold>
        }</fold>
        while <fold text='' expand='false'>(</fold>true<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
        break<fold text='' expand='false'>;</fold>
        }</fold>
        while <fold text='' expand='false'>(</fold>true<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println("...")<fold text='' expand='false'>;</fold>
        break<fold text='' expand='false'>;</fold>
        }</fold>
        do <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
        break<fold text='' expand='false'>;</fold>
        }</fold> while <fold text='' expand='false'>(</fold>true<fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        do <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println("...")<fold text='' expand='false'>;</fold>
        break<fold text='' expand='false'>;</fold>
        }</fold> while <fold text='' expand='false'>(</fold>true<fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        try <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println("...")<fold text='' expand='false'>;</fold>
        }</fold> catch <fold text='' expand='false'>(</fold>Exception e<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println("...")<fold text='' expand='false'>;</fold>
        }</fold>
        try <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println("...")<fold text='' expand='false'>;</fold>
        System.out.println("...")<fold text='' expand='false'>;</fold>
        }</fold> catch <fold text='' expand='false'>(</fold>Exception e<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println("...")<fold text='' expand='false'>;</fold>
        }</fold>
        try <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println("...")<fold text='' expand='false'>;</fold>
        }</fold> catch <fold text='' expand='false'>(</fold>Exception e<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println("...")<fold text='' expand='false'>;</fold>
        System.out.println("...")<fold text='' expand='false'>;</fold>
        }</fold>
    }</fold>
}
