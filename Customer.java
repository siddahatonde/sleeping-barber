import java.util.Date;

public class Customer implements Runnable {
    private int customerId;
    private Date inTime;
    private final Shop shop;

    public Customer(Shop shop) {
        this.shop = shop;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    @Override
    public void run() {
        shop.add(this);
    }
}
