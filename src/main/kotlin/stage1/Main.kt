package stage1

fun main() {
    val taskList = TaskList()
    taskList.getTasks()
    taskList.printAll()
}


class TaskList{

    private val tasks = mutableListOf<String>()

    fun getTasks() {
        println("Input the tasks (enter a blank line to end):")

        while (true){
            val task = readln().trim()
            if (task.isEmpty()) break
            tasks.add(task)
        }
    }

    fun printAll() {
        if (tasks.isEmpty()) {
            println("No tasks have been input.")
            return
        }

        tasks.forEachIndexed { i, task ->
            val index = (i + 1).toString().padEnd(2, ' ')
            println("$index $task")
        }
    }

}
