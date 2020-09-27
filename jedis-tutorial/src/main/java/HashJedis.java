import redis.clients.jedis.Jedis;

import java.util.Map;

public class HashJedis {
    private Jedis jedis;

    public HashJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    public void run() {
        jedis.hset("my_hash", "aaa", "111");
        jedis.hset("my_hash", "bbb", "222");
        jedis.hset("my_hash", "ccc", "333");
        System.out.println("hget aaa:" + jedis.hget("my_hash", "aaa"));
        System.out.println("hget bbb:" + jedis.hget("my_hash", "bbb"));
        System.out.println("hget All:" + jedis.hgetAll("my_hash"));
        jedis.hdel("my_hash", "ccc");
        System.out.println("hdel ccc:" + jedis.hgetAll("my_hash"));

        jedis.del("my_hash");
        Map<String, String> map = Map.of(
                "aaa", "111",
                "bbb", "222",
                "ccc", "333"
        );
        jedis.hmset("my_hash", map);
        System.out.println("hmset hmget:" + jedis.hmget("my_hash", "aaa", "bbb", "ccc"));

        System.out.println("hget before:" + jedis.hget("my_hash", "aaa"));
        jedis.hincrBy("my_hash", "aaa", 1);
        System.out.println("hget hincrBy 1:" + jedis.hget("my_hash", "aaa"));
        jedis.hincrBy("my_hash", "aaa", 100);
        System.out.println("hget hincrBy 100:" + jedis.hget("my_hash", "aaa"));
    }
}
