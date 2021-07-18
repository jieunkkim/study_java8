package study.java8to11;

public class PrintImpl implements Printer{

    private String name;

    public PrintImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void printName() {
        System.out.println("  printName() : PrintImple에서 구현됨 / 결과는 " + this.name);
    }

}
