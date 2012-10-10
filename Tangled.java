import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Tangled
{

    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);

        while (in.hasNext())
        {
            HashMap<String, Integer> houses = new HashMap<String, Integer>();
            Edge[] e;

            double length = in.nextDouble();

            // Number of houses
            int N = in.nextInt();
            for (int i = 0; i < N; i++)
            {
                houses.put(in.next(), i);
            }

            // Number of Edges
            int M = in.nextInt();

            e = new Edge[M];

            for (int i = 0; i < M; i++)
            {
                e[i] = (new Edge(in.next(), in.next(), in.nextDouble()));
            }

            DisjointSet set = new DisjointSet(N);
            double MSTLength = 0;
            Arrays.sort(e);

            for (int i = 0; i < M; i++)
            {

                if (set.completeUnion())
                {
                    break;
                }

                Edge edge = e[i];

                int toSet = set.findSet(houses.get(edge.to));
                int fromSet = set.findSet(houses.get(edge.from));

                if (toSet != fromSet)
                {
                    set.union(toSet, fromSet);
                    MSTLength += edge.weight;
                }

            }

            if (MSTLength > length)
            {
                System.out.println("Not enough cable");
            }
            else
            {
                System.out.format("Need %.1f miles of cable\n", MSTLength);
            }
        }
    }


    static class DisjointSet
    {

        int   N;
        int[] sets;
        int   numUnique;


        public DisjointSet(int N)
        {
            this.N = N;
            sets = new int[N];
            numUnique = N;
            for (int i = 0; i < N; i++)
            {
                sets[i] = i;
            }
        }


        public boolean completeUnion()
        {
            return numUnique == 1;
        }


        public void print()
        {
            for (int i = 0; i < N; i++)
            {
                System.out.print(sets[i] + " ");
            }
            System.out.println();
        }


        public void union(int set1, int set2)
        {
            numUnique--;

            for (int i = 0; i < N; i++)
            {
                if (sets[i] == set1)
                {
                    sets[i] = set2;
                }
            }
        }


        public int findSet(int set)
        {
            return sets[set];
        }

    }


    static class Edge
        implements Comparable
    {
        String to;
        String from;
        double weight;


        public Edge(String t, String f, double w)
        {
            to = t;
            from = f;
            weight = w;
        }


        @Override
        public int compareTo(Object o)
        {
            Edge that = (Edge)o;

            if (weight > that.weight)
            {
                return 1;
            }
            else if (weight < that.weight)
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }

    }

}
