[![Build Status](https://travis-ci.org/tjaranowski/Data-Structures.svg?branch=master)](https://travis-ci.org/tjaranowski/Data-Structures)
[![Maintainability](https://api.codeclimate.com/v1/badges/73900118e3755e712416/maintainability)](https://codeclimate.com/github/tjaranowski/Data-Structures/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/73900118e3755e712416/test_coverage)](https://codeclimate.com/github/tjaranowski/Data-Structures/test_coverage)

# Data-Structures
Data structures implemented in Java

# Data Structures currently in repository

- Arrays
  - Vector
- Lists
  - ArrayList
  - DoubleLinkedList
  - LinkedList
- Queues
  - LIFOLinkedListStackQueue
  - LIFOFixedSizeStackQueue
- Sets
  - ArraySet
- Stacks
  - LinkedListStack
  - FixedSizeStack
- Trees
  - BinaryTree
- CircularBuffer

# Build instructions
This project uses [Gradle](https://gradle.org/) build system and utilizes [gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html). That means that to build this project You need to invoke gradle wrapper with parameter build like `gradlew build`.

This project also contains unit tests and utilizes mutating testing library called [PITest](http://pitest.org/). To run tests invoke gradle wrapper with parameter `test` like `gradlew test` for unit tests only or with parameter `pitest` for mutation testing like `gradlew pitest`.

To generate JaCoCo test report invoke gradle with parameter `jTR` (abbreviation) or `jacocoTestReport` like `gradlew jTR` or `gradlew jacocoTestReport`.

License
-------

    MIT License

    Copyright (c) 2018 tjaranowski

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
