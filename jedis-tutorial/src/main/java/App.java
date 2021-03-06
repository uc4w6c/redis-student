import redis.clients.jedis.Jedis;

public class App {
    public static void main(String[] args) {
        System.out.println("Start");

        Jedis jedis = new Jedis("localhost", 6379);

        System.out.println("-------");
        System.out.println("Plane Set Start");
        PlaneSet planeSet = new PlaneSet(jedis);
        planeSet.run();
        System.out.println("Plane Set End");

        System.out.println("-------");
        System.out.println("Incr Decr Start");
        IncrDecr incrDecr = new IncrDecr(jedis);
        incrDecr.run();
        System.out.println("Incr Decr End");

        System.out.println("-------");
        System.out.println("MSet Start");
        Mset mset = new Mset(jedis);
        mset.run();
        System.out.println("MSet End");

        System.out.println("-------");
        System.out.println("Expire Set Start");
        ExpireSet expireSet = new ExpireSet(jedis);
        expireSet.run();
        System.out.println("Expire Set End");

        System.out.println("-------");
        System.out.println("List Set Start");
        ListSet listSet = new ListSet(jedis);
        listSet.run();
        System.out.println("List Set End");

        System.out.println("-------");
        System.out.println("Set Jedis Start");
        SetJedis setJedis = new SetJedis(jedis);
        setJedis.run();
        System.out.println("Set Jedis End");

        System.out.println("-------");
        System.out.println("ZSet Jedis Start");
        ZSetJedis zSetJedis = new ZSetJedis(jedis);
        zSetJedis.run();
        System.out.println("ZSet Jedis End");

        System.out.println("-------");
        System.out.println("Transaction Jedis Start");
        TransactionJedis transactionJedis = new TransactionJedis(jedis);
        transactionJedis.run();
        System.out.println("Transaction Jedis End");

        System.out.println("End");
    }
}
