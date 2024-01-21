package com.intellij.advancedExpressionFolding;

import <fold text='...' expand='false'>java.util.Objects;
import java.util.Optional;
import java.util.function.Function;</fold>

public class OptionalTestData {

    public void main(Data data, Optional<Data> opt) <fold text='{...}' expand='true'>{
        Optional.ofNullable(data)<fold text=' ?: ' expand='false'>.orElseGet(</fold>this::orElseGetReturn<fold text='' expand='false'>)</fold>;
        Optional.ofNullable(data)<fold text=' ?: ' expand='false'>.orElseGet(</fold>() -> data.getData()<fold text='' expand='false'>)</fold>;

        var p = Optional.ofNullable(data)<fold text='?.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold>null<fold text='' expand='false'>)</fold>;
        var p2 = Optional.ofNullable(data).map(OptionalTestData::getOutsideData)<fold text='!!' expand='false'>.get()</fold>;

        var ok = Optional.ofNullable(data)<fold text='?.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='?.' expand='false'>
                .map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>
                .filter(it -> it.ok)
                .map(Function.identity())<fold text='?.' expand='false'>
                .map(</fold><fold text='ok' expand='false'>Data::isOk</fold><fold text='' expand='false'>)</fold>
                .map(it -> !it).map(it -> <fold text='{...}' expand='true'>{
                var s = it.toString();
        return s.equals("false");
                }</fold>)<fold text=' ?: ' expand='false'>.orElse(</fold>null<fold text='' expand='false'>)</fold>;

        var string = Optional.of(data.getData()).map(OptionalTestData::getOutsideData)<fold text='?.' expand='false'>.map(</fold><fold text='string' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold>data.getString()<fold text='' expand='false'>)</fold>;

        Optional.of(data).flatMap(this::ofNullable).map(data::getDataMethod)<fold text=' ?: ' expand='false'>.orElseGet(</fold>() -> getOutsideData(data)<fold text='' expand='false'>)</fold>;

        Optional.<fold text='<~>' expand='false'><Data></fold>empty()<fold text='?.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>.stream().map(Data::getData).filter(Objects::nonNull).findAny()<fold text='?.' expand='false'>.map(</fold><fold text='string' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text='!!' expand='false'>.get()</fold>;

        opt<fold text='?.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold>null<fold text='' expand='false'>)</fold>;
    }</fold>

    private Data orElseGetReturn()<fold text=' { ' expand='false'> {
        </fold>return null;<fold text=' }' expand='false'>
    }</fold>

    private Optional<Data> ofNullable(Data data)<fold text=' { ' expand='false'> {
        </fold>return Optional.empty();<fold text=' }' expand='false'>
    }</fold>

    private static Data getOutsideData(Data data)<fold text=' { ' expand='false'> {
        </fold>return null;<fold text=' }' expand='false'>
    }</fold>

    class Data <fold text='{...}' expand='true'>{
        Data data;
        boolean ok;

        String string;

        public Data getData()<fold text=' { ' expand='false'> {
                </fold>return data;<fold text=' }' expand='false'>
        }</fold>

        public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
        }</fold>

        public void setData(Data data)<fold text=' { ' expand='false'> {
                </fold>this.data = data;<fold text=' }' expand='false'>
        }</fold>

        public void setOk(boolean ok)<fold text=' { ' expand='false'> {
                </fold>this.ok = ok;<fold text=' }' expand='false'>
        }</fold>

        public String getString()<fold text=' { ' expand='false'> {
                </fold>return string;<fold text=' }' expand='false'>
        }</fold>

        public void setString(String string)<fold text=' { ' expand='false'> {
                </fold>this.string = string;<fold text=' }' expand='false'>
        }</fold>

        public Data getDataMethod(Data data)<fold text=' { ' expand='false'> {
                </fold>return data;<fold text=' }' expand='false'>
        }</fold>
    }</fold>
}
