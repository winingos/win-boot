package com.concurrency.Vehicle;

import com.concurrency.annotation.Immutable;
import com.concurrency.annotation.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by 王宁 on 2018/2/24.
 */
@ThreadSafe
public class DelegatingVehicleTracker {
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<String, Point>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }
    public Map<String, Point> getLocations(){
        return unmodifiableMap;

    }

    public Point getLocation(String id){
        return locations.get(id);
    }

    public void setLocations(String id,int x,int y){
        if (locations.replace(id,new Point(x,y))==null){
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
    }
}
@Immutable
class Point{
   public final int x,y;
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }
}
