package data;

import <fold text='...' expand='false'>java.util.ArrayList;
import java.util.List;</fold>

public class ForRangeTestData {
        public static void main(String[] args) <fold text='{...}' expand='true'>{
                for <fold text='val (' expand='false'>(<fold text='val' expand='false'>int</fold> </fold>i = 0; i < args.length; i++) <fold text='{...}' expand='true'>{
                        <fold text='val' expand='false'>String</fold> arg<fold text=' : ' expand='false'> = </fold>args<fold text=') {
' expand='false'>
                                [i];</fold>
                <fold text='' expand='false'>System.out.</fold>println(arg);
                <fold text='' expand='false'>System.out.</fold>println(i);
        }</fold>
                for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>int</fold> i = 0; i < args.length; i++) <fold text='{...}' expand='true'>{
                        <fold text='val' expand='false'>String</fold> arg<fold text=' : ' expand='false'> = </fold>args<fold text=') {
' expand='false'>
                                [i];</fold>
                <fold text='' expand='false'>System.out.</fold>println(arg);
        }</fold>
                for <fold text='' expand='false'>(<fold text='val' expand='false'></fold>int</fold> i<fold text=' : [' expand='false'> = </fold>0<fold text=', ' expand='false'>; i < </fold>args.length<fold text=')' expand='false'>; i++</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                        <fold text='' expand='false'>System.out.</fold>println(i);
        }</fold>
                for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>int</fold> i<fold text=' : [' expand='false'> = </fold>0<fold text=', ' expand='false'>; i <= </fold>args.length - 1<fold text=']' expand='false'>; i++</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                        <fold text='' expand='false'>System.out.</fold>println(i);
        }</fold>
                        <fold text='val' expand='false'>List<String></fold> list = <fold text='[]' expand='false'>new ArrayList<>()</fold>;
                for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>int</fold> i = 0; i < list.size(); i++) <fold text='{...}' expand='true'>{
                        <fold text='val' expand='false'>String</fold> a<fold text=' : ' expand='false'> = </fold>list<fold text='[' expand='false'><fold text=') {
' expand='false'>
                                .get(</fold>i<fold text=']' expand='false'>)</fold>;</fold>
                <fold text='' expand='false'>System.out.</fold>println(a);
        }</fold>
                for <fold text='' expand='false'>(<fold text='val' expand='false'></fold>int</fold> i = 0; i < list.size(); i++)
        <fold text='{...}' expand='true'>{
                        <fold text='val' expand='false'>String</fold> b<fold text=' : ' expand='false'> = </fold>list<fold text='[' expand='false'><fold text=')
        {
' expand='false'>
                                .get(</fold>i<fold text=']' expand='false'>)</fold>;</fold>
                <fold text='' expand='false'>System.out.</fold>println(b);
        }</fold>
                for <fold text='val (' expand='false'>(<fold text='val' expand='false'>int</fold> </fold>i = 0; i < list.size(); i++) <fold text='{...}' expand='true'>{
                        <fold text='val' expand='false'>String</fold> a<fold text=' : ' expand='false'> = </fold>list<fold text='[' expand='false'><fold text=') {
' expand='false'>
                                .get(</fold>i<fold text=']' expand='false'>)</fold>;</fold>
                <fold text='' expand='false'>System.out.</fold>println(a);
                <fold text='' expand='false'>System.out.</fold>println(i);
        }</fold>
                if <fold text='' expand='false'>(</fold>args.length<fold text=' in (' expand='false'> > </fold>0<fold text=', ' expand='false'> && args.length < </fold>2<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                        <fold text='' expand='false'>System.out.</fold>println(args.length);
        }</fold>
        }</fold>
}