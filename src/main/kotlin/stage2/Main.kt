package stage2

fun main() {
    val taskList = TaskList()
    taskList.start()
}

class TaskList{

    private val tasks = mutableListOf<String>()
    private val commands = listOf("add","print","end")

    fun start() {
        when (getValidCommand()) {
            commands[0] -> addTask()
            commands[1] -> displayTasks()
            commands[2] -> endTask()
        }
    }

    private fun getValidCommand(): String {

        println("Input an action (add, print, end):")
        val input = readln()
        if (input.lowercase() !in commands) {
            println("The input action is invalid")
            getValidCommand()
        }

        return input.lowercase()
    }

    private fun addTask() {
        println("Input the tasks (enter a blank line to end):")

        while (true){
            val task = readln().trim()
            if (task.isEmpty()) getValidCommand()
            else tasks.add(task)
        }
    }

    private fun displayTasks() {
        if (tasks.isEmpty()) {
            println("No tasks have been input.")
            return
        }

        tasks.forEachIndexed { i, task ->
            val index = (i + 1).toString().padEnd(2, ' ')
            println("$index $task")
        }
    }

    private fun endTask() {
        println("Tasklist exiting!")
    }

}
