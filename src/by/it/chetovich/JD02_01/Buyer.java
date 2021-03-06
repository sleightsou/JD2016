package by.it.chetovich.JD02_01;


import java.util.*;

/**
 * class for buyers
 */
public class Buyer extends Thread implements Runnable,IBuyer, IUseBacket {

    private int num;
    private Backet backet;
    private boolean retired;
    private static Queue<Buyer> queue = new ArrayDeque<>();


    public Buyer(int num, boolean retired) {
        this.num = num;
        this.retired = retired;
        this.setName("Buyer " + num + " ");
        queue.add(this);
        start();
    }

    public boolean isRetired() {
        return retired;
    }

    public int getNum() {
        return num;
    }

    public Map<String, Integer> getBacket() {
        return backet.getGoods();
    }


    public static Queue<Buyer> getQueue() {
        return queue;
    }

    public void clearBacket(){
        backet.getGoods().clear();
    }

    @Override
    public void run (){
        enterMarket();
        takeBacket();
        chooseGoods();
        try {
            goToQueue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            waitingInTheQueue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*synchronized (this){
                while (!backet.getGoods().isEmpty()){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            //this.notifyAll();
        }*/

        //Cashier.takeBuyerFromQueue();

        exitMarket();

    }

    @Override
    public String toString (){

        return this.getName();
    }

    @Override
    public void enterMarket(){
        System.out.println(this+" entered the market.");
    }

    @Override
    public void chooseGoods(){

        Map<String,Integer> buyerBacketMap = new HashMap<>();
        int goodsQuantity = Rnd.fromTo(1,4);
        for (int i = 0; i < goodsQuantity; i++){

            String randomGood = Goods.takeRandomGood();
            putGoodsIntoBacket(randomGood);
            buyerBacketMap.put(randomGood, Goods.getPrice(randomGood));
        }
        backet = new Backet(buyerBacketMap, this.num);

        System.out.print(this+" chose goods, "+(retired?"it took him a while, he is retired. ":"did it fast. "));
        System.out.println(backet);
    }

    @Override
    public void exitMarket(){
        System.out.println(this+" exited the market.");

    }

    @Override
    public void takeBacket() {
        try{
            int pause = retired?Rnd.fromTo(200, 500):Rnd.fromTo(300, 700);
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            System.out.println(this+" некорректное завершение ожидания.");
        }
        System.out.println(this+" took a backet.");
    }

    @Override
    public void putGoodsIntoBacket(String good) {
        try{
            int pause = retired?Rnd.fromTo(100, 200):Rnd.fromTo(150, 300);
            Thread.sleep(pause);

        } catch (InterruptedException e) {
            System.out.println(this+" некорректное завершение ожидания.");
        }
        System.out.println(this+" put "+good+" into backet.");

    }


    @Override
    public void goToQueue() throws InterruptedException {
        synchronized (QueueToPay.queueToPay){
            QueueToPay.putBuyer(this);
            System.out.println("Buyer " + num + " is in the queue");

        }

    }


    @Override
    public synchronized void waitingInTheQueue() throws InterruptedException {
        while (!backet.getGoods().isEmpty())
            this.wait();
    }
}

