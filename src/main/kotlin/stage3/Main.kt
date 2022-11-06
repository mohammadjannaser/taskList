package stage3


import java.time.LocalDate
import java.time.LocalTime

enum class Priority(val string: String) {
    C("C"), H("H"), N("N"), L("L")
}

class Task(private val priority: Priority, private val date:String, private val time: String,
           private val lines: List<String>) {
    val isBlank get() = lines.isEmpty()

    fun print(number: Int) {
        val n = number.toString().padEnd(3)
        println("$n$date $time $priority")

        lines.forEach {
            println("   $it") // 3 blanks in front
        }
    }
}

fun main() {
    val tasks = mutableListOf<Task>()

    while (true) {
        println("Input an action (add, print, end):")

        when (readLine()!!.trim()) {
            "end" -> {
                println("Tasklist exiting!")
                return
            }
            "add" -> add(tasks)
            "print" -> print(tasks)
            else -> println("The input action is invalid")
        }
    }
}

fun add(tasks: MutableList<Task>) {
    val newTask = getNewTask()
    if (newTask.isBlank) {
        println("The task is blank")
    } else {
        tasks.add(newTask)
    }
}

fun getNewTask(): Task {
    val priority = getValidPriority()
    val date = getValidDate()
    val time = getValidTime()
    val lines = getTaskLines()

    return Task(priority, date, time, lines)
}

fun getValidPriority():Priority {
    while (true) {
        val validPrios = Priority.values().map { it.string }
        println("Input the task priority (${validPrios.joinToString(", ")}):")
        val input = readln().uppercase()
        if (input in validPrios) {
            return Priority.valueOf(input)
        }
    }
}

fun getValidDate(): String {
    while (true) {
        println("Input the date (yyyy-mm-dd):")
        try {
            val parts = readln().split('-').map { it.toInt() }
            return LocalDate.of(parts[0], parts[1], parts[2]).toString()
        } catch (ex: Exception) {
            println("The input date is invalid")
        }
    }
}

fun getValidTime(): String {
    while (true) {
        println("Input the time (hh:mm):")
        try {
            val parts = readln().split(':').map { it.toInt() }
            return LocalTime.of(parts[0], parts[1]).toString()
        } catch (ex: Exception) {
            println("The input time is invalid")
        }
    }
}

fun getTaskLines(): List<String> {
    println("Input a new task (enter a blank line to end):")
    val lines = mutableListOf<String>()
    while (true) {
        val input = readln().trim()
        if (input.isEmpty()) {
            return lines
        } else {
            lines.add(input)
        }
    }
}

fun print(tasks: List<Task>) {
    if (tasks.isEmpty()) {
        println("No tasks have been input")
        return
    }

    tasks.forEachIndexed { i, task ->
        task.print(i + 1)
        println()
    }
}