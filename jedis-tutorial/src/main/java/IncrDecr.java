import redis.clients.jedis.Jedis;

public class IncrDecr {
    private Jedis jedis;

    public IncrDecr(Jedis jedis) {
        this.jedis = jedis;
    }

    public void run() {
        jedis.set("my_count", "100");
        jedis.incr("my_count");
        System.out.println("incr:" + jedis.get("my_count"));

        jedis.incrBy("my_count", 10);
        System.out.println("incrBy:" + jedis.get("my_count"));

        jedis.decr("my_count");
        System.out.println("decr:" + jedis.get("my_count"));

        jedis.decrBy("my_count", 20);
        System.out.println("decrBy:" + jedis.get("my_count"));
    }
}
