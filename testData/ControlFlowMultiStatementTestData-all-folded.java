package data

public class ControlFlowMultiStatementTestData {
    public static void main(String[] args) {
        if args.length > 0 
                println("...")
        println("...")
        if args.length == 0 
                println("...")
        println("...")
        else 
                println("Success")
        if args.length > 0 
                println("Terminating")
        else 
                println("Terminating")
        println("...")
        for val arg : args 
                println(arg)
        var i = 0
        for val arg : args 
                println(i++)
        println(arg)
        while true 
                println("...")
        break
        while true 
        break
        try 
                println("...")
        catch Exception e 
                e.printStackTrace()
        try 
                println("...")
        println("...")
        catch Exception e 
                println("...")
        e.printStackTrace()
        do 
                println("...")
        break
        while true
        do 
        break
        while true
    }
}
