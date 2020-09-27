import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Map;

public class TransactionJedis {
    private Jedis jedis;

    public TransactionJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    public void run() {
        // コミット(exec)
        System.out.println("user_list get:" + jedis.lrange("user_list", 0, -1));
        System.out.println("counter get:" + jedis.get("counter"));
        Transaction t = jedis.multi();
        t.rpush("user_list", "bob");
        t.incr("counter");
        t.rpush("user_list", "alice");
        t.incr("counter");
        t.exec();
        System.out.println("user_list get:" + jedis.lrange("user_list", 0, -1));
        System.out.println("counter get:" + jedis.get("counter"));

        // ロールバック(discard)
        System.out.println("counter get:" + jedis.get("counter"));
        Transaction t2 = jedis.multi();
        t2.incr("counter");
        t2.incr("counter");
        t2.discard();
        System.out.println("counter discard get:" + jedis.get("counter"));

        // watch
        // 監視していたキーが他のクライアントから更新されると、exec()した際にエラーになります。
        System.out.println("counter watch before:" + jedis.get("counter"));
        jedis.watch("counter");
        Transaction t3 = jedis.multi();
        System.out.println("wait 10 seconds");
        t3.incr("counter");
        // ここで別クライアントで更新するとエラー発生？エラーは出ず更新されないだけかも。
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {}
        t3.exec();
        System.out.println("counter watch after:" + jedis.get("counter"));

        // unwatch
        // watch()で監視対象となったすべてのキーをフラッシュします。
        // exec()かdiscard()を呼び出した場合は自動でフラッシュされます。(手動でunwatch不要)
        System.out.println("coun†er unwatch before:" + jedis.get("counter"));
        jedis.watch("counter");
        jedis.unwatch();
        Transaction t4 = jedis.multi();
        t4.incr("counter");
        t4.exec();
        System.out.println("coun†er unwatch after:" + jedis.get("counter"));

    }
}
