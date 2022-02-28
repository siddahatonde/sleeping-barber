public class Barber implements Runnable {
    private final Shop shop;
    private final int id;
    public Barber(Shop shop, int barberId) {
        this.shop = shop;
        this.id = barberId;
    }
    @Override
    public void run() {
        for (;true;) {
            try {
                shop.working(id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
