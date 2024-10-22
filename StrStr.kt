fun main() {
    val sol = Solution()
    val haystack = "ccaccaacdba"
    val needle = "dba"
    // haystack = "leetcode"
    // needle = "leeto"
    // println(sol.strStr(haystack, needle))
}

class Solution {
    fun hashValue(string: String, RADIX: Int, MOD: Int, m: Int): Int {
        var ans: Long = 0
        var factor: Long = 1
        for (i in m - 1 downTo 0) {
            ans = (ans + (string[i] - 'a') * factor) % MOD
            factor = (factor * RADIX) % MOD
        }
        return ans.toInt()
    }

    fun strStr(haystack: String, needle: String): Int {
        val m = needle.length
        val n = haystack.length
        if (n < m) return -1

        // CONSTANTS
        val RADIX = 26
        val MOD = 1000000033
        var MAX_WEIGHT: Long = 1
        
        for (i in 0 until m) {
            MAX_WEIGHT = (MAX_WEIGHT * RADIX) % MOD
        }

        // Compute hash of needle
        val hashNeedle = hashValue(needle, RADIX, MOD, m)
        var hashHay: Long = 0

        // Check for each m-substring of haystack, starting at index windowStart
        for (windowStart in 0..(n - m)) {
            if (windowStart == 0) {
                // Compute hash of the First Substring
                hashHay = hashValue(haystack, RADIX, MOD, m).toLong()
            } else {
                // Update Hash using Previous Hash Value in O(1)
                hashHay = (((hashHay * RADIX) % MOD) -
                        (((haystack[windowStart - 1] - 'a') * MAX_WEIGHT) % MOD) +
                        (haystack[windowStart + m - 1] - 'a') + MOD) % MOD
            }
            // If the hash matches, Check Character by Character.
            // Because of Mod, spurious hits can be there.
            if (hashNeedle == hashHay.toInt()) {
                for (i in 0 until m) {
                    if (needle[i] != haystack[i + windowStart]) {
                        break
                    }
                    if (i == m - 1) {
                        return windowStart
                    }
                }
            }
        }
        return -1
    }
}
