package com.concurrency.building_blocks;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by 王宁 on 2018/4/24.
 */
public class CellularAutomata {

    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count,
                new Runnable() {
                    public void run() {
                        mainBoard.commitNewValues();
                    }
                });
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++)
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
    }

    private class Worker implements Runnable {
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        public void run() {
            while (!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++)
                    for (int y = 0; y < board.getMaxY(); y++) {
                        board.setNewValue(x, y, computeValue(x, y));
                    }
                try {
                    barrier.await();
                } catch (InterruptedException ex) {
                    return;
                } catch (BrokenBarrierException ex) {
                    return;
                }
            }
        }
    }



    public void start() {
        for (int i = 0; i < workers.length; i++)
            new Thread(workers[i]).start();
        mainBoard.waitForConvergence();
    }



/*不是很懂用法,还有exchanger 也略过了,希望后面有详细的说明*/


    /***
     *
     * @param x
     * @param y
     * @return
     */
    private int computeValue(int x, int y) {
        return x+y;
    }
}
class Board{

    private int maxX;
    private int maxY;

    public void commitNewValues() {

    }

    public Board getSubBoard(int count, int i) {
        return this;
    }

    public boolean hasConverged() {
        return false;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void waitForConvergence() {

    }

    public void setNewValue(int x, int y, int i) {

    }
}
/****/