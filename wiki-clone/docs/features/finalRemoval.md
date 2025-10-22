# Final Removal (State field: finalRemoval)

### Final Removal
Folds the final modifier away from non-field declarations.

#### Example: FinalRemovalTestData

examples/data/FinalRemovalTestData.java:
```java
    public final String m() {
        final String s = "1";
        final var s2 = "2";
// ...
        void main(final String arg, final int i, final Object o, Data data);
```

folded/FinalRemovalTestData-folded.java:
```java
    public  String m() {
         String s = "1";
         var s2 = "2";
// ...
        void main( String arg,  int i,  Object o, Data data);
```

Highlights FinalRemovalTestData with final removal.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `finalRemoval`
Related features: (none)
