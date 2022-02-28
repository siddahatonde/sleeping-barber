import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepingBarber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalBarbers;
        int customerId = 1;
        int totalCustomers = 100;
        int availableChairs;

        System.out.print("Please type the amount of barbers: ");
        totalBarbers = scanner.nextInt();

        System.out.print("Please type the defined chairs: ");
        availableChairs = scanner.nextInt();

        ExecutorService exec = Executors.newFixedThreadPool(12);
        Shop shop = new Shop(totalBarbers, availableChairs);
        Random r = new Random();

        System.out.println();
        System.out.println("Shop is now started with "
                + totalBarbers + " barber(s)");
        System.out.println();

        long startTime = System.currentTimeMillis();


        execThreads(totalBarbers, exec, shop);

        generateCustomerThreads(customerId, totalCustomers, exec, shop, r);
        exec.shutdown();

        long elapsedTime = System.currentTimeMillis() - startTime;
        displayData(totalBarbers, totalCustomers, availableChairs, shop, elapsedTime);
    }

    private static void displayData(int totalBarbers,
                                    int noOfCustomers, int totalChairs,
                                    Shop shop, long elapsedTime) {
        System.out.println();
        System.out.println("The shop is closed");
        System.out.println();
        System.out.println("The total amount of time gone"
                + " for haircut of " + noOfCustomers + " customers by "
                + totalBarbers + " barbers with " + totalChairs +
                " chairs in the waiting room is: "
                + TimeUnit.MILLISECONDS
                .toSeconds(elapsedTime));
        System.out.println();
        System.out.println("Total customers: " + noOfCustomers +
                "\nTotal customers served: " + shop.getTotalHairCuts()
                + "\nTotal customers lost: " + shop.getCustomerLost());
    }

    private static void generateCustomerThreads(int customerId, int noOfCustomers, ExecutorService exec, Shop shop, Random random) {
        int i = 0;
        while (i < noOfCustomers) {
            try {
                customerId = initValues(customerId, exec, shop);
                double val = random.nextGaussian() * 2000 + 2000;
                int millisDelay = Math.abs((int) Math.round(val));
                Thread.sleep(millisDelay);
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }
            i++;
        }
    }

    private static int initValues(int customerId, ExecutorService exec, Shop shop) {
        Customer customer = new Customer(shop);
        customer.setInTime(new Date());
        Thread customerThread = new Thread(customer);
        customer.setCustomerId(customerId++);
        exec.execute(customerThread);
        return customerId;
    }

    private static void execThreads(int noOfBarbers, ExecutorService exec, Shop shop) {
        int i = 1;
        while ( i <= noOfBarbers ) {
            Barber barber = new Barber(shop, i);
            Thread barberThread = new Thread(barber);
            exec.execute(barberThread);
            i++;
        }
    }
}
