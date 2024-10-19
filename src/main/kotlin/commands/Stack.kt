package commands

import Command
import kotlin.math.absoluteValue

/**
 * Push the number onto the stack
 */
class PushNumber(val number: Int): Command(IMP.STACK_MANIPULATION, " "){
    override fun toString() = super.toString() + decimalToWhitespaceBinary(number)
}

/**
 * Duplicate the top item on the stack
 */
class Duplicate : Command(IMP.STACK_MANIPULATION,"\n ")

/**
 * Swap the top two items on the stack
 */
class Swap: Command(IMP.STACK_MANIPULATION, "\n\t")

/**
 * Discard the top item on the stack
 */
class Discard: Command(IMP.STACK_MANIPULATION,"\n\n")


fun decimalToWhitespaceBinary(number:Int): String{
    val sign = if (number >= 0) " " else "\t"
    val binaryStr = Integer.toBinaryString(number.absoluteValue)
    return sign+binaryStr.replace("0"," ").replace("1","\t")+"\n"
}