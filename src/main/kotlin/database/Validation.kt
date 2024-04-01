package database

/*
 * Input validation class
 * Simple validation for the correct number of arguments
 * No input sanitization performed, as the input is expected to be strings and no genuine DSL is executed
 * Number of arguments are defined according to the TechAssessmentPrompt_2024.pdf specification
 */
class Validation {
    companion object {
        fun validate(input: List<String>) {
            return when (input.first()) {
                "BEGIN" -> {
                    handleValidationError(input = input, targetSize = 1, operation = "BEGIN")
                }
                "ROLLBACK" -> {
                    handleValidationError(input = input, targetSize = 1, operation = "ROLLBACK")
                }
                "COMMIT" -> {
                    handleValidationError(input = input, targetSize = 1, operation = "COMMIT")
                }
                "GET" -> {
                    handleValidationError(input = input, targetSize = 2, operation = "GET")
                }
                "SET" -> {
                    handleValidationError(input = input, targetSize = 3, operation = "SET")
                }
                "DELETE" -> {
                    handleValidationError(input = input, targetSize = 2, operation = "DELETE")
                }
                "COUNT" -> {
                    handleValidationError(input = input, targetSize = 2, operation = "COUNT")
                }
                // If end was given, regardless of the arguments, simply don't validate, execute, and exit
                "END" -> {
                    return
                }
                else -> { println("Invalid database operation provided ${input.first()}") }
            }
        }

        private fun handleValidationError(input: List<String>, targetSize: Int, operation: String) {
            if (input.size != targetSize) {
                throw RuntimeException("Invalid number of arguments provided for $operation.")
            }
        }
    }
}