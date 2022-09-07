//package com.lpyu.effective;
////(需要JDK1.9，暂不支持)
//import sun.misc.Cleaner;
//
//public class Room implements AutoCloseable{
//    private static final Cleaner clearer=Cleaner.create();
//
//    private static class State implements Runnable{
//        int numJunkPiles;
//        State(int numJunkPiles){
//            this.numJunkPiles=numJunkPiles;
//        }
//        @Override
//        public void run(){
//            System.out.println("Cleaning Room");
//            numJunkPiles=0;
//        }
//    }
//    private final State state;
//    private final Cleaner.Cleanable cleanable;
//    public Room (int numJunkPiles){
//        state=new State(numJunkPiles);
//        cleanable=clearer.register(this,state);
//    }
//    @Override
//    public void close(){
//        cleanable.clean();
//    }
//}
