package com.thinkinjava.innerclass;

/**
 * Created by ning.wang on 2016/8/31.
 */
interface Destination {
    String readLabel();
}

interface Contents {
    int value();
}

/**
 * hiding the implementation
 * Inner classes and upcasting
 */
class Parcel4 {
    public static void main(String[] args) {
        Parcel4 p4 = new Parcel4();
        p4.contents();
        p4.destination("Test");
    }

    public Destination destination(String s) {
        return new PDestination(s);
    }

    public Contents contents() {
        return new PContents();
    }

    private class PContents implements Contents {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    private class PDestination implements Destination {
        private String label;

        private PDestination(String whereTo) {
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }
}

/**
 * Nesting a class within a method
 */
public class Parcel5 {
    public static void main(String[] args) {
        Parcel5 parcel5 = new Parcel5();
        parcel5.destination("test");
    }

    public Destination destination(String s) {
        class PDestination implements Destination {
            private String label;

            private PDestination(String whereTo) {
                label = whereTo;
            }

            @Override
            public String readLabel() {
                return label;
            }
        }
        return new PDestination(s);
    }

}

class Parcel6 {
    public static void main(String[] args) {
        Parcel6 p = new Parcel6();
        p.track();
    }

    private void internalTracking(boolean b) {
        if (b) {
            class TrackingSlip {
                private String id;

                TrackingSlip(String s) {
                    id = s;
                }

                String getSlip() {
                    return id;
                }
            }
            TrackingSlip ts = new TrackingSlip("slip");
            String s = ts.getSlip();
        }
        // Can’t use it here! Out of scope:
//! TrackingSlip ts = new TrackingSlip("x");

    }

    public void track() {
        internalTracking(true);
    }
}

class Parcel7 {
    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
        c.value();
    }

    public Contents contents() {
        return new Contents() {// Insert a class definition
            private int i = 11;

            @Override
            public int value() {
                return i;
            }
        };// Semicolon required in this case
    }
}

/**
 * An anonymous inner class that performs
 * initialization. A briefer version of Parcel5.java.
 **/
class Parcel9 {
    // Argument must be final to use inside
// anonymous inner class:
    public Destination destination(final String dest) {
        return new Destination() {
            private String label = dest;
            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel9 p = new Parcel9();
        Destination d = p.destination("test");
        d.readLabel();
    }
}

class Parcel10{
    public Destination destination(String dest,float price){
        return new Destination() {
            private int cost;
            // Instance initialization for each object:
            {
                cost = Math.round(price);
                if (cost>100){
                    System.out.println("Over budget" + cost);
                }else {
                    System.out.println("On budget ,cost = " + cost);
                }
            }
            private String lable =dest;
            @Override
            public String readLabel() {
                return lable;
            }
        };
    }

    public static void main(String[] args) {
        Parcel10 p = new Parcel10();
        p.destination("test",101.935F);
    }
}

/**
 * Nested classes (static inner classes).
 */
class Parcel11{
    private  static class ParcelContents implements Contents{
        private int i = 11;
        @Override
        public int value() { return i; }
    }
    protected static class ParcelDestination implements Destination{
        private String label;
        private ParcelDestination(String whereTo) {
            label = whereTo;
        }
        @Override
        public String readLabel() { return label; }
        // Nested classes can contain other static elements:
        public static void f() {}
        static int x = 10;
        static class AnotherLevel {
            public static void f() {}
            static int x = 10;
        }
    }

    public static Destination destination(String s) {
        return new ParcelDestination(s);
    }
    public static Contents contents() {
        return new ParcelContents();
    }

    public static void main(String[] args) {
        Contents c=contents();
        Destination d = destination("yyyyy");
        ParcelContents parcelContents = new ParcelContents();
    }

}
