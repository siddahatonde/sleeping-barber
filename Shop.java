import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Shop {
    private int totalHairCuts = 0;
    private int customersLost = 0;
    private final int totalChairs;
    private int availableBarbers;
    private final List<Customer> list;

    Random random = new Random();

    public Shop(int noOfBarbers, int noOfChairs) {
        this.totalChairs = noOfChairs;
        this.list = new LinkedList<>();
        this.availableBarbers = noOfBarbers;
    }

    public int getTotalHairCuts() {
        return totalHairCuts;
    }

    public int getCustomerLost() {
        return customersLost;
    }

    public void working(int barberId) throws Exception {
        int delayTime;
        Customer customer;
        customer = syncCustomerList(barberId);
        availableBarbers--;
        displayResult("Barber " + barberId + " cutting hair of " +
                customer.getCustomerId() + " so customer sleeps");

        double val = random.nextGaussian() * 2000 + 4000;
        delayTime = Math.abs((int) Math.round(val));
        Thread.sleep(delayTime);

        System.out.println();
        displayResult("Completed Cutting hair of " +
                customer.getCustomerId() + " by barber " +
                barberId + " in " + delayTime + " milliseconds.");

        totalHairCuts += 1;
        if (list.size() > 0) {
            displayResult("Barber " + barberId +
                    " wakes up a customer in the "
                    + "waiting room");
        }
        availableBarbers++;
    }

    private Customer syncCustomerList(int barberId) {
        Customer customer;
        synchronized (list) {
            startThreads(barberId);
            customer = (Customer) ((LinkedList<?>) list).poll();
            assert customer != null;
            displayResult("Customer " + customer.getCustomerId() +
                    " finds the barber asleep and wakes up "
                    + "the barber " + barberId);
        }
        return customer;
    }

    private void displayResult(String customer) {
        System.out.println(customer);
    }

    private void startThreads(int barberId) {
        while (list.size() == 0) {
            displayResult("\nBarber " + barberId + " is waiting "
                    + "for the customer and sleeps in his chair");
            try {
                list.wait();
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }
        }
    }

    public void add(Customer customer) {
        System.out.println();
        displayResult("Customer " + customer.getCustomerId() +
                " enters through the entrance door in the the shop at "
                + customer.getInTime());
        synchronized (list) {
            checkConditions(customer);
        }
    }

    private void checkConditions(Customer customer) {
        if (list.size() == totalChairs) {
            displayResult("\n Chair not available "
                    + "for customer " + customer.getCustomerId() +
                    " so customer leaves the shop");
            customersLost += 1;
        } else if (availableBarbers > 0) {
            ((LinkedList<Customer>) list).offer(customer);
            list.notify();
        } else {
            ((LinkedList<Customer>) list).offer(customer);
            displayResult("All barber(s) are busy so " +
                    customer.getCustomerId() +
                    " takes a chair in the waiting room");
            if (list.size() == 1)
                list.notify();
        }
    }
}

