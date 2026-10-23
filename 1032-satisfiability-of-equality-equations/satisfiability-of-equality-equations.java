class Solution {
    int[] parent = new int[26];

    int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public boolean equationsPossible(String[] equations) {
        for (int i = 0; i < 26; i++)
            parent[i] = i;

        for (String s : equations)
            if (s.charAt(1) == '=')
                parent[find(s.charAt(0) - 'a')] = find(s.charAt(3) - 'a');

        for (String s : equations)
            if (s.charAt(1) == '!' && find(s.charAt(0) - 'a') == find(s.charAt(3) - 'a'))
                return false;

        return true;
    }
}