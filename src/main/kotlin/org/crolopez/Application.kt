package org.crolopez

import io.micronaut.runtime.Micronaut

object Application {

	@JvmStatic
	fun main(args: Array<String>) {
		Micronaut.build()
			.packages("org.crolopez")
			.mainClass(Application.javaClass)
			.start()
	}
}