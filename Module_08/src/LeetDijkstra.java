import java.util.*;

public class LeetDijkstra {
//https://leetcode.com/problems/network-delay-time/solutions/2310813/dijkstra-s-algorithm-template-list-of-problems/
        public static int networkDelayTime(int[][] times, int n, int k) {

            //Step 1
            Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

            for(int[] time : times) {
                int start = time[0];
                int end = time[1];
                int weight = time[2];

                map.putIfAbsent(start, new HashMap<>());
                map.get(start).put(end, weight);
            }

            // Step 2
            int[] dis = new int[n+1];
            Arrays.fill(dis, Integer.MAX_VALUE);
            dis[k] = 0;

            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{k,0});

            //Step 3:
            while(!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curNode = cur[0];
                int curWeight = cur[1];

                for(int next : map.getOrDefault(curNode, new HashMap<>()).keySet()) {
                    int nextweight = map.get(curNode).get(next);

                    if(curWeight + nextweight < dis[next]) {
                        dis[next] = curWeight + nextweight;
                        queue.add(new int[]{next, curWeight + nextweight});
                    }
                }
            }

            //Step 4:
            int res = 0;
            for(int i=1; i<=n; i++) {
                if(dis[i] > res) {
                    res = Math.max(res, dis[i]);
                }
            }

            return res == Integer.MAX_VALUE ? -1 : res;
        }
    public static void main(String[] args) {
        int n =5 ;
        int[][] edges = {
                {2,1,1},
                {2,3,1},
                {3,4,1},
                {1,5,10}
        };
        int k = 2;

        int result = networkDelayTime(edges,n,k);
        System.out.println("Минимальное время для достижения сигналом всех: " + result);
    }

}
