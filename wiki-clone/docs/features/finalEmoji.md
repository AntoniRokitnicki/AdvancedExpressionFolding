# Final Emoji

## Overview

Replaces final modifiers with lock emoji markers.


## Configuration

- **Toggle ID:** `finalEmoji`
- **Default state:** Off
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

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


---
### Folding catalogue

#### FinalEmojiTestData

##### Scenario 1

**Before**
```java
    public final String m() {
        final String s = "1";
        final var s2 = "2";
```

**After**
```java
    public 🔒 String m() {
        🔒 String s = "1";
        🔒 var s2 = "2";
```


##### Scenario 2

**Before**
```java
        void main(final String arg, final int i, final Object o, Data data);
```

**After**
```java
        void main(🔒 String arg, 🔒 int i, 🔒 Object o, Data data);
```


##### Scenario 3

**Before**
```java
    final static class Data {
```

**After**
```java
    🔒 static class Data {
```


##### Scenario 4

**Before**
```java
    final public record UserDataRecord(String username, boolean active, String userIdentifier) {
        final void main(final String arg, final int i, final Object o, Data data) {
```

**After**
```java
    🔒 public record UserDataRecord(String username, boolean active, String userIdentifier) {
        🔒 void main(🔒 String arg, 🔒 int i, 🔒 Object o, Data data) {
```
