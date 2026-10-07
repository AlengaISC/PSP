public class Child {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("I'm the child process");

        Thread.sleep(10000);

        System.out.println("Process endend");
    }
}
