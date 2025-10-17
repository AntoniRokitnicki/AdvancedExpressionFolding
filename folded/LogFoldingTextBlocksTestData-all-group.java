package data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Formatter;

@SuppressWarnings("ALL")
[0:"p"]ublic class LogFoldingTextBlocksTestData {[0:"\n\n    "][0:"private static final Logger log = LoggerFactory.getLogger(LogFoldingTextBlocksTestData.class);"]Data.class);

    private [2:"static final "][2:"Marker"] MY_MARKER = MarkerFactory.getMarker("MY_MARKER");

    public LogBrackets.Data logPrintfStyle(LogBrackets.Data data) {
        [3:"String"][75:"String"] name = "John";
        [4:"int"][76:"int"] age = 30;
        [5:"String"][77:"String"] city = "New York";
        log.debug("Debug message with 1 parameter - Name: [6:"%s\", "][77:"%s\", "]name[6:")"][77:")"];

        log.info(MY_MARKER, "Info message with 2 parameters - Name: [7:"%s, Age: %d\", "][77:"%s, Age: %d\", "]name[7:", "][77:", "]age[7:")"][77:")"];

        log.info("Info message with 2 parameters - Name: [8:"%s, Age: %d    \", "][77:"%s, Age: %d    \", "]name[8:", "][77:", "]age[8:")"][77:")"];
        log.info("Info message with 2 parameters - Name: [9:"%s, Age: %d\", "][77:"%s, Age: %d\", "]name[9:", "][77:", "]age[9:")"][77:")"];


        log.debug("Debug message with 1 parameter - Name: [10:"\" + "][10:"\" + "][78:"\" + "][78:"\" + "]name[10:")"][78:")"];
        log.trace("Trace message - Name: [11:"%s, log:%s    $\", "][78:"%s, log:%s    $\", "]data.[12:"getName()"][79:"getName()"][80:"getName()"][12:", "][79:", "]logPrintfStyle(data)[12:")"][79:")"];
        log.warn("Warning message with three parameters - Name: [13:"%s, Age: %s, City: %s\", "][80:"%s, Age: %s, City: %s\", "]name[13:", "][80:", "]data.[15:"getData()"][82:"getData()"][84:"getData()"].[14:"getName()"][81:"getName()"][83:"getName()"][15:", "][82:", "]city[15:")"][82:")"];

        log.error("Missing 1 parameter - 1: [16:"%s, 2: %d, 3: %s, empty: %s\", "][84:"%s, 2: %d, 3: %s, empty: %s\", "]name[16:", "][84:", "]age[16:", "][84:", "]city[16:")"][84:")"];
        log.error("Missing 2 parameters - 1: [17:"%s, 2: %d, empty: %s, empty: %s\", "][84:"%s, 2: %d, empty: %s, empty: %s\", "]name[17:", "][84:", "]age[17:")"][84:")"];
        log.error("Missing 3 parameters - 1: [18:"%s, empty: %s, empty: %s, empty: %s\", "][84:"%s, empty: %s, empty: %s, empty: %s\", "]name[18:")"][84:")"];
        log.error("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s");

        try {
            log.warn("Warning message with 3 parameters and formatting - 1: [19:"%s, 2: %s, 3: %s\",\n                    "][84:"%s, 2: %s, 3: %s\",\n                    "]name[19:",\n\n                    "][84:",\n\n                    "]data.[21:"getData()"][86:"getData()"][88:"getData()"].[20:"getName()"][85:"getName()"][87:"getName()"][21:",\n\n                    "][86:",\n\n                    "]city[21:"\n            "][86:"\n            "]);

            log.warn("Warning message with 3 parameters and formatting - 1: [22:"%s, 2: %s, 3: %s\",\n                    "][88:"%s, 2: %s, 3: %s\",\n                    "]data.[24:"getData()"][90:"getData()"][94:"getData()"].[23:"getName()"][89:"getName()"][93:"getName()"][24:",\n                    "][90:",\n                    "]name[24:",\n                    "][90:",\n                    "]data.[26:"getData()"][92:"getData()"][96:"getData()"].[25:"getName()"][91:"getName()"][95:"getName()"][26:"\n            "][92:"\n            "]);
        } catch [27:"("][97:"("]Exception e[27:")"][97:")"] {
            log.error("error1 [28:"%s\", "][97:"%s\", "]e[28:","][97:","] e.[29:"getMessage()"][98:"getMessage()"][99:"getMessage()"], e);
            log.error("error2 [30:"%s\", "][99:"%s\", "]data.[32:"getData()"][101:"getData()"][107:"getData()"].[31:"getName()"][100:"getName()"][106:"getName()"][32:","][101:","] data.[34:"getData()"][103:"getData()"][109:"getData()"].[33:"getName()"][102:"getName()"][108:"getName()"], data.[36:"getData()"][105:"getData()"][111:"getData()"].[35:"getName()"][104:"getName()"][110:"getName()"]);
        }

        // 1. String.format()
        [37:"String"][112:"String"] formatted = String.format("Hello, [38:"%s! Your age is %d\", "][112:"%s! Your age is %d\", "]name[38:", "][112:", "]age[38:")"][112:")"];
        log.info("String.format example: [39:"{}\", "][112:"{}\", "]formatted[39:")"][112:")"];

        // 2. System.out.printf()
        System.out.printf("User: [40:"%s, Age: %d, City: %s%n\", "][112:"%s, Age: %d, City: %s%n\", "]name[40:", "][112:", "]age[40:", "][112:", "]city[40:")"][112:")"];

        // 3. System.err.printf()
        System.err.printf("Error scenario: User [41:"%s not found in %s and ignore new-line break%n\", "][112:"%s not found in %s and ignore new-line break%n\", "]name[41:", "][112:", "]city[41:","][112:","] [42:"\"ignored\""][113:"\"ignored\""]);

        // 4. Formatter class
        [43:"Formatter"][114:"Formatter"] formatter = new Formatter();
        formatter.format("User details - Name: [44:"%s, Age: %d, City: %s\", "][114:"%s, Age: %d, City: %s\", "]name[44:", "][114:", "]age[44:", "][114:", "]city[44:")"][114:")"];
        log.info("Formatter example: [45:"{}\", "][114:"{}\", "]formatter.toString()[45:")"][114:")"];

        // 5. PrintWriter with printf
        try {
            [46:"PrintWriter"][115:"PrintWriter"] writer = new PrintWriter(new File("log.txt"));
            writer.printf("Log entry: User [47:"%s, Age %d, accessed from %s\", "][115:"%s, Age %d, accessed from %s\", "]name[47:", "][115:", "]age[47:", "][115:", "]city[47:")"][115:")"];
            writer.close();
        } catch [48:"("][116:"("]FileNotFoundException e[48:")"][116:")"] {
            log.error("Failed to write to log file: [49:"%s\", "][116:"%s\", "]e.[50:"getMessage()"][117:"getMessage()"][118:"getMessage()"][50:")"][117:")"];
        }

        // 6. String with formatted
        [51:"System.out."][119:"System.out."]println("Debug message with 1 parameter - Name: [51:"%s\".formatted("][52:"%s\".formatted("][119:"%s\".formatted("][119:"%s\".formatted("]name[51:")"][52:")"][119:")"][119:")"]);
        [53:"System.out."][120:"System.out."]println("Trace message - Name: [53:"%s, log:%s    $\".formatted("][55:"%s, log:%s    $\".formatted("][120:"%s, log:%s    $\".formatted("][121:"%s, log:%s    $\".formatted("]data.[54:"getName()"][56:"getName()"][121:"getName()"][122:"getName()"][123:"getName()"][54:", "][56:", "][121:", "][122:", "]logPrintfStyle(data)[54:")"][56:")"][121:")"][122:")"]);
        [57:"System.out."][124:"System.out."]println("Warning message with three parameters - Name: [57:"%s, Age: %s, City: %s\".formatted("][60:"%s, Age: %s, City: %s\".formatted("][124:"%s, Age: %s, City: %s\".formatted("][126:"%s, Age: %s, City: %s\".formatted("]name[57:", "][60:", "][124:", "][126:", "]data.[59:"getData()"][62:"getData()"][126:"getData()"][128:"getData()"][130:"getData()"].[58:"getName()"][61:"getName()"][125:"getName()"][127:"getName()"][129:"getName()"][59:", "][62:", "][126:", "][128:", "]city[59:")"][62:")"][126:")"][128:")"]);

        [63:"System.out."][131:"System.out."]println("Missing 1 parameter - 1: [63:"%s, 2: %d, 3: %s, empty: %s\".formatted("][64:"%s, 2: %d, 3: %s, empty: %s\".formatted("][131:"%s, 2: %d, 3: %s, empty: %s\".formatted("][131:"%s, 2: %d, 3: %s, empty: %s\".formatted("]name[63:", "][64:", "][131:", "][131:", "]age[63:", "][64:", "][131:", "][131:", "]city[63:")"][64:")"][131:")"][131:")"]);
        [65:"System.out."][132:"System.out."]println("Missing 2 parameters - 1: [65:"%s, 2: %d, empty: %s, empty: %s\".formatted("][66:"%s, 2: %d, empty: %s, empty: %s\".formatted("][132:"%s, 2: %d, empty: %s, empty: %s\".formatted("][132:"%s, 2: %d, empty: %s, empty: %s\".formatted("]name[65:", "][66:", "][132:", "][132:", "]age[65:")"][66:")"][132:")"][132:")"]);
        [67:"System.out."][133:"System.out."]println("Missing 3 parameters - 1: [67:"%s, empty: %s, empty: %s, empty: %s\".formatted("][68:"%s, empty: %s, empty: %s, empty: %s\".formatted("][133:"%s, empty: %s, empty: %s, empty: %s\".formatted("][133:"%s, empty: %s, empty: %s, empty: %s\".formatted("]name[67:")"][68:")"][133:")"][133:")"]);
        [69:"System.out."][134:"System.out."]println("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s".formatted());
        [70:"System.out."][135:"System.out."]println("Additional 1 parameter - Name: [70:"%s\".formatted("][71:"%s\".formatted("][135:"%s\".formatted("][135:"%s\".formatted("]name[70:","][71:","][135:","][135:","] data));
        [72:"System.out."][136:"System.out."]println("Additional 2 parameters - Name: [72:"%s\".formatted("][73:"%s\".formatted("][136:"%s\".formatted("][136:"%s\".formatted("]name[72:","][73:","][136:","][136:","] data, logPrintfStyle(data)));

        // 7. Text Block examples (Java 15+)
        log.error([137:"\"\"\"\n                Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s\n                \"\"\""]   \"\"\", "][136:"%s, 2: %d, 3: %s, empty: %s\n                \"\"\", "]name[74:", "][136:", "]age[74:", "][136:", "]city[74:")"][136:")"];
        return data;
    }

    [138:"p"]ublic static class Data {
        private LogBrackets.Data data;
        private String name;[138:"\n\n        "][138:"public String getName() {\n            return name;\n        }"]][139:" "]name[139:";"][139:"\n        "]}[138:"\n\n        "][138:"public LogBrackets.Data getData() {\n            return data;\n        }"]][140:" "]data[140:";"][140:"\n        "]}
    }

}
