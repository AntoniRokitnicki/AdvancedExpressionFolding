---
title: Interface Extension Properties (State field: interfaceExtensionProperties)
option: interfaceExtensionProperties
source: wiki-clone/docs/features/interfaceExtensionProperties.md
---
# Interface Extension Properties (State field: interfaceExtensionProperties)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiMethodExt.kt:21` – `if (interfaceExtensionProperties && method.body == null) {`
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiMethodExt.kt:67` – `if (interfaceExtensionProperties && InterfacePropertiesExt.ignoreFoldingParameter(parameter)) {`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/InterfacePropertiesExt.kt:23` – `val group = group("interfaceExtensionProperties")`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:87` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/LombokState.kt:15` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:238` – `state::interfaceExtensionProperties,`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:41` – `::interfaceExtensionPropertiesTestData to 8`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/LombokFoldingTest.kt:23` – `fun interfaceExtensionPropertiesTestData() = testCase.runFoldingTest(`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/LombokFoldingTest.kt:24` – `foldingState()::interfaceExtensionProperties,`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Interface Extension Properties (State field: interfaceExtensionProperties)

### Interface Extension Properties
Folds interface getters and setters into Kotlin extension properties.

#### Example: InterfaceExtensionPropertiesTestData

examples/data/InterfaceExtensionPropertiesTestData.java:
```java
        String getName();
        void setName(String name);

        int getAge();
        void setAge(int age);
// ...
        public String getName();
        public void setName(String name);

        public int getAge();
        public void setAge(int age);
```

folded/InterfaceExtensionPropertiesTestData-folded.java:
```java
       @Getter String name;
       @Setter String name;

       @Getter int age;
       @Setter int age;
// ...
       @Getter public String name;
       @Setter public String name;

       @Getter public int age;
       @Setter public int age;
```

Highlights InterfaceExtensionPropertiesTestData with interface extension properties.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `interfaceExtensionProperties`
Related features: (none)

---
### Folding catalogue

#### InterfaceExtensionPropertiesTestData

##### Scenario 1

**Before**
```java
        String getName();
        void setName(String name);

        int getAge();
        void setAge(int age);
```

**After**
```java
       @Getter String name;
       @Setter String name;

       @Getter int age;
       @Setter int age;
```


##### Scenario 2

**Before**
```java
        public String getName();
        public void setName(String name);

        public int getAge();
        public void setAge(int age);
```

**After**
```java
       @Getter public String name;
       @Setter public String name;

       @Getter public int age;
       @Setter public int age;
```


##### Scenario 3

**Before**
```java
            int getAge();
```

**After**
```java
           @Getter int age;
```


##### Scenario 4

**Before**
```java
        String getName();
        int getAge();
```

**After**
```java
       @Getter String name;
       @Getter int age;
```


##### Scenario 5

**Before**
```java
        void setName(String name);
        void setAge(int age);
```

**After**
```java
       @Setter String name;
       @Setter int age;
```


##### Scenario 6

**Before**
```java
        String getName();
        void setName(String name);

        int getAge();
```

**After**
```java
       @Getter String name;
       @Setter String name;

       @Getter int age;
```


##### Scenario 7

**Before**
```java
        String getName();
        void setName(String name);

        void setAge(int age);
```

**After**
```java
       @Getter String name;
       @Setter String name;

       @Setter int age;
```


##### Scenario 8

**Before**
```java
        String getName();
```

**After**
```java
       @Getter String name;
```


##### Scenario 9

**Before**
```java
        void setName(String name);
```

**After**
```java
       @Setter String name;
```


##### Scenario 10

**Before**
```java
        String getName();
        void setName(String name);
```

**After**
```java
       @Getter String name;
       @Setter String name;
```


##### Scenario 11

**Before**
```java
            String getName();

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
            void setName(String name);
```

**After**
```java
           @Getter String name;

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           @Setter String name;
```


##### Scenario 12

**Before**
```java
            void setAge(int age);
```

**After**
```java
           @Setter int age;
```


##### Scenario 13

**Before**
```java
            String getName();
```

**After**
```java
           @Getter String name;
```


##### Scenario 14

**Before**
```java
            void setName(String name);
```

**After**
```java
           @Setter String name;
```


##### Scenario 15

**Before**
```java
        //@FindBy String tag(String name);
        String findTagByName(String name);

        String findTagByAge(byte name);

        String findNameByName(String name);
```

**After**
```java
       @FindBy //@FindBy String tag(String name);
        String tag(String name);

       @FindBy String tag(byte age);

       @FindBy String name(String name);
```


##### Scenario 16

**Before**
```java
        @Nullable
        Integer getAge();
        void setAge(@Nullable int age);
        @Nullable
        String getName();
        void setName(@Nullable String name);
```

**After**
```java
       @Getter Integer? age;
       @Setter int? age;
       @Getter String? name;
       @Setter String? name;
```


##### Scenario 17

**Before**
```java
        @NotNull() String getName();
        void setName(@NotNull String name);
        int getAge();
```

**After**
```java
       @Getter String!! name;
       @Setter String!! name;
       @Getter int age;
```
