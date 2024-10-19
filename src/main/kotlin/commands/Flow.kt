package commands

import Command
import IMP

abstract class CommandWithLabel( command: String, val label: String): Command(IMP.FLOW_CONTROL, command){
    override fun toString() = super.toString()+label+"\n"
}

class Label(labelValue:String) : CommandWithLabel("  ", labelValue)

class CallSubroutine(labelValue:String) : CommandWithLabel(" \t", labelValue)

class JumpToLabel(labelValue:String) : CommandWithLabel(" \n", labelValue)

class JumpToLabelIfTopOfStackIsZero(labelValue:String) : CommandWithLabel("\t ", labelValue)

class JumpToLabelIfTopOfStackIsNegative(labelValue:String) : CommandWithLabel("\t\t", labelValue)

class EndSubroutine  : Command(IMP.FLOW_CONTROL,"\t\n ")

class End : Command(IMP.FLOW_CONTROL,"\n\n")