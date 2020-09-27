import redis.clients.jedis.Jedis;

import java.util.Map;

public class ZSetJedis {
    private Jedis jedis;

    public ZSetJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    public void run() {
        jedis.zadd("my_zset", 111, "member1");
        jedis.zadd("my_zset", 222, "member2");
        jedis.zadd("my_zset", 333, "member3");
        System.out.println("zadd get:" + jedis.zrange("my_zset", 0, -1));

        jedis.del("my_zset");
        Map<String, Double> map = Map.of(
            "member1", 111D,
            "member2", 222D,
            "member3", 333D
        );
        jedis.zadd("my_zset", map);

        System.out.println("zadd (range add) get:" + jedis.zrange("my_zset", 0, -1));
        jedis.zrangeWithScores("my_zset", 0, -1)
                .forEach(t -> System.out.println("element get score:" + t.getElement() + " : " + t.getScore()));

        System.out.println("zrangeByScore 0 - 150:" + jedis.zrangeByScore("my_zset", 0, 150));
        jedis.zrangeByScoreWithScores("my_zset", 0, 150)
                .forEach(t -> System.out.println("0-150:" + t.getElement() + " : " + t.getScore()));
        jedis.zrangeByScoreWithScores("my_zset", 100, 230)
                .forEach(t -> System.out.println("100-230:" + t.getElement() + " : " + t.getScore()));

        // redis-cliだと、第2引数と第3引数には無限大(+inf/-inf)を指定することが可能だが、
        // jedisではDouble.MIN_VALUE/MAX_VALUEを使用する
        System.out.println("MIN_VALUE - 200:" + jedis.zrangeByScore("my_zset", Double.MIN_VALUE, 200));
        System.out.println("200 - MAX_VALUE:" + jedis.zrangeByScore("my_zset", 200, Double.MAX_VALUE));

        jedis.del("my_zset");
        jedis.zadd("my_zset", map);
        jedis.zrangeWithScores("my_zset", 0, -1)
                .forEach(t -> System.out.println("zremrangeByScore before:" + t.getElement() + " : " + t.getScore()));
        // 指定した範囲のスコアを削除
        jedis.zremrangeByScore("my_zset", 200, 400);
        jedis.zrangeWithScores("my_zset", 0, -1)
                .forEach(t -> System.out.println("zremrangeByScore after:" + t.getElement() + " : " + t.getScore()));

    }
}
