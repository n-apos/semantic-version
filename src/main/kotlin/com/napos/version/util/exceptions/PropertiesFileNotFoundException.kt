package com.napos.version.util.exceptions

import com.napos.version.tasks.InitializeVersionTask

class PropertiesFileNotFoundException(path: String) :
    PropertiesFileException(
        message = "Internal error occurred, the specified property file was not found in the following location : '$path'\n" +
                "Consider running ${InitializeVersionTask::getName} task first",
    )