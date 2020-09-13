# Setup
In this project, we use  
* JDK 1.8
* sbt 1.3.13
* scala 2.13.3

## Install JDK 1.8
* `openjdk64-1.8.0.265`
  * `brew cask install homebrew/cask-versions/adoptopenjdk8`
* (Optional) If you have several jdk versions in your machine, it's good to manage with jenv. 
  * `brew install jenv`

## Install [sbt](https://www.scala-sbt.org/index.html)
`sbt` is scala build tool.
* Install sbt via [sdkman](https://sdkman.io/install)
  * `sdk install sbt 1.3.13`

## Install scala
* Install scala via [homebrew](https://brew.sh/index_ko)
  * `brew install scala`
  
# Getting Start
1. Copy assignment dir to this project. ex) `recfun`
1. Import project with IntelliJ IDE  
    * Set JDK version, Set Scala version
    * Right click `recfun` dir > Add Framework Support > Select scala
1. Run `sbt`

# Simple Guideline 

```sh
# open sbt shell
$ sbt

# in sbt shell
> run                               // Main.main 실행
> compile                           // compile
> clean                             // Clean all build artifacts
> test                              // Run all tests once
> ~ testOnly recfun.PascalSuite     // Run only test on file changes
> ~ testQuick                       // Run all tests on file changes
```