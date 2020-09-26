import redis.clients.jedis.Jedis;

public class App {
    public static void main(String[] args) {
        System.out.println("Start");

        Jedis jedis = new Jedis("localhost", 6379);

        System.out.println("Plane Set Start");
        PlaneSet planeSet = new PlaneSet(jedis);
        planeSet.run();
        System.out.println("Plane Set End");

        System.out.println("End");
    }
}
