package com.concurrency.building_blocks;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 王宁 on 2018/4/23.
 */

public class FileCrawler implements Runnable {
    private final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException{
        File[] files = root.listFiles(fileFilter);
        if (files!=null){
            for (File entry : files) {
                if (entry.isDirectory())
                    crawl(entry);
                else  if (!alreadyIndexed(entry))
                    fileQueue.put(entry);
            }
        }
    }

    private boolean alreadyIndexed(File entry) {
        return false;
    }
    public static class Indexer implements Runnable{
        private final BlockingQueue<File> queue;
        public Indexer(BlockingQueue<File> queue) {
            this.queue = queue;
        }
        @Override
        public void run() {
            try {
                while (true)
                    indexFile(queue.take());
            } catch (InterruptedException e) {
                // restore interrupted status
                Thread.currentThread().interrupt();
            }
        }

        private void indexFile(File take) {

        }
    }
}
