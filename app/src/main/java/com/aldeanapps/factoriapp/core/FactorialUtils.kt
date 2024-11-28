package com.aldeanapps.factoriapp.core

import com.aldeanapps.factoriapp.core.Constants.Companion.FACTORIAL_MAX_DIGITS
import com.aldeanapps.factoriapp.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.BigDecimal
import java.math.BigInteger
import java.text.DecimalFormat

object FactorialUtils {

    fun calculateFactorial(input: String): Flow<Resource<BigInteger>> = flow {
        try {
            emit(Resource.Loading())

            var result = BigInteger.ONE
            val inputNumber = input.toBigInteger()
            val sequence = generateSequence(inputNumber) {
                if (it > BigInteger.ONE) it - BigInteger.ONE else null
            }

            for(i in sequence) {
                result = result.multiply(i)
            }

            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun toExponentialForm(number: BigInteger): String {
        return if (number.toString().length > FACTORIAL_MAX_DIGITS) {
            val decimalForm = BigDecimal(number)
            val formatter = DecimalFormat("0.#####E0")
            formatter.format(decimalForm)
        } else {
            number.toString()
        }
    }
}