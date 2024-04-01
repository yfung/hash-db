import database.Database
import database.Validation

fun main() {
    println("Starting database...")
    val db = Database()
    var run = true
    while (run) {
        val args = readLine().toString().split(' ')
        try {
            Validation.validate(input = args)
            run = db.execute(args = args)
        } catch (e: Exception) {
            // Exceptions are frowned upon in Kotlin, and using exceptions is not ideal for control flow, but cleaner than
            // returning booleans from validation and rechecking the Boolean
            // Traditional client calls with invalid syntax should return exceptions to the client,
            // but no specific behavior is specified so the program can continue in this case if there are typos
            println("Invalid database operation received, skipping.")
        }
    }
}