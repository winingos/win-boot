package com.concurrency.Vehicle;

import com.concurrency.annotation.Immutable;
import com.concurrency.annotation.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by 王宁 on 2018/2/24.
 */
@ThreadSafe
public class DelegatingVehicleTracker {
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("");
        locations = new ConcurrentHashMap<String, Point>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    /**
     * live" view
     * @return
     */
    public Map<String, Point> getLocations(){
        return unmodifiableMap;

    }

    /**
     *  shallow copy to snapshot
     * @return
     */
    public Map<String,Point> getLocationsSnapshot(){
        return Collections.unmodifiableMap(new HashMap<>(locations));
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
