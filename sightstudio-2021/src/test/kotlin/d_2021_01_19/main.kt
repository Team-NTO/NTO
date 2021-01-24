package d_2021_01_19

fun main() {

    val commandCnt  = readLine()!!.toInt()
    val commandList = mutableListOf<Command>()

    for (i in 0 until commandCnt) {
        val (type, target, time) = readLine()!!.split(" ")

        commandList.add (
            Command(
                type   = type,
                target = target,
                time   = time.toInt()
            )
        )
    }

    val result = solution(commandList)
    print(result)
}

fun solution(commands: List<Command>): String {

    var curTime = 0
    val history = mutableListOf<Command>()

    for (command in commands) {
        curTime += (command.time - curTime)

        if(command.type == "type") {
            val preHistory = if(history.isEmpty()) "" else history.last().content

            history.add(Command(command, preHistory + command.target))
        }

        if(command.type == "undo") {
            history.add(getRollBackHistory(history, command, curTime))
        }
    }

    return history.last().content ?: ""
}

fun getRollBackHistory(historyList: MutableList<Command>, command: Command, curTime: Int): Command {
    val undoTime  = command.target.toInt()
    val timeUntil = curTime - undoTime

    for (idx in historyList.indices.reversed()) {
        val history = historyList[idx]

        if(history.time < timeUntil) {
            return Command(history, command)
        }
    }

    return Command(
        type    = command.type,
        target  = undoTime.toString(),
        time    = command.time,
        content = ""
    )
}

data class Command(
    val type   : String,
    val target : String,
    val time   : Int,
    val content: String? = ""
) {

    constructor(command: Command, content: String)
    :this(
        type    = command.type,
        target  = command.target,
        time    = command.time,
        content = content
    )

    constructor(history: Command, command: Command)
            :this(
        type    = command.type,
        target  = command.target,
        time    = command.time,
        content = history.content
    )
}


