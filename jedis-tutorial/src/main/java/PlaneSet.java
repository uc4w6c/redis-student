import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

public class PlaneSet {
    private Jedis jedis;

    public PlaneSet(Jedis jedis) {
        this.jedis = jedis;
    }

    public void run() {
        // setは値がない場合は作成し、ある場合は更新する
        jedis.set("foo", "bar");
        System.out.println("get:" + jedis.get("foo"));
        jedis.set("foo", "foo");
        System.out.println("updated:" + jedis.get("foo"));

        jedis.del("foo");
        System.out.println("deleted:" + jedis.get("foo"));

        // nxの場合は値がセットされていない場合にのみ更新される
        jedis.set("foo", "bar", new SetParams().nx());
        System.out.println("nx set:" + jedis.get("foo"));
        jedis.set("foo", "foo", new SetParams().nx());
        System.out.println("nx updated set:" + jedis.get("foo"));
        jedis.del("foo");

        // xxの場合は値がセットされている場合にのみ更新される
        jedis.set("foo", "bar", new SetParams().xx());
        System.out.println("xx set:" + jedis.get("foo"));
        jedis.set("foo", "bar");
        jedis.set("foo", "foo", new SetParams().xx());
        System.out.println("xx updated set:" + jedis.get("foo"));

    }
}
