fun main() {
    // val haystack = "sadbutsad"
    // val needle = "sad"
    val haystack = "leetcode"
    val needle = "leeto"
    // val haystack = "hello"
    // val needle = "ll"
    println(strStr(haystack, needle))
}

fun strStr(haystack: String, needle: String): Int {
    val s = haystack.length
    val n = needle.length
    for (windowStart in 0..s - n) {
        for (i in 0 until n) {
            if (haystack[windowStart + i] != needle[i]) break
            if (i == n -1) {
                return windowStart
            }
        }
    }
    return -1
}