package top.vcare

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class VcareApplication

fun main(args: Array<String>) {
    runApplication<VcareApplication>(*args)
}