import redis.clients.jedis.Jedis;

public class Mset {
    private Jedis jedis;

    public Mset(Jedis jedis) {
        this.jedis = jedis;
    }

    public void run() {
        jedis.mset("AAA", "111", "BBB", "222", "CCC", "333");
        System.out.println("AAA:" + jedis.get("AAA"));
        System.out.println("mget:" + jedis.mget("AAA", "BBB", "CCC"));
        System.out.println("mget2:" + jedis.mget("AAA", "BBB", "CCC", "DDD"));
    }
}
