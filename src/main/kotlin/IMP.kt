enum class IMP(val lexicalToken: String) {
    STACK_MANIPULATION(" "),
    ARITHMETIC("\t "),
    HEAP_ACCESS("\t\t"),
    FLOW_CONTROL("\n"),
    IO("\t\n");

    override fun toString(): String {
        return lexicalToken
    }
}