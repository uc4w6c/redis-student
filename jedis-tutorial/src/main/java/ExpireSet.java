import redis.clients.jedis.Jedis;

public class ExpireSet {
    private Jedis jedis;

    public ExpireSet(Jedis jedis) {
        this.jedis = jedis;
    }

    public void run() {
        jedis.set("my_key", "abcde");
        jedis.expire("my_key", 5);
        System.out.println(jedis.get("my_key"));
        System.out.println("my_key ttl:" + jedis.ttl("my_key"));
        System.out.println("Wait for 6 sec...");
        try {
            Thread.sleep(6_000L);
        } catch (InterruptedException e) {}
        System.out.println(jedis.get("my_key"));
        System.out.println("my_key ttl:" + jedis.ttl("my_key"));
    }
}
