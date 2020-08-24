import java.util.*;
import java.io.*;
 
class Node implements Comparable<Node> {
    public int id;
    public int dist;
 
    public Node(int id, int dist) {
        this.id = id;
        this.dist = dist;
    }
 
    @Override
    public int compareTo(Node other) {
        return this.dist - other.dist;
    }
}
class Main {
    public static ArrayList<Node>[] graph;
    public static final int INF = (int)1e9;
    public static void main(String[] args) throws Exception {
        FastInput in = new FastInput(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out), true);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();
        int[] availableCity = new int[k];
        for (int i = 0; i < k; i++) {
            availableCity[i] = in.nextInt();
        }
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int d = in.nextInt();
            graph[u].add(new Node(v, d));
            graph[v].add(new Node(u, d));
        }
        int A = in.nextInt();
        int B = in.nextInt();
        int[] distA = Dijkstra(A);
        int[] distB = Dijkstra(B);
        int res = INF;
        for (int i = 0; i < k; i++) {
            int c = availableCity[i];
            if (distB[c] <= x && distA[c] + distB[c] < res)
                res = distA[c] + distB[c];
        }
        if (res >= INF)
            res = -1;
        out.println(res);
        out.close();
    }
 
    public static int[] Dijkstra(int s) {
        int[] dist = new int[graph.length];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[s] = 0;
        pq.add(new Node(s, 0));
        while (!pq.isEmpty()) {
            Node top = pq.remove();
            int u = top.id;
            int w = top.dist;
            if (w != dist[u])
                continue;
            for (int i = 0; i < graph[u].size(); i++) {
                int id = graph[u].get(i).id;
                int distance = graph[u].get(i).dist;
                if(dist[id] > w + distance){
                    dist[id] = w + distance;
                    pq.add(new Node(id, dist[id]));
                }
            }
        }
        return dist;
    }
 
}
class FastInput
{
    final private int BUFFER_SIZE = 1 << 16;
 
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;
 
    public FastInput(InputStream in)
    {
        din = new DataInputStream(in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }
 
    public long nextLong() throws Exception
    {
        long ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = c == '-';
        if (neg) c = read();
        do
        {
         ret = ret * 10 + c - '0';
         c = read();
        } while (c > ' ');
        if (neg) return -ret;
        return ret;
    }
 
    //reads in the next string
    public String next() throws Exception
    {
        StringBuilder ret =  new StringBuilder();
        byte c = read();
        while (c <= ' ') c = read();
        do
        {
            ret = ret.append((char)c);
            c = read();
        } while (c > ' ');
        return ret.toString();
    }
 
    public int nextInt() throws Exception
    {
        int ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = c == '-';
        if (neg) c = read();
        do
        {
            ret = ret * 10 + c - '0';
            c = read();
        } while (c > ' ');
        if (neg) return -ret;
        return ret;
    }
 
    private void fillBuffer() throws Exception
    {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1) buffer[0] = -1;
    }
 
    private byte read() throws Exception
    {
        if (bufferPointer == bytesRead) fillBuffer();
        return buffer[bufferPointer++];
    }
}
