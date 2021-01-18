package d_2021_01_12

import java.util.*


const val INFINITE = 10000
const val MAX = 125

val dist : Array<Array<Int>> = Array(MAX) { Array(MAX) { INFINITE } }
val map  : Array<Array<Int>> = Array(MAX) { Array(MAX) { 0 } }

val dx: Array<Int> = arrayOf(0,  0, 1, -1)
val dy: Array<Int> = arrayOf(1, -1, 0,  0)

fun solution(map: Array<Array<Int>>, N: Int): Int {
    val cmp: Comparator<Pair<Int, Pair<Int, Int>>> = compareBy { it.first }

    val q: Queue<Pair<Int, Pair<Int, Int>>> = PriorityQueue(cmp)
    q.offer(Pair(map[0][0], Pair(0, 0)))
    dist[0][0] = map[0][0] // 시작

    while(!q.isEmpty()) {
        val cost = q.peek().first

        val x = q.peek().second.first
        val y = q.peek().second.second

        q.poll()

        for (i in 0 until 4) {

            val nx: Int = x + dx[i]
            val ny: Int = y + dy[i]

            if(isInBoundary(nx, ny, N)) {
                val nCost = cost + map[nx][ny]

                if(dist[nx][ny] > nCost) {
                    dist[nx][ny] = nCost
                    q.offer(Pair(dist[nx][ny], Pair(nx, ny)))
                }
            }
        }
    }

    return dist[N-1][N-1]
}

fun isInBoundary(nx: Int, ny: Int, N: Int): Boolean {
    return nx >= 0 && ny >= 0 && nx < N && ny < N
}

fun main() {

    var idx = 1
    while (true) {
        val cnt = readLine()!!.toInt()
        if(cnt == 0) break

        initMap(map, cnt)

        val result: Int = solution(map, cnt)
        println("Problem $idx: $result")
        idx++

        dist.forEach { it.fill(INFINITE) }
        map.forEach { it.fill(0) }
    }
}

fun initMap(map: Array<Array<Int>>, cnt: Int) {
    for (row in 0 until cnt) {
        val rowArr: IntArray = readLine()!!.split(" ").map(String::toInt).toIntArray()

        for (col in rowArr.indices) {
            map[row][col] = rowArr[col]
        }
    }
}
