import javax.script.ScriptEngineManager

fun main() {
    ScriptEngineManager().getEngineByName("groovy").eval("1")
}