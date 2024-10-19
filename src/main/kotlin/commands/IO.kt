package commands

import Command

class PrintChar: Command(IMP.IO, "  ")
class PrintNumber: Command(IMP.IO," \t")
class ReadChar: Command(IMP.IO, "\t ")
class ReadNumber: Command(IMP.IO,"\t\t")

