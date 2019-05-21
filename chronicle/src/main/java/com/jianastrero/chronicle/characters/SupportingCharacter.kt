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

import kotlin.math.max
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

abstract class SupportingCharacter(private val character: Any) {
    abstract fun getName(): String

    private val traits: MutableList<Trait> = mutableListOf()

    override fun toString(): String {
        updateTraits()

        var cmcOne = getCellOneMaxCharacters()
        var cmcTwo = getCellTwoMaxCharacters()
        var cmcThree = getCellThreeMaxCharacters()

        var str = "-${getName()}"

        val missingDashesLength = cmcOne + cmcTwo + cmcThree + 2 - str.length
        for (i in 1..max(missingDashesLength, 5))
            str += "-"

        val topLength = str.length

        if (topLength > cmcOne + cmcTwo + cmcThree + 2) {
            val additional = (topLength - cmcOne - cmcTwo - cmcThree - 2) / 3
            val additionalThree = topLength - cmcOne - cmcTwo - cmcThree - 2 - additional * 2
            cmcOne += additional
            cmcTwo += additional
            cmcThree += additionalThree
        }

        str = " \n\n|$str|"

        traits.add(0, Trait("TYPE", "NAME", "VALUE"))

        traits.forEachIndexed { index, item ->
            str += "\n|${item.toRow(cmcOne, cmcTwo, cmcThree)}|"
            if (index == 0) {
                str += "\n|"

                for (i in 1..topLength)
                    str += "-"

                str += "|"
            }
        }

        str += "\n|"

        for (i in 1..topLength)
            str += "-"

        str += "|\n "

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

    private fun getCellOneMaxCharacters(): Int {
        var max = "NAME".length

        traits.forEach {
            if (it.name.length > max)
                max = it.name.length
        }

        return max + 2
    }

    private fun getCellTwoMaxCharacters(): Int {
        var max = "VALUE".length

        traits.forEach {
            if (it.value.length > max)
                max = it.value.length
        }

        return max + 2
    }

    private fun getCellThreeMaxCharacters(): Int {
        var max = "CLASS / TYPE".length

        traits.forEach {
            if (it.type.length > max)
                max = it.type.length
        }

        return max + 2
    }

    class Trait(val type: String, val name: String, val value: String) {
        override fun toString(): String {
            return "$name($value): $type"
        }

        fun toRow(
            cellOneCharCount: Int,
            cellTwoCharCount: Int,
            cellThreeCharCount: Int
        ): String {
            return addSpacesToCenter(name, cellOneCharCount) + "|" +
                    addSpacesToCenter(value, cellTwoCharCount) + "|" +
                    addSpacesToCenter(type, cellThreeCharCount)
        }

        private fun addSpacesToCenter(string: String, length: Int): String {
            val remainingLength = length - string.length
            val leftSpaces = remainingLength / 2
            val rightSpaces = remainingLength - leftSpaces

            var str = string

            for (i in 1..leftSpaces)
                str = " $str"

            for (i in 1..rightSpaces)
                str += " "

            return str
        }
    }
}