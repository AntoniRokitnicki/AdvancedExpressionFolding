---
title: Interface Extension Properties
slug: /features/interface-extension-properties
sidebar_label: Interface Extension Properties
description: Folds interface getters and setters into Kotlin extension properties.
---

:::info Toggle summary
- **State key:** `interfaceExtensionProperties`
- **Default:** On
- **Controlled by:** `interfaceExtensionProperties`
:::

## Interface Extension Properties
Folds interface getters and setters into Kotlin extension properties.

### Example: InterfaceExtensionPropertiesTestData

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
