import redis.clients.jedis.Jedis;

public class SetJedis {
    private Jedis jedis;

    public SetJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    public void run() {
        jedis.sadd("my_set", "AAA");
        jedis.sadd("my_set", "BBB");
        jedis.sadd("my_set", "CCC");
        jedis.sadd("my_set", "AAA");
        System.out.println("set add:" + jedis.smembers("my_set"));

        jedis.del("my_set");
        jedis.sadd("my_set", "AAA", "BBB", "CCC");
        System.out.println("set sadd:" + jedis.smembers("my_set"));

        // spopはランダムで1つ削除される
        jedis.spop("my_set");
        System.out.println("spop:" + jedis.smembers("my_set"));

        jedis.del("my_set");
        jedis.sadd("my_set", "AAA", "BBB", "CCC");
        Boolean existAAA = jedis.sismember("my_set", "AAA");
        System.out.println("sismember exist:" + existAAA);
        Boolean existDDD = jedis.sismember("my_set", "DDD");
        System.out.println("sismember not exist:" + existDDD);

        jedis.del("my_set");
        jedis.sadd("my_set", "AAA", "BBB", "CCC");
        System.out.println("srem before del:" + jedis.smembers("my_set"));
        jedis.srem("my_set", "BBB");
        System.out.println("srem BBB del:" + jedis.smembers("my_set"));
    }
}
