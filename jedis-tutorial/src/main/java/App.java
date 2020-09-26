import redis.clients.jedis.Jedis;

public class App {
    public static void main(String[] args) {
        System.out.println("Start");

        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println("value: " + value);

        System.out.println("End");
    }
}
