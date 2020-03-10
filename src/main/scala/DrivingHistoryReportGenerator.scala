import scala.collection.mutable.ListBuffer

case class DrivingHistoryReportGenerator(input: List[String], minSpeed: Int, maxSpeed: Int) {

  private val _drivers = new ListBuffer[Driver]()

  if (!input.isEmpty) {
    val driverCommand = "Driver"
    val tripCommand = "Trip"
    var commands = new ListBuffer[Array[String]]()

    for (command <- input) {
      commands += command.split(" ")
    }

    commands.foreach(command => {
      val name = command(1)

      if (command.head.toUpperCase() == driverCommand.toUpperCase() || command.head.toUpperCase() == tripCommand.toUpperCase()) {
        if (_drivers.filter(d => d.name.toUpperCase() == name.toUpperCase()).isEmpty) {
          _drivers.addOne(Driver(name))
        }
      }

      if (command.head.toUpperCase() == tripCommand.toUpperCase()) {
        _drivers.foreach(driver => {
          if (driver.name.toUpperCase() == name.toUpperCase()) {
            driver.addTrip(Trip(command(2), command(3), command(4).toDouble), this.minSpeed, this.maxSpeed)
          }
        })
      }
    })
  }

  def drivers = _drivers

  def generateReport = {

    val generatedList: ListBuffer[String] = new ListBuffer[String]()
    val sortedDrivers = _drivers.sortBy(_.totalMiles)(Ordering[Int]).reverse
    sortedDrivers.foreach(driver => {

      val avgSpeed = driver.averageTotalSpeed
      val stringBuilder = new StringBuilder()

      stringBuilder.append(driver.name + ": " + driver.totalMiles.toString + " miles")

      if (!driver.trips.isEmpty) {
        stringBuilder.append(" @ " + avgSpeed.toString + " mph")
      }

      generatedList.append(stringBuilder.toString().trim)
    })

    generatedList
  }

}
