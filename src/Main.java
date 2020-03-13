public class Main{
    public static void main(String[] args) {
        KeyLogger kl = new KeyLogger();
        Thread t1 = new Thread(kl);
        t1.start();
    }
}