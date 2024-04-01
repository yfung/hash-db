package database

class Database {
    private var databaseCache: MutableList<HashMap<String, String>> = mutableListOf(HashMap())
    private var valueCountBuffer: MutableList<HashMap<String, Int>> = mutableListOf(HashMap())

    /*
     * Main function - handles executing of validate user input commands
     * For validation requirements of each, @see {@link database.Validation }
     */
    fun execute(args: List<String>): Boolean {
        return when (args.first()) {
            "BEGIN" -> { beginTransaction(); true }
            "ROLLBACK" -> { rollbackTransaction(); true }
            "COMMIT" -> { commitToDisk(); true }
            "GET" -> { executeGet(args[1]); true }
            "SET" -> { executeSet(args[1], args[2]); true }
            "DELETE" -> { executeDelete(args[1]); true }
            "COUNT" -> { executeCount(args[1]); true }
            "END" -> { println("Exiting database..."); false }
            else -> { println("Unreachable due to validation"); true }
        }
    }

    // Execute a fetch from the database
    // Runtime: List index fetch O(1) + HashMap fetch O(1)
    private fun executeGet(name: String) {
        val result = databaseCache.last()[name] ?: "NULL"
        println(result)
    }

    // Execute write to the database
    // Runtime: List index fetch x2 2 * O(1) + x2 HashMap fetch 2 * O(1) + HashMap writes x2 2 * O(1)
    private fun executeSet(name: String, value: String) {
        // fetch to remove subsequent gets
        val currentDb = databaseCache.last()
        val currentCount = valueCountBuffer.last()
        val previous =  currentDb[name]
        if (previous == value) return

        previous?.let {
            currentCount[previous] = currentCount[previous]!!.minus(1)
        }
        currentDb[name] = value
        currentCount[value] = currentCount.getOrDefault(value, 0).plus(1)
    }

    // Remove an entry from the database
    // Runtime: List index fetch x2 2 * O(1) + HashMap fetch x2 O(1) + HashMap remove O(1) + HashMap set O(1)
    private fun executeDelete(name: String) {
        val currentDb = databaseCache.last()
        val currentCount = valueCountBuffer.last()
        val value = currentDb[name]
        value?.let {
            currentDb.remove(name)
            currentCount[it] = currentCount[it]!!.minus(1)
        }
    }

    // Fetch the count for a given value in the database
    // Runtime: List index fetch O(1) + HashMap fetch O(1)
    private fun executeCount(value: String) {
        println(valueCountBuffer.last()[value] ?: 0)
    }

    private fun beginTransaction() {
        databaseCache.add(HashMap(databaseCache.last()))
        valueCountBuffer.add(HashMap(valueCountBuffer.last()))
    }

    private fun rollbackTransaction() {
        if (databaseCache.size > 1) {
            databaseCache.removeLast()
            valueCountBuffer.removeLast()
        } else {
            println("TRANSACTION NOT FOUND")
        }
    }

    private fun commitToDisk() {
        if (databaseCache.size > 1) {
            databaseCache = mutableListOf(databaseCache.last())
            valueCountBuffer = mutableListOf(valueCountBuffer.last())
        } else {
            println("No open transactions to commit")
        }
    }

}