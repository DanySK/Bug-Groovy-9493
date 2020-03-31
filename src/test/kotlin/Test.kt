import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import javax.script.Bindings
import javax.script.ScriptEngineManager

class Test : StringSpec({
    "Reproduce Groovy bug 9493" {
        if(System.getProperty("java.vm.name").contains("Eclipse OpenJ9 VM") {
            println("The test is expected to fail with NPE under J9")
        } else {
            println("The test is expected to complete under HotSpot")
        }
        val engine = ScriptEngineManager().getEngineByName("groovy")
        engine shouldNotBe  null
        engine.eval("5 * foo", mapOf("foo" to 4, "fiz" to 0).asBindings()) shouldBe 20
    }
})

fun Map<String, Any>.asBindings(): Bindings = object : Bindings, MutableMap<String, Any> by this.toMutableMap() { }
