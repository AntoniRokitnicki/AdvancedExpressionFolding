# Final Emoji (State field: finalEmoji)

### Final Emoji
Replaces final modifiers with lock emoji markers.

#### Example: FinalEmojiTestData

examples/data/FinalEmojiTestData.java:
```java
    public final String m() {
        final String s = "1";
        final var s2 = "2";
// ...
        void main(final String arg, final int i, final Object o, Data data);
```

folded/FinalEmojiTestData-folded.java:
```java
    public 🔒 String m() {
        🔒 String s = "1";
        🔒 var s2 = "2";
// ...
        void main(🔒 String arg, 🔒 int i, 🔒 Object o, Data data);
```

Highlights FinalEmojiTestData with final emoji.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `finalEmoji`
Related features: (none)
---

#### Folding catalogue

##### FinalEmojiTestData mappings
| Before | After |
| --- | --- |
| `public final String m() {` | `public 🔒 String m() {` |
| `final String s = "1";` | `🔒 String s = "1";` |
| `final var s2 = "2";` | `🔒 var s2 = "2";` |
| `void main(final String arg, final int i, final Object o, Data data);` | `void main(🔒 String arg, 🔒 int i, 🔒 Object o, Data data);` |
| `final static class Data {` | `🔒 static class Data {` |
| `final public record UserDataRecord(String username, boolean active, String userIdentifier) {` | `🔒 public record UserDataRecord(String username, boolean active, String userIdentifier) {` |
| `final void main(final String arg, final int i, final Object o, Data data) {` | `🔒 void main(🔒 String arg, 🔒 int i, 🔒 Object o, Data data) {` |
