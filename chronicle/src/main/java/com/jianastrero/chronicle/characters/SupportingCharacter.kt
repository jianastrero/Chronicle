/*
 * MIT License
 *
 * Copyright (c) 2019 Jian James Astrero
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.jianastrero.chronicle.characters

import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

abstract class SupportingCharacter(private val character: Any) {
    abstract fun getName(): String

    private val traits: MutableList<Trait> = mutableListOf()

    override fun toString(): String {
        updateTraits()
        var str = " \n\n-${getName()}---"

        traits.forEach {
            str += "\n$it"
        }

        str += "\n "

        return str
    }

    private fun updateTraits() {
        val properties = character::class.declaredMemberProperties as Collection<KProperty1<Any, Any?>>
        properties.forEach {
            val accessible = it.isAccessible
            it.isAccessible = true

            traits.add(Trait(it.returnType.toString(), it.name, "${it.get(character)}"))

            it.isAccessible = accessible
        }
    }

    private fun getMaxLineCharacters(): Int {
        var max = getName().length

        traits.forEach {
            if (it.toString().length > max)
                max = it.toString().length
        }

        return max
    }

    class Trait(val type: String, val name: String, val value: String) {
        override fun toString(): String {
            return "$name($value): $type"
        }
    }
}