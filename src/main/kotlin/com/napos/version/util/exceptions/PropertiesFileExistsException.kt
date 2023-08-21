package com.napos.version.util.exceptions

class PropertiesFileExistsException(path: String) :
    PropertiesFileException(
        message = "Internal error occurred, " +
                "the specified property file already exists in the following location : '$path'",
    ) {
    init {
        println(path)
    }
}