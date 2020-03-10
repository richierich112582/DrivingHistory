# Welcome!

This project is the DrivingHistory application.  I've chosen to use the Scala programming language for the following reasons:
- I've been learning Scala over the past few weeks
- Although I'm more fluent in JS or C#, I wanted to try something new
- Completing this exercise shows that with SOLID principles and a good foundation of OOP/Functional, engineers should be able to learn new languages in a timely manner

## Requirements

You need the following tools:
  - Java SDK. The baseline version is 8 for 2.13.x.
  - sbt (sbt 1 on the 2.13.x branch)

MacOS and Linux work. Windows may work if you use Cygwin.

## Run Tests and/or Program

- Make sure you `cd` your way into the project folder where the build.sbt file is located.
- `sbt test` to run the testsuite
- `sbt "run PATH_TO_INPUT_FILE"` to run the program with all logs
    - Example: `sbt --error "run src/resources/input.txt"` to run with only error logs (less messy)

Feel free to edit the existing input.txt file located in `src/resources/input.txt` or supply your own.
## My Approach

My thought process started with getting a bare minimum version of the application working without edge cases.
I started with a single Object and started writing code in the `main` method.  It became clear that all my code in a single method isn't ideal and hard to test.  So I started writing tests
in a BDD fashion to guide my design.  I ended up coming with 3 classes that handle their own single responsibility and ability to be extended (Single Responsibility, Open/Close in SOLID).
- Driver class to hold information particular to a single Driver with some private methods that calculate avg speed and miles, but no need to expose them. It's public methods/properties are:
    - `name`
    - `trips`
    - `addTrip`
    - `avgTotalSpeed`
    - `totalMiles`

- Trip class to hold information particular to a trip.  It's public methods/properties are:
    - `startTime`
    - `stopTime`
    - `miles`
    
- DrivingHistoryReportGenerator class to hold all the Drivers and to generate the report.  It's public methods/properties are:
    - `input` (source file with list of commands)
    - `minSpeed` (min speed needed in order for a trip to be added)
    - `maxSpeed` (max speed needed in order for a trip to be added)

- DrivingHistory object that is needed to call into the `main` function with the filename as one of its arguments
    - `main` (creates a new DrivingHistoryReportGenerator and prints out the report)
