package d_2021_01_10

fun main() {

    val ( studentCnt, cmpCnt ) = readLine()!!.split(" ").map(String::toInt)
    val adjList = mutableListOf<MutableList<Int>>()
    val counter = IntArray(studentCnt+1) {0}

    // [1] init adjList
    for(i in 0..studentCnt) {
        adjList.add(mutableListOf())
    }

    // [2] init adj
    for (i in 1..cmpCnt) {
        val (smaller, taller) = readLine()!!.split(" ").map(String::toInt)
        adjList[smaller].add(taller)
    }

    val visited = BooleanArray(studentCnt+1)
    for(i in 1..studentCnt) {
        visited.fill(false)

        dfs(i, adjList, visited)

        for(idx in 1..studentCnt) {
            if(visited[idx]) {

                if(idx == i) {
                    counter[idx] += visited.filter { it }.size - 1
                } else {
                    counter[idx]++
                }
            }
        }
    }

    print(counter.filter { it == studentCnt - 1 }.size)
}

fun dfs(startIdx: Int, adjList: MutableList<MutableList<Int>>, visited: BooleanArray) {

    if(visited[startIdx]) {
        return
    }

    visited[startIdx] = true
    val nodeAdjList = adjList[startIdx]

    if (nodeAdjList.isEmpty()) {
        return
    }

    for(adj in nodeAdjList) {
        dfs(adj, adjList, visited)
    }
}
