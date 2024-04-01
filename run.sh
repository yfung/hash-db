#!/bin/bash
# No environment variables or path used here to locate the generated jar, simple root directory base Jar to run test cases
java -jar ./database.jar "$@"
