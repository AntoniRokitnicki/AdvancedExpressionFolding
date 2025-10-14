[video](https://www.youtube.com/watch?v=zvpvhn7ISAw)


![Null-safe if folding showcased on chained access](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/56aa2dbb-0aa1-4143-a296-801ffb0668cd)


## example Structural Searches

`$Instance$ != null && $Instance$.$MethodCall$() != null`

![Structural search template for null-safe guard with method call](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/ce93188e-72bf-4a5b-8bff-cc6fb8fb3c76)


`$Instance$ != null && $Instance$.$MethodCall$()`

![Structural search template for null-check followed by usage](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/3eb6192c-b69e-4841-b28f-8edae3a95636)


## example

[example file](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/master/examples/data/IfNullSafeData.java)

[folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/master/folded/IfNullSafeData-folded.java)