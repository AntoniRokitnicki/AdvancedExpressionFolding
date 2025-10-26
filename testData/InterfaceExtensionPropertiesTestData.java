package data;

import <fold text='...' expand='false'>org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;</fold>

@SuppressWarnings("ALL")
public class InterfaceExtensionPropertiesTestData {

    public interface User <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

       <fold text='@Getter ' expand='true'> </fold>int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='int' expand='true'>void</fold> <fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(int age)</fold>;
    }</fold>

    public interface PublicUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>public String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold>public <fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

       <fold text='@Getter ' expand='true'> </fold>public int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold>public <fold text='int' expand='true'>void</fold> <fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(int age)</fold>;
    }</fold>

    interface Ignored <fold text='{...}' expand='true'>{
        public interface DefaultUser <fold text='{...}' expand='true'>{
            default String getName()<fold text=' { ' expand='false'> {
                </fold>return "Unknown User";<fold text=' }' expand='false'>
            }</fold>
            default void setName(String name) <fold text='{}' expand='true'>{
            }</fold>
           <fold text='@Getter ' expand='true'> </fold>int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
        }</fold>

        public interface StaticUser <fold text='{...}' expand='true'>{
            static String getName()<fold text=' { ' expand='false'> {
                </fold>return "Static User";<fold text=' }' expand='false'>
            }</fold>
           <fold text='@Getter ' expand='true'> </fold>int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
            static void setName(String name) <fold text='{}' expand='true'>{
            }</fold>
        }</fold>
    }</fold>

    public interface ReadOnlyUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Getter ' expand='true'> </fold>int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
    }</fold>

    public interface WriteOnlyUser <fold text='{...}' expand='true'>{
       <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='int' expand='true'>void</fold> <fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(int age)</fold>;
    }</fold>

    public interface MixedAccessUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

       <fold text='@Getter ' expand='true'> </fold>int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
    }</fold>

    public interface MixedAccessUser2 <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

       <fold text='@Setter ' expand='true'> </fold><fold text='int' expand='true'>void</fold> <fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(int age)</fold>;
    }</fold>

    public interface SingleGetterUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
    }</fold>

    public interface SingleSetterUser <fold text='{...}' expand='true'>{
       <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;
    }</fold>

    public interface SinglePropertyUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;
    }</fold>

    class Javadoced <fold text='{...}' expand='true'>{

public interface User <fold text='{...}' expand='true'>{

<fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

<fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

<fold text='@Getter ' expand='true'> </fold>int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;

<fold text='@Setter ' expand='true'> </fold><fold text='int' expand='true'>void</fold> <fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(int age)</fold>;
        }</fold>

public interface ReadOnlyUser <fold text='{...}' expand='true'>{

<fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

<fold text='@Getter ' expand='true'> </fold>int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
        }</fold>

public interface WriteOnlyUser <fold text='{...}' expand='true'>{

<fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

<fold text='@Setter ' expand='true'> </fold><fold text='int' expand='true'>void</fold> <fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(int age)</fold>;
        }</fold>

public interface MixedAccessUser <fold text='{...}' expand='true'>{

<fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

<fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

<fold text='@Getter ' expand='true'> </fold>int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
        }</fold>

public interface MixedAccessUser2 <fold text='{...}' expand='true'>{

<fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

<fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

<fold text='@Setter ' expand='true'> </fold><fold text='int' expand='true'>void</fold> <fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(int age)</fold>;
        }</fold>

public interface SingleGetterUser <fold text='{...}' expand='true'>{

<fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
        }</fold>

public interface SingleSetterUser <fold text='{...}' expand='true'>{

<fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;
        }</fold>

public interface SinglePropertyUser <fold text='{...}' expand='true'>{

<fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

<fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;
        }</fold>
    }</fold>

    //TODO: nullable support
    interface Finder <fold text='{...}' expand='true'>{
       <fold text='@FindBy ' expand='true'> </fold>//@FindBy String tag(String name);
        String <fold text='t' expand='true'>findT</fold>ag<fold text='' expand='true'>ByName</fold>(String name);

       <fold text='@FindBy ' expand='true'> </fold>String <fold text='t' expand='true'>findT</fold>ag<fold text='' expand='true'>ByAge</fold>(byte <fold text='age' expand='true'>name</fold>);

       <fold text='@FindBy ' expand='true'> </fold>String <fold text='n' expand='true'>findN</fold>ame<fold text='' expand='true'>ByName</fold>(String name);
    }</fold>

    public interface NullableUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold><fold text='' expand='true'>@Nullable</fold><fold text='' expand='true'>
        </fold>Integer<fold text='? ' expand='true'> </fold><fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='int' expand='true'>void</fold><fold text='? ' expand='true'> </fold><fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(<fold text='' expand='true'>@Nullable</fold> int age)</fold>;
       <fold text='@Getter ' expand='true'> </fold><fold text='' expand='true'>@Nullable</fold><fold text='' expand='true'>
        </fold>String<fold text='? ' expand='true'> </fold><fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold><fold text='? ' expand='true'> </fold><fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(<fold text='' expand='true'>@Nullable</fold> String name)</fold>;
    }</fold>

    public interface NotNullUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold><fold text='' expand='true'>@NotNull()</fold><fold text='' expand='true'> </fold>String<fold text='!! ' expand='true'> </fold><fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold><fold text='!! ' expand='true'> </fold><fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(<fold text='' expand='true'>@NotNull</fold> String name)</fold>;
       <fold text='@Getter ' expand='true'> </fold>int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
    }</fold>

}
