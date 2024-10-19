package util

import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.ceil

fun main(){
    WhitespaceTextMasker().startMasking()
}

class WhitespaceTextMasker {
    fun startMasking(){
        println("Введите путь до файла с текстом, в котором надо замаскировать whitespace-код")
        val textPathAsStr = readln()
        val textPath = Paths.get(textPathAsStr)
        val text=StringBuilder()
        Files.lines(textPath, Charsets.UTF_8).forEach { text.append(it+"\n")}

        println("Введите путь до файла с вашим whitespace-кодом")
        val codPathAsStr = readln()
        val codePath = Paths.get(codPathAsStr)
        val code=StringBuilder()
        Files.lines(codePath, Charsets.UTF_8).forEach { code.append(it+"\n")}

        val countOfAllUnprintableCharsInText = text.count { it.isWhitespace() }
        val insertionCoefficient = code.length.toDouble().div(countOfAllUnprintableCharsInText)

        if(insertionCoefficient<=1) println(insertAllCharsByOne(text.toString(),code.toString()))
        else println(insertAllCharsByPacks(text.toString(),code.toString(), ceil(insertionCoefficient).toInt()))
    }

    private fun insertAllCharsByOne(text:String, code:String):String{
        val result = StringBuilder()
        var codeCounter = 0;
        for (i in 0..text.length){
            val c:Char = text[i]
            if(c.isWhitespace()){
                if(codeCounter>=code.length) return result.toString()
                result.append(code[codeCounter])
                codeCounter++
            }else{
                result.append(c)
            }
        }
        return result.toString()
    }

    private fun insertAllCharsByPacks(text:String, code:String, insertionCoefficient:Int):String{
        val result = StringBuilder()
        var codeCounter = 0;
        for (i in 0..text.length){
            val c:Char = text[i]
            if(c.isWhitespace()){
                for (pos in codeCounter..codeCounter+insertionCoefficient) {
                    if (pos >= code.length) return result.toString()
                    result.append(code[pos])
                    codeCounter++
                }
            }else{
                result.append(c)
            }
        }
        return result.toString()
    }
}