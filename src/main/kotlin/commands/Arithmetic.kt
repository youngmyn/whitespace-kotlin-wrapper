package commands

import Command

class Addition: Command(IMP.ARITHMETIC, "  ")
class Subtraction: Command(IMP.ARITHMETIC, " \t")
class Multiplication: Command(IMP.ARITHMETIC, " \n")
class IntegerDivision: Command(IMP.ARITHMETIC, "\t ")
class Modulo: Command(IMP.ARITHMETIC, "\t\t")