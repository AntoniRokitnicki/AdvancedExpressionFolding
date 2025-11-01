# LitmusKt Sample

This standalone Kotlin Multiplatform project shows how to describe and execute a LitmusKt test on both the JVM and Kotlin/Native.

## Running the sample

From the repository root run:

```bash
./gradlew -p examples/litmuskt-sample runJvm
```

To build and execute the native binary (Linux x64):

```bash
./gradlew -p examples/litmuskt-sample runDebugExecutableLinuxX64
```

Both entry points print the outcome table produced by LitmusKt's message passing litmus test.
