
import java.util.*;

class Solution {

    int[] parent;
    int[] size;

    // STEP 1: Initialize Union-Find
    void init(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // STEP 2: Find root
    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // STEP 3: Merge two accounts
    void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return;
        }

        if (size[rootA] < size[rootB]) {
            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }

        parent[rootB] = rootA;
        size[rootA] += size[rootB];
    }

    // STEP 4: Main problem logic
    public List<List<String>> accountsMerge(
        List<List<String>> accounts) {

        int n = accounts.size();

        init(n);

        // Email -> Account index
        Map<String, Integer> emailOwner = new HashMap<>();

        // Process all accounts
        for (int i = 0; i < n; i++) {

            List<String> account = accounts.get(i);

            for (int j = 1; j < account.size(); j++) {

                String email = account.get(j);

                if (emailOwner.containsKey(email)) {

                    int previous = emailOwner.get(email);

                    union(i, previous);

                } else {

                    emailOwner.put(email, i);
                }
            }
        }

        // Root account -> Emails
        Map<Integer, List<String>> groups = new HashMap<>();

        for (String email : emailOwner.keySet()) {

            int root = find(emailOwner.get(email));

            groups.putIfAbsent(root, new ArrayList<>());

            groups.get(root).add(email);
        }

        // Prepare final answer
        List<List<String>> result = new ArrayList<>();

        for (int root : groups.keySet()) {

            List<String> emails = groups.get(root);

            Collections.sort(emails);

            List<String> merged = new ArrayList<>();

            // Add account name
            merged.add(accounts.get(root).get(0));

            // Add sorted emails
            merged.addAll(emails);

            result.add(merged);
        }

        return result;
    }
}
