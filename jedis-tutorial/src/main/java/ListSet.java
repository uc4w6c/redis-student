import redis.clients.jedis.Jedis;

public class ListSet {
    private Jedis jedis;

    public ListSet(Jedis jedis) {
        this.jedis = jedis;
    }

    public void run() {
        jedis.rpush("my_list", "aaa");
        jedis.rpush("my_list", "bbb");
        jedis.rpush("my_list", "ccc");
        jedis.rpush("my_list", "ddd");
        System.out.println("rpush:" + jedis.lrange("my_list", 0, 3));

        jedis.del("my_list");
        jedis.rpush("my_list", "aaa", "bbb", "ccc", "ddd");
        System.out.println("rpush:" + jedis.lrange("my_list", 0, 3));

        // 指定した位置の値を取得
        System.out.println("lindex 0:" + jedis.lindex("my_list", 0));
        System.out.println("lindex 2:" + jedis.lindex("my_list", 2));

        System.out.println("lrange 0 to 2:" + jedis.lrange("my_list", 0, 2));
        System.out.println("lrange 1 to 3:" + jedis.lrange("my_list", 1, 3));
        System.out.println("lrange 0 to -1:" + jedis.lrange("my_list", 0, -1));
        System.out.println("lrange 1 to -1:" + jedis.lrange("my_list", 1, -1));

        // lpushで先頭に要素を追加
        jedis.lpush("my_list", "aa");
        System.out.println("lpush get:" + jedis.lrange("my_list", 0, -1));

        // lpopでリストの最初の要素を削除して取得
        System.out.println(jedis.lpop("my_list"));
        System.out.println("lpop:" + jedis.lrange("my_list", 0, -1));
        // rpopでリストの最後の要素を削除して取得
        System.out.println(jedis.rpop("my_list"));
        System.out.println("rpop:" + jedis.lrange("my_list", 0, -1));

        // 指定した範囲を残すようにリストをトリムする
        jedis.del("my_list");
        jedis.rpush("my_list", "aaa", "bbb", "ccc", "ddd");
        System.out.println("ltrim before:" + jedis.lrange("my_list", 0, -1));
        jedis.ltrim("my_list", 0, 1);
        System.out.println("ltrim after:" + jedis.lrange("my_list", 0, -1));
    }
}
