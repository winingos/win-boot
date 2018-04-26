package com.concurrency.Vehicle;

import com.concurrency.annotation.GuardedBy;
import com.concurrency.annotation.NotThreadSafe;
import com.concurrency.annotation.ThreadSafe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 王宁 on 2018/2/24.
 */
@ThreadSafe
public class MonitorVehicleTracker {
    @GuardedBy("this")
    private final Map<String,MutablePoint> locations;

    public MonitorVehicleTracker(Map<String,MutablePoint> locations){
        this.locations=deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }


    public synchronized Map<String,MutablePoint> getLocations(){
        return deepCopy(locations);
    }

    public synchronized void setLocations(String id,int x,int y){
        MutablePoint loc = locations.get(id);
        Objects.requireNonNull(loc);
        loc.x=x;
        loc.y=y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        Map<String, MutablePoint> result = new HashMap();
        for (String id : result.keySet()) {
            result.put(id,new MutablePoint(m.get(id)));
        }
        return result;
    }

    @NotThreadSafe
    public static class MutablePoint{
        public int x,y;
        public MutablePoint(){
            x=0;y=0;
        }
        public MutablePoint(MutablePoint m){
            this.x=m.x;
            this.y=m.y;
        }
    }

}
