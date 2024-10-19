abstract class Command(val imp: IMP, val commandValue: String) {
    override fun toString() = imp.lexicalToken+commandValue
}