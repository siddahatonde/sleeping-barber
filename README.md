SLEEPING BARBER PROBLEM


Problem Explanation:
The sleeping barber dilemma is a classic challenge of inter-process communication and synchronization among many operating system processes. The issue is similar to keeping a barber working when there are customers and relaxing when there are none, all while remaining orderly.
The example is based on a single barber in a fictitious barbershop. The barber has a cutting area with one barber's chair and a waiting room with several chairs. When a barber finish cutting a customer's hair, he dismisses them and goes to the waiting room to see whether anyone else is waiting. If there are any, he returns to the chair and cuts one of their hairs. He returns to the chair and sleeps in it if there are none. When a customer walks in the door, they all glance to see what the barber is doing. The customer wakes up the barber and sits in the cutting room chair if the barber is sleeping. The customer remains in the waiting room while the barber is cutting hair. The consumer sits in a free chair in the waiting area and waits for their turn. If there isn't a seat available, the consumer will depart. 
The following decisions, based on analysis, should ensure that the shop runs smoothly, with the barber cutting the hair of anyone who walks in until there are no more clients, then napping until the next customer arrives. There are a variety of issues that can arise in practice that are indicative of basic scheduling issues.
 
a. Sleeping Barber Problem 

Description of the solution:
We have made use of three semaphores to design the solution to this problem. Firstly, it has been presented for the customer who enters and counts the number of customers in the waiting room where the customer who is been served is not counted as he is not waiting for his turn to arrive. Next, the first and the second barber are used to tell that the barber is idle or working. The last semaphore is used to provide the mutual exclusion which is an important factor for execution. 

The client tracks the number of clients that are holding up in the sitting area, and in the event that the quantity of clients is equivalent to the quantity of seats in the waiting area, the following client leaves the barbershop. At the point when the barber shows up in the first part of the day, he follows the method barber, which makes him block the semaphore clients in light of the fact that the worth is initially 0. The barber then, at that point, goes to sleep until the first customer arrives.
A barber must obtain the room status mutex before entering the store and release it once they are sitting in a waiting room or barber chair, as well as when they depart since no seats were available. This would solve both of the previously listed issues. To show the condition of the system, a number of semaphores are also necessary. For example, one may keep track of how many people are in the waiting room. In our solution barber is a Semaphore, customer is a Semaphore and chairs are Mutex.

Justification:
1. Fairness:
Executor services are used in the code. It will maintain a thread pool by employing the executor service, which will reuse a fixed number of threads operating off a shared unbounded queue. This queues tasks that must be finished before at least one of the threads becomes available. The thread that becomes free first is assigned to the next job in the queue. This ensures that thread execution is fair. Each barber will have a fresh customer in the shared queue.
2. Absence of Starvation:
Customers that do not follow any order for having a haircut may face issue in this scenario, as some will not get a haircut even after waiting a long period. To address this issue, I've placed the customers in a linked list that follows the first in, first out principle. As a result, every time a customer sits in the waiting area, the barber will select them on a first come, first served basis. Other data structures, such as a stack, could have been employed, but the linked list appears to be the best option in this case.
3. Mutual Exclusion:
For fairness, we use executor services to maintain many threads, and we use re-entrant locks for the shared queue to prevent interleaving of the vital area. 

Real-World Example:
The real-world implementation of this is the most dependable portrayal of a call centre. Whenever there isn't a client on the line, all call-attender unwind and sit tight for the call. At the point when the underlying client contacts the number, the person is associated with any call-attender, and if all call-attender are occupied, the client should stand by in a line until a call-attender is distributed to them. Clients are separated with a message asserting chiefs are occupied and clients will be reached later by the organization assuming all leaders are occupied and the holding up line is full.
The similarities can be defined in three parts. Firstly, the section which can be considered as critical will be the initiated call between the customer and the call attendee. Secondly, the area where all 

the callers wait before the call has been connected is the waiting room where each one of them will be served in FIFO i.e. First In First Out. Lastly, we can execute the locks during the waiting room because of which a single customer won’t be given to two different call attendees if they both get free at the same time.

