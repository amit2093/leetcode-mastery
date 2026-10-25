package Question_NeetCode_LeetCode.Arrays_Hashing;

/**
 * <h2>LeetCode 705: Design HashSet</h2>
 *
 * <h3>Problem</h3>
 * <p>
 * Design a HashSet without using any built-in hash table library.
 * Implement {@code add(key)}, {@code remove(key)} and {@code contains(key)}.
 * </p>
 *
 * <h3>Idea: direct-address table</h3>
 * <p>
 * The constraints say {@code 0 <= key <= 10^6}. That range is small enough
 * that every possible key can have its own slot. So we use a
 * {@code boolean[]} where the <b>key itself is the index</b>:
 * </p>
 * <ul>
 *   <li>{@code set[key] == true}  means the key is in the set</li>
 *   <li>{@code set[key] == false} means the key is not in the set</li>
 * </ul>
 * <p>
 * No hash function and no collisions: the key maps to exactly one slot.
 * </p>
 *
 * <h3>Diagram</h3>
 * <p>Example: add(1), add(3), remove(1), contains(3)</p>
 * <pre>
 *     index :   0      1      2      3      4    ...
 *     start : [ F ,    F ,    F ,    F ,    F ,  ... ]
 *
 *     add(1)      [ F ,    T ,    F ,    F ,    F ,  ... ]
 *     add(3)      [ F ,    T ,    F ,    T ,    F ,  ... ]
 *     remove(1)   [ F ,    F ,    F ,    T ,    F ,  ... ]
 *     contains(3) &rarr; set[3] = T &rarr; true
 * </pre>
 *
 * <h3>Why adding or removing twice is safe</h3>
 * <p>
 * Setting a slot to {@code true} (or {@code false}) is idempotent, so a
 * duplicate {@code add} or a {@code remove} of a missing key needs no
 * special handling.
 * </p>
 *
 * <h3>Complexity</h3>
 * <ul>
 *   <li><b>Time: O(1)</b> for {@code add}, {@code remove}, {@code contains}.</li>
 *   <li><b>Space: O(K)</b> where K is the key range (here about 10^6),
 *       regardless of how many keys are actually stored.</li>
 * </ul>
 *
 * <h3>Trade-off and the "real" HashSet</h3>
 * <p>
 * This wastes memory when the key range is huge or the set is sparse. A real
 * HashSet uses an array of buckets with {@code index = hash(key) % size}, and
 * handles collisions (two keys landing in the same bucket) with chaining
 * (linked lists) or open addressing. That uses space proportional to the
 * number of stored keys, not the key range.
 * </p>
 */
public class DesignHashSet {

    private final boolean[] set;

    public DesignHashSet() {
        set = new boolean[1000001]; // indices 0..10^6, one slot per possible key
    }

    public void add(int key) {
        set[key] = true;  // mark key as present
    }

    public void remove(int key) {
        set[key] = false; // mark key as absent
    }

    public boolean contains(int key) {
        return set[key];  // true only if the key was added and not removed
    }
}