package com.jianastrero.chronicle.exceptions

import java.lang.RuntimeException

class UnknownSeverityException(severity: Int) : RuntimeException("The value $severity is not a valid severity level")