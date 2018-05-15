package com.practice;

import java.util.*;

/**
 * Created by pbhattacharya on 6/26/16.
 */
public class FindTriangles {

    private static final Random RANDOM = new Random();

    static class Point {
        int x;
        int y;
        boolean visited;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Group {

        Set<Point> points = new HashSet<>(3);

        Group(List<Point> groupPoints) {
            points.addAll(groupPoints);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null || !(obj instanceof Point))
                return false;
            Group myGroup = (Group)obj;
            for(Point point : myGroup.points) {

            }
            return false;
        }

        @Override
        public int hashCode() {
            return -1;
        }
    }

    private static int calculateTotalPossibleGroups(int n) {
        return n*(n-1)*(n-2)/6;
    }



    public static int findPossibleTriangles(List<Point> points) {
        int totalPossibleSize = calculateTotalPossibleGroups(points.size());
        Set<Group> groups = new HashSet<>();
        while(groups.size() <= totalPossibleSize) {
            Group group = getGroupOfThree(points);
            groups.add(group);
        }
        int count = 0;

        for(Group group : groups) {
            if(isTriangle(group)) {
                count++;
            }
        }
        return count;
    }


    public static Group getGroupOfThree(List<Point> points) {
        int count = 0;
        List<Point> groupPoints = new ArrayList<>(3);
        while(count < 3) {
            Point point = points.get(RANDOM.nextInt(points.size()));
            if(!point.visited) {
                groupPoints.add(point);
                point.visited = true;
                count++;
            }
        }
        for(Point point : groupPoints) {
            point.visited = false;
        }
        return new Group(points);
    }


    public  static boolean isTriangle(Group group) {
        return false;
    }

}
