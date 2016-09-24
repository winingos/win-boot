package com.thinkinjava.innerclass;

/**
 * Created by ning.wang on 2016/9/1.
 * Using anonymous inner classes with the Game framework.
 */
interface Game { boolean move(); }
interface GameFactory { Game getGame(); }
class Checkers implements Game {
    private int moves = 0;
    private static final int MOVES = 3;
    @Override
    public boolean move() {
        System.out.println("Checkers move " + moves);
        return ++moves != MOVES;
    }
    //java8 lambda
//    public static GameFactory factory1 = ()->{return new Checkers();};
//    public static GameFactory factory2 = () -> new Checkers();
    public static GameFactory factory=new GameFactory() {
        @Override
        public Game getGame() {
            return new Checkers();
        }
    };
}
class Chess implements Game{
    private int moves = 0;
    private static final int MOVES = 4;
    @Override
    public boolean move() {
        System.out.println("Chess move " + moves);
        return ++moves != MOVES;
    }
    public static GameFactory factory = new GameFactory() {
        @Override
        public Game getGame() {
            return new Chess();
        }
    };
}
public class Games {
    public static void playGames(GameFactory f){
        Game game = f.getGame();
        while (game.move());
    }

    public static void main(String[] args) {
        Games.playGames(Checkers.factory);
        Games.playGames(Chess.factory);
    }
}
