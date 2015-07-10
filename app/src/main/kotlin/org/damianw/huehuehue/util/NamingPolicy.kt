package org.damianw.huehuehue.util

import kotlin.text.Regex

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/9/15
 * (C) 2015 Damian Wieczorek
 */

/**
 * Converts the field name that uses camel-case define word separation into
 * separate words that are separated by the provided `separatorString`.
 */
fun separateCamelCase(name: String, separator: String): String {
  val translation = StringBuilder()
  for (i in 0..name.length() - 1) {
    val character = name.charAt(i)
    if (Character.isUpperCase(character) && translation.length() != 0) {
      translation.append(separator)
    }
    translation.append(character)
  }
  return translation.toString()
}

/**
 * Ensures the JSON field names begins with an upper case letter.
 */
fun upperCaseFirstLetter(name: String): String {
  val fieldNameBuilder = StringBuilder()
  var index = 0
  var firstCharacter = name.charAt(index)

  while (index < name.length() - 1) {
    if (Character.isLetter(firstCharacter)) {
      break
    }

    fieldNameBuilder.append(firstCharacter)
    firstCharacter = name.charAt(++index)
  }

  if (index == name.length()) {
    return fieldNameBuilder.toString()
  }

  if (!Character.isUpperCase(firstCharacter)) {
    val modifiedTarget = modifyString(Character.toUpperCase(firstCharacter), name, ++index)
    return fieldNameBuilder.append(modifiedTarget).toString()
  } else {
    return name
  }
}

fun modifyString(firstCharacter: Char, srcString: String, indexOfSubstring: Int): String {
  return if ((indexOfSubstring < srcString.length()))
    firstCharacter + srcString.substring(indexOfSubstring)
  else
    firstCharacter.toString()
}