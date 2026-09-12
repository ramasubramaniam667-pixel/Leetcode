class BIT {
    int n;
    int[] tree;

    BIT(int n) {
        this.n = n;
        tree = new int[n + 1];
    }

    void add(int i, int v) {
        for (; i <= n; i += i & -i) tree[i] += v;
    }

    int query(int i) {
        int r = 0;
        for (; i > 0; i -= i & -i) r += tree[i];
        return r;
    }
}

class Solution {
    private int upperBound(long[] a, int len, long t) {
        int l = 0, r = len;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] <= t) l = m + 1;
            else r = m;
        }
        return l;
    }

    private int lowerBound(long[] a, int len, long t) {
        int l = 0, r = len;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] < t) l = m + 1;
            else r = m;
        }
        return l;
    }

    public long distantSubarrays(int[] A, int goal, int k) {
        long n = A.length;
        if (k == 0) return n * (n + 1) / 2;

        long[] p = new long[(int)n + 1];
        for (int i = 0; i < n; i++) {
            p[i + 1] = p[i] + A[i];
        }

        long[] s = p.clone();
        Arrays.sort(s);
        int m = 0;
        for (int i = 0; i < s.length; i++) {
            if (i == 0 || s[i] != s[i - 1]) {
                s[m++] = s[i];
            }
        }

        BIT bit = new BIT(m);
        long res = 0;
        int tot = 0;

        for (long v : p) {
            int idx1 = upperBound(s, m, v - goal - k);
            res += bit.query(idx1);

            int idx2 = lowerBound(s, m, v - goal + k);
            res += tot - bit.query(idx2);

            int idx = lowerBound(s, m, v) + 1;
            bit.add(idx, 1);
            tot++;
        }

        return res;
    }
}