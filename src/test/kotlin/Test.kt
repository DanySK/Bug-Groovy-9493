import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import javax.script.Bindings
import javax.script.ScriptEngineManager

class Test : StringSpec({
    "Reproduce Groovy bug 9493" {
        System.getProperty("java.vm.name") shouldContain "Eclipse OpenJ9 VM"
        val engine = ScriptEngineManager().getEngineByName("groovy")
        engine shouldNotBe  null
        engine.eval("5 * foo", mapOf("foo" to 4, "fiz" to 0).asBindings()) shouldBe 20
    }
})

fun Map<String, Any>.asBindings(): Bindings = object : Bindings, MutableMap<String, Any> by this.toMutableMap() { }