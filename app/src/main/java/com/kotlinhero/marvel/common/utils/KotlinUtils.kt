package com.kotlinhero.marvel.common.utils

/**
 * Evaluates a list of Results and returns a Result containing a list of their values.
 *
 * This function takes a list of Result instances and evaluates each one. If any of the
 * results is a failure, the function immediately returns a failure Result with the exception
 * from the first failing result encountered. If all results are successful, it returns a
 * success Result containing a list of the values extracted from each Result.
 *
 * @return A Result containing either a list of values if all results are successful, or a failure
 *         containing the exception from the first failing result encountered.
 */
fun<T> List<Result<T>>.evaluate(): Result<List<T?>> {
    // Iterate over each Result in the list
    this.forEach {
        // If a Result is a failure, return a failure Result with the exception
        if (it.isFailure) {
            return Result.failure(it.exceptionOrNull() ?: Exception())
        }
    }
    // If all Results are successful, map each Result to its value
    val results = this.map { it.getOrNull() }
    // Return a success Result containing the list of values
    return Result.success(results)
}