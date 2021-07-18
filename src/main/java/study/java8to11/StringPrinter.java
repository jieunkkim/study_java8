package study.java8to11;

public class StringPrinter {

    private String name;

    public StringPrinter() {
    }

    public StringPrinter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String printHello(String name){
        return "Hello " + name;
    }

    public static String printHi(String name){
        return "Hi " + name;
    }
}
