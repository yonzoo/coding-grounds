package ThirdLab;

public class Main {
    public static MyStringBuilder sb;

    public static void main(String[] args) {
        sb = new MyStringBuilder("testtesttest");
        String test1 = sb.toString();
        sb.append​("lol");
        String test2 = sb.toString();
        sb.undo();
        String test3 = sb.toString();

        System.out.println(test1 + " " + test2);
        System.out.println("String before append and after append + undo should be equal. The result is:");
        System.out.println(test1.equals(test3));
        System.out.println("String before append and after should not be equal. The result is:");
        System.out.print(test1.equals(test2));
    }
}